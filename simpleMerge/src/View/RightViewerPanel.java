package View;

import Controller.LeftViewerController;
import Controller.RightViewerController;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Observer.Observer;
import Observer.UpdateEvent;
import Util.AttributeUtil;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class RightViewerPanel extends JPanel implements Observer {
    JPanel menuPanel;
    JButton btn_load,btn_edit,btn_save;
    JTextPane jTextPane;
    StyledDocument styledDocument;

    public RightViewerPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        menuPanel = new JPanel(new FlowLayout());
        jTextPane = new JTextPane();

        setMenuPanel();

        add(menuPanel);
        add(jTextPane);
        setVisible(true);
    }

    private void setMenuPanel(){
        btn_load = new JButton("로드");
        btn_load.addActionListener(new RightViewerController.ViewerPanelActionListener(DataId.ACTION_VIEWER_BTN_LOAD));
        btn_edit = new JButton("에딧");
        btn_edit.addActionListener(new RightViewerController.ViewerPanelActionListener(DataId.ACTION_VIEWER_BTN_EDIT));
        btn_save = new JButton("세이부");
        btn_save.addActionListener(new RightViewerController.ViewerPanelActionListener(DataId.ACTION_VIEWER_BTN_SAVE));
        menuPanel.add(btn_load);
        menuPanel.add(btn_edit);
        menuPanel.add(btn_save);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(650, 900);
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
                    System.out.println("오른쪽꺼인데요, state = "+contents.getState()+"인데요!, "+contents.isAddedString()+contents.isEmptyString()+contents.isDiffString());
                }
            }
            jTextPane.setDocument(styledDocument);
        } catch (BadLocationException e) {
            System.out.println("Exception occur in LeftViewerPanel");
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
