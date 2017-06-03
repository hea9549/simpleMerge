package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Data.DataId.*;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class CenterController implements Controller {
    CenterModel centerModel;
    ViewerModel leftViewerModel,rightViewerModel;
    CenterController(CenterModel centerModel){
        this.centerModel = centerModel;
        leftViewerModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        rightViewerModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
    }

    @Override
    public ActionListener getActionListener(DataId id) {
        return new CenterControllerActionListener(id);
    }

    @Override
    public ActionListener getActionListener(DataId id, Object extraData) {
        return null;
    }

    private class CenterControllerActionListener implements ActionListener{
        private DataId actionSubjectId;
        @Override
        public void actionPerformed(ActionEvent e) {
            ViewerModel leftModel,rightModel;
            ContentService contentService;
            switch (actionSubjectId){
                case ACTION_BTN_COMPARE:
                    contentService= new ContentServiceImpl();
                    leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    ArrayList<ComparableBlock>[] compareResult = contentService.compare(leftModel.getContentsBlock(),rightModel.getContentsBlock());
                    leftModel.setContentsBlock(compareResult[0]);
                    rightModel.setContentsBlock(compareResult[1]);
                    for(int i = 0;i<leftModel.getContentsBlock().size();i++){
                        if(leftModel.getContentsBlock().get(i).isEqualString())continue;
                        centerModel.setCompareBlockIndex(i);
                        break;
                    }
                    centerModel.setCanCompare(false);
                    break;
                case ACTION_BTN_MERGE_LEFT:
                    leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    contentService= new ContentServiceImpl();
                    contentService.merge(rightModel,leftModel);
                    if(!setNextBlockIndex())setPrevBlockIndex();
                    break;
                case ACTION_BTN_MERGE_RIGHT:
                    leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    contentService= new ContentServiceImpl();
                    contentService.merge(leftModel,rightModel);
                    if(!setNextBlockIndex())setPrevBlockIndex();
                    break;
                case ACTION_BTN_UPPER:
                    setPrevBlockIndex();
                    break;
                case ACTION_BTN_LOWER:
                    setNextBlockIndex();
                    break;
            }
        }
        private CenterControllerActionListener(DataId dataId){
            this.actionSubjectId = dataId;
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
        private void setPrevBlockIndex(){
            ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");;
            for(int i = centerModel.getCompareBlockIndex()-1;i>=0 ;i--){
                if(leftModel.getContentsBlock().get(i).isEqualString())continue;
                centerModel.setCompareBlockIndex(i);
                break;
            }
        }
    }
}
