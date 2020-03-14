package com.example.se2;

import android.provider.ContactsContract;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCP implements Runnable {
    private String input;
    private String output;

    String getOutput() {
        return output;
    }

    void setInput(String input) {
        this.input = input;
    }



    @Override
    public void run() {

        try {
            Socket clientSocket = new Socket("se2-isys.aau.at",53212);
            DataOutputStream outUser =  new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outUser.writeBytes(input + "\n");
            output = inServer.readLine();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
