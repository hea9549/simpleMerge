package View;

import Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class TopMenuPanel extends JPanel implements Observer{
    TopMenuPanel(){
        setBackground(Color.CYAN);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1500,200);
    }

    @Override
    public void update(Map<Integer, Object> data) {

    }
}
