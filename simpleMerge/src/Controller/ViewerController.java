package Controller;

import Data.*;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
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
                    }

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
        public ViewerPanelActionListener(DataId actionId,Object extraData){
            this.actionId = actionId;
            this.extraData = extraData;
        }
    }
}
