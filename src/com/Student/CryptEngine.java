package com.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;


public class CryptEngine {

    public static String encrypt(String st, String key) {

        StringBuilder FINAL = new StringBuilder();
        List<Integer> ASCII_KEY = get_ASCII_KEY(key);


        for (int i = 0, j = 0; i < st.length(); i++, j++) {
            if (j >= ASCII_KEY.size() || j >= st.length()) j = 0;
            //Main enrypt function
            FINAL.append ((char) (st.charAt(i) + ((i * j + 1) / key.length()) + (ASCII_KEY.get(j))));
        }

        System.out.println("encrypt out = " + FINAL);

        return FINAL.toString();

    }


    public static String decrypt(String st, String key) {

        StringBuilder FINAL = new StringBuilder();
        List<Integer> ASCII_KEY = get_ASCII_KEY(key);

        for (int i = 0, j = 0; i < st.length(); i++, j++) {
            if (j >= ASCII_KEY.size() || j >= st.length()) j = 0;
            //Main decryption function
            FINAL.append ((char) ((st.charAt(i) - ASCII_KEY.get(j)) - ((i * j + 1) / key.length())));
        }

        System.out.println("decrypt out = " + FINAL);
        return FINAL.toString();
    }

    private static List<Integer> get_ASCII_KEY(String key) {

        List<Integer> ASCII_KEY = new Vector<>();

        for (int i = 0; i < key.length(); i++) {
            ASCII_KEY.add((int) key.charAt(i));
            //System.out.println(" Index "+ i + "\n" + "ASK " + ASCII_KEY.get(i));
        }
        return ASCII_KEY;
    }

}
