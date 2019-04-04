import UserViewPack.MainView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //needs all the model classes to be initiated here before the view...
        MainView view = new MainView();
        view.start(primaryStage);
    }
}
