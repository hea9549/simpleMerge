package Controller;

import Data.ContentService;
import Data.ContentServiceImpl;
import Data.DataId;
import Model.*;
import View.MainFrame;

/**
 * @author ParkHaeSung
 * @since 2017-05-24
 * */
public class MainController extends ViewController {
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
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).initModel();
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
    public void onEventLoad(DataId id, Object extraData) {
        ViewerModel leftModel,rightModel;
        ContentService contentService;
        switch (id){
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
            default:
                break;
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
