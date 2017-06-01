package View;

import Observer.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-15.
 */
public class MainFrame extends JFrame implements Observer{
    private LeftViewerPanel leftViewer;
    private RightViewerPanel rightViewer;
    private TopMenuPanel topMenuPanel;
    private CenterMenuPanel centerMenuPanel;
    private JButton btn = new JButton("버튼 아이디 1");
    public MainFrame(){
        leftViewer = new LeftViewerPanel();
        rightViewer = new RightViewerPanel();
        topMenuPanel = new TopMenuPanel();
        centerMenuPanel = new CenterMenuPanel();
        setLayout(new BorderLayout());
        setBounds(100,0,1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(topMenuPanel,BorderLayout.NORTH);
        add(centerMenuPanel,BorderLayout.CENTER);
        add(leftViewer,BorderLayout.WEST);
        add(rightViewer,BorderLayout.EAST);
    }
    public LeftViewerPanel getLeftViewer() {
        return leftViewer;
    }

    public RightViewerPanel getRightViewer() {
        return rightViewer;
    }

    public TopMenuPanel getTopMenuPanel(){ return topMenuPanel;}
    public CenterMenuPanel getCenterMenuPanel() { return centerMenuPanel; }


    @Override
    public void updateView(UpdateEvent updateEvent) {

    }
}
