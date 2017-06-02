package View;

import Controller.Controller;
import Data.DataId;

import Observer.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class TopMenuPanel extends JPanel implements Observer{

    JLabel lb_diffLine =new JLabel("Different Line");
    JButton btn_newProject =new JButton(new ImageIcon("img/white_new.png"));
    JButton btn_saveAll =new JButton(new ImageIcon("img/white_saveAll.png"));
    JButton btn_leftAll =new JButton(new ImageIcon("img/white_left_all.png"));
    JButton btn_rightAll =new JButton(new ImageIcon("img/white_right_all.png"));
    Font f1;
    Controller controller;
    TopMenuPanel(Controller controller){
        this.controller = controller;
        setLayout(null);
        btn_newProject.setSize(80,80);
        btn_newProject.setLocation(0,10);
        btn_newProject.setOpaque(false);
        btn_newProject.setContentAreaFilled(false);
        btn_newProject.setBorderPainted(false);
        btn_newProject.addActionListener(controller.getActionListener(DataId.ACTION_BTN_NEW_PROJECT));
        add(btn_newProject);
        //lb_diffLine.setSize(100,100);

        btn_saveAll.setSize(80,80);
        btn_saveAll.setLocation(100, 10);
        btn_saveAll.setOpaque(false);
        btn_saveAll.setContentAreaFilled(false);
        btn_saveAll.setBorderPainted(false);
        btn_saveAll.addActionListener(controller.getActionListener(DataId.ACTION_BTN_SAVE_ALL));
        add(btn_saveAll);

        btn_leftAll.setSize(80,80);
        btn_leftAll.setLocation(200, 10);
        btn_leftAll.setOpaque(false);
        btn_leftAll.setContentAreaFilled(false);
        btn_leftAll.setBorderPainted(false);
        btn_leftAll.addActionListener(controller.getActionListener(DataId.ACTION_BTN_LEFT_ALL));
        add(btn_leftAll);

        btn_rightAll.setSize(80,80);
        btn_rightAll.setLocation(300, 10);
        btn_rightAll.setOpaque(false);
        btn_rightAll.setContentAreaFilled(false);
        btn_rightAll.setBorderPainted(false);
        btn_rightAll.addActionListener(controller.getActionListener(DataId.ACTION_BTN_RIGHT_ALL));
        add(btn_rightAll);

        lb_diffLine.setSize(200,20);
        lb_diffLine.setLocation(1100,70);
        f1=new Font("Arial Black", Font.BOLD, 20);
        lb_diffLine.setForeground(Color.WHITE);
        lb_diffLine.setFont(f1);
        add(lb_diffLine);

        setSize(250,250);
        //setBackground(new Color(244, 158, 186));
        setBackground(new Color(0,191,255));
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1350,100);
    }

    @Override
    public void updateView(UpdateEvent updateEvent) {
        switch(updateEvent.getId()){
            case UPDATE_TOP_CAN_SAVE_ALL:
                if((Boolean)updateEvent.getObject()){
                    btn_saveAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_saveAll.setEnabled(true);
                }
                else{
                    btn_saveAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_saveAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_CAN_LEFT_ALL:
                if((Boolean)updateEvent.getObject()){
                    btn_leftAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_leftAll.setEnabled(true);
                }
                else{
                    btn_leftAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_leftAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_CAN_RIGHT_ALL:
                if((Boolean)updateEvent.getObject()){
                    btn_rightAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_rightAll.setEnabled(true);
                }
                else{
                    btn_rightAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_rightAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_DIFF_LINE:
                lb_diffLine.setText("diff line : "+((Integer)updateEvent.getObject()));

                /*
                if((Boolean)updateEvent.getObject()){
                    btn_saveAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_saveAll.setEnabled(true);
                }
                else{
                    btn_saveAll.setIcon(new ImageIcon("img/white_saveAll.png"));
                    btn_saveAll.setEnabled(false);
                }
                */
                break;
        }
    }
}
