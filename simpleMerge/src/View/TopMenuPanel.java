package View;

import Observer.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class TopMenuPanel extends JPanel implements Observer{
    /*
    JButton newProject=new JButton(new ImageIcon("img/new_active.png"));
    JButton saveAll=new JButton(new ImageIcon("img/save_active.png"));
    JButton leftAll=new JButton(new ImageIcon("img/left_all_active.png"));
    JButton rightAll=new JButton(new ImageIcon("img/right_all_active.png"));
    */
    JLabel diffLine=new JLabel("Different Line");
    JButton newProject=new JButton(new ImageIcon("img/white_new.png"));
    JButton saveAll=new JButton(new ImageIcon("img/white_save.png"));
    JButton leftAll=new JButton(new ImageIcon("img/white_left_all.png"));
    JButton rightAll=new JButton(new ImageIcon("img/white_right_all.png"));
    Font f1;
    TopMenuPanel(){

        setLayout(null);
        newProject.setSize(80,80);
        newProject.setLocation(0,10);
        newProject.setOpaque(false);
        newProject.setContentAreaFilled(false);
        newProject.setBorderPainted(false);
        add(newProject);
        //diffLine.setSize(100,100);

        saveAll.setSize(80,80);
        saveAll.setLocation(100, 10);
        saveAll.setOpaque(false);
        saveAll.setContentAreaFilled(false);
        saveAll.setBorderPainted(false);
        add(saveAll);
        leftAll.setSize(80,80);
        leftAll.setLocation(200, 10);
        leftAll.setOpaque(false);
        leftAll.setContentAreaFilled(false);
        leftAll.setBorderPainted(false);
        add(leftAll);
        rightAll.setSize(80,80);
        rightAll.setLocation(300, 10);
        rightAll.setOpaque(false);
        rightAll.setContentAreaFilled(false);
        rightAll.setBorderPainted(false);
        add(rightAll);

        diffLine.setSize(200,20);
        diffLine.setLocation(1150,70);
        f1=new Font("Arial Black", Font.BOLD, 20);
        diffLine.setForeground(Color.WHITE);
        diffLine.setFont(f1);
        add(diffLine);

        setSize(250,250);
        setBackground(new Color(240, 173, 203));
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1500,100);
    }


    @Override
    public void updateView(UpdateEvent updateEvent) {

    }
}
