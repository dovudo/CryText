package com.Student;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import static com.Student.CryptEngine.*;

public class MainFrame extends JFrame {
    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private JTextArea IN_OUT_TEXT;
    private JButton goButton;
    private JButton clearButton;
    private JPanel MainPanel;
    private JButton openFileButton;
    private JTextField IN_KEY;
    private File file;

    public MainFrame() {

        goButton.addActionListener(e -> endcrypt(IN_OUT_TEXT.toString()));
        clearButton.addActionListener(e -> IN_OUT_TEXT.setText(""));

        IN_OUT_TEXT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) endcrypt(IN_OUT_TEXT.getText());
            }
        });

        //Switch between RadioButtons
        encryptRadioButton.addActionListener(e -> {
            decryptRadioButton.setSelected(false);
            encryptRadioButton.setSelected(true);
        });
        decryptRadioButton.addActionListener(e -> {
            decryptRadioButton.setSelected(true);
            encryptRadioButton.setSelected(false);
        });
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    private void endcrypt(String e) {
        //future
        if (encryptRadioButton.isSelected()) IN_OUT_TEXT.setText(encrypt(e, IN_KEY.getText()));
        else IN_OUT_TEXT.setText(decrypt(e, IN_KEY.getText()));
    }

    //Future перегрузку класса сделать
    public void start() {

        this.getContentPane().add(MainPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}

class forStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FileChooser fc = new FileChooser();
        final FileChooser.ExtensionFilter fcFilters = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        primaryStage.setTitle("Select a file");
        
    }
}

