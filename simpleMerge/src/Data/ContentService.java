package Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public interface ContentService {
    int LEFT_CONTENT = 0;
    int RIGHT_CONTENT= 1;
    Map<Integer,ArrayList<ComparableString>> getProcessedContent(Map<Integer,ArrayList<ComparableString>> rawData);

}
