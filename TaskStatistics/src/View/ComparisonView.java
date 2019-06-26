package View;


import ViewModel.ComparisonViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
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
    private ArrayList<Float> localDataValues;
    private ArrayList<String> localDataNames;
    private ArrayList<SimpleFloatProperty> globalDataValueProperties;
    private ArrayList<SimpleStringProperty> globalDataNameProperties;
    private ArrayList<String> usedLocalDataNames;
    private ArrayList<Float> usedLocalDataValues;

    public void init(MainView parent, ComparisonViewModel model, Scene scene, String title) {
        System.out.println();
        System.out.println(" INITIALIZING ComparisonView ...");
        System.out.println();
        this.parent = parent;
        this.model = model;
        this.scene = scene;
        this.title = title;
        localDataValues = new ArrayList<>();
        localDataNames = new ArrayList<>();
        globalDataValueProperties = new ArrayList<>();
        globalDataNameProperties = new ArrayList<>();

        usedLocalDataNames = new ArrayList<>();
        usedLocalDataValues = new ArrayList<>();
    }

    public void loadData(ArrayList<String> dataNames, ArrayList<Float> dataValues) throws IOException {
        System.out.println("ComparisonView: loadData: received program amount: " + dataNames.size() + ", and toggle button is: " + global.isSelected());
        localDataNames.addAll(dataNames);
        localDataValues.addAll(dataValues);

        /*  <-- Program reception from the ProgramListView is functional, commenting this for now to clear up the chat
        for(int i=0;i<localDataNames.size();i++)
        {
            System.out.println("ComparisonView: loadData: local data name: "+localDataNames.get(i));
            System.out.println("ComparisonView: loadData: local data value: "+localDataValues.get(i));
        }
*/

        if(global.isSelected()) {
            model.getGlobalData(dataNames);

            initializeGlobalProperties(dataNames);
            bindGlobalProperties(dataNames);

            Platform.runLater(() -> {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handleBarChartData();
            });
        }

        else
        {
            handleBarChartData();
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
            XYChart.Series displaySet = new XYChart.Series();
            for (int i = 0; i < localDataNames.size(); i++) {

                displaySet.getData().add(new XYChart.Data(localDataNames.get(i), localDataValues.get(i)));
                System.out.println("ComparisonView: handleBarChartData: loop: " + i + " usedDataNameProperty name: " + parent.getUserView().getDataNameProperties().get(i).getValue());

            }

            barChart.getData().addAll(displaySet);
            for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
                n.setStyle("-fx-bar-fill: red;");
            }
            //second bar color
            for(Node n:barChart.lookupAll(".default-color1.chart-bar")) {
                n.setStyle("-fx-bar-fill: green;");
            }


        }
        else if (global.isSelected()) {
            System.out.println("ComparisonView: handleBarChartData: loading data into the chart...");
            XYChart.Series localDisplaySet = new XYChart.Series();
            XYChart.Series globalDisplaySet = new XYChart.Series();
            for (int i = 0; i < localDataNames.size(); i++) {

                System.out.println("ComparisonView: handleBarChartData: loading local data name:" + localDataNames.get(i));
                System.out.println("ComparisonView: handleBarChartData: loading local data value: "+ localDataValues.get(i));
                localDisplaySet.getData().add(new XYChart.Data(localDataNames.get(i), localDataValues.get(i)));
                System.out.println("     ComparisonView: handleBarChartData: loading global data name:" + globalDataNameProperties.get(i).getValue());
                System.out.println("     ComparisonView: handleBarChartData: loading global data value: "+globalDataValueProperties.get(i).getValue());
                globalDisplaySet.getData().add(new XYChart.Data(localDataNames.get(i), globalDataValueProperties.get(i).getValue()));

            }
            barChart.getData().addAll(localDisplaySet, globalDisplaySet);
            for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
                n.setStyle("-fx-bar-fill: red;");
            }
            //second bar color
            for(Node n:barChart.lookupAll(".default-color1.chart-bar")) {
                n.setStyle("-fx-bar-fill: green;");
            }
        }
        moveUnusedToUsed();
        System.out.println("ComparisonView: handleBarChartData: Data loaded.");
    }


    @FXML
    public void clearAllPrograms() {
        localDataValues.clear();
        localDataNames.clear();
        usedLocalDataNames.clear();
        usedLocalDataValues.clear();
        barChart.getData().clear();
    }

    @FXML
    public void clearSpecificPrograms() {
        parent.closeProgramListView();
        parent.openProgramRemoveListView();
        parent.getRemoveProgramListView().loadData(usedLocalDataNames);
    }

    @FXML
    public void selectPrograms() {
        parent.closeProgramRemoveListView();
        parent.openProgramListView();
        parent.getProgramListView().setUsedDataNames(usedLocalDataNames);
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void moveUnusedToUsed() {
        for (int i = 0; i < localDataValues.size(); i++) {
            System.out.println("ComparisonView: moveUsedToUnused: loop: " + i);
            usedLocalDataNames.add(localDataNames.get(i));
            usedLocalDataValues.add(localDataValues.get(i));
        }
        localDataNames.clear();
        localDataValues.clear();
    }

    public void removeData(ObservableList<String> programs) {

        boolean remove;
        ArrayList<String> sendableProgramNames = new ArrayList<>();
        ArrayList<Float> sendableProgramValues = new ArrayList<>();

        barChart.getData().clear();

        for (int i = 0; i < usedLocalDataValues.size(); i++) {

            remove = false;

            for (int j = 0; j < programs.size(); j++) {
                if (usedLocalDataNames.get(i).equals(programs.get(j))) {
                    remove = true;
                }
            }
            if (remove == false) {
                sendableProgramNames.add(usedLocalDataNames.get(i));
                sendableProgramValues.add(usedLocalDataValues.get(i));

                usedLocalDataNames.remove(i);
                usedLocalDataValues.remove(i);
                if(usedLocalDataNames.size()!=0)i--;

            } else if (remove == true) {
                usedLocalDataValues.remove(i);
                usedLocalDataNames.remove(i);
                if(usedLocalDataNames.size()!=0)i--;
            }
        }
        try {
            loadData(sendableProgramNames, sendableProgramValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelPressed() {
        parent.closeProgramRemoveListView();
        parent.closeProgramListView();
        parent.openUserView();
        parent.getUserView().Update();
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
