package ViewModel;

import View.MainView;
import javafx.application.Platform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener {
    private ViewModelChat viewModelChat;
    private LoginViewModel viewModelLogin;
    private ObservableModel model;
    private MainView mainView;

    public MainViewModel(ObservableModel model) {
        this.model = model;
        viewModelChat = new ViewModelChat(model);
        viewModelLogin = new LoginViewModel(model);
        model.addListener(this);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public ViewModelChat getViewModelChat() {
        return viewModelChat;
    }

    public LoginViewModel getViewModelLogin() {
        return viewModelLogin;
    }

    public ObservableModel getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

        });
    }
}
