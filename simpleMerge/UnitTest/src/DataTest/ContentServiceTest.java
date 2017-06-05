package DataTest;

import Data.ComparableBlock;
import Data.ContentService;
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

    @Before
    protected void setUp() throws Exception{
        mock = createMock(ContentService.class);
        runner = new RunContentService();
        runner.setCont(mock);
        super.setUp();

    }

    @Test
    public void testCompare(){
        System.out.println("1");
        expect(mock.compare(left, right)).andReturn(result);
        //mock.compare(left, right);
        System.out.println("2");
        replay(mock);
        System.out.println("3");
        assertEquals(result, runner.compare(left, right));
        System.out.println("4");
        verify(mock);
        System.out.println("5");
    }

}
