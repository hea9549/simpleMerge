package View;

import Data.DataId;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-15.
 */
public class MainFrame extends JFrame implements Observer{
    private ViewerPanel leftViewer,rightViewer;
    private TopMenuPanel topMenuPanel;
    private CenterMenuPanel centerMenuPanel;
    private JButton btn = new JButton("버튼 아이디 1");
    public MainFrame(){
        leftViewer = new ViewerPanel();
        rightViewer = new ViewerPanel();
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
    public ViewerPanel getLeftViewer() {
        return leftViewer;
    }

    public ViewerPanel getRightViewer() {
        return rightViewer;
    }

    @Override
    public void update(Map<Integer, Object> data) {
        Iterator<Integer> keyIterator = data.keySet().iterator();
        while (keyIterator.hasNext()){
            int key;
            switch (key = keyIterator.next()){
                case DataId.UPDATE_LEFT_CONTENT:
                    //컨텐츠 데이터세팅
                    System.out.println("아 들어온데이터는요 !! = "+data.get(key));
                    break;
                default:
                    System.out.println("옵저버 로 부터 알 수없는 데이터 받음 = "+data.get(key));
            }
        }
    }
}
