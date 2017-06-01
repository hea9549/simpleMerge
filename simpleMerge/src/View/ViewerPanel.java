package View;

import Model.ViewerModel;
import Observer.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements Observer {
    ViewerModel mainModel;
    JTextField contents;
    public ViewerPanel(){
        this.setLayout(new BorderLayout());
        /*this.setBounds(0,0,500,500);*/
        contents = new JTextField("내용"){
            @Override
            public void setBorder(Border border) {

            }
        };
        contents.setVisible(true);
        TextField textField;

        add(contents,BorderLayout.CENTER);
        setVisible(true);
    }
    @Override
    public void update(Map<Integer, Object> data) {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
}
