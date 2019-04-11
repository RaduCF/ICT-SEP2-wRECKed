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
    private UserView userView;
    private MainViewModel mainViewModel;

    public MainView(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        openUserView();
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
        if (primaryStage.getScene() != null){
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(loginView.getScene());
        primaryStage.setTitle(loginView.getTitle());
        System.out.println("Opening login window...");
        primaryStage.show();
    }
    
    public void openUserView() throws IOException {
        try {

        }
        catch (Exception e){
            e.getStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userViewFXML.fxml"));
        System.out.println("works!");
        Parent root = loader.load();
        System.out.println("works!");
        Scene scene = new Scene(root,900,500);
        userView= loader.getController();
        userView.init(this,mainViewModel.getUserViewModel(),scene, "UserViewPack");
        
        userView.getScene();
        
        primaryStage.setScene(userView.getScene());
        //primaryStage.setScene();
        primaryStage.setTitle(userView.getTitle());
        primaryStage.show();
    }

    public void close(){
        Platform.exit();
    }

}