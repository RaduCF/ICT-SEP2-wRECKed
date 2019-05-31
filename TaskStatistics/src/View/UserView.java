package View;

import ViewModel.UserViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
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

    private ArrayList<SimpleFloatProperty> dataValueProperties;
    private ArrayList<SimpleStringProperty> dataNameProperties;



    public void init(MainView parent, UserViewModel model, Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        pageCount=0;

        initializeProperties();
        bindProperties();
    }

    public UserView() {
    }

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
        barChart.getData().clear();
        for (int i = pageCount*5; i < (pageCount*5)+5; i++) {
            if (dataValueProperties.get(i).getValue() != null) {
                displaySet.getData().add(new XYChart.Data(dataNameProperties.get(i).getValue(),dataValueProperties.get(i).getValue()));
            }
        }
        barChart.getData().addAll(displaySet);
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
                fixPage();
                handleBarChartData();
            }
        }
        catch (IndexOutOfBoundsException exception) {
            System.out.println("UserView: nextPage: next page does not exist!");
        }
    }

    public void fixPage()
    {
        for(int i=pageCount*5;i<pageCount*5+5;i++) {
            try {
                getDataNameProperties().get(i);
            } catch (IndexOutOfBoundsException exception) {
                getDataNameProperties().add(new SimpleStringProperty());
                getDataNameProperties().get(i).set("EMPTY");
                getDataValueProperties().add(new SimpleDoubleProperty());
                getDataValueProperties().get(i).setValue(0);
            }
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
}
