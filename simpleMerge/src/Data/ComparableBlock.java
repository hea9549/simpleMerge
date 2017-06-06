package Data;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-06-01.
 */
public class ComparableBlock {
    public static final byte DEFAULT = 0b00000000;
    public static final byte EQUAL = 0b00000001;
    public static final byte DIFF = 0b00000010;
    public static final byte ADDED = 0b00000100;
    public static final byte EMPTY = 0b00001000;
    private byte state = 0b00000000;
    private boolean isSelect = false;
    private ArrayList<ComparableString> contents;
    public ComparableBlock(byte state){
        this.state = state;
        contents = new ArrayList<>();
    }
    public ComparableBlock(byte state,ArrayList<ComparableString> contents){
        this.state= state;
        this.contents = contents;
    }

    public int getCount(){
        return contents.size();
    }

    public ComparableString getContents(int index) {
        return contents.get(index);
    }

    public ArrayList<ComparableString> getContents() {
        return contents;
    }

    public void addContents(ComparableString comparableString){
        contents.add(comparableString);
    }

    public void setContents(ArrayList<ComparableString> contents) {
        this.contents = contents;
    }

    public boolean isEqualString() {
        return (EQUAL & state) == EQUAL;
    }

    public boolean isDiffString() {
        return (DIFF & state) == DIFF;
    }

    public boolean isAddedString() {
        return (ADDED & state) == ADDED;
    }

    public boolean isEmptyString() {
        return (EMPTY & state) == EMPTY;
    }

    public void setDiff() {
        state = DIFF;
    }

    public void setEqual() {
        state = EQUAL;
    }
    /**
     * if state is not Diff or already Empty, return false
     * */
    public boolean setEmpty() {
        if((state&(DIFF+EMPTY)) != 0)return false;
        state += EMPTY;
        return true;
    }

    /**
     * if state is not Diff or already Empty, return false
     * */
    public boolean setAdded() {
        if((state&(DIFF+ADDED)) != 0)return false;
        state += ADDED;
        return true;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
