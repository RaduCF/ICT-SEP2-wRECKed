package Model.Mediator;

import Model.Domain.ChartManager;
import utility.observer.subject.PropertyChangeSubject;

import java.io.IOException;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    boolean attemptLogin(String password);
    void sendReport(String comment) throws IOException;
    void getLocalData(ChartManager.SORTTYPE type);
    void getMoreData(ChartManager.SORTTYPE type);
}
