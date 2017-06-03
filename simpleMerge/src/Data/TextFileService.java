package Data;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-23.
 */
public class TextFileService implements FileService {

    @Override
    public ArrayList<ComparableString> getContents(String filePath) {
        File file = new File(filePath);
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
    public String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile().getPath();
    }

    @Override
    public void saveFile(File fileToSave, ArrayList<String> contentsToSave) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(fileToSave));

            for (int i = 0; i < contentsToSave.size(); i++) {
                bw.write(contentsToSave.get(i));
                bw.newLine();
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
