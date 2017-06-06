package DataTest;

import Data.ComparableString;
import Service.FileService;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jgm on 6/4/17.
 */
public class RunFileService {
    FileService fs;

    public void setFs(FileService fs){
        this.fs = fs;
    }

    public ArrayList<ComparableString> getContents(String filePath){
        System.out.println("Get Contents Test  ");
        return fs.getContents(filePath);
    }

    public String getFilePath(){
        System.out.println("Get File Path Test  ");
        return fs.getFilePath();
    }

    public void saveFile(File fileToSave, ArrayList<String> contentsToSave){
        System.out.println("Save File Test  ");
        fs.saveFile(fileToSave, contentsToSave);
    }

}
