package Data;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public interface FileService {
    ArrayList<ComparableString> getContents(String filePath);
    String getFilePath();
    void saveFile(File fileToSave, ArrayList<String> contentsToSave);
}
