package DataTest;

import Data.ComparableBlock;
import Data.ContentService;
import Model.ViewerModel;

import java.util.ArrayList;

/**
 * Created by jgm on 6/3/17.
 */
public class RunContentService {
    ContentService cont;

    public void setCont(ContentService cont){
        this.cont = cont;
    }

    public ArrayList<ComparableBlock>[] compare(ArrayList<ComparableBlock> leftContent, ArrayList<ComparableBlock> rightContent){
        System.out.println("Compare Test  ");

        return cont.compare(leftContent, rightContent);
    }

    public boolean merge(ViewerModel sourceModel, ViewerModel targetModel) {
        System.out.println("Merge Test   ");

        return cont.merge(sourceModel, targetModel);
    }


}
