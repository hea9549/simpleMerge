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

        setMenuPanel();

        add(menuPanel);
        add(jTextPane);
        setVisible(true);
    }

    private void setMenuPanel(){
        btn_load = new JButton(new ImageIcon("img/white_load.png"));
        btn_load.setOpaque(false);
        btn_load.setContentAreaFilled(false);
        btn_load.setBorderPainted(false);
        btn_load.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_LOAD));

        btn_edit = new JButton(new ImageIcon("img/white_edit.png"));
        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.setBorderPainted(false);
        btn_edit.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_EDIT));

        btn_save = new JButton(new ImageIcon("img/white_save.png"));
        btn_save.setOpaque(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setBorderPainted(false);
        btn_save.addActionListener(controller.getActionListener(DataId.ACTION_VIEWER_BTN_SAVE));

        menuPanel.add(btn_load);
        menuPanel.add(btn_edit);
        menuPanel.add(btn_save);
        menuPanel.setBackground(Color.darkGray);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 700);
    }

    private void updateContent(ArrayList<ComparableBlock> contentsBlock) {
        styledDocument = new DefaultStyledDocument();
        try {
            for (ComparableBlock comparableBlock : contentsBlock) {
                for (ComparableString contents : comparableBlock.getContents()) {
                    styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getDefaultAttribute());
                    if(contents.isAddedString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getAddedAttribute());
                    if(contents.isEmptyString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getEmptyAttribute());
                    if(contents.isDiffString())styledDocument.insertString(styledDocument.getLength(), contents.getContentString()+"\n", AttributeUtil.getDiffAttribute());
                    System.out.println("왼쪽꺼인데요, state = "+contents.getState()+"인데요!, "+contents.isAddedString()+contents.isEmptyString()+contents.isDiffString());
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
            case DataId.UPDATE_VIEWER_CONTENT:
                updateContent((ArrayList)updateEvent.getObject());
                break;
        }
    }
}
