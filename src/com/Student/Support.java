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

        while (true) {
            try {
                string = bf.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        return string;
    }

    public static void startGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
                mf.start();
            }
        });
    }

}
