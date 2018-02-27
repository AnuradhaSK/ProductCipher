package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    public void writeFileLines(String fileName, List<String> lines) throws IOException {
        File fileToWrite = new File(fileName);

        for (String line : lines) {
            /*if (!fileToWrite.exists()) {
                fileToWrite.createNewFile();
            }*/
            java.io.FileWriter fw = new java.io.FileWriter(fileToWrite.getAbsoluteFile(),true);

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line);
            bw.newLine();
            bw.close();
        }
    }
}
