package View;

import ViewModel.LoginViewModel;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.MainViewModel;

public class Login {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private Scene scene;
    private String title;
    private MainView parent;
    private LoginViewModel model;

    public Login() {
    }

    public void init(MainView parent, MainViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.scene = scene;
        this.title = title;

        userField.textProperty().bindBidirectional(model.usernameProperty());
        errorLabel.textProperty().bindBidirectional(model.errorProperty());
    }


    @FXML
    public void LoginButtonPressed() {
        model.getViewModelLogin().sendUsername();
    }

    @FXML
    public void cancelButtonPressed() {
        Platform.exit();
    }

    public void onEnter(Event event) {
        if (event.getSource() == user) {
            LoginButtonPressed();
        }
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }
}
