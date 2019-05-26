package View;


import ViewModel.ComparisonViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private ArrayList<SimpleDoubleProperty> localDataValueProperties;
    private ArrayList <SimpleStringProperty> localDataNameProperties;
    private ArrayList<SimpleDoubleProperty> globalDataValueProperties;
    private ArrayList <SimpleStringProperty> globalDataNameProperties;

    private ArrayList<SimpleStringProperty> usedLocalDataNameProperties;
    private ArrayList<SimpleDoubleProperty> usedLocalDataValueProperties;
    private ArrayList<SimpleStringProperty> usedGlobalDataNameProperties;
    private ArrayList<SimpleDoubleProperty> usedGlobalDataValueProperties;


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
        usedGlobalDataNameProperties = new ArrayList<>();
        usedGlobalDataValueProperties = new ArrayList<>();
    }



    public void loadData(ArrayList<String> dataNames)
    {
        System.out.println("ComparisonView: loadData: received program amount: "+dataNames.size() + ", and toggle button is: "+global.isSelected());
        model.loadData( dataNames, global.isSelected());
        bindProperties(dataNames.size());
        // implement request to receive EXACT data objects

        handleBarChartData();
        localDataNameProperties.clear();
        localDataValueProperties.clear();
    }


    public void bindProperties(int size) // creates and binds this class properties to the existing model properties
    {
        localDataNameProperties.clear();
        localDataValueProperties.clear();
        globalDataNameProperties.clear();
        globalDataValueProperties.clear();

        if(!global.isSelected())
        {
            for(int i = 0; i< size; i++)
            {
                localDataNameProperties.add(new SimpleStringProperty());
                localDataValueProperties.add(new SimpleDoubleProperty());
                localDataNameProperties.get(i).bindBidirectional(model.getLocalDataNameProperties().get(i));
                localDataValueProperties.get(i).bindBidirectional(model.getLocalDataValueProperties().get(i));
            }
        }

        else if(global.isSelected())
        {
            for(int i = 0; i< size; i++)
            {
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

    public void handleBarChartData()
    {
        System.out.println("ComparisonView: handleBarChartData: executing data loading");

        if(!global.isSelected()) {

            for(int i = 0; i< localDataValueProperties.size(); i++) {
                XYChart.Series displaySet = new XYChart.Series();
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i).getValue(), localDataValueProperties.get(i).getValue()));
                System.out.println("ComparisonView: handleBarChartData: loop: "+i+" usedDataNameProperty name: "+ localDataNameProperties.get(i).getValue());
                barChart.getData().addAll(displaySet);
            }

        }

        else if(global.isSelected())
        {


            for(int i = 0; i< localDataValueProperties.size(); i++)
            {
                XYChart.Series displaySet = new XYChart.Series();
                displaySet.getData().add(new XYChart.Data(localDataNameProperties.get(i).getValue(), localDataValueProperties.get(i).getValue()));
                displaySet.getData().add(new XYChart.Data(globalDataNameProperties.get(i).getValue(), globalDataValueProperties.get(i).getValue()));
                System.out.println("ComparisonView: handleBarChartData: loop: "+i+" usedDataNameProperty name: "+ localDataNameProperties.get(i).getValue());
                barChart.getData().addAll(displaySet);
            }

        }


        moveUnusedToUsed();
        System.out.println("ComparisonView: handleBarChartData: Data loaded.");
    }

    @FXML
    public void setInterval(ActionEvent event)
    {

    }

    @FXML
    public void clearAllPrograms(ActionEvent event)
    {
        for(int i = 0; i< usedLocalDataValueProperties.size(); i++)
        {
            usedLocalDataValueProperties.clear();
            usedLocalDataNameProperties.clear();
        }
        barChart.getData().clear();
    }

    @FXML
    public void clearSpecificPrograms(ActionEvent event)
    {
        parent.openProgramRemoveListView();
        parent.getRemoveProgramListView().loadData(usedLocalDataNameProperties);
    }

    @FXML
    public void selectPrograms(ActionEvent event)
    {
        parent.openProgramListView(usedLocalDataNameProperties);
    }

    public Scene getScene() { return scene; }

    public String getTitle() {return title;}

    public void moveUnusedToUsed()
    {
        for(int i = 0; i< localDataNameProperties.size(); i++) {
            System.out.println("ComparisonView: moveUsedToUnused: loop: "+i);
            usedLocalDataNameProperties.add(localDataNameProperties.get(i));
            usedLocalDataValueProperties.add(localDataValueProperties.get(i));
        }
        localDataNameProperties.clear();
        localDataValueProperties.clear();

    }

    public void removeData(ObservableList<String> programs) {

        boolean remove;
        ArrayList<String> sendablePrograms = new ArrayList<>();

        barChart.getData().clear();

        for(int i = 0; i< usedLocalDataValueProperties.size(); i++) {
            remove = false;

            for(int j=0;j<programs.size();j++) {
                if(usedLocalDataNameProperties.get(i).getValue().equals(programs.get(j))) {
                  remove = true;
                }
            }
            if(remove == false) {
                //displaySet.getData().add(new XYChart.Data(usedLocalDataNameProperties.get(i).getValue(),usedLocalDataValueProperties.get(i).getValue()));
                //barChart.getData().addAll(displaySet);
                sendablePrograms.add(usedLocalDataNameProperties.get(i).getValue());
                usedLocalDataNameProperties.clear();
                usedLocalDataValueProperties.clear();
                i--;
            }
            else if(remove == true){
                usedLocalDataValueProperties.remove(i);
                usedLocalDataNameProperties.remove(i);
                i--;
            }
        }
        loadData(sendablePrograms);
    }
}
