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
    String string1 = "abc";
    String string2 = "abc wioe giji woqw";
    String testString = "liooo";
    @Before
    public void init(){
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
    public void testEditActionEditingTrue() throws BadLocationException {
        StyledDocument styledDocument = new DefaultStyledDocument();
        styledDocument.insertString(0,"abcd", AttributeUtil.getAddedAttribute());
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument);
        leftModel.setEditing(true);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        assertTrue(leftModel.isCanSave());
        assertFalse(leftModel.isEditing());
        assertTrue(leftModel.getContentsBlock().get(0).getContents().get(0).getContentString().equals("abcd"));
    }
    @Test
    public void testLoadAction(){
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_LOAD);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        /*private void loadAction() {
            File file;
            FileService fileService = new TextFileService();
            ArrayList<ComparableString> contents = fileService.getContents(file = fileService.getFilePath());
            model.setFile(file);
            ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
            if(contents!= null){
                comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
            }
            model.setContentsBlock(comparableBlocks);
            checkCanCompare();
        }*/

        //맥이라 테스트를 진행할수없음. 테스트 요망
    }
    @Test
    public void testSaveAction(){
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_SAVE);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();


    }
}
