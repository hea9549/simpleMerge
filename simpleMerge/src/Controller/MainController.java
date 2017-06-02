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

/**
 * @author ParkHaeSung
 * @since 2017-05-24
 * */
public class MainController implements Controller{
    private ViewerController leftViewerController;
    private ViewerController rightViewerController;
    private MainModel mainModel;
    private MainFrame mainFrame;
    public MainController(){
        initProgramModel();
        this.leftViewerController = new ViewerController((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel"));
        this.rightViewerController = new ViewerController((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel"));
        this.mainFrame = new MainFrame(this,leftViewerController, rightViewerController);
        ((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).addObserver(mainFrame.getLeftViewer());
        ((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).addObserver(mainFrame.getRightViewer());
        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).addObserver(mainFrame.getCenterMenuPanel());
        ((TopModel)ModelProvider.getInstance().getModel("topModel")).addObserver(mainFrame.getTopMenuPanel());
        this.mainModel = new MainModel();
        mainModel.addObserver(mainFrame);
        ModelProvider.getInstance().registerModel("mainModel",mainModel);

    }

    public static void main(String[] args){
        new MainController();
    }

    @Override
    public ActionListener getActionListener(int id) {
        return new MainViewActionListener(id);
    }

    public class MainViewActionListener implements ActionListener {
        private int actionSubjectId = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("아이디 = "+actionSubjectId);
            switch (actionSubjectId){
                case DataId.ACTION_BTN_COMPARE:
                    ContentService contentService = new ContentServiceImpl();
                    ViewerModel leftModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    ViewerModel rightModel = (ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel");
                    ArrayList<ComparableBlock>[] compareResult = contentService.compare(leftModel.getContentsBlock(),rightModel.getContentsBlock());
                    leftModel.setContentsBlock(compareResult[0]);
                    rightModel.setContentsBlock(compareResult[1]);
                    break;
                default:
                    break;
            }
        }

        public MainViewActionListener(int actionId){
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
