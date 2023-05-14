package com.example.textencoder;

import android.widget.EditText;
import android.widget.TextView;

public class Decoder {
    EditText text = MainWindow.messageInput;

    private String plainText;
    private String cypher;

    TextView textEnDe = MainWindow.textEnDe;

    public Decoder(EditText text){
        this.text = text;
    }

    public String getPlainText() {
        return plainText;
    }

    public void decode(){
        String text1 = text.getText().toString();

        String textDecode = "";
        for (int i = 2; i < text1.length()-1; i+=2) {
            textDecode += text1.charAt(i);
        }
        StringBuilder stringBuilder = new StringBuilder(textDecode);
        stringBuilder.reverse();
        textDecode = stringBuilder.toString();
        this.plainText = textDecode;
    }
    public void pasteMessage(){
        textEnDe.setText(plainText);
    }
}
