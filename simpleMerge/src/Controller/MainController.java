package Controller;

import Data.DataId;
import Model.MainModel;
import Model.ModelProvider;
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
    private MainModel mainModel;
    private MainFrame mainFrame;

    public MainController(){
        ViewerModel viewerModel = new ViewerModel();
        viewerModel.addObserver(mainFrame.getLeftViewer());
        viewerModel.addObserver(mainFrame.getRightViewer());
        ModelProvider.getInstance().registerModel("viewerModel",viewerModel);
        this.leftViewerController = new ViewerController();
        this.rightViewController = new ViewerController();
        this.mainFrame = new MainFrame();
        this.mainModel = new MainModel();
        mainModel.addObserver(mainFrame);
        ModelProvider.getInstance().registerModel("mainModel",mainModel);
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

        public MainViewActionListener(int actionId){
            this.actionSubjectId = actionId;
        }
    }

}
