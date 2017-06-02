package Model;

import Data.ComparableBlock;
import Data.DataId;
import Observer.Observable;
import Observer.UpdateEvent;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private ArrayList<ComparableBlock> contentsBlock;
    private boolean canEdit = false;
    private String filePath;
    public ViewerModel(){
        setCanEdit(false);
    }

    public void setContentsBlock(ArrayList<ComparableBlock> contentsBlock) {
        this.contentsBlock = contentsBlock;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_CONTENT,contentsBlock));
    }

    public ArrayList<ComparableBlock> getContentsBlock() {
        return contentsBlock;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_EDITED, canEdit));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
