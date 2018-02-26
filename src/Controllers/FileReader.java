package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<String> readFileLines(File file) throws FileNotFoundException, IOException {

        java.io.FileReader fr = new java.io.FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> returnList = new ArrayList();

        String currentLine;
        while((currentLine= br.readLine()) != null){
            returnList.add(currentLine);
            System.out.println(currentLine);
        }

        return returnList;
    }
}
