package View;

import Model.ViewerModel;
import Observer.Observer;

import javax.swing.*;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements Observer {

    ViewerModel mainModel;

    @Override
    public void update(Map<Integer, String> data) {

    }
}
