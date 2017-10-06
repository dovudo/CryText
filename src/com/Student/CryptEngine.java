package com.Student;

import java.util.List;
import java.util.Vector;

class CryptEngine {

    static String encrypt(String st, String key) {

        StringBuilder FINAL = new StringBuilder();
        List<Integer> ASCII_KEY = get_ASCII_KEY(key);

        for (int i = 0, j = 0; i < st.length(); i++, j++) {
            if (j >= ASCII_KEY.size() || j >= st.length()) j = 0;
            //Main enrypt function
            FINAL.append ((char) (st.charAt(i) + ((i * j + 1) / key.length()) + (ASCII_KEY.get(j))));
        }
        return FINAL.toString();
    }

    static String decrypt(String st, String key) {

        StringBuilder FINAL = new StringBuilder();
        List<Integer> ASCII_KEY = get_ASCII_KEY(key);

        for (int i = 0, j = 0; i < st.length(); i++, j++) {
            if (j >= ASCII_KEY.size() || j >= st.length()) j = 0;
            //Main decryption function
            FINAL.append ((char) ((st.charAt(i) - ASCII_KEY.get(j)) - ((i * j + 1) / key.length())));
        }
        return FINAL.toString();
    }

    private static List<Integer> get_ASCII_KEY(String key) {
        List<Integer> ASCII_KEY = new Vector<>();

        for (int i = 0; i < key.length(); i++) {
            ASCII_KEY.add((int) key.charAt(i));
        }
        return ASCII_KEY;
    }
}
