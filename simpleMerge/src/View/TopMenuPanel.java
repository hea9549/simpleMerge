package View;

import Controller.ViewController;
import Data.DataId;

import Observer.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class TopMenuPanel extends JPanel implements Observer{

    JLabel lb_diffLine =new JLabel("Diff Line");

    JButton btn_saveAll =new JButton(new ImageIcon("img/pink_saveAll.png"));
    JButton btn_leftAll =new JButton(new ImageIcon("img/pink_leftAll.png"));
    JButton btn_rightAll =new JButton(new ImageIcon("img/pink_rightAll.png"));

    Font f1;
    ViewController controller;

    TopMenuPanel(ViewController controller){
        this.controller = controller;
        setLayout(null);
        btn_saveAll.setDisabledIcon(new ImageIcon("img/gray_saveAll.png"));
        btn_leftAll.setDisabledIcon(new ImageIcon("img/gray_leftAll.png"));
        btn_rightAll.setDisabledIcon(new ImageIcon("img/gray_rightAll.png"));

        btn_saveAll.setSize(80,80);
        btn_saveAll.setLocation(0, 10);
        btn_saveAll.setOpaque(false);
        btn_saveAll.setContentAreaFilled(false);
        btn_saveAll.setBorderPainted(false);
        btn_saveAll.addActionListener(controller.getEventListener(DataId.ACTION_BTN_SAVE_ALL));
        add(btn_saveAll);

        btn_leftAll.setSize(80,80);
        btn_leftAll.setLocation(120, 10);
        btn_leftAll.setOpaque(false);
        btn_leftAll.setContentAreaFilled(false);
        btn_leftAll.setBorderPainted(false);
        btn_leftAll.addActionListener(controller.getEventListener(DataId.ACTION_BTN_LEFT_ALL));
        add(btn_leftAll);

        btn_rightAll.setSize(80,80);
        btn_rightAll.setLocation(240, 10);
        btn_rightAll.setOpaque(false);
        btn_rightAll.setContentAreaFilled(false);
        btn_rightAll.setBorderPainted(false);
        btn_rightAll.addActionListener(controller.getEventListener(DataId.ACTION_BTN_RIGHT_ALL));
        add(btn_rightAll);

        lb_diffLine.setSize(200,20);
        lb_diffLine.setLocation(1100,70);
        f1=new Font("Arial Black", Font.BOLD, 20);
        lb_diffLine.setForeground(Color.DARK_GRAY);
        lb_diffLine.setFont(f1);
        add(lb_diffLine);

        setSize(250,250);
        setBackground(new Color(254, 122, 165));
        //setBackground(new Color(68, 193, 195));
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
                    btn_saveAll.setEnabled(true);
                }
                else{
                    btn_saveAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_CAN_LEFT_ALL:
                if((Boolean)updateEvent.getObject()){
                    btn_leftAll.setEnabled(true);
                }
                else{
                    btn_leftAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_CAN_RIGHT_ALL:
                if((Boolean)updateEvent.getObject()){
                    btn_rightAll.setEnabled(true);
                }
                else{
                    btn_rightAll.setEnabled(false);
                }
                break;
            case UPDATE_TOP_DIFF_LINE:
                lb_diffLine.setText("Diff Line : " + ((Integer)updateEvent.getObject()));
                break;

        }
    }
}
