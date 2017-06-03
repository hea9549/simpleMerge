package Model;

import Data.DataId;
import Observer.Observable;
import Observer.UpdateEvent;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class TopModel extends Observable {
    private boolean canLeftAll;
    private boolean canRightAll;
    private boolean canSaveAll;
    private int diffLine;

    public void initModel(){
        setCanLeftAll(false);
        setCanRightAll(false);
    }

    public boolean isCanLeftAll() {
        return canLeftAll;
    }

    public void setCanLeftAll(boolean canLeftAll) {
        this.canLeftAll = canLeftAll;
        notifyChange(new UpdateEvent(DataId.UPDATE_TOP_CAN_LEFT_ALL,canLeftAll));
    }

    public boolean isCanRightAll() {
        return canRightAll;
    }

    public void setCanRightAll(boolean canRightAll) {
        this.canRightAll = canRightAll;
        notifyChange(new UpdateEvent(DataId.UPDATE_TOP_CAN_RIGHT_ALL,canRightAll));
    }

    public boolean isCanSaveAll() {
        return canSaveAll;
    }

    public void setCanSaveAll(boolean canSaveAll) {
        this.canSaveAll = canSaveAll;
        notifyChange(new UpdateEvent(DataId.UPDATE_TOP_CAN_SAVE_ALL,canSaveAll));
    }

    public int getDiffLine() {
        return diffLine;
    }

    public void setDiffLine(int diffLine) {
        this.diffLine = diffLine;
        notifyChange(new UpdateEvent(DataId.UPDATE_TOP_DIFF_LINE,diffLine));
    }
}
