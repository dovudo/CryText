package com.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String st = "";
        String key = "";
        System.out.println("input Str");
        while (true) {
            try {
                st = bf.readLine();
                System.out.println("input key");
                key = bf.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        System.out.println("String = " + st + "\nkey = " + key + "\r\n");
        decrypt(encrypt(st, key),key);

    }

    private static String encrypt(String st, String key) {

        String FINAL = "";
        List<Integer> ASK_KEY = new LinkedList<>();

        for (int i = 0; i < key.length(); i++) {
            ASK_KEY.add(Integer.valueOf(key.charAt(i)));
         // System.out.println(" Index "+ i + "\n" + "ASK " + ASK_KEY.get(i));
        }

        for(int i = 0, j = 0; i < st.length(); i++,j++){
            if (j >= ASK_KEY.size() || j >= st.length()) j = 0;
            //Main crypto function
            FINAL += (char)( Integer.valueOf(st.charAt(i)) * (i + j + 1) + ( ASK_KEY.get(j)));
        }

        System.out.println("encrypt out = " + FINAL);

        return FINAL;

    }


    private static String decrypt(String st, String key){

        String FINAL = "";
        List<Integer> ASCII_KEY = new LinkedList<>();

        for (int i = 0; i < key.length(); i++) {
            ASCII_KEY.add(Integer.valueOf(key.charAt(i)));
            //System.out.println(" Index "+ i + "\n" + "ASK " + ASCII_KEY.get(i));
        }

        for(int i = 0, j = 0; i < st.length(); i++,j++){
            if (j >= ASCII_KEY.size() || j >= st.length()) j = 0;
            FINAL += (char)( (Integer.valueOf(st.charAt(i)) - ASCII_KEY.get(j)) / (i + j + 1));
        }

            System.out.println("decrypt out = " + FINAL);

        return FINAL;
    }

}


