package Model;

import Data.DataId;
import Observer.Observable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private String content;
    private Map<Integer,String> notifyValue = new HashMap<Integer,String>();

    public void setContent(String content) {
        this.content = content;
        notifyValue.put(DataId.STR_CONTENT,"바껴라 얍");
        notifyChange(notifyValue);
        notifyValue.clear();
    }
}
