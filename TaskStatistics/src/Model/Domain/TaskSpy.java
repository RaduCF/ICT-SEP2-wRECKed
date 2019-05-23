package Model.Domain;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class TaskSpy implements Runnable{

	ServerSocket serverSocket;
    Socket socket;
    InputStream in; 
    
    String incoming = "";
	
    boolean init = false;
     
    public void close()
    {
    	try {
            socket.close();	
		} catch (Exception e) {}
    }
    int i = 0;
    public void run()
    {
        System.out.println("TaskSpy run method starting.");
    	while (true) {
    		if(!init)
        	{
        		try
        		{
                    Process process = new ProcessBuilder("..\\TaskSpy/TaskSpy/bin/Release/taskSpy.exe").start();
                    serverSocket = new ServerSocket(5000, 10);
                    socket = serverSocket.accept();
                    in = socket.getInputStream();
            		init = true;
        		} catch (Exception e){ System.out.println("TaskSpy failed to Start Or TaskSpy connection not succesful" + e);}
        	
                System.out.println("TaskSpy initializing complete...");
        	}
            try
            {
                String received = "";
                // Receiving
                byte[] lenBytes = new byte[4];
                in.read(lenBytes, 0, 4);
                int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                        ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
                byte[] receivedBytes = new byte[len];
                in.read(receivedBytes, 0, len);
                received = new String(receivedBytes, 0, len);

                System.out.println("Changed application " + received);
                i++;
                incoming = received;


            } catch (Exception e){
            	socket = null;
            	serverSocket = null;
            	in = null;
            	init = false;
            }
		}
    }
}