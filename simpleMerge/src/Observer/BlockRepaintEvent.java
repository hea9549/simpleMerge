package Observer;

/**
 * Created by ParkHaeSung on 2017-06-06.
 */
public class BlockRepaintEvent {
    private int index;
    private byte state;
    private boolean isSelected = false;

    public BlockRepaintEvent(int index, byte state, boolean isSelected) {
        this.index = index;
        this.state = state;
        this.isSelected = isSelected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
