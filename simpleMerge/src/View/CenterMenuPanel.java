package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-05-29.
 */
public class CenterMenuPanel extends JPanel {
    CenterMenuPanel(){
        setBackground(Color.BLUE);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,800);
    }
}
