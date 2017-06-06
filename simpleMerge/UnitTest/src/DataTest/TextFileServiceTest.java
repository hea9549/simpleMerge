package DataTest;

import Data.ComparableString;
import Data.FileService;
import Data.TextFileService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jgm on 6/6/17.
 */
public class  TextFileServiceTest{

    FileService mock;
    RunFileService runner;

    @Before
    protected void setUp() throws Exception {
        System.out.println("SetUp");
        /*mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        super.setUp();*/
    }

    @Test
    public void getContentsTest() {
        System.out.println("GetContentsTest");
        File file = new File("C:\\title.txt");
        mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        expect(mock.getContents(file)).andReturn(new ArrayList<>());
        replay(mock);
        assertNotNull(runner.getContents(file));
    }

    @Test
    public void getFilePathTest(){
        File fm = new File("C:\\");
        mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        expect(mock.getFilePath()).andReturn(fm);
        replay(mock);
        assertEquals(fm.getAbsoluteFile(), runner.getFilePath().getAbsoluteFile());
        //verify(mock);
    }

    @Test
    public void getSaveFileTest(){
        System.out.println("GetSaveFileTest");
        File fileToSave = new File("C:\\");
        ArrayList<String> contentsToSave = new ArrayList<>();
        String optionTitleString = new String("title.txt");
        File result = new File(fileToSave.getAbsoluteFile()+"/" + optionTitleString);

        mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        expect(mock.saveFile(fileToSave, contentsToSave, optionTitleString)).andReturn(result);
        replay(mock);
        runner.saveFile(fileToSave, contentsToSave, optionTitleString);

        assertEquals(true, result.exists());
        //verify(mock);

    }


}
