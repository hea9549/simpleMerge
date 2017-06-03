package Data;

/**
 * @author ParkHaeSung
 * @apiNote
 * DEFAULT = 빈내용으로 객체 설정
 * EQUAL = 같은 인덱스의 반대 String 과 내용이 같음.
 * DIFF = 같은 인덱스의 반대 String 과 내용이 다름.
 * ADDED = 반대 스트링에 없는 내용임 ( 추가됨 )
 * EMPTY = 반대 스트링이 추가되 이번 인덱스는 빈칸임 ( 컨텐츠 = null ).
 */

public class ComparableString {
    public static final byte DEFAULT = 0b00000000;
    public static final byte EQUAL = 0b00000001;
    public static final byte DIFF = 0b00000010;
    public static final byte ADDED = 0b00000100;
    public static final byte EMPTY = 0b00001000;
    private byte state = 0b00000000;
    private String contentString;

    private ComparableString(byte state, String content){
        this.state =state;
        this.contentString = content;
    }

    public boolean isDefaultString() { return state == DEFAULT;}
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

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public byte getState() {
        return state;
    }

    public static class Builder {
        private byte state = 0;
        private String content = "";
        public Builder setFlags(byte... param) {
            for (int i = 0; i < param.length; i++) {
                if (param[i] == EQUAL) state += EQUAL;
                if (param[i] == DIFF) state += DIFF;
                if (param[i] == ADDED) state += ADDED;
                if (param[i] == EMPTY) state += EMPTY;
            }
            return this;
        }

        public ComparableString build() {
            checkValid();
            return new ComparableString(state,content);
        }


        public Builder setContent(String content){
            this.content = content;
            return this;
        }

        private void checkValid() {
            if((ADDED & state) == ADDED && (EMPTY & state) == EMPTY)throw new IllegalArgumentException("더해진것과 빈게 동시에 있을 수 없음");
            if((EQUAL & state) == EQUAL&& (DIFF & state) == DIFF) throw new IllegalArgumentException("같음과 다름이 동시에 존재할 수 없음");
        }
    }
}
