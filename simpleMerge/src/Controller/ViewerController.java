package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
import Model.TopModel;
import Model.ViewerModel;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class ViewerController extends ViewController {
    private ViewerModel model;
    public ViewerController(ViewerModel model) {
        this.model = model;
    }

    @Override
    public void onEventLoad(DataId id, Object extraData) {
        switch (id) {
            case ACTION_VIEWER_BTN_EDIT:
                editAction((StyledDocument)extraData);
                break;
            case ACTION_VIEWER_BTN_LOAD:
                loadAction();
                break;
            case ACTION_VIEWER_BTN_SAVE:
                saveAction();
                break;
        }
    }

    private void editAction(StyledDocument viewStringData) {
        if(model.isEditing()){
            ArrayList<ComparableBlock> inputBlock= new ArrayList<ComparableBlock>();
            ComparableBlock comparableBlock = new ComparableBlock(ComparableBlock.DEFAULT);
            try {
                String[] texts =viewStringData.getText(0,viewStringData.getLength()).split("\n");
                for(int i = 0;i<texts.length;i++){
                    comparableBlock.addContents(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent(texts[i]).build());
                }
                inputBlock.add(comparableBlock);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            model.setCanSave(true);
            model.setContentsBlock(inputBlock);
        }else{
            model.setCanSave(false);
        }
        model.setEditing(!model.isEditing());
        checkCanCompare();
    }

    private void loadAction() {
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
    }

    private void saveAction(){
        FileService fileService = new TextFileService();
        model.setFile(fileService.saveFile(model.getFile(), model.getRawData()));
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
