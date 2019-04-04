package UserViewPack;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    private Stage primaryStage;
    private UserView userView;

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage=primaryStage;
        openUserView();
        Platform.setImplicitExit(false);
    }

    public MainView()
    {

    }

    public void openUserView() throws IOException {
        try {


        }
        catch (Exception e){
            e.getStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userViewFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,900,500);
        userView= loader.getController();
        userView.init(this,scene, "UserViewPack");
        
        userView.getScene();
        
        primaryStage.setScene(userView.getScene());
        //primaryStage.setScene();
        primaryStage.setTitle(userView.getTitle());
        primaryStage.show();
    }
}
