package Model.Domain;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TaskSpy {

	ServerSocket serverSocket;
    Socket socket;
    InputStream in; 
	
    boolean init = false;
    int timeOutTries = 0;
     
    public void close()
    {
    	try {
            socket.close();	
		} catch (Exception e) {}
    }
     
    public String run()
    {
        System.out.print("TaskSpy run method starting.");

    	if(!init)
    	{

    		try
    		{

                Process process = new ProcessBuilder(
                		"C:\\Users\\chris\\Documents\\GitHub\\ICT-SEP2-wRECKed\\TaskSpy\\TaskSpy\\bin\\Release\taskSpy.exe"
                		).start();
                wait(1000);
                serverSocket = new ServerSocket(5000, 10);
                socket = serverSocket.accept();
                in = socket.getInputStream();
    		} catch (Exception e){ System.out.println("TaskSpy failed to Start Or TaskSpy connection not succesful");}
    	
    		init = true;
            System.out.print("TaskSpy initializing complete...");
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

            return received;


        } catch (Exception e){
        	timeOutTries++;
        	if (timeOutTries > 5) {
                try {
					Process process = new ProcessBuilder(
							"C:\\Users\\chris\\Documents\\GitHub\\ICT-SEP2-wRECKed\\TaskSpy\\TaskSpy\\bin\\Release\taskSpy.exe"
							).start();
				} catch (IOException e1) {System.out.println("TaskSpy didn't start. Did someone delete something?");}
			}
        }

        return null;
    }

}