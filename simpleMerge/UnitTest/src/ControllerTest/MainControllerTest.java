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
import javax.swing.text.View;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static Data.DataId.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by chaebyeonghun on 2017. 6. 5..
 */
public class MainControllerTest {

    public MainController mainController;
    public CenterController centerController;
    public ViewerModel leftModel,rightModel;
    public ViewerController leftController, rightController;
    public CenterModel centerModel;
    public ArrayList<ComparableBlock> testLeftBlocks = new ArrayList<>();
    public ArrayList<ComparableBlock> testRightBlocks = new ArrayList<>();
    StyledDocument styledDocument1 = new DefaultStyledDocument();
    StyledDocument styledDocument2 = new DefaultStyledDocument();
    ActionListener compareAction,leftEditAction,rightEditAction,leftAllAction,rightAllAction;
    JButton leftEditButton,rightEditButton,leftAllButton,rightAllButton,compareButton;
    String string1 = "안녕하세요!\n테스트\n중입니다.";
    String string2 = "hi\n테스트\ning\nsucess!";

    @Before
    public void init() throws BadLocationException {
        testRightBlocks.clear();
        testLeftBlocks.clear();
        mainController = new MainController();
        centerController = new CenterController((CenterModel)ModelProvider.getInstance().getModel("centerModel"));

        leftEditButton = new JButton();
        rightEditButton = new JButton();
        leftAllButton = new JButton();
        rightEditButton = new JButton();
        rightAllButton = new JButton();
        compareButton = new JButton();
        centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");
        leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");

        leftController = new ViewerController(leftModel);
        rightController = new ViewerController(rightModel);
        styledDocument1.insertString(0,string1, AttributeUtil.getAddedAttribute());
        styledDocument2.insertString(0,string2, AttributeUtil.getAddedAttribute());

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
        compareAction = centerController.getEventListener(ACTION_BTN_COMPARE);
        leftAllAction = mainController.getEventListener(ACTION_BTN_LEFT_ALL);
        rightAllAction = mainController.getEventListener(ACTION_BTN_RIGHT_ALL);

        leftEditAction = leftController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument1);
        rightEditAction = rightController.getEventListener(DataId.ACTION_VIEWER_BTN_EDIT,styledDocument2);
    }
    @Test
    public void testLeftAllAction(){

        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        leftAllButton.addActionListener(leftAllAction);

        leftEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();
        leftAllButton.doClick();

        assertEquals("hi\n테스트\ning\nsucess!",leftModel.toString());

    }
    @Test
    public void testRightAllAction(){
        leftEditButton.addActionListener(leftEditAction);
        rightEditButton.addActionListener(rightEditAction);
        compareButton.addActionListener(compareAction);
        rightAllButton.addActionListener(rightAllAction);

        leftEditButton.doClick();
        rightEditButton.doClick();
        compareButton.doClick();
        rightAllButton.doClick();
        assertEquals("안녕하세요!\n테스트\n중입니다.",rightModel.toString());

    }

}
