package Model;

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
    private ArrayList<ComparableString> leftContent;
    private ArrayList<ComparableString> rightContent;
    private Map<Integer,Object> notifyValue = new HashMap<Integer,Object>();

    public void setLeftContents(ArrayList<ComparableString> content) {
        this.leftContent= content;
        notifyValue.put(DataId.UPDATE_LEFT_CONTENT,content);
        notifyChange(notifyValue);
        notifyValue.clear();
    }
    public void setRightContents(ArrayList<ComparableString> content) {
        this.rightContent= content;
        notifyValue.put(DataId.UPDATE_RIGHT_CONTENT,content);
        notifyChange(notifyValue);
        notifyValue.clear();
    }
}
