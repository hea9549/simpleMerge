package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class ContentServiceImpl implements ContentService {

    @Override
    public Map<Integer, ArrayList<ComparableString>> getProcessedContent(Map<Integer, ArrayList<ComparableString>> rawData) {
        ArrayList<ComparableString> leftContent = rawData.get(LEFT_CONTENT);
        ArrayList<ComparableString> rightContent = rawData.get(RIGHT_CONTENT);
        Map<Integer,ArrayList<ComparableString>> returnData = new HashMap<Integer,ArrayList<ComparableString>>();
        return returnData;
    }
}
