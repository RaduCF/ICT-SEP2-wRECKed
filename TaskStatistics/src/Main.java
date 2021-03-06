
import Model.Mediator.Client;
import Model.Mediator.ModelManager;
import Model.Mediator.ObservableModel;
import View.MainView;
import ViewModel.MainViewModel;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client();
        Thread clientThread = new Thread(client);
        clientThread.start();

        ObservableModel model = new ModelManager(client);
    	MainViewModel viewModel = new MainViewModel(model);
        MainView view = MainView.getInstance(viewModel);
        view.start(primaryStage);
    }
}
