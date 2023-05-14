package com.example.textencoder;

import android.widget.EditText;

public class InputPassword {
    String password = "tekstas";
    EditText inputPsw = MainWindow.pswInput;
    EditText message = MainWindow.messageInput;

    public InputPassword(EditText inputPsw, EditText message){
        this.inputPsw = inputPsw;
        this.message = message;
    }

    public void unclockTextArea(){
        String userInput= inputPsw.getText().toString();
        if(userInput.equals(password)){
            message.setEnabled(true);
        }
    }
}
