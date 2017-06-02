import Data.ComparableBlock;
import Data.ComparableString;
import Data.ContentService;
import Data.ContentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class ContentServiceTest {
    ArrayList<ComparableBlock> comparableBlocks1 = new ArrayList<>();
    ArrayList<ComparableBlock> comparableBlocks2 = new ArrayList<>();

    @Test
    void paramSettingTest() {
        ArrayList<ComparableString> contents1 = new ArrayList<>();
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        ComparableBlock block1 = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        comparableBlocks1.add(block1);

        ArrayList<ComparableString> contents2 = new ArrayList<>();
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("d").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("f").build());
        ComparableBlock block2 = new ComparableBlock(ComparableBlock.DEFAULT, contents2);
        comparableBlocks2.add(block2);

        ContentService contentService = new ContentServiceImpl();
        assertEquals(contentService.compare(comparableBlocks1, comparableBlocks2)[1].get(1).getContents().get(0).getState(), ComparableString.ADDED);
    }

    @Test
    void blockNumTest() {
        ArrayList<ComparableString> contents1 = new ArrayList<>();
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        ComparableBlock block1 = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        comparableBlocks1.add(block1);

        ArrayList<ComparableString> contents2 = new ArrayList<>();
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("a").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("b").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("c").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("d").build());
        contents2.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("f").build());
        ComparableBlock block2 = new ComparableBlock(ComparableBlock.DEFAULT, contents2);
        comparableBlocks2.add(block2);

        // Case 1
        ContentService contentService = new ContentServiceImpl();
        assertEquals(2, contentService.compare(comparableBlocks1, comparableBlocks2)[0].size());

        // Case 2
        contents1.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent("f").build());
        contents2.remove(1);
        block1 = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        block2 = new ComparableBlock(ComparableBlock.DEFAULT, contents2);
        comparableBlocks1 = new ArrayList<>();
        comparableBlocks1.add(block1);
        comparableBlocks2 = new ArrayList<>();
        comparableBlocks2.add(block2);
        ArrayList<ComparableBlock> testAL = contentService.compare(comparableBlocks1, comparableBlocks2)[1];
        assertEquals(3, testAL.size());

        // Case 3
        contents1.remove(0);
        contents1.remove(0);
        block1 = new ComparableBlock(ComparableBlock.DEFAULT, contents1);
        comparableBlocks1 = new ArrayList<>();
        comparableBlocks1.add(block1);
        assertEquals(4, contentService.compare(comparableBlocks1, comparableBlocks2)[0].size());
    }
}
