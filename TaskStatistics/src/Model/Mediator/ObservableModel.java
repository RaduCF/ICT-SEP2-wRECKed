package Model.Mediator;

import Model.Domain.ChartManager;
import utility.observer.subject.PropertyChangeSubject;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    boolean attemptLogin(String password);
     void getLocalData(ChartManager.SORTTYPE type);

}
