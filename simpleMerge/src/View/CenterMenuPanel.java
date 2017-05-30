package View;

import Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class CenterMenuPanel extends JPanel implements Observer {
    CenterMenuPanel(){
        setBackground(Color.BLUE);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,800);
    }

    @Override
    public void update(Map<Integer, Object> data) {

    }
}
