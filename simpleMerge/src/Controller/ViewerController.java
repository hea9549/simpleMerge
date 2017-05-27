package Controller;

import Model.ViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerController {
    private ViewerModel model;

    public ViewerController(ViewerModel model){
        this.model = model;
    }

    public void setModel(ViewerModel model) {
        this.model = model;
    }

    public class ViewerPanelActionListener implements ActionListener{
        private int actionId = 0;
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        public int getActionId() {
            return actionId;
        }

        public void setActionId(int actionId) {
            this.actionId = actionId;
        }

        public ViewerPanelActionListener setActionSubjectId(int actionId){
            this.actionId = actionId;
            return this;
        }
        public ViewerPanelActionListener build(){
            return this;
        }
    }
}
