package Mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import Domain.*;

public class Receiver implements Runnable {

    private BufferedReader in;
    private boolean connected;
    private Client client;
    private TaskSpy taskSpy;
    private LocalData localData;

    public Receiver(BufferedReader in, Client chat, LocalData localdata) {
        this.client = chat;
        this.in = in;
        this.connected = true;
        this.localData = localdata;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        String reply = "";

        while (true) {
        	localData.updateLocal(taskSpy.run());


        }
    }
}
