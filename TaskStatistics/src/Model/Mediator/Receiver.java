package Model.Mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;

import Model.Domain.*;

public class Receiver implements Runnable {

    private Client client;
    private TaskSpy taskSpy;
    private LocalData localData;

    public Receiver(LocalData localdata) {
        this.localData = localdata;
        taskSpy = new TaskSpy();
    }

    @Override
    public void run() {
        while (true) {
        	localData.updateLocal(taskSpy.run());
        }
    }
}
