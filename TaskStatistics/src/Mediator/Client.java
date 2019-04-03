package Mediator;

import com.google.gson.Gson;

import Domain.LocalData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable, ClientModel {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Gson gson;
    private ClientModel clientModel;
    private Receiver receiver;
    private static final String host = "10.152.220.75";
    private static final int port = 1337;
    
    private LocalData localData = new LocalData("temporary");

    public Client() throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();
        this.receiver= new Receiver(out, this);

        Thread thread = new Thread(receiver);
        thread.start();

    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public LocalData getLocalData() {
		return localData;
	}
}