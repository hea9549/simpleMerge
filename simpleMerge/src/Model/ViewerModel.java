package Model;

import Data.ComparableBlock;
import Data.ComparableString;
import Data.DataId;
import Observer.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private ArrayList<ComparableBlock> leftContent;
    private ArrayList<ComparableBlock> rightContent;
    private Map<Integer,Object> notifyValue = new HashMap<Integer,Object>();

    public void setLeftContents(ArrayList<ComparableBlock> content) {
        this.leftContent= content;
        notifyValue.put(DataId.UPDATE_LEFT_CONTENT,content);
        notifyChange(notifyValue);
        notifyValue.clear();
    }
    public void setRightContents(ArrayList<ComparableBlock> content) {
        this.rightContent= content;
        notifyValue.put(DataId.UPDATE_RIGHT_CONTENT,content);
        notifyChange(notifyValue);
        notifyValue.clear();
    }

    public ArrayList<ComparableBlock> getLeftContents() {
        return leftContent;
    }

    public ArrayList<ComparableBlock> getRightContents() {
        return rightContent;
    }
}
