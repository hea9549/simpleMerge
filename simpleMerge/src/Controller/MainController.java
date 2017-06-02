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
        this.mainFrame = new MainFrame(this,leftViewerController, rightViewerController,centerController);
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
    public ActionListener getActionListener(DataId id) {
        return new MainViewActionListener(id);
    }

    private class MainViewActionListener implements ActionListener {
        private DataId actionSubjectId;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("아이디 = "+actionSubjectId);
            switch (actionSubjectId){
                case ACTION_BTN_COMPARE:

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
