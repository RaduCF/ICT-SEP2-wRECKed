package View;

import ViewModel.UserViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class UserView {
    private MainView parent;

    private UserViewModel model;
    private Scene scene;
    private String title;

    @FXML
    private BarChart barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


    private ArrayList <SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;



    public void init(MainView parent , UserViewModel model , Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        for(int i=0;i<5;i++)
        {
            dataValueProperties.add(new SimpleDoubleProperty());
            dataValueProperties.get(i).bindBidirectional(model.getDataValueProperty(1));
        }
        System.out.println("Starting info handling..");
        for(int i=0;i<5;i++)
        {
            dataNameProperties.add(new SimpleStringProperty());
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperty(1));
        }

        handleInfo();

    }

    public UserView() {

    }

    public void handleInfo() {

        yAxis.setLabel("Time");
        yAxis.setTickLabelRotation(90);
        xAxis.setLabel("Program");

        XYChart.Series displaySet = new XYChart.Series();

            System.out.println("Loading data..");
            model.loadLocalData();
        for(int i=0;i<5;i++)
        {
            displaySet.getData().add(new XYChart.Data(dataValueProperties.get(i).doubleValue(), dataNameProperties.get(i).getValue()) );
        }

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
