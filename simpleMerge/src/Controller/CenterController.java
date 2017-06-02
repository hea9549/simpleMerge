package Controller;

import Data.ComparableBlock;
import Data.ContentService;
import Data.ContentServiceImpl;
import Data.DataId;
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
        return null;
    }

    private class CenterControllerActionListener implements ActionListener{
        private DataId actionSubjectId;
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionSubjectId){
                case ACTION_BTN_COMPARE:
                    ContentService contentService = new ContentServiceImpl();
                    ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    ViewerModel rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    ArrayList<ComparableBlock>[] compareResult = contentService.compare(leftModel.getContentsBlock(),rightModel.getContentsBlock());
                    leftModel.setContentsBlock(compareResult[0]);
                    rightModel.setContentsBlock(compareResult[1]);
                    break;
            }
        }
    }
}
