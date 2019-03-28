package View;

import ViewModel.MainViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Platform.setImplicitExit(false);
    }

    public void openLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WindowLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 300);
            loginView = loader.getController();
            loginView.init(this,mainViewModel, scene, "Login");
        } catch (Exception e) {
            e.getStackTrace();
        }
        primaryStage.setScene(loginView.getScene());
        primaryStage.setTitle(loginView.getTitle());
        primaryStage.show();
    }
}