package DataTest;

import Data.ComparableBlock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by jgm on 6/2/17.
 */
public class ComparableBlockTest {
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

}
