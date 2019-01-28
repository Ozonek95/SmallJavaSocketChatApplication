package com.mychat.view;

import com.mychat.networking.MessageListener;
import com.mychat.networking.MessageTransmitter;
import com.mychat.networking.WritableGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.*;


public class View implements WritableGUI {
    public TextField ipTextField;
    public TextField portTextField;
    public TextArea chat;
    public TextField message;
    public Button sendButton;
    public Button listenButton;
    public TextField targetPort;


    public void write(String s) {
        chat.appendText("[Mate] "+s+System.lineSeparator());
    }
    @FXML
    private void listenButtonActionPerformed(){
        MessageListener listener = new MessageListener(this, Integer.parseInt(portTextField.getText()));
        Thread t1 = new Thread(listener);
        t1.start();
    }

    @FXML
    void typedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonActionPerformed();
        }
    }
    @FXML
    private void sendButtonActionPerformed(){
        chat.appendText("[Me] "+message.getText()+System.lineSeparator());
        MessageTransmitter transmitter = new MessageTransmitter(message.getText()
                ,ipTextField.getText(),Integer.parseInt(targetPort.getText()));
        Thread thread = new Thread(transmitter);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message.setText("");
    }
}
