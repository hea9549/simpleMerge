package Data;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public interface ContentService {
    ArrayList<ComparableBlock>[] compare(ArrayList<ComparableBlock> leftContent, ArrayList<ComparableBlock> rightContent);
}
