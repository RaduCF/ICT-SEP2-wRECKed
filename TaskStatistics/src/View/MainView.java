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
    private ComparisonView comparisonView;
    private ProgramListView programListView;

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
    
    public void openUserView()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userViewFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,900,500);
            userView= loader.getController();
            userView.init(this,mainViewModel.getUserViewModel(),scene, "UserView");
        }
        catch (Exception e){
            e.getStackTrace();
        }
        if (primaryStage.getScene() != null){
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(userView.getScene());
        primaryStage.setTitle(userView.getTitle());
        primaryStage.show();
    }

    public void openComparisonView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ComparisonFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,900,500);
            comparisonView = loader.getController();
            comparisonView.init(this, mainViewModel.getComparisonViewModel(),scene,"ComparisonView");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (primaryStage.getScene() != null){
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(comparisonView.getScene());
        primaryStage.setTitle(comparisonView.getTitle());
        primaryStage.show();
    }

    public void openProgramListView()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SelectProgramsFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,900,500);
            programListView = loader.getController();
            programListView.init( mainViewModel.getProgramListViewModel(),this,scene,"SelectPrograms");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*
        if (primaryStage.getScene() != null){
            primaryStage.getScene().getWindow().hide();
        }
        */
        primaryStage.setScene(programListView.getScene());
        primaryStage.setTitle(programListView.getTitle());
        primaryStage.show();
    }

    public void close(){
        Platform.exit();
    }

    public ComparisonView getComparisonView(){return comparisonView;}

}