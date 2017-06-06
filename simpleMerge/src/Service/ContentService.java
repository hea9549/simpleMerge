package Service;

import Data.ComparableBlock;
import Model.ViewerModel;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public interface ContentService {
    ArrayList<ComparableBlock>[] compare(ArrayList<ComparableBlock> leftContent, ArrayList<ComparableBlock> rightContent);
    boolean merge(ViewerModel sourceModel,ViewerModel targetModel);
}
