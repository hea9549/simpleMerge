package Controller;

import Model.ViewerModel;

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
}
