package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decryptor {

    private FileWriter fileWriter = new FileWriter();
    private FileReader fileReader = new FileReader();

    public void decrypt(File file, String secretkey) throws IOException {
        String seckey = keyGenerator(secretkey);
        List<String> lines = this.fileReader.readFileLines(file);
        List<String> decryptedArray = new ArrayList<>();
        for (String line : lines) {
            //System.out.println("Line:"+ line);
            decryptedArray.add(decrypt(line, seckey));
        }
        /*for (String i:decryptedArray){
            System.out.println("decrypted line:"+i);
        }*/
        fileWriter.writeFileLines("decrypted-" + file.getName(), decryptedArray);

    }

    //generate key
    public String keyGenerator(String secretkey) {
        int sum = 0;
        String key = "";
        for (int i = 0; i < secretkey.length(); i++) {
            sum += (int) (secretkey.charAt(i));

        }
        for (int j = 0; j < Config.getBlockSize(); j++) {
            key += (sum % 10);
            sum = sum / 10;
        }
        System.out.println(key);
        return key;
    }

    //decrypt a line
    public String decrypt(String line, String seckey) {
        return PermuteAndsubstitute(line, seckey);
    }

    //permutation and substitution
    public String PermuteAndsubstitute(String line, String seckey) {

        int rows = line.length() / Config.getBlockSize();
        if (line.length() % Config.getBlockSize() != 0) {
            rows++;
        }
        int columns = Config.getBlockSize();
        //System.out.println("Rows:"+rows);
        //System.out.println("Columns:"+columns);

        String[] keyArray = seckey.split("");
        for (int k = 0; k < keyArray.length; k++) {
            System.out.println(keyArray[k]);
        }

        char matrix[][] = new char[rows][columns];

        int position = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (position < line.length()) {
                    matrix[i][j] = (char) ((int) line.charAt(position));
                    //System.out.print(line.charAt( position));
                }

                position++;
            }
            System.out.println();
        }

        for (int i = 0; i < rows; i++) {
            char temp1 = matrix[i][0];
            char temp2 = matrix[i][Config.getBlockSize() - 1];
            matrix[i][Config.getBlockSize() - 1] = temp1;
            matrix[i][0] = temp2;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (char) ((int) matrix[i][j] - Integer.parseInt(keyArray[j]));
                //System.out.print(matrix[i][j]+ "=>"+ (char) ((int)matrix[i][j] - Integer.parseInt(keyArray[j])));

            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                builder.append(matrix[i][j]);
            }
        }


        return builder.toString();
    }


}

