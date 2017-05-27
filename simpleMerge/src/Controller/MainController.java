package Controller;

import Data.DataId;
import Model.MainModel;
import Model.ViewerModel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ParkHaeSung
 * @since 2017-05-24
 * */
public class MainController {
    private ViewerController leftViewerController,rightViewController;
    private ViewerModel leftViewerModel,rightViewerModel;
    private MainModel mainModel;
    private MainFrame mainFrame;

    public MainController(){
        this.leftViewerModel = new ViewerModel();
        this.rightViewerModel = new ViewerModel();
        this.leftViewerController = new ViewerController(leftViewerModel);
        this.rightViewController = new ViewerController(rightViewerModel);
        this.mainFrame = new MainFrame();
        this.mainModel = new MainModel();
        leftViewerModel.addObserver(mainFrame.getLeftViewer());
        rightViewerModel.addObserver(mainFrame.getRightViewer());
        mainModel.addObserver(mainFrame);
    }

    public MainController(MainModel mainModel){
        this.leftViewerModel = new ViewerModel();
        this.rightViewerModel = new ViewerModel();
        this.leftViewerController = new ViewerController(leftViewerModel);
        this.rightViewController = new ViewerController(rightViewerModel);
        this.mainFrame = new MainFrame();
        this.mainModel = mainModel;
        leftViewerModel.addObserver(mainFrame.getLeftViewer());
        rightViewerModel.addObserver(mainFrame.getRightViewer());
        this.mainModel.addObserver(mainFrame);
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
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
                    System.out.println("테스트 버튼이 눌렸습니당");
                    break;
                default:
                    break;
            }
        }

        public int getActionSubjectId() {
            return actionSubjectId;
        }

        public MainViewActionListener setActionSubjectId(int actionSubjectId) {
            this.actionSubjectId = actionSubjectId;
            return this;
        }

        public MainViewActionListener build(){
            return this;
        }
    }

}
