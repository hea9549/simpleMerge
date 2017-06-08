package Service;

import Data.ComparableString;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public interface FileService {
    ArrayList<ComparableString> getContents(File filePath);
    File getFilePath();
    File saveFile(File fileToSave, ArrayList<String> contentsToSave);
    File saveFile(File fileToSave, ArrayList<String> contentsToSave, String optionTitleString);
}
