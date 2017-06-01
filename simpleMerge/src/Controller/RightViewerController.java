package Controller;

import Data.*;
import Model.ViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class RightViewerController {
    private static ViewerModel model;

    public RightViewerController(ViewerModel model) {
        this.model = model;
    }

    public static class ViewerPanelActionListener implements ActionListener {
        private int actionId = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionId) {
                case DataId.ACTION_VIEWER_BTN_EDIT:
                    break;
                case DataId.ACTION_VIEWER_BTN_LOAD:
                    FileService fileService = new TextFileService();
                    ArrayList<ComparableString> contents = fileService.getContents(fileService.getFilePath());
                    ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
                    if(contents!= null){
                        comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
                    }
                    model.setContentsBlock(comparableBlocks);
                    break;
                case DataId.ACTION_VIEWER_BTN_SAVE:
                    break;
            }
        }

        public ViewerPanelActionListener(int actionId) {
            this.actionId = actionId;
        }
    }
}
