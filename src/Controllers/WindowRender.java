package Controllers;

import Views.MainWindow;

import javax.swing.*;


public class WindowRender {

    private static MainWindow mainWindow;

    public static void render(){

        System.out.print("Came here");
        JFrame window =new JFrame("Product Cipher");
        window.setContentPane(new MainWindow().getWindow());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void exit(){
        System.exit(0);
    }
}
