package ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private StringProperty username;
    private StringProperty password;
    private StringProperty error;
    private ObservableModel model;

    public LoginViewModel(ObservableModel model) {
        this.username = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        this.model = model;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public void sendUsername() {
        model.attemptLogin(username.getValue());
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
