package com.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final FileNameExtensionFilter FileFilter = new FileNameExtensionFilter("Text","txt");

    public MainFrame() {

        goButton.addActionListener(e -> endcrypt(IN_OUT_TEXT.getText()));
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
        openFileButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            fc.setFileFilter(FileFilter);
            int result = fc.showOpenDialog(openFileButton);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                try (Stream<String> stream = Files.lines(Paths.get(file.toString()))){
                    IN_OUT_TEXT.setText(stream.collect(Collectors.joining("\n")));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {

                }
            }
        });
    }


    private void endcrypt(String e) {
        //future
        if (Objects.equals(IN_KEY.getText(), "")) IN_KEY.setText("default");
        if (encryptRadioButton.isSelected()) IN_OUT_TEXT.setText(encrypt(e, IN_KEY.getText()));
        else IN_OUT_TEXT.setText(decrypt(e, IN_KEY.getText()));

    }

    public void start() {

        //set system style
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MainPanel.setSize(600,400);
        this.getContentPane().add(MainPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}


