package DataTest;


import Data.ComparableBlock;
import Data.ComparableString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by jgm on 6/2/17.
 */
public class ComparableBlockTest {

    @Test
    void getCountTest(){
        ArrayList<ComparableString> contents1 = new ArrayList<>();
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        ComparableBlock test = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        assertEquals(3, test.getCount());
    }

    @Test
    void getContentsTest(){
        ArrayList<ComparableString> contents1 = new ArrayList<>();
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        ComparableBlock test = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        assertEquals(contents1.get(0), test.getContents(0));
    }

    @Test
    void isEqualStringTest(){
        ComparableBlock test = new ComparableBlock(ComparableBlock.EQUAL);
        assertEquals(true, test.isEqualString());
    }

    @Test
    void isDiffStringTest(){
        ComparableBlock test = new  ComparableBlock(ComparableBlock.DIFF);
        assertEquals(true, test.isDiffString());
    }

    @Test
    void isAddedStringTest(){
        ComparableBlock test = new ComparableBlock(ComparableBlock.ADDED);
        assertEquals(true, test.isAddedString());
    }

    @Test
    void isEmptyStringTest(){
        ComparableBlock test = new ComparableBlock(ComparableBlock.EMPTY);
        assertEquals(true, test.isEmptyString());
    }

}
