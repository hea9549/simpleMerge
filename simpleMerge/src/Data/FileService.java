package Data;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public interface FileService {
    ArrayList<ComparableString> getContentsFromFile();
    void setPath(String path);
}
