package View;

import Observer.*;

import javax.swing.*;
import java.awt.*;

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
    public void updateView(UpdateEvent updateEvent) {

    }
}
