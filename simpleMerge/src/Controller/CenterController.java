package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
import Model.TopModel;
import Model.ViewerModel;
import Service.ContentService;
import Service.ContentServiceImpl;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class CenterController extends ViewController {
    CenterModel centerModel;
    ViewerModel leftViewerModel,rightViewerModel;
    public CenterController(CenterModel centerModel){
        this.centerModel = centerModel;
        leftViewerModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightViewerModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
    }
    private boolean setNextBlockIndex(){
        ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        if(centerModel.getCompareBlockIndex()+1>leftModel.getContentsBlock().size())return false;
        for(int i = centerModel.getCompareBlockIndex()+1;i<leftModel.getContentsBlock().size();i++){
            if(leftModel.getContentsBlock().get(i).isEqualString())continue;
            centerModel.setCompareBlockIndex(i);
            return true;
        }
        return false;
    }
    private boolean setPrevBlockIndex(){
        ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        if(centerModel.getCompareBlockIndex()-1<0)return false;
        for(int i = centerModel.getCompareBlockIndex()-1;i>=0 ;i--){
            if(leftModel.getContentsBlock().get(i).isEqualString())continue;
            centerModel.setCompareBlockIndex(i);
            return true;
        }
        return false;
    }

    @Override
    public void onEventLoad(DataId id, Object extraData) {
        switch (id){
            case ACTION_BTN_COMPARE:
                compareAction();
                break;
            case ACTION_BTN_MERGE_LEFT:
                mergeAction(rightViewerModel,leftViewerModel);
                break;
            case ACTION_BTN_MERGE_RIGHT:
                mergeAction(leftViewerModel,rightViewerModel);
                break;
            case ACTION_BTN_UPPER:
                setPrevBlockIndex();
                break;
            case ACTION_BTN_LOWER:
                setNextBlockIndex();
                break;
        }
    }

    private void mergeAction(ViewerModel sourceModel, ViewerModel targetModel) {
        ContentService contentService=  new ContentServiceImpl();
        contentService.merge(sourceModel,targetModel);
        sourceModel.setCanSave(true);
        targetModel.setCanSave(true);
        if(!setNextBlockIndex())if(!setPrevBlockIndex()){
            mergeFinishModelSetting();
            centerModel.setCompareBlockIndex(-1);
        };
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).setDiffLine(leftViewerModel.getDiffLines());
    }

    private void compareAction() {
        ContentService contentService = new ContentServiceImpl();
        ArrayList<ComparableBlock>[] compareResult = contentService.compare(leftViewerModel.getContentsBlock(),rightViewerModel.getContentsBlock());
        leftViewerModel.setContentsBlock(compareResult[0]);
        rightViewerModel.setContentsBlock(compareResult[1]);
        centerModel.resetComparableBlockIndex();
        for(int i = 0;i<leftViewerModel.getContentsBlock().size();i++){
            if(leftViewerModel.getContentsBlock().get(i).isEqualString())continue;
            centerModel.setCompareBlockIndex(i);
            break;
        }
        centerModel.setCanCompare(false);
        CenterModel centerModel =((CenterModel)ModelProvider.getInstance().getModel("centerModel"));
        centerModel.setCanUpperBlock(true);
        centerModel.setCanLowerBlock(true);
        centerModel.setCanLeftMerge(true);
        centerModel.setCanRightMerge(true);
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).setDiffLine(leftViewerModel.getDiffLines());
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanLeftAll(true);
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanRightAll(true);
    }


    private void mergeFinishModelSetting() {
        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanUpperBlock(false);
        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanLowerBlock(false);
    }
}
