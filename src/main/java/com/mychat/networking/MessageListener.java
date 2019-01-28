package com.mychat.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageListener implements Runnable {
    private ServerSocket server;
    private int port = 8877;
    private WritableGUI gui;

    public MessageListener(WritableGUI gui, int port) {
        this.port = port;
        this.gui=gui;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageListener(){
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Socket clientSocket;
        try {
            while ((clientSocket=server.accept())!=null){
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = bufferedReader.readLine();
                if(line!=null){
                    gui.write(line);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
