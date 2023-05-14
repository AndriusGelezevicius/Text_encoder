package com.example.textencoder;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Encoder {
    EditText message = MainWindow.messageInput;
    TextView textEnDe = MainWindow.textEnDe;
    private String chyter;

    public String getChyter() {
        return chyter;
    }

    public Encoder(EditText message){
        this.message = message;
    }

    public char generateChar(){
        int min = 33;
        int max = 126;
        Random random = new Random();
        char randomChar =  (char)random.nextInt((max+1-min)+min);
        return randomChar;
    }

    public void encode(){
        String text = message.getText().toString();
        String addedWord = "" + generateChar() + generateChar();

        for (int i = 0; i < message.length(); i++) {
            addedWord += text.charAt(i);
            addedWord += generateChar();
        }
        addedWord += generateChar();
        StringBuilder stringBuilder = new StringBuilder(addedWord);
        stringBuilder.reverse();
        addedWord = stringBuilder.toString();
        this.chyter = addedWord;
    }
    public void pasteMessage(){
        textEnDe.setText(chyter);
    }
}
