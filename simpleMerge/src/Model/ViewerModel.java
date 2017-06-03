package Model;

import Data.ComparableBlock;
import Data.DataId;
import Observer.Observable;
import Observer.UpdateEvent;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerModel extends Observable {
    private ArrayList<ComparableBlock> contentsBlock;
    private boolean canEdit = false;
    private boolean isEditing = false;
    private File file;
    public void viewerModelInit(){
        setEditing(false);
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
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_CAN_EDIT, canEdit));
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_IS_EDITING, isEditing));
    }
}
