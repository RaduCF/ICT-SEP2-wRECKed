package View;

import ViewModel.UserViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class UserView  {

    private MainView parent;
    private UserViewModel model;
    private Scene scene;
    private String title;

    @FXML
    private BarChart barChart;

    private ArrayList <SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;


    public void init(MainView parent , UserViewModel model , Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        // TESTING

        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();

        initializeProperties();
        bindProperties();

        handleBarChartData();
        System.out.println("Works!");
    }

    public UserView() {

    }

    public void initializeProperties()
    {
        System.out.println("UserView: initializeProperties: initializing the properties");
        for(int i=0;i<5;i++)
        {
            dataValueProperties.add(new SimpleDoubleProperty());
            dataNameProperties.add(new SimpleStringProperty());
        }
    }

    public void bindProperties()
    {
        System.out.println("UserView: bindProperties: binding the properties");
        for(int i=0;i<5;i++)
        {
            dataValueProperties.get(i).bindBidirectional(model.getDataValueProperties().get(i));
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }


    public void handleBarChartData() {
        System.out.println("Putting thread to sleep!");
        try {
            sleep(4567);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        XYChart.Series displaySet = new XYChart.Series();

        for(int i=0;i<2;i++)
        {
            System.out.println("UserView: handleBarChartData: loop "+i);
            displaySet.getData().add(new XYChart.Data( dataNameProperties.get(i).getValue(), dataValueProperties.get(i).getValue()));
        }

        barChart.getData().addAll(displaySet);
        System.out.println("Data loaded.");
    }




    public void refresh()
    {
        barChart.getData().clear();

        System.out.println("Loading data..");
        XYChart.Series displaySet = new XYChart.Series();
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(0).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(1).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(2).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(3).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(4).getValue()));

        barChart.getData().addAll(displaySet);

        System.out.println("Data loaded.");
    }


    public Scene getScene()
    {
        return scene;
    }

    public String getTitle()
    {
        return title;
    }

    @FXML
    public void comparison(ActionEvent event){
        parent.openComparisonView();
    }

    @FXML
    public void login(ActionEvent event)
    {
        parent.openLoginView();
    }

    @FXML
    public void nextPage(ActionEvent event)
    {
        System.out.println("Loading next page..");
        refresh();
        System.out.println("Page loaded.");
    }

    @FXML
    public void previousPage(ActionEvent event)
    {
        System.out.println("Loading previous page..");
        refresh();
        System.out.println("Page loaded.");
    }

    public void refresh()
    {
        model.loadLocalData();
        barChart.getData().clear();

        System.out.println("Loading data..");
        XYChart.Series displaySet = new XYChart.Series();
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(0).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(1).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(2).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(3).getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperties.get(0).doubleValue(), dataNameProperties.get(4).getValue()));

        barChart.getData().addAll(displaySet);

        System.out.println("Data loaded.");
    }

    @FXML
    public void comparison(){
        parent.openComparisonView();
    }

    public void Report() {
        parent.OpenSendReportView();
    }
}
