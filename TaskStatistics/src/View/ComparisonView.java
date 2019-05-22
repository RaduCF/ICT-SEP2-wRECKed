package View;


import ViewModel.ComparisonViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private ArrayList<SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;
    private SimpleIntegerProperty count;


    public void init(MainView parent, ComparisonViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.scene = scene;
        this.title = title;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        count = new SimpleIntegerProperty();

    }

    @FXML
    public void selectPrograms(ActionEvent event)
    {
       parent.openProgramListView();
    }

    public void loadData(ObservableList<String> dataNames)
    {
        count.bindBidirectional(model.getCountProperty());
        count.set(dataNames.size());
        model.initializeProperties();
        for(int i=0;i<count.intValue();i++) {
            dataNameProperties.add(new SimpleStringProperty());
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
            dataValueProperties.get(i).bindBidirectional(model.getDataValueProperties().get(i));
        }

        // implement request to receive EXACT data objects

        handleData();
    }

    @FXML
    public void setInterval(ActionEvent event)
    {

    }

    public Scene getScene() { return scene; }

    public String getTitle() {return title;}

    public void handleData()
    {
        XYChart.Series displaySet = new XYChart.Series();

        System.out.println("Loading data..");
        model.loadData();
        for(int i=0;i<count.intValue();i++)
        {
            displaySet.getData().add(new XYChart.Data(dataValueProperties.get(i).doubleValue(), dataNameProperties.get(i).getValue()) );
        }
        barChart.getData().addAll(displaySet);
        System.out.println("Data loaded.");
    }

}
