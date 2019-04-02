package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private StringProperty username;
    private StringProperty password;
    private StringProperty error;
    private ObservableModel model;

    public LoginViewModel(ObservableModel model) {
        this.model = model;
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public void attemptLogin() {
       // model.attemptLogin(username.getValue(), password.getValue());
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
