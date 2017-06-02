package View;

import Controller.Controller;
import Controller.MainController;
import Data.DataId;
import Observer.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class CenterMenuPanel extends JPanel implements Observer {

    JButton btn_right=new JButton(new ImageIcon("img/white_right.png"));
    JButton btn_left=new JButton(new ImageIcon("img/white_left.png"));
    JButton btn_up=new JButton(new ImageIcon("img/white_up.png"));
    JButton btn_down=new JButton(new ImageIcon("img/white_down.png"));
    JButton btn_compare=new JButton(new ImageIcon("img/white_compare.png"));

    Controller controller;
    CenterMenuPanel(Controller controller){
        this.controller = controller;

        btn_right.setSize(100,100);
        btn_right.setLocation(18, 70);
        btn_right.setOpaque(false);
        btn_right.setContentAreaFilled(false);
        btn_right.setBorderPainted(false);
        add(btn_right);
        btn_left.setSize(100,100);
        btn_left.setLocation(18, 170);
        btn_left.setOpaque(false);
        btn_left.setContentAreaFilled(false);
        btn_left.setBorderPainted(false);
        add(btn_left);

        btn_up.setSize(100,100);
        btn_up.setLocation(18, 270);
        btn_up.setOpaque(false);
        btn_up.setContentAreaFilled(false);
        btn_up.setBorderPainted(false);
        add(btn_up);

        btn_down.setSize(100,100);
        btn_down.setLocation(18, 370);
        btn_down.setOpaque(false);
        btn_down.setContentAreaFilled(false);
        btn_down.setBorderPainted(false);
        add(btn_down);

        btn_compare.setSize(100,100);
        btn_compare.setLocation(18, 470);
        btn_compare.setOpaque(false);
        btn_compare.setContentAreaFilled(false);
        btn_compare.setBorderPainted(false);
        btn_compare.addActionListener(controller.getActionListener(DataId.ACTION_BTN_COMPARE));
        add(btn_compare);

        setBackground(new Color(188, 181, 182));
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150,700);
    }

    @Override
    public void updateView(UpdateEvent updateEvent) {
        switch(updateEvent.getId()){
            case UPDATE_CENTER_CAN_RIGHT_MERGE:
                if((Boolean)updateEvent.getObject()){
                    btn_right.setIcon(new ImageIcon("img/white_right.png"));
                    btn_right.setEnabled(true);
                }
                else{
                    btn_right.setIcon(new ImageIcon("img/gray_right.png"));
                    btn_right.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LEFT_MERGE:
                if((Boolean)updateEvent.getObject()){
                    btn_left.setIcon(new ImageIcon("img/white_left.png"));
                    btn_left.setEnabled(true);
                }
                else{
                    btn_left.setIcon(new ImageIcon("img/gray_left.png"));
                    btn_left.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_UPPER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_up.setIcon(new ImageIcon("img/white_up.png"));
                    btn_up.setEnabled(true);
                }
                else{
                    btn_up.setIcon(new ImageIcon("img/gray_up.png"));
                    btn_up.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LOWER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_down.setIcon(new ImageIcon("img/white_down.png"));
                    btn_down.setEnabled(true);
                }
                else{
                    btn_down.setIcon(new ImageIcon("img/gray_down.png"));
                    btn_down.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_COMPARE:
                if((Boolean)updateEvent.getObject()){
                    btn_compare.setIcon(new ImageIcon("img/white_compare.png"));
                    btn_compare.setEnabled(true);
                }
                else{
                    btn_compare.setIcon(new ImageIcon("img/gray_compare.png"));
                    btn_compare.setEnabled(false);
                }
                break;
        }
    }


}
