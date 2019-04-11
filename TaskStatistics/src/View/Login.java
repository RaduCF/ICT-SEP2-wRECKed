package View;

import ViewModel.LoginViewModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class Login {
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

    public void init(MainView parent, LoginViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.scene = scene;
        this.title = title;

        passwordField.textProperty().bindBidirectional(model.passwordProperty());
        errorLabel.textProperty().bindBidirectional(model.errorProperty());
    }

    @FXML
    public void LoginButtonPressed() {
        model.attemptLogin();


        //parent.openAdminView();

    }

    @FXML
    public void cancelButtonPressed() throws IOException {
        parent.openUserView();
    }

    public void onEnter(Event event) {
        if (event.getSource() != passwordField) {
            passwordField.requestFocus();
        }
        else
        {
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
