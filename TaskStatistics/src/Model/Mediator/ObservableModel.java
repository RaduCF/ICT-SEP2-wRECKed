package Model.Mediator;

import utility.observer.subject.PropertyChangeSubject;

public interface ObservableModel extends PropertyChangeSubject, ClientModel {
    boolean attemptLogin(String password);
    void sendReport(String comment);
}
