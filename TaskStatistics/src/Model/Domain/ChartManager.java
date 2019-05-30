package Model.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartManager {

    public enum SORTTYPE {
        BYHOURS,
        RAW
    }


    public ChartManager() {
    }

    public ArrayList<DataPoint> getSpecific(SORTTYPE sorttype, String[] apps, ArrayList<DataPoint> data) {
        ArrayList<DataPoint> out = new ArrayList<>();
        for (String app : apps) {
            for (DataPoint dataPoint : data) {
                if (dataPoint.getId().equals(app)) {
                    out.add(dataPoint);
                }
            }
        }
        Collections.sort(out);
        return out;
    }

    public ArrayList<DataPoint> getData(SORTTYPE sorttype, ArrayList<DataPoint> data) {
        ArrayList<DataPoint> Data = data;
        switch (sorttype) {
            case BYHOURS:

                Collections.sort(Data);
                return Data;

            case RAW:
                return Data;
        }

        return null;
    }

}
