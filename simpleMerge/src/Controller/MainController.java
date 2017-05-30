package Controller;

import Data.DataId;
import Model.*;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ParkHaeSung
 * @since 2017-05-24
 * */
public class MainController {
    private ViewerController leftViewerController,rightViewController;
    private MainModel mainModel;
    private MainFrame mainFrame;

    public MainController(){

        this.mainFrame = new MainFrame();
        this.mainModel = new MainModel();
        this.leftViewerController = new ViewerController();
        this.rightViewController = new ViewerController();
        mainModel.addObserver(mainFrame);
        ModelProvider.getInstance().registerModel("mainModel",mainModel);
        initProgram();
    }

    public static void main(String[] args){
        new MainController();
    }
    public static class MainViewActionListener implements ActionListener {
        private int actionSubjectId = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("아이디 = "+actionSubjectId);
            switch (actionSubjectId){
                case 0:
                    break;
                case DataId.BTN_TEST:
                    break;
                default:
                    break;
            }
        }

        public MainViewActionListener(int actionId){
            this.actionSubjectId = actionId;
        }
    }

    private void initProgram(){
        ViewerModel viewerModel = new ViewerModel();
        viewerModel.addObserver(mainFrame.getLeftViewer());
        viewerModel.addObserver(mainFrame.getRightViewer());
        ModelProvider.getInstance().registerModel("viewerModel",viewerModel);

        TopModel topModel = new TopModel();
        topModel.addObserver(mainFrame.getTopMenuPanel());
        ModelProvider.getInstance().registerModel("topModel",topModel);

        CenterModel centerModel = new CenterModel();
        centerModel.addObserver(mainFrame.getCenterMenuPanel());
        ModelProvider.getInstance().registerModel("centerModel",centerModel);

    }

}
