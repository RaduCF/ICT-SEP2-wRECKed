package View;

import ViewModel.MainViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {
    private Stage primaryStage;
    private Login loginView;
    private MainViewModel mainViewModel;

    public MainView(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openLoginView();
    }

    public void openLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 360);
            loginView = loader.getController();
            loginView.init(this, mainViewModel.getViewModelLogin(), scene, "Login");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(loginView.getScene());
        primaryStage.setTitle(loginView.getTitle());
        primaryStage.show();
    }
}