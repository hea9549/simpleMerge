package Controller;

import Data.DataId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Data.DataId.*;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class CenterController implements Controller {
    @Override
    public ActionListener getActionListener(DataId id) {
        return null;
    }

    private class CenterControllerActionListener implements ActionListener{
        private DataId actionSubjectId;
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionSubjectId){
                case ACTION_BTN_COMPARE:
                    break;
            }
        }
    }
}
