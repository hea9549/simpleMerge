package Controller;

import Data.*;
import Model.ModelProvider;
import Model.ViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerController {
    private ViewerModel model;

    public ViewerController(ViewerModel model) {
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
                    if(contents!= null){
                        ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
                        comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
                        /*model.setContentsBlock(comparableBlocks);*/
                    }

                    break;
                case DataId.ACTION_VIEWER_BTN_SAVE:
                    break;
                /*case DataId.ACTION_BTN_TEST:
                    ComparableBlock comparableBlock = new ComparableBlock(ComparableBlock.ADDED);
                    ComparableString str1 = new ComparableString.Builder().setContent("str1").setFlags(ComparableString.ADDED).build();
                    ComparableString str2 = new ComparableString.Builder().setContent("str2").setFlags(ComparableString.DIFF).build();
                    comparableBlock.addContents(str1);
                    comparableBlock.addContents(str2);
                    ArrayList<ComparableBlock> blocks = new ArrayList<>();
                    blocks.add(comparableBlock);
                    ViewerModel leftViewerModel = (ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel");
                    leftViewerModel.setContentsBlock(blocks);
                    break;*/
            }
        }

        public ViewerPanelActionListener(int actionId) {
            this.actionId = actionId;
        }
    }
}
