package com.Student;
import static com.Student.CryptEngine.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String Text = getData("Input Text");
        String key = getData("input key");
        decrypt(encrypt(Text, key),key);

    }

}


