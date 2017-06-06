package ControllerTest;

import Controller.CenterController;
import Controller.MainController;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
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
    public ViewerModel leftModel,rightModel;
    public CenterModel centerModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    String string1 = "abc";
    String string2 = "abcdefg";

    @Before
    public void initTest(){
        testRightBlocks.clear();
        testLeftBlocks.clear();
        mainController = new MainController();
        centerController = new CenterController((CenterModel)ModelProvider.getInstance().getModel("centerModel"));
        centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");

        leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");

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
    public void testCompareButton(){
        ActionListener underTest = centerController.getEventListener(DataId.ACTION_BTN_COMPARE);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();

        assertFalse(centerModel.isCanCompare());
        assertTrue(centerModel.isCanUpperBlock());
        assertTrue(centerModel.isCanLowerBlock());
        assertTrue(centerModel.isCanLeftMerge());
        assertTrue(centerModel.isCanRightMerge());

    }
    @Test
    public void testMergeLeftButton(){
        ActionListener underTest = centerController.getEventListener(DataId.ACTION_BTN_MERGE_LEFT);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();

        assertTrue(leftModel.isCanSave());
        assertTrue(rightModel.isCanSave());
        assertEquals(0,string2.compareTo(leftModel.toString()));

    }
    @Test
    public void testMergeRightButton(){
        ActionListener underTest = centerController.getEventListener(DataId.ACTION_BTN_MERGE_RIGHT);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();

        assertTrue(leftModel.isCanSave());
        assertTrue(rightModel.isCanSave());
        assertEquals(0,string1.compareTo(rightModel.toString()));

    }
    @Test
    public void testActionButtonUpper(){
        ActionListener underTest = centerController.getEventListener(DataId.ACTION_BTN_UPPER);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();



    }
    @Test
    public void testActionButtonLower(){
        ActionListener underTest = centerController.getEventListener(DataId.ACTION_BTN_LOWER);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();


    }

}
