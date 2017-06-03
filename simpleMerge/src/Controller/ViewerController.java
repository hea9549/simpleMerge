package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
import Model.TopModel;
import Model.ViewerModel;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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

    @Override
    public ActionListener getActionListener(DataId id, Object extraData) {
        return new ViewerPanelActionListener(id,extraData);
    }

    private class ViewerPanelActionListener implements ActionListener {
        private DataId actionId;
        private Object extraData;
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionId) {
                case ACTION_VIEWER_BTN_EDIT:
                    if(model.isEditing()){
                        StyledDocument styledDocument = (StyledDocument)extraData;
                        ArrayList<ComparableBlock> inputBlock= new ArrayList<ComparableBlock>();
                        ComparableBlock comparableBlock = new ComparableBlock(ComparableBlock.DEFAULT);
                        try {
                            String[] texts =styledDocument.getText(0,styledDocument.getLength()).split("\n");
                            for(int i = 0;i<texts.length;i++){
                                comparableBlock.addContents(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent(texts[i]).build());
                            }
                            inputBlock.add(comparableBlock);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        model.setContentsBlock(inputBlock);
                    }
                    model.setEditing(!model.isEditing());
                    checkCanCompare();
                    break;
                case ACTION_VIEWER_BTN_LOAD:
                    File file;
                    FileService fileService = new TextFileService();
                    ArrayList<ComparableString> contents = fileService.getContents(file = fileService.getFilePath());
                    model.setFile(file);
                    ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
                    if(contents!= null){
                        comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
                    }
                    model.setContentsBlock(comparableBlocks);
                    checkCanCompare();
                    break;
                case ACTION_VIEWER_BTN_SAVE:
                    ArrayList<String> stringToSave = new ArrayList<>();
                    fileService = new TextFileService();

                    for(int i = 0; i< model.getContentsBlock().size(); i++){
                        for(int j = 0; j<model.getContentsBlock().get(i).getContents().size(); j++) {
                            stringToSave.add(model.getContentsBlock().get(i).getContents(j).getContentString());
                        }
                    }
                    model.setFile(fileService.saveFile(model.getFile(), stringToSave));
                    break;

            }
        }

        public ViewerPanelActionListener(DataId actionId) {
            this.actionId = actionId;
        }
        public ViewerPanelActionListener(DataId actionId,Object extraData){
            this.actionId = actionId;
            this.extraData = extraData;
        }
    }

    private void checkCanCompare() {
        if(!((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).isEditing()
                &&!((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).isEditing()
                &&((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).getContentsBlock()!=null
                &&((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).getContentsBlock()!=null){
            ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanCompare(true);
            ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanLeftAll(true);
            ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanRightAll(true);
        }else{
            ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanCompare(false);

            ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanLeftAll(false);
            ((TopModel)ModelProvider.getInstance().getModel("topModel")).setCanRightAll(false);
        }

    }
}
