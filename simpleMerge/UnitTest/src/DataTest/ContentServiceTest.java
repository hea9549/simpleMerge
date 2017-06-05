package DataTest;

import Data.ComparableBlock;
import Data.ContentService;
import Model.ViewerModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Created by jgm on 6/3/17.
 */
public class ContentServiceTest extends TestCase {
    ContentService mock;
    RunContentService runner;

    ArrayList<ComparableBlock> left = new ArrayList<>();
    ArrayList<ComparableBlock> right = new ArrayList<>();
    ArrayList<ComparableBlock>[] result = new ArrayList[2];
    ViewerModel sourceModel = new ViewerModel();
    ViewerModel targetModel = new ViewerModel();


    protected void setUp() throws Exception{
        mock = createMock(ContentService.class);
        runner = new RunContentService();
        runner.setCont(mock);
        super.setUp();

    }

    public void testCompare(){
        expect(mock.compare(left, right)).andReturn(result);
        //mock.compare(left, right);
        replay(mock);
        assertEquals(result, runner.compare(left, right));
        verify(mock);

    }
    public void testMerge(){
        expect(mock.merge(sourceModel, targetModel)).andReturn(true);
        replay(mock);
        assertEquals(true, runner.merge(sourceModel, targetModel));
        verify(mock);

    }

}
