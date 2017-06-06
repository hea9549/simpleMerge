package ControllerTest;

import Controller.CenterController;
import Controller.MainController;
import Controller.ViewerController;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Model.CenterModel;
import Model.ModelProvider;
import Model.TopModel;
import Model.ViewerModel;
import Util.AttributeUtil;
import org.junit.Before;
import org.junit.Test;
import sun.plugin.javascript.navig.Document;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ParkHaeSung on 2017-06-01.
 */
public class ViewerControllerTest {
    public MainController mainController;
    public ViewerController viewerController;
    public ViewerModel leftModel,rightModel;
    public CenterModel centerModel;
    public TopModel topModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    StyledDocument styledDocument = new DefaultStyledDocument();
    String string1 = "abc";
    String string2 = "abc wioe giji woqw";

    @Before
    public void init() throws BadLocationException {
        testRightBlocks.clear();
        testLeftBlocks.clear();
        mainController = new MainController();
        centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");
        leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
        topModel = (TopModel)ModelProvider.getInstance().getModel("topModel");
        viewerController = new ViewerController(leftModel);

        ComparableBlock testLeftBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testLeftString1 = new ComparableString.Builder()
                .setFlags(ComparableString.DIFF)
                .setContent(string1).build();
        testLeftBlock1.addContents(testLeftString1);
        testLeftBlocks.add(testLeftBlock1);

        ComparableBlock testRightBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testRightString1 = new ComparableString.Builder()
                .setFlags(ComparableString.DIFF)
                .setContent(string2).build();
        testRightBlock1.addContents(testRightString1);

        testRightBlocks.add(testRightBlock1);
        leftModel.setContentsBlock(testLeftBlocks);
        rightModel.setContentsBlock(testRightBlocks);
        styledDocument.insertString(0,string1, AttributeUtil.getAddedAttribute());
    }
    @Test
    public void testEditActionEditingFalse(){
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT);
        leftModel.setEditing(false);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        assertFalse(leftModel.isCanSave());
        assertTrue(leftModel.isEditing());
        assertFalse(centerModel.isCanCompare());
        assertFalse(topModel.isCanLeftAll());
        assertFalse(topModel.isCanRightAll());
    }
    @Test
    public void testEditActionEditingTrue(){

        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument);
        leftModel.setEditing(true);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        assertTrue(leftModel.isCanSave());
        assertFalse(leftModel.isEditing());
        assertTrue(leftModel.getContentsBlock().get(0).getContents().get(0).getContentString().equals(string1));
    }

}
