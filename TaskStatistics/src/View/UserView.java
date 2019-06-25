package View;

import ViewModel.UserViewModel;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class UserView {

    private MainView parent;
    private UserViewModel model;
    private Scene scene;
    private String title;
    private int pageCount;
    @FXML
    private BarChart barChart;
    @FXML
    private Label Title;
    private Updater updater;
    private ArrayList<SimpleFloatProperty> dataValueProperties;
    private ArrayList<SimpleStringProperty> dataNameProperties;

    public void init(MainView parent, UserViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;
        this.updater=new Updater(this);

        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        pageCount=0;

        Thread upd = new Thread(updater);
        upd.start();
    }

    public UserView() { }

    public void initializeProperties() {
        System.out.println("UserView: initializeProperties: initializing the properties");
        for (int i = dataNameProperties.size(); i < model.getDataNameProperties().size(); i++) {
            dataValueProperties.add(new SimpleFloatProperty());
            dataNameProperties.add(new SimpleStringProperty());
        }
    }

    public void bindProperties() {
        System.out.println("UserView: bindProperties: binding the properties");
        for (int i = 0; i < model.getDataNameProperties().size(); i++) {
            dataValueProperties.get(i).bindBidirectional(model.getDataValueProperties().get(i));
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }

    public void handleBarChartData() {
        System.out.println("UserView: handleBarChartData: Loading data into bar chart");
        XYChart.Series displaySet = new XYChart.Series();
        Platform.runLater(() -> {
            barChart.getData().clear();
        });
        for (int i = pageCount*5; i < (pageCount*5)+5; i++) {
            System.out.println("Adding data to barchart: loop " + i+": " + dataNameProperties.get(i).getValue() + " + "+ dataValueProperties.get(i).getValue());
            try {
                displaySet.getData().add(new XYChart.Data(dataNameProperties.get(i).getValue(),dataValueProperties.get(i).getValue()));
            } catch (IndexOutOfBoundsException exception) {
                displaySet.getData().add(new XYChart.Data("EMPTY",(float)0));
            }
        }
        Platform.runLater(() -> {
                    barChart.getData().addAll(displaySet);
                });
        System.out.println("UserView: handleBarChartData: Data loaded into bar chart.");
    }

    public ArrayList<SimpleFloatProperty> getDataValueProperties(){
        return dataValueProperties;
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties(){
        return dataNameProperties;
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    @FXML
    public void nextPage() {
        try{
            if(dataNameProperties.get((pageCount+1)*5)!=null) {
                System.out.println("UserView: nextPage: loading next page");
                pageCount++;
                handleBarChartData();
            }
        }
        catch (IndexOutOfBoundsException exception) {
            System.out.println("UserView: nextPage: next page does not exist!");
        }
    }

    @FXML
    public void previousPage() {
        if(pageCount!=0) {
        pageCount--;
        System.out.println("UserView: previousPage: loading previous page");
        handleBarChartData();
        }
        else System.out.println("UserView: previousPage: Previous page does not exist.");
    }

    @FXML
    public void comparison() {
        parent.openComparisonView();
    }

    @FXML
    public void Report() {
        parent.OpenSendReportView();
    }

    @FXML
    public void Update() {
        model.getMoreData();
    }

    public UserViewModel getModel(){
        return model;
    }
}
