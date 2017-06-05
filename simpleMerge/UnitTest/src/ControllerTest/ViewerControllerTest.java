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
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-06-01.
 */
public class ViewerControllerTest {
    public MainController mainController;
    public ViewerController viewerController;
    public ViewerModel leftModel,rightModel;
    public CenterModel centerModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    String string1 = "abc";
    String string2 = "abc wioe giji woqw";

    @Before
    public void init(){
        testRightBlocks.clear();
        testLeftBlocks.clear();
        mainController = new MainController();
        leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
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
    public void testEditAction(){
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
    }
    @Test
    public void testLoadAction(){
        ActionListener underTest = viewerController.getEventListener(DataId.ACTION_VIEWER_BTN_LOAD);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();

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
