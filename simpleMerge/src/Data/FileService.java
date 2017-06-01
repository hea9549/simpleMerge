package Data;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public interface FileService {
    ArrayList<ComparableString> getContents(String filePath);
    String getFilePath();
}
