package View;

import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Model.ViewerModel;
import Observer.Observer;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import Util.AttributeUtil;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements Observer {
    ViewerModel mainModel;
    JTextField contents;
    JTextPane jTextPane;
    StyledDocument styledDocument;

    public ViewerPanel() {
        this.setLayout(new BorderLayout());
        /*this.setBounds(0,0,500,500);*/
        contents = new JTextField("내용");
        jTextPane = new JTextPane();
        contents.setVisible(true);
        add(jTextPane, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void update(Map<Integer, Object> data) {
        Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            int key;
            switch (key = iterator.next()) {
                case DataId.UPDATE_VIEWER_CONTENT:
                    updateContent((ArrayList) data.get(key));
                    break;
                default:
                    System.out.println("unknown update call, key = " + key);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    private void updateContent(ArrayList<ComparableBlock> contentsBlock) {
        styledDocument = new DefaultStyledDocument();
        try {
            for (ComparableBlock comparableBlock : contentsBlock) {
                for (ComparableString contents : comparableBlock.getContents()) {
                    switch (contents.getState()) {
                        case ComparableString.ADDED:
                            styledDocument.insertString(styledDocument.getLength(), contents.getContentString(), AttributeUtil.getAddedAttribute());
                            break;
                        case ComparableString.DEFAULT:
                            styledDocument.insertString(styledDocument.getLength(), contents.getContentString(), AttributeUtil.getDefaultAttribute());
                            break;
                        case ComparableString.EMPTY:
                            styledDocument.insertString(styledDocument.getLength(), contents.getContentString(), AttributeUtil.getEmptyAttribute());
                            break;
                        case ComparableString.DIFF:
                            styledDocument.insertString(styledDocument.getLength(), contents.getContentString(), AttributeUtil.getDiffAttribute());
                            break;
                    }

                }
            }
            jTextPane.setDocument(styledDocument);
        } catch (BadLocationException e) {

            //에러처리해야됨
        }

    }
}
