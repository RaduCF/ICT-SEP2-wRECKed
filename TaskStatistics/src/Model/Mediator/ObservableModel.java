package Model.Mediator;

import Model.Domain.ChartManager;
import utility.observer.subject.PropertyChangeSubject;

import java.io.IOException;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    void sendReport(String comment) throws IOException;

    void getLocalData(ChartManager.SORTTYPE type);

    void getMoreData(ChartManager.SORTTYPE type);
}
