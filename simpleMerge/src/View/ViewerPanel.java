package View;

import Controller.*;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Observer.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import Util.AttributeUtil;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements Observer {
    JPanel menuPanel;
    JButton btn_load,btn_edit,btn_save;
    JTextPane jTextPane;
    StyledDocument styledDocument;
    Controller controller;
    public ViewerPanel(Controller controller) {
        this.controller =controller;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        menuPanel = new JPanel(new FlowLayout());
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        setMenuPanel();

        add(menuPanel);
        add(jTextPane);
        setVisible(true);
    }

    private void setMenuPanel(){
        btn_load = new JButton(new ImageIcon("img/folder.png"));
        btn_load.setOpaque(false);
        btn_load.setContentAreaFilled(false);
        btn_load.setBorderPainted(false);
        btn_load.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_LOAD));

        btn_edit = new JButton(new ImageIcon("img/edit.png"));
        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.setBorderPainted(false);
        btn_edit.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_EDIT,jTextPane.getStyledDocument()));

        btn_save = new JButton(new ImageIcon("img/save.png"));
        btn_save.setOpaque(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setBorderPainted(false);
        btn_save.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_SAVE));

        menuPanel.add(btn_load);
        menuPanel.add(btn_edit);
        menuPanel.add(btn_save);
        //menuPanel.setBackground(new Color(239, 206, 197));
        menuPanel.setBackground(new Color(245, 210, 227));
        //menuPanel.setBackground(new Color(50,0,10));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 700);
    }

    private void updateContent(ArrayList<ComparableBlock> contentsBlock) {
        styledDocument = jTextPane.getStyledDocument();
        try {
            styledDocument.remove(0,styledDocument.getLength());
            for (ComparableBlock comparableBlock : contentsBlock) {
                for (ComparableString contents : comparableBlock.getContents()) {
                    if(contents.isDefaultString() || contents.isEqualString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getDefaultAttribute());
                    if(contents.isAddedString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getAddedAttribute());
                    if(contents.isEmptyString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getEmptyAttribute());
                    if(contents.isDiffString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getDiffAttribute());
                    }
            }
            jTextPane.setDocument(styledDocument);
        } catch (BadLocationException e) {
            System.out.println("Exception occur in ViewerPanel");
            //에러처리해야됨
        }

    }

    @Override
    public void updateView(UpdateEvent updateEvent) {
        switch (updateEvent.getId()){
            case UPDATE_VIEWER_CONTENT:
                updateContent((ArrayList)updateEvent.getObject());
                break;
            case UPDATE_VIEWER_CAN_EDIT:
                if((Boolean)updateEvent.getObject()){
                    btn_edit.setIcon(new ImageIcon("img/edit.png"));
                    btn_edit.setEnabled(true);
                }
                else{
                    btn_edit.setIcon(new ImageIcon("img/edit.png"));
                    btn_edit.setEnabled(false);
                }
                break;
            case UPDATE_VIEWER_IS_EDITING:
                if((Boolean)updateEvent.getObject()){
                    jTextPane.setEnabled(true);
                    btn_edit.setIcon(new ImageIcon("img/red_edit.png"));
                }else{
                    jTextPane.setEnabled(false);
                    btn_edit.setIcon(new ImageIcon("img/edit.png"));
                }
                break;
        }
    }
}
