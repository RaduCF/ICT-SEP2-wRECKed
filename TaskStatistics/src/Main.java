import UserViewPack.MainView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainView view = new MainView();
        view.start(primaryStage);
    }
}
