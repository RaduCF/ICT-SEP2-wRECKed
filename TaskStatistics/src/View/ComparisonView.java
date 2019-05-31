package View;


import ViewModel.ComparisonViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;


public class ComparisonView {

    @FXML
    private BarChart barChart;
    @FXML
    private CategoryAxis yAxis;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private ToggleButton global;

    private Scene scene;
    private String title;

    private MainView parent;
    private ComparisonViewModel model;
    private ArrayList<Float> localDataValueProperties;
    private ArrayList<String> localDataNameProperties;
    private ArrayList<Float> globalDataValueProperties;
    private ArrayList<String> globalDataNameProperties;
    private ArrayList<String> usedLocalDataNameProperties;
    private ArrayList<Float> usedLocalDataValueProperties;

    public void init(MainView parent, ComparisonViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.scene = scene;
        this.title = title;
        localDataValueProperties = new ArrayList<>();
        localDataNameProperties = new ArrayList<>();
        globalDataValueProperties = new ArrayList<>();
        globalDataNameProperties = new ArrayList<>();

        usedLocalDataNameProperties = new ArrayList<>();
        usedLocalDataValueProperties = new ArrayList<>();
    }

    public void loadData(ArrayList<String> dataNames, ArrayList<Float> dataValues) {
        System.out.println("ComparisonView: loadData: received program amount: " + dataNames.size() + ", and toggle button is: " + global.isSelected());
        //model.loadData(dataNames, global.isSelected());
        //bindProperties(dataNames.size());
        // implement request to receive EXACT data objects
        localDataNameProperties.addAll(dataNames);
        localDataValueProperties.addAll(dataValues);

        handleBarChartData();
        localDataNameProperties.clear();
        localDataValueProperties.clear();
    }

    /*public void bindProperties(int size) // creates and binds this class properties to the existing model properties
    {
        localDataNameProperties.clear();
        localDataValueProperties.clear();
        globalDataNameProperties.clear();
        globalDataValueProperties.clear();

        if (!global.isSelected()) {
            for (int i = 0; i < size; i++) {
                localDataNameProperties.add(new SimpleStringProperty());
                localDataValueProperties.add(new SimpleDoubleProperty());
                localDataNameProperties.get(i).bindBidirectional(model.getLocalDataNameProperties().get(i));
                localDataValueProperties.get(i).bindBidirectional(model.getLocalDataValueProperties().get(i));
            }
        } else if (global.isSelected()) {
            for (int i = 0; i < size; i++) {
                localDataNameProperties.add(new SimpleStringProperty());
                localDataValueProperties.add(new SimpleDoubleProperty());
                globalDataNameProperties.add(new SimpleStringProperty());
                globalDataValueProperties.add(new SimpleDoubleProperty());

                localDataNameProperties.get(i).bindBidirectional(model.getLocalDataNameProperties().get(i));
                localDataValueProperties.get(i).bindBidirectional(model.getLocalDataValueProperties().get(i));
                globalDataValueProperties.get(i).bindBidirectional(model.getGlobalDataValueProperties().get(i));
                globalDataNameProperties.get(i).bindBidirectional(model.getGlobalDataNameProperties().get(i));
            }
        }
    }
    */

    public void handleBarChartData() {
        System.out.println("ComparisonView: handleBarChartData: executing data loading");

        if (!global.isSelected()) {

            for (int i = 0; i < localDataNameProperties.size(); i++) {
                XYChart.Series displaySet = new XYChart.Series();
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i), localDataValueProperties.get(i)));
                System.out.println("ComparisonView: handleBarChartData: loop: " + i + " usedDataNameProperty name: " + parent.getUserView().getDataNameProperties().get(i).getValue());
                barChart.getData().addAll(displaySet);
            }
        } else if (global.isSelected()) {
            for (int i = 0; i < localDataNameProperties.size(); i++) {
                XYChart.Series displaySet = new XYChart.Series();
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i), localDataValueProperties.get(i)));
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i), localDataValueProperties.get(i)));
                System.out.println("ComparisonView: handleBarChartData: loop: " + i + " usedDataNameProperty name: " + parent.getUserView().getDataNameProperties().get(i).getValue());
                barChart.getData().addAll(displaySet);
            }

        }

        moveUnusedToUsed();
        System.out.println("ComparisonView: handleBarChartData: Data loaded.");
    }

    @FXML
    public void setInterval() {

    }

    @FXML
    public void clearAllPrograms() {
        for (int i = 0; i < usedLocalDataValueProperties.size(); i++) {
            usedLocalDataValueProperties.clear();
            usedLocalDataNameProperties.clear();
        }
        barChart.getData().clear();
    }

    @FXML
    public void clearSpecificPrograms() {
        parent.openProgramRemoveListView();
        parent.getRemoveProgramListView().loadData(usedLocalDataNameProperties);
    }

    @FXML
    public void selectPrograms() {
        parent.openProgramListView(usedLocalDataNameProperties);
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void moveUnusedToUsed() {
        for (int i = 0; i < localDataValueProperties.size(); i++) {
            System.out.println("ComparisonView: moveUsedToUnused: loop: " + i);
            usedLocalDataNameProperties.add(localDataNameProperties.get(i));
            usedLocalDataValueProperties.add(localDataValueProperties.get(i));
        }
        localDataNameProperties.clear();
        localDataValueProperties.clear();
    }

    public void removeData(ObservableList<String> programs) {

        boolean remove;
        ArrayList<String> sendablePrograms = new ArrayList<>();
        ArrayList<Float> sendableProgramsValues = new ArrayList<>();

        barChart.getData().clear();

        for (int i = 0; i < usedLocalDataValueProperties.size(); i++) {
            remove = false;

            for (int j = 0; j < programs.size(); j++) {
                if (usedLocalDataNameProperties.get(i).equals(programs.get(j))) {
                    remove = true;
                }
            }
            if (remove == false) {
                //displaySet.getData().add(new XYChart.Data(usedLocalDataNameProperties.get(i).getValue(),usedLocalDataValueProperties.get(i).getValue()));
                //barChart.getData().addAll(displaySet);
                sendablePrograms.add(usedLocalDataNameProperties.get(i));
                sendableProgramsValues.add(usedLocalDataValueProperties.get(i));

                usedLocalDataNameProperties.clear();
                usedLocalDataValueProperties.clear();
                i--;
            } else if (remove == true) {
                usedLocalDataValueProperties.remove(i);
                usedLocalDataNameProperties.remove(i);
                i--;
            }
        }
        loadData(sendablePrograms, sendableProgramsValues);
    }

    @FXML
    public void cancelPressed() {
        parent.closeProgramRemoveListView();
        parent.closeProgramListView();
        parent.openUserView();
    }
}
