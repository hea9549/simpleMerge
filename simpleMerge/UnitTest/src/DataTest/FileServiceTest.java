/*
package DataTest;

import Data.ComparableBlock;
import Data.ComparableString;
import Data.FileService;
import Data.TextFileService;
import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;

*/
/**
 * Created by jgm on 6/4/17.
 *//*

public class FileServiceTest extends TestCase {
    FileService mock;
    RunFileService runner;
    String filePath = new String();
    ArrayList<ComparableString> result = new ArrayList<>();
    File fileToSave;
    ArrayList<String> contentsToSave = new ArrayList<>();

    protected void setUp() throws Exception {
        mock = createMock(TextFileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        super.setUp();
    }

    public void testGetContents() {
       // expect(mock.getContents(filePath)).andReturn(result);
        replay(mock);
        assertEquals(result, runner.getContents(filePath));
        verify(mock);
    }
    public void testGetFilePath(){
       // expect(mock.getFilePath()).andReturn(filePath);
        replay(mock);
        assertEquals(filePath, runner.getFilePath());
        verify(mock);
    }
    public void testSaveFile(){
        mock.saveFile(fileToSave, contentsToSave);
        replay(mock);
      //  runner.saveFile(fileToSave, contentsToSave);
        verify(mock);
    }

}
*/
