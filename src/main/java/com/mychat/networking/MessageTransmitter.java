package com.mychat.networking;

import java.io.IOException;
import java.net.Socket;

public class MessageTransmitter implements Runnable{

    private String message, hostname;
    private int port;

    public MessageTransmitter(String message, String hostname, int port) {
        this.message = message;
        this.hostname = hostname;
        this.port = port;
    }

    public MessageTransmitter(){

    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname,port);
            socket.getOutputStream().write(message.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
