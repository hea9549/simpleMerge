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

    JButton right=new JButton(new ImageIcon("img/white_right.png"));
    JButton left=new JButton(new ImageIcon("img/white_left.png"));
    JButton up=new JButton(new ImageIcon("img/white_up.png"));
    JButton down=new JButton(new ImageIcon("img/white_down.png"));
    JButton compare=new JButton(new ImageIcon("img/white_compare.png"));
    Controller controller;
    CenterMenuPanel(Controller controller){
        this.controller = controller;
        right.setSize(100,95);
        right.setLocation(700, 180);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);
        add(right);

        left.setSize(100,95);
        left.setLocation(700, 280);
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        add(left);

        up.setSize(100,95);
        up.setLocation(700, 380);
        up.setOpaque(false);
        up.setContentAreaFilled(false);
        up.setBorderPainted(false);
        add(up);

        down.setSize(100,95);
        down.setLocation(700, 480);
        down.setOpaque(false);
        down.setContentAreaFilled(false);
        down.setBorderPainted(false);
        add(down);

        compare.setSize(100,95);
        compare.setLocation(700, 580);
        compare.setOpaque(false);
        compare.setContentAreaFilled(false);
        compare.setBorderPainted(false);
        compare.addActionListener(controller.getActionListener(DataId.ACTION_BTN_COMPARE));
        add(compare);

        setBackground(new Color(220, 210, 213));
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,800);
    }

    @Override
    public void updateView(UpdateEvent updateEvent) {

    }
}
