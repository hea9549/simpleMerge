package Observer;

import Data.ComparableString;

/**
 * Created by ParkHaeSung on 2017-06-06.
 */
public class BlockSelectEvent {
    private int beforeIndex;
    private int afterIndex;

    public BlockSelectEvent(int beforeIndex, int afterIndex) {
        this.beforeIndex = beforeIndex;
        this.afterIndex = afterIndex;
    }

    public int getBeforeIndex() {
        return beforeIndex;
    }

    public void setBeforeIndex(int beforeIndex) {
        this.beforeIndex = beforeIndex;
    }

    public int getAfterIndex() {
        return afterIndex;
    }

    public void setAfterIndex(int afterIndex) {
        this.afterIndex = afterIndex;
    }

}
