package Model.Mediator;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import utility.observer.subject.PropertyChangeSubject;

import java.io.IOException;
import java.util.ArrayList;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    void sendReport(String comment) throws IOException;

    void getLocalData(ChartManager.SORTTYPE type);

    void getMoreData(ChartManager.SORTTYPE type);

    public ArrayList<DataPoint> getData(ChartManager.SORTTYPE type);

    void getGlobalData(String dataName);
}
