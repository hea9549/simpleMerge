package View;

import Observer.ViewerObserver;
import Model.ViewerModel;

import javax.swing.*;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerPanel extends JPanel implements ViewerObserver {

    ViewerModel mainModel;
    @Override
    public void updateContents(String contents) {

    }
}
