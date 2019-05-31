package View;


import ViewModel.ComparisonViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class ComparisonView {

    @FXML
    private BarChart barChart;
    @FXML
    private ToggleButton global;

    private Scene scene;
    private String title;

    private MainView parent;
    private ComparisonViewModel model;
    private ArrayList<Float> localDataValueProperties;
    private ArrayList<String> localDataNameProperties;
    private ArrayList<SimpleFloatProperty> globalDataValueProperties;
    private ArrayList<SimpleStringProperty> globalDataNameProperties;
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
        localDataNameProperties.addAll(dataNames);
        localDataValueProperties.addAll(dataValues);

        for(int i=0;i<localDataNameProperties.size();i++)
        {
            System.out.println("ComparisonView: loadData: local data name: "+localDataNameProperties.get(i));
            System.out.println("ComparisonView: loadData: local data value: "+localDataValueProperties.get(i));
        }

        if(global.isSelected()) {
            model.getGlobalData(dataNames);

            initializeGlobalProperties(dataNames);
            bindGlobalProperties(dataNames);

            Platform.runLater(() -> {
                handleBarChartData();
            });


        }

    }


    public void initializeGlobalProperties(ArrayList<String> dataNames) {
        for(int i=0;i<dataNames.size();i++)
        {
            globalDataNameProperties.add(new SimpleStringProperty());
            globalDataValueProperties.add(new SimpleFloatProperty());
        }
    }

    public void bindGlobalProperties(ArrayList<String> dataNames) {
        for(int i=0;i<dataNames.size();i++)
        {
            System.out.println("ComparisonView: bindGlobalProperties: binding the properties");
            globalDataNameProperties.get(i).bindBidirectional(model.getGlobalDataNameProperties().get(i));
            globalDataValueProperties.get(i).bindBidirectional(model.getGlobalDataValueProperties().get(i));
        }
    }


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
            System.out.println("ComparisonView: handleBarChartData: loading data into the chart...");
            for (int i = 0; i < localDataNameProperties.size(); i++) {
                XYChart.Series displaySet = new XYChart.Series();
                System.out.println("ComparisonView: handleBarChartData: loading local data name:" + localDataNameProperties.get(i));
                System.out.println("ComparisonView: handleBarChartData: loading local data value: "+localDataValueProperties.get(i));
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i), localDataValueProperties.get(i)));
                System.out.println("ComparisonView: handleBarChartData: loading global data name:" + globalDataNameProperties.get(i));
                System.out.println("ComparisonView: handleBarChartData: loading global data value: "+globalDataValueProperties.get(i));
                displaySet.getData().add(new XYChart.Data("global: "+globalDataNameProperties.get(i), globalDataValueProperties.get(i).getValue()));
                barChart.getData().addAll(displaySet);
            }

        }

        moveUnusedToUsed();
        System.out.println("ComparisonView: handleBarChartData: Data loaded.");
    }


    @FXML
    public void clearAllPrograms() {
        localDataValueProperties.clear();
        localDataNameProperties.clear();
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


    @FXML
    public void globalPressed()
    {
        if(global.isSelected())
        {
            global.setStyle("-fx-background-color: #c9c9c9");
        }
        else {
            global.setStyle("-fx-background-color: #0BA4B0");
        }

    }
}
