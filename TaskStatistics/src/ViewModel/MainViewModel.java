package ViewModel;

import Model.Mediator.ObservableModel;
import View.MainView;
import javafx.application.Platform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener {

    private LoginViewModel viewModelLogin;
    private ObservableModel model;
    private MainView mainView;

    public MainViewModel(ObservableModel model) {
        this.model = model;

        viewModelLogin = new LoginViewModel(model);
        model.addListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

        });
    }

    public LoginViewModel getViewModelLogin() {
        return viewModelLogin;
    }
}
