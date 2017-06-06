package ControllerTest;

import Controller.CenterController;
import Controller.MainController;
import Data.ComparableBlock;
import Data.ComparableString;
import Data.ContentServiceImpl;
import Data.DataId;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Data.DataId.ACTION_BTN_LEFT_ALL;
import static Data.DataId.ACTION_BTN_RIGHT_ALL;
import static Data.DataId.ACTION_BTN_SAVE_ALL;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by chaebyeonghun on 2017. 6. 5..
 */
public class MainControllerTest {

    public MainController mainController;
    public CenterController centerController;
    public ViewerModel leftModel,rightModel;
    public CenterModel centerModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    String string1 = "abc qoueu fgqbgu soqperl";
    String string2 = "abc wioe giji woqw";

    @Before
    public void init(){
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
    public void testLeftAllAction(){

        ActionListener underTest = mainController.getEventListener(ACTION_BTN_LEFT_ALL);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        assertEquals(string2,leftModel.toString());

    }
    @Test
    public void testRightAllAction(){
        ActionListener underTest = mainController.getEventListener(ACTION_BTN_RIGHT_ALL);
        JButton testButton = new JButton();
        testButton.addActionListener(underTest);
        testButton.doClick();
        assertEquals(string1,rightModel.toString());

    }

}
