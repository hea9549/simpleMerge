package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
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
                    System.out.println("에딧버튼 이벤트");
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
                    System.out.println(!((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).isEditing()
                            &&!((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).isEditing());
                    if(!((ViewerModel)ModelProvider.getInstance().getModel("leftViewerModel")).isEditing()
                        &&!((ViewerModel)ModelProvider.getInstance().getModel("rightViewerModel")).isEditing()){
                        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanCompare(true);
                    }else{
                        ((CenterModel)ModelProvider.getInstance().getModel("centerModel")).setCanCompare(false);
                    }

                    break;
                case ACTION_VIEWER_BTN_LOAD:
                    String filePath;
                    FileService fileService = new TextFileService();
                    ArrayList<ComparableString> contents = fileService.getContents(filePath = fileService.getFilePath());
                    model.setFile(new File(filePath));
                    ArrayList<ComparableBlock> comparableBlocks = new ArrayList<>();
                    if(contents!= null){
                        comparableBlocks.add(new ComparableBlock(ComparableBlock.DEFAULT,contents));
                    }
                    model.setContentsBlock(comparableBlocks);
                    break;
                case ACTION_VIEWER_BTN_SAVE:
                    ArrayList<String> stringToSave = new ArrayList<>();
                    fileService = new TextFileService();

                    for(int i = 0; i< model.getContentsBlock().size(); i++){
                        for(int j = 0; j<model.getContentsBlock().get(i).getContents().size(); j++) {
                            stringToSave.add(model.getContentsBlock().get(i).getContents(j).getContentString());
                        }
                    }

                    fileService.saveFile(model.getFile(), stringToSave);

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
}
