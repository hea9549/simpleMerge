package View;

import Controller.ViewController;
import Data.DataId;
import Observer.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class CenterMenuPanel extends JPanel implements Observer {

    JButton btn_right=new JButton(new ImageIcon(getClass().getClassLoader().getResource("right.png")));
    JButton btn_left=new JButton(new ImageIcon(getClass().getClassLoader().getResource("left.png")));
    JButton btn_up=new JButton(new ImageIcon(getClass().getClassLoader().getResource("up.png")));
    JButton btn_down=new JButton(new ImageIcon(getClass().getClassLoader().getResource("down.png")));
    JButton btn_compare=new JButton(new ImageIcon(getClass().getClassLoader().getResource("compare.png")));

    ViewController controller;
    CenterMenuPanel(ViewController controller){
        setLayout(null);
        this.controller = controller;

        btn_right.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("gray_right.png")));
        btn_left.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("gray_left.png")));
        btn_up.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("gray_up.png")));
        btn_down.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("gray_down.png")));
        btn_compare.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("gray_compare.png")));

        btn_right.setSize(50,50);
        btn_right.setLocation(40, 80);
        btn_right.setOpaque(false);
        btn_right.setContentAreaFilled(false);
        btn_right.setBorderPainted(false);
        btn_right.addActionListener(controller.getEventListener(DataId.ACTION_BTN_MERGE_RIGHT));
        add(btn_right);


        btn_left.setSize(50,50);
        btn_left.setLocation(40, 180);
        btn_left.setOpaque(false);
        btn_left.setContentAreaFilled(false);
        btn_left.setBorderPainted(false);
        btn_left.addActionListener(controller.getEventListener(DataId.ACTION_BTN_MERGE_LEFT));
        add(btn_left);

        btn_up.setSize(50,50);
        btn_up.setLocation(40, 280);
        btn_up.setOpaque(false);
        btn_up.setContentAreaFilled(false);
        btn_up.setBorderPainted(false);
        btn_up.addActionListener(controller.getEventListener(DataId.ACTION_BTN_UPPER));
        add(btn_up);

        btn_down.setSize(50,50);
        btn_down.setLocation(40, 380);
        btn_down.setOpaque(false);
        btn_down.setContentAreaFilled(false);
        btn_down.setBorderPainted(false);
        btn_down.addActionListener(controller.getEventListener(DataId.ACTION_BTN_LOWER));
        add(btn_down);

        btn_compare.setSize(100,100);
        btn_compare.setLocation(20, 480);
        btn_compare.setOpaque(false);
        btn_compare.setContentAreaFilled(false);
        btn_compare.setBorderPainted(false);
        btn_compare.addActionListener(controller.getEventListener(DataId.ACTION_BTN_COMPARE));
        add(btn_compare);

        //setBackground(new Color(255, 187, 218));
        setBackground(new Color(241, 185, 199));
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
                    btn_right.setEnabled(true);
                }
                else{
                    btn_right.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LEFT_MERGE:
                if((Boolean)updateEvent.getObject()){
                    btn_left.setEnabled(true);
                }
                else{
                    btn_left.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_UPPER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_up.setEnabled(true);
                }
                else{
                    btn_up.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LOWER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_down.setEnabled(true);
                }
                else{
                    btn_down.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_COMPARE:
                if((Boolean)updateEvent.getObject()){
                    btn_compare.setEnabled(true);
                }
                else{
                    btn_compare.setEnabled(false);
                }
                break;
        }
    }


}
