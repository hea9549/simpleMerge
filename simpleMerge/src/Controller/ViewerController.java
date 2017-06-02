package Controller;

import Data.*;
import Model.ViewerModel;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Data.DataId.*;

import static Data.DataId.*;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerController implements Controller {
    private ViewerModel model;
    public ViewerController(ViewerModel model) {
        this.model = model;
    }

    @Override
    public ActionListener getActionListener(DataId id) {
        return new ViewerPanelActionListener(id);
    }

    private class ViewerPanelActionListener implements ActionListener {
        private DataId actionId;

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionId) {
                case ACTION_VIEWER_BTN_EDIT:
                    break;
                case ACTION_VIEWER_BTN_LOAD:
                    FileService fileService = new TextFileService();
                    ArrayList<ComparableString> contents = fileService.getContents(fileService.getFilePath());
                    ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
                    if(contents!= null){
                        comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
                    }
                    model.setContentsBlock(comparableBlocks);
                    break;
                case ACTION_VIEWER_BTN_SAVE:
                    break;

            }
        }

        public ViewerPanelActionListener(DataId actionId) {
            this.actionId = actionId;
        }
    }
}
