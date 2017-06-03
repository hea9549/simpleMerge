package Controller;

import Data.ComparableBlock;
import Data.ContentService;
import Data.ContentServiceImpl;
import Data.DataId;
import Model.*;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Data.DataId.ACTION_BTN_COMPARE;

/**
 * @author ParkHaeSung
 * @since 2017-05-24
 * */
public class MainController implements Controller{
    private ViewerController leftViewerController;
    private ViewerController rightViewerController;
    private CenterController centerController;
    private MainModel mainModel;
    private MainFrame mainFrame;
    public MainController(){
        initProgramModel();
        this.leftViewerController = new ViewerController((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel"));
        this.rightViewerController = new ViewerController((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel"));
        this.centerController = new CenterController((CenterModel)ModelProvider.getInstance().getModel("centerModel"));
        this.mainFrame = new MainFrame(this, leftViewerController, rightViewerController,centerController);
        ((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).addObserver(mainFrame.getLeftViewer());
        ((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).addObserver(mainFrame.getRightViewer());
        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).addObserver(mainFrame.getCenterMenuPanel());
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).addObserver(mainFrame.getTopMenuPanel());
        ((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).viewerModelInit();
        ((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).viewerModelInit();
        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).initModel();
        this.mainModel = new MainModel();
        mainModel.addObserver(mainFrame);
        ModelProvider.getInstance().registerModel("mainModel",mainModel);

    }
    private boolean setNextBlockIndex(){
        ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
        CenterModel centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");
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
        CenterModel centerModel = (CenterModel)ModelProvider.getInstance().getModel("centerModel");
        for(int i = centerModel.getCompareBlockIndex()-1;i>=0 ;i--){
            if(leftModel.getContentsBlock().get(i).isEqualString())continue;
            centerModel.setCompareBlockIndex(i);
            break;
        }
    }
    public static void main(String[] args){
        new MainController();
    }

    @Override
    public ActionListener getActionListener(DataId id) {
        return new MainViewActionListener(id);
    }

    @Override
    public ActionListener getActionListener(DataId id, Object extraData) {
        return null;
    }

    private class MainViewActionListener implements ActionListener {
        private DataId actionSubjectId;

        @Override
        public void actionPerformed(ActionEvent e) {
            ViewerModel leftModel,rightModel;
            ContentService contentService;
            switch (actionSubjectId){
                case ACTION_BTN_LEFT_ALL:
                    leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    contentService = new ContentServiceImpl();
                    while(contentService.merge(rightModel,leftModel))setNextBlockIndex();
                    break;
                case ACTION_BTN_RIGHT_ALL:
                    leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    contentService = new ContentServiceImpl();
                    while(contentService.merge(leftModel,rightModel))setNextBlockIndex();
                    break;
                case ACTION_BTN_SAVE_ALL:
                    break;
                default:
                    break;
            }
        }

        public MainViewActionListener(DataId actionId){
            this.actionSubjectId = actionId;
        }
    }

    private void initProgramModel(){
        ViewerModel leftViewerModel = new ViewerModel();
        ModelProvider.getInstance().registerModel("leftViewerModel",leftViewerModel);

        ViewerModel rightViewerModel = new ViewerModel();
        ModelProvider.getInstance().registerModel("rightViewerModel",rightViewerModel);

        TopModel topModel = new TopModel();
        ModelProvider.getInstance().registerModel("topModel",topModel);

        CenterModel centerModel = new CenterModel();
        ModelProvider.getInstance().registerModel("centerModel",centerModel);

    }

}
