import Model.Mediator.ModelManager;
import Model.Mediator.ObservableModel;
import View.MainView;
import ViewModel.MainViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //ChatClient client = new ChatClient();
        ObservableModel model = new ModelManager(/*client*/);
        //client.setChatModel(model);
        MainViewModel mainViewModel = new MainViewModel(model);
        MainView view = new MainView(mainViewModel);
        view.start(primaryStage);
    }
}
