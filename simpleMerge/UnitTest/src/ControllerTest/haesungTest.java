package ControllerTest;

import Controller.MainController;
import Data.*;
import Model.ViewerModel;
import Service.ContentService;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.easymock.EasyMock.createMock;

/**
 * Created by ParkHaeSung on 2017-06-04.
 */
public class haesungTest {
    @TestSubject
    MainController controller = new MainController();
    @Mock
    ContentService contentService;
    ArrayList<ComparableBlock> testLeftBlocks,testRightBlocks;
    ArrayList<ComparableBlock> testLeftReturn,testRightReturn;
    ArrayList<ComparableBlock>[] expectReturn;
    ViewerModel testLeftViewerModel,testRightViewerModel;
    @Before
    public void setUp(){
        ComparableBlock testLeftBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testLeftString1 = new ComparableString.Builder()
                .setFlags(ComparableString.DIFF)
                .setContent("abc").build();
        testLeftBlock1.addContents(testLeftString1);
        testLeftBlocks.add(testLeftBlock1);

        ComparableBlock testRightBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testRightString1 = new ComparableString.Builder()
                .setFlags(ComparableString.DIFF)
                .setContent("abcd").build();
        testLeftBlock1.addContents(testRightString1);
        testLeftBlocks.add(testRightBlock1);

        testLeftReturn = new ArrayList<ComparableBlock>();
        testRightReturn = new ArrayList<ComparableBlock>();
        ComparableBlock testLeftReturnBlock1 = new ComparableBlock(ComparableBlock.EQUAL);
        ComparableString testLeftReturnString1 = new ComparableString.Builder()
                .setFlags(ComparableString.EQUAL)
                .setContent("abcd").build();
        testLeftReturnBlock1.addContents(testLeftReturnString1);
        testLeftReturn.add(testLeftReturnBlock1);

        ComparableBlock testRightReturnBlock1 = new ComparableBlock(ComparableBlock.DIFF);
        ComparableString testRightReturnString1 = new ComparableString.Builder()
                .setFlags(ComparableString.EQUAL)
                .setContent("abcd").build();
        testRightReturnBlock1.addContents(testRightReturnString1);
        testRightReturn.add(testRightReturnBlock1);

        expectReturn = new ArrayList[2];
        expectReturn[0] = testLeftReturn;
        expectReturn[1] = testRightReturn;

        testLeftViewerModel = new ViewerModel();
        testRightViewerModel = new ViewerModel();
        testLeftViewerModel.setContentsBlock(testLeftBlocks);
        testRightViewerModel.setContentsBlock(testRightBlocks);
    }

    @Test
    public void test(){
        EasyMock.expect(contentService.merge(testLeftViewerModel,testRightViewerModel)).andAnswer(new IAnswer<Boolean>() {
            @Override
            public Boolean answer() throws Throwable {
                //이부분에 머지하는 행동 하면댈듯

                return true;
            }
        });
        EasyMock.replay(contentService);
        contentService.merge(testLeftViewerModel,testRightViewerModel);

    }
}
