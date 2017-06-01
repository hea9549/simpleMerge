package Model;

import Data.ComparableBlock;
import Data.DataId;
import Observer.Observable;
import Observer.UpdateEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private ArrayList<ComparableBlock> contentsBlock;
    private boolean isEdited = false;
    private String filePath;
    public void setContentsBlock(ArrayList<ComparableBlock> contentsBlock) {
        this.contentsBlock = contentsBlock;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_CONTENT,contentsBlock));
    }

    public ArrayList<ComparableBlock> getContentsBlock() {
        return contentsBlock;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_EDITED,edited));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
