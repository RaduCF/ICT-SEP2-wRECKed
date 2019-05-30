package Model.Domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class LocalData extends ChartManager {
    private ArrayList<DataPoint> data;
    private String user;
    private int LastActiveIndex = -1;
    private ChartManager chartManager;
    private TaskSpy taskSpy;
    private Thread thread;

    public LocalData(String user) {
        this.data = new ArrayList<DataPoint>();
        this.user = user;
        this.chartManager = new ChartManager();

        UpdateFromStorage();

        taskSpy = new TaskSpy();

        thread = new Thread(taskSpy);
        thread.start();
    }

    /**
     * updates local dataPoints and creates new if new programs are used
     */
    public void updateLocal() {
        synchronized (taskSpy) {
            //Check for new data
            if (taskSpy.getIncoming().equals("")) {
                return;
            }
            //update init
            String currentActiveAPP = taskSpy.getIncoming();
            taskSpy.resetIncoming();
            //debug
            System.out.println("Changed application: " + currentActiveAPP);
            //update DataPoint
            for (int i = 0; i < data.size(); i++) {
                if (currentActiveAPP.equals(data.get(i).getId())) {
                    if (LastActiveIndex != -1) {
                        data.get(LastActiveIndex).DeFocused();
                    }
                    data.get(i).Focused();
                    LastActiveIndex = i;
                    UpdateStorage();
                    return;
                }
            }
            //Datapoint does not exist
            DataPoint point = new DataPoint(currentActiveAPP);
            point.Focused();
            data.add(point);
            if (LastActiveIndex != -1) {
                data.get(LastActiveIndex).DeFocused();
            }
            LastActiveIndex = data.indexOf(point);
            UpdateStorage();
        }
    }

    public void UpdateStorage() {
        try {
            FileWriter FWriter = new FileWriter("LocalStorage.txt");
            PrintWriter PWriter = new PrintWriter(FWriter);
            //Clear file
            PWriter.print("");

            for (int i = 0; i < data.size(); i++) {
                PWriter.println(data.get(i).toString());
            }

            PWriter.close();
        } catch (IOException e) {
            System.out.println("FileWriter error: " + e);
        }
    }

    public void UpdateFromStorage() {
        try {
            FileReader FReader = new FileReader("LocalStorage.txt");
            BufferedReader BReader = new BufferedReader(FReader);

            String str;
            while ((str = BReader.readLine()) != null) {
                String[] split = str.split(",");
                data.add(new DataPoint(split[0], Float.parseFloat(split[1])));
            }

            BReader.close();
        } catch (Exception e) {
            System.out.println("FileReader error: " + e);
        }
    }

    /**
     * gets fresh data from DB
     */
    public void refresh() {

        /*Magic upload/Download code*/

    }

    /**
     * published data to db
     */
    public void publish() {

    }

    public String toString() {
        String out = user + " \n";
        ArrayList<DataPoint> outData = data;
        Collections.sort(outData);
        for (int i = 0; i < outData.size(); i++) {
            out += outData.get(i).toString() + " \n";
        }
        return out;
    }

    public ArrayList<DataPoint> getData(SORTTYPE type) {
        return this.getData(type, data);
    }

    public ArrayList<DataPoint> getSpecific(String[] apps) {
        return this.getSpecific(apps);
    }


}
