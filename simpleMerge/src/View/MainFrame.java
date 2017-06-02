package View;

import Controller.Controller;
import Observer.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-15.
 */
public class MainFrame extends JFrame implements Observer{
    private ViewerPanel leftViewer;
    private ViewerPanel rightViewer;
    private TopMenuPanel topMenuPanel;
    private CenterMenuPanel centerMenuPanel;
    private JButton btn = new JButton("버튼 아이디 1");
    private Controller controller;
    public MainFrame(Controller mainController,Controller leftViewerController,Controller rightViewerController){
        this.controller = mainController;
        leftViewer = new ViewerPanel(leftViewerController);
        rightViewer = new ViewerPanel(rightViewerController);
        topMenuPanel = new TopMenuPanel(mainController);
        setBackground(new Color(188, 181, 182));
        centerMenuPanel = new CenterMenuPanel(mainController);
        setLayout(new BorderLayout());
        setBounds(100,0,1350,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(topMenuPanel,BorderLayout.NORTH);
        add(centerMenuPanel,BorderLayout.CENTER);
        add(leftViewer,BorderLayout.WEST);
        add(rightViewer,BorderLayout.EAST);
    }



    public ViewerPanel getLeftViewer() {
        return leftViewer;
    }

    public ViewerPanel getRightViewer() {
        return rightViewer;
    }

    public TopMenuPanel getTopMenuPanel(){ return topMenuPanel;}
    public CenterMenuPanel getCenterMenuPanel() { return centerMenuPanel; }


    @Override
    public void updateView(UpdateEvent updateEvent) {

    }
}
