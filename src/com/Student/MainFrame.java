package com.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static com.Student.CryptEngine.*;

public class MainFrame extends JFrame {
    private JTextField IN;
    private JRadioButton encryptButton;
    private JRadioButton decryptButton;
    private JLabel OUT;
    private JPanel MainPanel;
    private boolean firstPress = true;


    public MainFrame() {

        IN.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //Check press Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    OUT.setText("Here will be encrypted or de");
                    endcrypt(IN.getText());
                    firstPress = true;
                }
                //Clear field
                if (firstPress) {
                    IN.setText("");
                    firstPress = false;
                }
            }
        });
        encryptButton.addActionListener(e -> {
            encryptButton.setSelected(true);
            decryptButton.setSelected(false);
        });
        decryptButton.addActionListener(e -> {
            encryptButton.setSelected(false);
            decryptButton.setSelected(true);
        });
    }

    private void endcrypt(String e) {
        //future
        if (encryptButton.isSelected()) OUT.setText(encrypt(e, "default"));
        else OUT.setText(decrypt(e, "default"));

    }

    //Future перегрузку класса сделать

    public void start() {

        this.getContentPane().add(MainPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
