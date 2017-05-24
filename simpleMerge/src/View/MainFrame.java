package View;

import Controller.MainController;
import Data.DataId;
import Observer.MainFrameObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-15.
 */
public class MainFrame extends JFrame implements MainFrameObserver{
    private ViewerPanel leftViewer,rightViewer;
    private JButton btn = new JButton("버튼 아이디 1");
    public MainFrame(){
        leftViewer = new ViewerPanel();
        rightViewer = new ViewerPanel();
        setLayout(new BorderLayout());
        setBounds(100,100,300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(btn,BorderLayout.CENTER);
        btn.addActionListener(new MainController.MainViewActionListener().setActionSubjectId(DataId.BTN_TEST));
    }
    public ViewerPanel getLeftViewer() {
        return leftViewer;
    }

    public ViewerPanel getRightViewer() {
        return rightViewer;
    }

    @Override
    public void updateContents(String contents) {

    }
}
