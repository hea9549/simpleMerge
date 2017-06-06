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
    private boolean canSave = false;
    private boolean isEditing = false;
    private File file;
    public void viewerModelInit(){
        setEditing(false);
        setCanSave(false);
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

    public boolean isCanSave() {
        return canSave;
    }

    public void setCanSave(boolean canSave) {
        this.canSave = canSave;
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_CAN_SAVE,canSave));
    }

    public void setBlockIsSelect(boolean isSelect,int index){
        contentsBlock.get(index).setSelect(isSelect);
        notifyChange(new UpdateEvent(DataId.UPDATE_VIEWER_CONTENT,contentsBlock));
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0;i<contentsBlock.size();i++){
            for(int j = 0 ; j<contentsBlock.get(i).getContents().size();j++){
                if(j == contentsBlock.get(i).getContents().size()-1)str += contentsBlock.get(i).getContents().get(j).getContentString();
                else str += contentsBlock.get(i).getContents().get(j).getContentString()+"\n";
            }
        }

        return str;
    }

    public ArrayList<String> getRawData(){
        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0;i<contentsBlock.size();i++){
            for(int j = 0 ; j<contentsBlock.get(i).getContents().size();j++){
                datas.add(contentsBlock.get(i).getContents().get(j).getContentString());
            }
        }
        return datas;
    }

    public int getDiffLines(){
        int count = 0;
        for(int i = 0 ; i < contentsBlock.size();i++){
            if(contentsBlock.get(i).isEqualString())continue;
            count += contentsBlock.get(i).getCount();
        }
        return count;
    }
}
