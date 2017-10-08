package com.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
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
    private JButton saveFileButton;
    private File file;
    private JFileChooser fc = new JFileChooser();
    private final ImageIcon imageIcon = new ImageIcon("src/img/icon.png");

    MainFrame() {

        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("text", "txt");
        FileNameExtensionFilter cttxFilter = new FileNameExtensionFilter("Encrypted text","cttx");

        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileFilter(txtFilter);
        fc.addChoosableFileFilter(cttxFilter);

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
            int result = fc.showOpenDialog(openFileButton);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                try (Stream<String> stream = Files.lines(Paths.get(file.toString()))){
                    IN_OUT_TEXT.setText(stream.collect(Collectors.joining("\n")));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException ignored) {
                    System.out.println("Someone exception");
                }
            }
        });

        saveFileButton.addActionListener(e -> {
            fc.setFileFilter(cttxFilter);
            int userSelection = fc.showSaveDialog(saveFileButton);
            fc.setDialogTitle("Create save file");
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(IN_OUT_TEXT.getText());
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void endcrypt(String e) {
        if (Objects.equals(IN_KEY.getText(), "")) IN_KEY.setText("default");
        if (encryptRadioButton.isSelected()) IN_OUT_TEXT.setText(encrypt(e, IN_KEY.getText()));
        else IN_OUT_TEXT.setText(decrypt(e, IN_KEY.getText()));
    }

    void start() {
        this.setTitle("CryText");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().add(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


