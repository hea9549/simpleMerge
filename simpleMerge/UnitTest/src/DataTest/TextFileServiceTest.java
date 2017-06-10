package DataTest;

import Data.ComparableString;
import Service.FileService;
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


    @Test
    public void getContentsTest() {
        System.out.println("GetContentsTest Start");
        File file = new File("C:\\Users\\ParkHaeSung\\Desktop\\2017컴공\\title.txt");
        mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        expect(mock.getContents(file)).andReturn(new ArrayList<>());
        replay(mock);
        assertNotNull(runner.getContents(file));

    }

    @Test
    public void getFilePathTest(){
        System.out.println("GetFilePathTest Start");
        File fm = new File("C:\\Users\\ParkHaeSung\\Desktop\\2017컴공\\");
        mock = createMock(FileService.class);
        runner = new RunFileService();
        runner.setFs(mock);
        expect(mock.getFilePath()).andReturn(fm);
        replay(mock);
        assertEquals(fm.getAbsoluteFile(), runner.getFilePath().getAbsoluteFile());

    }

    @Test
    public void SaveFileTest(){
        System.out.println("SaveFileTest Start");
        File fileToSave = new File("C:\\Users\\ParkHaeSung\\Desktop\\2017컴공\\");
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


    }


}
