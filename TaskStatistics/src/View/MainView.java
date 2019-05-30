package View;

import ViewModel.MainViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainView {
    private Stage primaryStage;
    private UserView userView;
    private MainViewModel mainViewModel;
    private ComparisonView comparisonView;
    private ProgramListView programListView;
    private static Object lock = new Object();
    private RemoveProgamListView removeProgramListView;
    private ReportBugView reportBugView;
    private static MainView instance;
    private Stage programListViewStage;
    private Stage programRemoveListStage;

    private MainView(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public static MainView getInstance(MainViewModel viewModel) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MainView(viewModel);
                }
            }
        }
        return instance;
    }

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        openUserView();
    }

    public void openUserView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userViewFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 710, 500);
            userView = loader.getController();
            userView.init(this, mainViewModel.getUserViewModel(), scene, "UserView");
        } catch (Exception e) {
            e.getStackTrace();
        }
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(userView.getScene());
        primaryStage.setTitle(userView.getTitle());
        primaryStage.show();

    }

    public void OpenSendReportView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ReportBugFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            reportBugView = loader.getController();
            reportBugView.init(this, mainViewModel.getReportBugViewModel(), scene, "UserView");
        } catch (Exception e) {
            e.getStackTrace();
        }
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(reportBugView.getScene());
        primaryStage.setTitle(reportBugView.getTitle());
        primaryStage.show();
    }

    public void openComparisonView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ComparisonFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            comparisonView = loader.getController();
            comparisonView.init(this, mainViewModel.getComparisonViewModel(), scene, "ComparisonView");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().getWindow().hide();
        }
        primaryStage.setScene(comparisonView.getScene());
        primaryStage.setTitle(comparisonView.getTitle());
        primaryStage.show();
    }

    public void openProgramListView(ArrayList<String> dataNameProperties) {
        programListViewStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SelectProgramsFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 278, 325);
            programListView = loader.getController();
            programListView.init(dataNameProperties, mainViewModel.getProgramListViewModel(), this, scene, "SelectPrograms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        programListViewStage.setScene(programListView.getScene());
        programListViewStage.setTitle(programListView.getTitle());
        programListViewStage.show();
    }

    public void close() {
        Platform.exit();
    }

    public ComparisonView getComparisonView() {
        return comparisonView;
    }

    public RemoveProgamListView getRemoveProgramListView() {
        return removeProgramListView;
    }

    public void closeProgramListView() {
        if (programListViewStage.getScene() != null) {
            programListViewStage.getScene().getWindow().hide();
        }
    }

    public void closeProgramRemoveListView() {
        if (programRemoveListStage.getScene() != null) {
            programRemoveListStage.getScene().getWindow().hide();
        }
    }

    public void openProgramRemoveListView() {
        programRemoveListStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RemoveProgramsFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 278, 325);
            removeProgramListView = loader.getController();
            removeProgramListView.init(this, scene, "RemovePrograms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        programRemoveListStage.setScene(removeProgramListView.getScene());
        programRemoveListStage.setTitle(removeProgramListView.getTitle());
        programRemoveListStage.show();
    }

    public UserView getUserView(){
        return userView;
    }

    public void loadData() {
        userView.handleBarChartData();
    }

}