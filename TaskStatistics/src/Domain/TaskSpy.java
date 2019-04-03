package Domain;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TaskSpy {

    public String run()
    {

        try
        {

            ServerSocket serverSocket = new ServerSocket(5000, 10);
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();

            String received = "";
            while (!received.equals("exit"))
            {
                // Receiving
                byte[] lenBytes = new byte[4];
                in.read(lenBytes, 0, 4);
                int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                        ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
                byte[] receivedBytes = new byte[len];
                in.read(receivedBytes, 0, len);
                received = new String(receivedBytes, 0, len);

                return received;
            }

            socket.close();

        } catch (Exception e){}

        return null;
    }

}