package Model;

import Data.ComparableBlock;
import Data.DataId;
import Observer.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private ArrayList<ComparableBlock> contentsBlock;
    private Map<Integer,Object> notifyValue = new HashMap<Integer,Object>();

    public void setContentsBlock(ArrayList<ComparableBlock> contentsBlock) {
        this.contentsBlock = contentsBlock;
        notifyValue.put(DataId.UPDATE_VIEWER_CONTENT,contentsBlock);
        notifyChange(notifyValue);
        notifyValue.clear();
    }

    public ArrayList<ComparableBlock> getContentsBlock() {
        return contentsBlock;
    }
}
