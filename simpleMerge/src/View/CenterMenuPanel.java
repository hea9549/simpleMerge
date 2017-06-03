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

    JButton btn_right=new JButton(new ImageIcon("img/gray_right.png"));
    JButton btn_left=new JButton(new ImageIcon("img/gray_left.png"));
    JButton btn_up=new JButton(new ImageIcon("img/gray_up.png"));
    JButton btn_down=new JButton(new ImageIcon("img/gray_down.png"));
    JButton btn_compare=new JButton(new ImageIcon("img/gray_compare.png"));

    Controller controller;
    CenterMenuPanel(Controller controller){
        setLayout(null);
        this.controller = controller;
        btn_compare.setDisabledIcon(new ImageIcon("img/gray_compare.png"));

        btn_right.setSize(50,50);
        btn_right.setLocation(40, 80);
        btn_right.setOpaque(false);
        btn_right.setContentAreaFilled(false);
        btn_right.setBorderPainted(false);
        btn_right.addActionListener(controller.getActionListener(DataId.ACTION_BTN_MERGE_RIGHT));
        add(btn_right);


        btn_left.setSize(50,50);
        btn_left.setLocation(40, 180);
        btn_left.setOpaque(false);
        btn_left.setContentAreaFilled(false);
        btn_left.setBorderPainted(false);
        btn_left.addActionListener(controller.getActionListener(DataId.ACTION_BTN_MERGE_LEFT));
        add(btn_left);

        btn_up.setSize(50,50);
        btn_up.setLocation(40, 280);
        btn_up.setOpaque(false);
        btn_up.setContentAreaFilled(false);
        btn_up.setBorderPainted(false);
        btn_up.addActionListener(controller.getActionListener(DataId.ACTION_BTN_UPPER));
        add(btn_up);

        btn_down.setSize(50,50);
        btn_down.setLocation(40, 380);
        btn_down.setOpaque(false);
        btn_down.setContentAreaFilled(false);
        btn_down.setBorderPainted(false);
        btn_down.addActionListener(controller.getActionListener(DataId.ACTION_BTN_LOWER));
        add(btn_down);

        btn_compare.setSize(100,100);
        btn_compare.setLocation(20, 480);
        btn_compare.setOpaque(false);
        btn_compare.setContentAreaFilled(false);
        btn_compare.setBorderPainted(false);
        btn_compare.addActionListener(controller.getActionListener(DataId.ACTION_BTN_COMPARE));
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
                    btn_right.setIcon(new ImageIcon("img/right.png"));
                    btn_right.setEnabled(true);
                }
                else{
                    btn_right.setIcon(new ImageIcon("img/gray_right.png"));
                    btn_right.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LEFT_MERGE:
                if((Boolean)updateEvent.getObject()){
                    btn_left.setIcon(new ImageIcon("img/left.png"));
                    btn_left.setEnabled(true);
                }
                else{
                    btn_left.setIcon(new ImageIcon("img/gray_left.png"));
                    btn_left.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_UPPER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_up.setIcon(new ImageIcon("img/up.png"));
                    btn_up.setEnabled(true);
                }
                else{
                    btn_up.setIcon(new ImageIcon("img/gray_up.png"));
                    btn_up.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_LOWER_BLOCK:
                if((Boolean)updateEvent.getObject()){
                    btn_down.setIcon(new ImageIcon("img/down.png"));
                    btn_down.setEnabled(true);
                }
                else{
                    btn_down.setIcon(new ImageIcon("img/gray_down.png"));
                    btn_down.setEnabled(false);
                }
                break;
            case UPDATE_CENTER_CAN_COMPARE:
                System.out.println("컴페어가능여부가 도착했어요! ="+(Boolean)updateEvent.getObject());
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
