package Model.Domain;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TaskSpy implements Runnable{

    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream in;
    private Process process;

    private String incoming;

    private boolean init = false;

    public TaskSpy() {
        incoming = "";
    }

    public void close()
    {
        try {
            socket.close();
        } catch (Exception e) {}
    }

    public String getIncoming() {
        return incoming;
    }

    public void resetIncoming() {
        synchronized (incoming) {
            incoming = "";
        }
    }

    public void run()
    {
        System.out.println("TaskSpy run method starting.");
        while (true) {
            if(!init)
            {
                try
                {
                    process = new ProcessBuilder("..\\TaskSpy/TaskSpy/bin/Release/taskSpy.exe").start();
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

                incoming = received;


            } catch (Exception e){
                System.out.println(e);
                try {
                    serverSocket.close();
                } catch (IOException e1) {}
                init = false;
            }
        }
    }
}