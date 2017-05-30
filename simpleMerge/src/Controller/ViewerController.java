package Controller;

import Model.ModelProvider;
import Model.ViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerController {
    private ViewerModel model;

    public ViewerController(){
        this.model = (ViewerModel) ModelProvider.getInstance().getModel("viewerModel");
    }

    public class ViewerPanelActionListener implements ActionListener{
        private int actionId = 0;
        @Override
        public void actionPerformed(ActionEvent e) {

        }
        public ViewerPanelActionListener(int actionId){
            this.actionId = actionId;
        }
    }
}
