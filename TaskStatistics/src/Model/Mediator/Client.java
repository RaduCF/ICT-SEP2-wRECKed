package Model.Mediator;

import Model.Domain.ChartManager;

import Model.Domain.ChartManager.SORTTYPE;
import Model.Domain.DataPoint;
import Model.Domain.LocalData;
import Model.Domain.TaskSpy;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Client implements Runnable, ClientModel {
    private DataOutputStream stream;
    private DataInputStream istream;
    private Socket socket;

    private ClientModel clientModel;
    private Receiver receiver;

    //Domain init:
    private LocalData localData;

    public Client() {
        localData = new LocalData(getUserID());
    	/* Receiver is not used?!?!
        this.receiver= new Receiver();
        Thread thread = new Thread(receiver);
        thread.start();
		*/
    }

    @Override
    public void run() {
		System.out.println("Running the client...");
        if(!connect())
        {
            System.out.println("Not connected!!");
        }
        synchronized (this) {
            while (true) {
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                localData.updateLocal();
            }
        }
    }

    public ArrayList<DataPoint> getLocalData(SORTTYPE type) {
        /*
        ArrayList<DataPoint> dummy = new ArrayList<>();
        dummy.add(new DataPoint("Netflix", 10));
        dummy.add(new DataPoint("Chrome", 15));
        dummy.add(new DataPoint("Spotify", 90));
        dummy.add(new DataPoint("Outlook", 23));
        dummy.add(new DataPoint("FileExplorer", 53));
        return dummy;
*/
        ArrayList<DataPoint> points = new ArrayList<>();
        for (int i = 0; i<localData.getData(type).size();i++){
            points.add(new DataPoint(localData.getData(type).get(i).getId(), localData.getData(type).get(i).getHours()));
        }
        return points;
    }
/*
    public ArrayList<DataPoint> getMoreData(SORTTYPE type) {
        ArrayList<DataPoint> dummy = new ArrayList<>();
        dummy.add(new DataPoint("League of Legends", 100));
        dummy.add(new DataPoint("Calculator", 5));
        dummy.add(new DataPoint("IntelliJ", 78));
        dummy.add(new DataPoint("EclipseIDE", 34));
        dummy.add(new DataPoint("Brackets", 29));
        dummy.add(new DataPoint("Github", 5));

        for (int i = 0; i<localData.getData(type).size();i++){
            dummy.add(new DataPoint(localData.getData(type).get(i).getId(),localData.getData(type).get(i).getHours()));
        }
        return dummy;
    }
*/

    private String UserID = null;

    public String getUserID() {
        if (UserID == null) {

            //Combine attributes to get a strong hardware ID
            int cores = Runtime.getRuntime().availableProcessors();
            String username = System.getProperty("user.name");
            long maxMemory = Runtime.getRuntime().maxMemory();

            String _hash = cores + username + maxMemory;
            String hash = "";

            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(_hash.getBytes());
                hash = new String(messageDigest.digest());
            } catch (Exception ex) {
                System.out.println("Something went wrong in the client...");
            }

            UserID = hash;
            return hash;
        }

        return UserID;
    }

    public float getAvgHours(String program) throws IOException {
        /*stream.writeInt(2);
        stream.writeUTF(program);
        return istream.readFloat();
        */
        return (float) 1.5;
    }

    public void reportBug(String str) throws IOException {
        stream.writeInt(3);
        stream.writeUTF(str);
    }

    public boolean connect() {
        System.out.println("Connecting...");
        try {
            socket = new Socket("10.152.210.8", 5001);
            stream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connected!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        //Send user ID to server
        String userid = getUserID();

        try {
            System.out.println("Sending userID...");
            stream.writeUTF(userid);
            System.out.println("UserID sent!");
        } catch (Exception e) {
            return false;
        }


        while (true) {/* DEBUGING CODE
            try
            {
                stream.writeUTF("doogle.org");
                stream.writeFloat(15.f);
            } catch (Exception e)
            {
                return false;
            }
            */
            for (DataPoint point : localData.getData(ChartManager.SORTTYPE.RAW)) {
                try {
                    stream.writeInt(1); //PacketType: 1 = Update table
                    stream.writeUTF(point.getId());
                    stream.writeFloat(point.getHours());
                } catch (Exception e) {
                    return false;
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
