package Service;

import Data.ComparableString;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class TextFileService implements FileService {

    @Override
    public ArrayList<ComparableString> getContents(File file) {
        ArrayList<ComparableString> contents = new ArrayList<>();
        if (!file.isFile()) {
            JOptionPane.showMessageDialog(null, "File Select Error. please reselect File", "error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s;
            while ((s = in.readLine()) != null) {
                contents.add(new ComparableString.Builder().setFlags(ComparableString.DEFAULT).setContent(s).build());
            }
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File Select Error. please reselect File", "error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return contents;

    }

    @Override
    public File getFilePath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));
        chooser.setMultiSelectionEnabled(false);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile();
    }

    @Override
    public File saveFile(File fileToSave, ArrayList<String> contentsToSave) {
        return saveFile(fileToSave,contentsToSave,"");
    }

    @Override
    public File saveFile(File fileToSave, ArrayList<String> contentsToSave, String optionTitleString) {
        BufferedWriter bw = null;
        if(fileToSave == null){
            Object options[]={"예","아니오"};
            if(JOptionPane.showOptionDialog(null,optionTitleString+"파일이 존재하지 않습니다. 생성하여 저장 하시겠습니까?","경고"+optionTitleString,
                    JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]) !=0)
                return fileToSave;
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("C:\\"));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));
            chooser.setMultiSelectionEnabled(false);
            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return fileToSave;
            File newFile = chooser.getSelectedFile();
            if (newFile.exists()) { //파일 이름이 이미 존재하면 덮어쓸 것인지 물어본다
                if(JOptionPane.showOptionDialog(null,"파일이 이미 존재 합니다. 덮어 쓰시겠습니까?","경고",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]) !=0)
                    return fileToSave ;//아니오를 선택하면 저장취소
                fileToSave =newFile;
            }else{
                int dotLength = newFile.getPath().split(".").length-1;
                if(dotLength == -1){
                    fileToSave = new File(newFile.getPath()+".txt");
                }else if(!newFile.getPath().split(".")[dotLength].equals("txt"))
                    fileToSave = new File(newFile.getPath()+".txt");
            }

        }

        try {
            bw = new BufferedWriter(new FileWriter(fileToSave));

            for (int i = 0; i < contentsToSave.size(); i++) {
                bw.write(contentsToSave.get(i));
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    fileToSave.getName() + "파일을 생성 할 수 없습니다.",
                    "파일 저장 에러", JOptionPane.ERROR_MESSAGE);
        }

        return fileToSave;
    }
}
