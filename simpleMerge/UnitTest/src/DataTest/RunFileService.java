package DataTest;

import Data.ComparableString;
import Data.FileService;
import Data.TextFileService;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by jgm on 6/4/17.
 */
public class RunFileService {
    FileService fs;

    public void setFs(FileService fs){
        this.fs = fs;
    }

    public ArrayList<ComparableString> getContents(File file){
        System.out.println("Get Contents Test  ");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public File getFilePath(){
        System.out.println("Get File Path Test  ");

        return new File("C:\\");
    }

    public void saveFile(File fileToSave, ArrayList<String> contentsToSave, String fileName){
        System.out.println("Save File Test  ");
        BufferedWriter bw = null;
        fileToSave = new File(fileToSave.getAbsolutePath()+"/"+fileName);

        try {
            bw = new BufferedWriter(new FileWriter(fileToSave));
            System.out.println("file made");
            bw.write("");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
