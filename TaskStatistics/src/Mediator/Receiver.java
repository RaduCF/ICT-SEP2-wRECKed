package Mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver implements Runnable {

    private BufferedReader in;
    private boolean connected;
    private Client chat;

    public Receiver(BufferedReader in, Client chat) {
        this.chat = chat;
        this.in = in;
        this.connected = true;
    }

    @Override
    public void run() {
       
    }
}