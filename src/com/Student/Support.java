package com.Student;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Support {

    public static String getData(String viewText){

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        System.out.println(viewText + "\r\n");
        try {
        string = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    static void startGUI(){
        SwingUtilities.invokeLater(() -> {
            //set system style
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            MainFrame mn = new MainFrame();
            mn.start();
        });
    }
}
