package Controller;

import Data.DataId;

import java.awt.event.ActionListener;

/**
 * Created by ParkHaeSung on 2017-06-05.
 */
public abstract class TestAbstractController implements TestController<ActionListener> {

    private abstract class ViewerActionListener implements ActionListener{
        private DataId actionId;
        private Object extraData;
    }
}
