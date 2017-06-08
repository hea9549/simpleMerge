package ControllerTest;

import Controller.CenterController;
import Controller.MainController;
import Controller.ViewerController;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;
import Util.AttributeUtil;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chaebyeonghun on 2017. 6. 5..
 */
public class CenterControllerTest {

    public MainController mainController;
    public CenterController centerController;
    public ViewerController leftController, rightController;
    public ViewerModel leftModel,rightModel;
    public CenterModel centerModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    StyledDocument styledDocument1 = new DefaultStyledDocument();
    StyledDocument styledDocument2 = new DefaultStyledDocument();
    ActionListener compareAction,mergeLeftAction,mergeRightAction,upperAction,lowerAction,leftEditAction,rightEditAction;
    JButton testButton,leftEditButton,rightEditButton,compareButton;
    String string1 = "hi my name is Byunghoon\nhihi\nkkk\nlolo";
    String string2 = "hi my name is Gunmo\nhihi\naki\nlolo";

    @Before
    public void initTest() throws BadLocationException {
        testRightBlocks.clear();
        testLeftBlocks.clear();
        mainController = new MainController();
        centerController = new CenterController((CenterModel)ModelProvider.getInstance().getModel("centerModel"));
        centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");
        leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
        leftController = new ViewerController(leftModel);
        rightController = new ViewerController(rightModel);
        testButton = new JButton();
        leftEditButton = new JButton();
        rightEditButton = new JButton();
        compareButton = new JButton();
        styledDocument1.insertString(0,string1, AttributeUtil.getAddedAttribute());
        styledDocument2.insertString(0,string2, AttributeUtil.getAddedAttribute());
        ComparableBlock testLeftBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testLeftString1 = new ComparableString.Builder().setFlags(ComparableString.DIFF).setContent(string1).build();
        testLeftBlock1.addContents(testLeftString1);
        testLeftBlocks.add(testLeftBlock1);
        ComparableBlock testRightBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testRightString1 = new ComparableString.Builder().setFlags(ComparableString.DIFF).setContent(string2).build();
        testRightBlock1.addContents(testRightString1);

        testRightBlocks.add(testRightBlock1);
        leftModel.setContentsBlock(testLeftBlocks);
        rightModel.setContentsBlock(testRightBlocks);

        compareAction = centerController.getEventListener(DataId.ACTION_BTN_COMPARE);
        lowerAction = centerController.getEventListener(DataId.ACTION_BTN_LOWER);
        upperAction = centerController.getEventListener(DataId.ACTION_BTN_UPPER);
        mergeLeftAction = centerController.getEventListener(DataId.ACTION_BTN_MERGE_LEFT);
        mergeRightAction = centerController.getEventListener(DataId.ACTION_BTN_MERGE_RIGHT);
        leftEditAction = leftController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument1);
        rightEditAction = rightController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument2);

    }
    @Test
    public void testCompareAction(){
        testButton.addActionListener(compareAction);
        testButton.doClick();

        assertFalse(centerModel.isCanCompare());
        assertTrue(centerModel.isCanUpperBlock());
        assertTrue(centerModel.isCanLowerBlock());
        assertTrue(centerModel.isCanLeftMerge());
        assertTrue(centerModel.isCanRightMerge());

    }
    @Test
    public void testMergeLeftAction(){
        testButton.addActionListener(mergeLeftAction);
        testButton.doClick();

        assertTrue(leftModel.isCanSave());
        assertTrue(rightModel.isCanSave());
        assertEquals(0,string2.compareTo(leftModel.toString()));

    }
    @Test
    public void testMergeRightAction(){

        testButton.addActionListener(mergeRightAction);
        testButton.doClick();

        assertTrue(leftModel.isCanSave());
        assertTrue(rightModel.isCanSave());
        assertEquals(0,string1.compareTo(rightModel.toString()));

    }
    @Test
    public void testSegmentMergeLeftAction(){
        JButton test1Button = new JButton();
        test1Button.addActionListener(upperAction);
        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        testButton.addActionListener(mergeLeftAction);

        leftEditButton.doClick();
        leftEditButton.doClick();
        rightEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();
        test1Button.doClick();
        testButton.doClick();

        assertEquals("hi my name is Gunmo\nhihi\nkkk\nlolo",leftModel.toString());

    }
    @Test
    public void testSegmentMergeRightAction(){
        JButton test1Button = new JButton();
        test1Button.addActionListener(upperAction);
        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        testButton.addActionListener(mergeRightAction);

        leftEditButton.doClick();
        leftEditButton.doClick();
        rightEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();
        test1Button.doClick();
        testButton.doClick();

        assertEquals("hi my name is Byunghoon\nhihi\naki\nlolo",rightModel.toString());

    }
    @Test
    public void testUpperAction (){

        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        testButton.addActionListener(upperAction);
        leftEditButton.doClick();
        leftEditButton.doClick();
        rightEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();
        testButton.doClick();
        assertEquals(0,centerModel.getCompareBlockIndex());

    }
    @Test
    public void testLowerAction() {

        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        testButton.addActionListener(lowerAction);

        leftEditButton.doClick();
        leftEditButton.doClick();
        rightEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();

        testButton.doClick();
        assertEquals(2,centerModel.getCompareBlockIndex());

    }

}
