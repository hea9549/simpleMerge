package Model;

import Data.DataId;
import Observer.BlockSelectEvent;
import Observer.Observable;
import Observer.UpdateEvent;

import java.util.Arrays;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class CenterModel extends Observable {
    private boolean canLeftMerge = false;
    private boolean canRightMerge = false;
    private boolean canUpperBlock = false;
    private boolean canLowerBlock = false;
    private boolean canCompare = false;
    private int compareBlockIndex = 0;
    public void initModel(){
        setCanCompare(false);
        setCanLeftMerge(false);
        setCanRightMerge(false);
        setCanUpperBlock(false);
        setCanLowerBlock(false);

    }

    public boolean isCanLeftMerge() {
        return canLeftMerge;
    }

    public void setCanLeftMerge(boolean canLeftMerge) {
        this.canLeftMerge = canLeftMerge;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_CAN_LEFT_MERGE,canLeftMerge));
    }

    public boolean isCanRightMerge() {
        return canRightMerge;
    }

    public void setCanRightMerge(boolean canRightMerge) {
        this.canRightMerge = canRightMerge;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_CAN_RIGHT_MERGE,canRightMerge));
    }

    public boolean isCanUpperBlock() {
        return canUpperBlock;
    }

    public void setCanUpperBlock(boolean canUpperBlock) {
        this.canUpperBlock = canUpperBlock;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_CAN_UPPER_BLOCK,canUpperBlock));
    }

    public boolean isCanLowerBlock() {
        return canLowerBlock;
    }

    public void setCanLowerBlock(boolean canLowerBlock) {
        this.canLowerBlock = canLowerBlock;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_CAN_LOWER_BLOCK,canLowerBlock));
    }

    public boolean isCanCompare() {
        return canCompare;
    }

    public void setCanCompare(boolean canCompare) {
        this.canCompare = canCompare;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_CAN_COMPARE,canCompare));
    }

    public int getCompareBlockIndex() {
        return compareBlockIndex;
    }

    public void setCompareBlockIndex(int compareBlockIndex) {
        int beforeIndex = this.compareBlockIndex;
        if(compareBlockIndex ==-1){
            notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_COMPARE_BLOCK_INDEX, new BlockSelectEvent(beforeIndex,-1)));
            return;
        }
        ViewerModel leftViewerModel = ((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel"));
        if(leftViewerModel.getContentsBlock().size()-1<compareBlockIndex) return;
        this.compareBlockIndex = compareBlockIndex;
        notifyChange(new UpdateEvent(DataId.UPDATE_CENTER_COMPARE_BLOCK_INDEX, new BlockSelectEvent(beforeIndex,compareBlockIndex)));
    }

    public void resetComparableBlockIndex(){
        this.compareBlockIndex = 0;
    }

}
