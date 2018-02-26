package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encryptor {

    private FileWriter fileWriter = new FileWriter();
    private FileReader fileReader = new FileReader();

    public void encrypt(File file,String secretkey) throws IOException {
        String seckey=keyGenerator(secretkey);
        List<String> lines= this.fileReader.readFileLines(file);
        List<String> encryptedArray = new ArrayList<>();
        for(String line:lines) {
            System.out.println("Line:"+ line);
            encryptedArray.add(encrypt(line,seckey));
        }
        //TODO write to the file
        for (String i:encryptedArray){
            System.out.println("encrypted line:"+i);
        }
    }

    public String keyGenerator(String secretkey) {
        int sum = 0;
        String key = "";
        for (int i = 0; i < secretkey.length(); i++) {
            sum += (int) (secretkey.charAt(i));

        }
        for (int j = 0; j < Config.getBlockSize(); j++){
            key += (sum % 10);
            sum=sum/10;
        }
        System.out.println(key);
        return key;
    }

    public String encrypt(String line,String seckey){
      return substituteAndPermute(line,seckey);
    }

    public String substituteAndPermute(String line,String seckey){

        int rows=line.length()/Config.getBlockSize();
        if(line.length()%Config.getBlockSize()!=0){
            rows++;
        }
        int columns= Config.getBlockSize();
        System.out.println("Rows:"+rows);
        System.out.println("Columns:"+columns);

        String[] keyArray= seckey.split("");
        for(int k = 0; k<  keyArray.length; k++){
            System.out.println(keyArray[k]);
        }
        char matrix[][]=new char[rows][columns];
        int position=0;
        for (int i=0; i<rows;i++) {
            for (int j = 0; j < columns; j++) {
                if (position < line.length()){
                    matrix[i][j] = (char) ((int) line.charAt( position) + Integer.parseInt(keyArray[j]));
                    System.out.print(line.charAt( position)+ "=>"+ (char) ((int) line.charAt( position) + Integer.parseInt(keyArray[j])));
                }
                else{
                    char character = '*';
                    matrix[i][j] = character;
                }
                position++;
            }
        }

        for(int i=0;i<rows;i++){
            char temp1=matrix[i][0];
            char temp2=matrix[i][Config.getBlockSize()-1];
            matrix[i][Config.getBlockSize()-1]=temp1;
            matrix[i][0]=temp2;

        }

        StringBuilder builder = new StringBuilder();
        for (int i=0; i<rows;i++) {
            for (int j = 0; j < columns; j++) {
                builder.append(matrix[i][j]);
            }
        }


        return builder.toString();
    }


}
