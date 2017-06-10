package View;

import Controller.*;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Observer.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Util.AttributeUtil;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements Observer {
    JPanel menuPanel;
    JButton btn_load = new JButton(new ImageIcon(getClass().getClassLoader().getResource("folder.png")));
    JButton btn_edit = new JButton(new ImageIcon(getClass().getClassLoader().getResource("edit.png")));
    JButton btn_save = new JButton(new ImageIcon(getClass().getClassLoader().getResource("save.png")));
    JTextPane jTextPane;
    JScrollPane scroll;
    StyledDocument styledDocument;
    Font font;
    ViewController controller;

    public ViewerPanel(ViewController controller) {
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        btn_load.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("not_folder.png")));
        btn_edit.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("not_edit.png")));
        btn_save.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("not_save.png")));
        menuPanel = new JPanel(new FlowLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600,50);
            }
        };
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        DefaultCaret caret = (DefaultCaret) jTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        font=new Font("돋움",Font.PLAIN,20);
        setJTextPaneFont(jTextPane, font);

        scroll=new JScrollPane(jTextPane){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600,650);
            }
        };
        scroll.setViewportView(jTextPane);
        setMenuPanel();
        add(menuPanel);
        add(scroll);
        setVisible(true);
    }

    private void setMenuPanel() {
        btn_load.setOpaque(false);
        btn_load.setContentAreaFilled(false);
        btn_load.setBorderPainted(false);
        btn_load.addActionListener(controller.getEventListener(DataId.ACTION_VIEWER_BTN_LOAD));

        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.setBorderPainted(false);
        btn_edit.addActionListener(controller.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT, jTextPane.getStyledDocument()));
        btn_edit.addActionListener(i->{
            StyledDocument styledDocument = jTextPane.getStyledDocument();
            styledDocument.setCharacterAttributes(0,styledDocument.getLength(),AttributeUtil.getDefaultAttribute(),false);
            jTextPane.setDocument(styledDocument);
        });

        btn_save.setOpaque(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setBorderPainted(false);
        btn_save.addActionListener(controller.getEventListener(DataId.ACTION_VIEWER_BTN_SAVE));

        menuPanel.add(btn_load);
        menuPanel.add(btn_edit);
        menuPanel.add(btn_save);
        menuPanel.setBackground(new Color(245, 210, 227));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 700);
    }

    public static void setJTextPaneFont(JTextPane jtp, Font font) {
        MutableAttributeSet attrs = jtp.getInputAttributes();

        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize() * 1);

        StyledDocument doc = jtp.getStyledDocument();

        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

    private void updateContent(ArrayList<ComparableBlock> contentsBlock) {
        styledDocument = jTextPane.getStyledDocument();

        int focusOffset = 0;
        try {
            styledDocument.remove(0, styledDocument.getLength());
            for (ComparableBlock comparableBlock : contentsBlock) {
                if (comparableBlock.isSelect()) {
                    focusOffset = styledDocument.getLength();
                    for (ComparableString contents : comparableBlock.getContents()) {
                        styledDocument.insertString(styledDocument.getLength(), contents.getContentString() + "\n", AttributeUtil.getClickAttribute());
                    }
                    continue;
                }
                for (ComparableString contents : comparableBlock.getContents()) {
                    if (contents.isDefaultString() || contents.isEqualString()) {
                        styledDocument.insertString(styledDocument.getLength(), contents.getContentString() + "\n", AttributeUtil.getDefaultAttribute());
                    }
                    if (contents.isAddedString()) {
                        styledDocument.insertString(styledDocument.getLength(), contents.getContentString() + "\n", AttributeUtil.getAddedAttribute());
                    }
                    if (contents.isEmptyString()) {
                        styledDocument.insertString(styledDocument.getLength(), contents.getContentString() + "\n", AttributeUtil.getEmptyAttribute());
                    }
                    if (contents.isDiffString()) {
                        styledDocument.insertString(styledDocument.getLength(), contents.getContentString() + "\n", AttributeUtil.getDiffAttribute());
                    }

                }
            }
            setJTextPaneFont(jTextPane,font);
            jTextPane.setDocument(styledDocument);
            jTextPane.setCaretPosition(focusOffset);
        } catch (BadLocationException e) {
            System.out.println("Exception occur in ViewerPanel");
        }

    }

    @Override
    public void updateView(UpdateEvent updateEvent) {
        switch (updateEvent.getId()) {
            case UPDATE_VIEWER_CONTENT:
                updateContent((ArrayList)updateEvent.getObject());
                break;
            case UPDATE_VIEWER_CAN_EDIT:
                if ((Boolean) updateEvent.getObject()) {
                    btn_edit.setEnabled(true);
                } else {
                    btn_edit.setEnabled(false);
                }
                break;
            case UPDATE_VIEWER_IS_EDITING:
                if ((Boolean) updateEvent.getObject()) {
                    jTextPane.setEditable(true);
                    btn_edit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("red_edit.png")));
                } else {
                    jTextPane.setEditable(false);
                    btn_edit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit.png")));
                }
                break;
            case UPDATE_VIEWER_CAN_SAVE:
                btn_save.setEnabled((Boolean) updateEvent.getObject());
                break;
            case UPDATE_CENTER_COMPARE_BLOCK_INDEX:
                controller.getEventListener(DataId.UPDATE_VIEWER_BEFORE_REPAINT_REQUEST,updateEvent.getObject()).actionPerformed(null);
                break;
        }
    }
}
