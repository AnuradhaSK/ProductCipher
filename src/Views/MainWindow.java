package Views;

import Controllers.Encryptor;
import Controllers.WindowRender;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static com.sun.glass.ui.Cursor.setVisible;

public class MainWindow {
    private JTextField fileField;
    private JButton decryptButton;
    private JButton encryptButton;
    private JButton exitButton;
    private JButton browseButton;
    private JPanel window;
    private JTextField secretKey;

    private JFileChooser fileChooser = new JFileChooser();

    public MainWindow(){

        //Browser button performance
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //browse a file
                int response = fileChooser.showOpenDialog(null);
                System.out.println(response);

                if(response == JFileChooser.APPROVE_OPTION){
                    fileField.setText(fileChooser.getSelectedFile().getName());
                }
            }
        });

        //Exit button's performance
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //exit from the system
                WindowRender.exit();
            }
        });

        //Encryption button's action
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //show an error if a file has not been chosen
                if(fileChooser.getSelectedFile()==null || secretKey.getText().length()==0){
                    if(secretKey.getText().length()==0){
                        JOptionPane.showMessageDialog(window,"No secret key is inserted");
                    }
                    else{
                        JOptionPane.showMessageDialog(window,"No file chosen");
                    }
                }
                //call encryptor
                else{
                        File file= fileChooser.getSelectedFile();
                        String SecretString = secretKey.getText();
                        System.out.println(SecretString);
                        Encryptor encryptor = new Encryptor();
                    try {
                        encryptor.encrypt(file,SecretString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }



            }
        });

        //Decryption button's action
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //show an error if a file has not been chosen
                if(fileChooser.getSelectedFile()==null){
                    JOptionPane.showMessageDialog(window,"No file chosen");
                }
                //call decryptor
                else{

                }
            }
        });

    }

    public JPanel getWindow(){
        return window;
    }
}
