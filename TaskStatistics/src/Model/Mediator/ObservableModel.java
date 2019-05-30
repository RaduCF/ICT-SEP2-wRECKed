package Model.Mediator;

import Model.Domain.ChartManager;
import utility.observer.subject.PropertyChangeSubject;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    boolean attemptLogin(String password);
    void sendReport(String comment);
    void getLocalData(ChartManager.SORTTYPE type);
    void getMoreData(ChartManager.SORTTYPE type);
}
