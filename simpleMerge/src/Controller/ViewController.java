package Controller;

import Data.DataId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ParkHaeSung on 2017-06-05.
 */
public abstract class ViewController implements Controller<ActionListener> {
    @Override
    public ActionListener getEventListener(DataId id) {
        return new ViewerActionListener(id);
    }

    @Override
    public ActionListener getEventListener(DataId id, Object extraData) {
        return new ViewerActionListener(id,extraData);
    }

    private class ViewerActionListener implements ActionListener{
        private DataId actionId;
        private Object extraData;

        @Override
        public void actionPerformed(ActionEvent e) {
            onEventLoad(actionId,extraData);
        }
        ViewerActionListener(DataId actionId){
            this.actionId = actionId;
        }
        ViewerActionListener(DataId actionId,Object extraData){
            this.actionId = actionId;
            this.extraData = extraData;
        }
    }

    public abstract void onEventLoad(DataId id,Object extraData);
}
