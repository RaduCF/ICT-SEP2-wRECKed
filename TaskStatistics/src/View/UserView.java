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

        initializeProperties();
        bindProperties();
    }

    public UserView() {
    }

    public void initializeProperties() {
        System.out.println("UserView: initializeProperties: initializing the properties");
        for (int i = 0; i < 5; i++) {
            dataValueProperties.add(new SimpleFloatProperty());
            dataNameProperties.add(new SimpleStringProperty());
        }
    }

    public void bindProperties() {
        System.out.println("UserView: bindProperties: binding the properties");
        for (int i = 0; i < 5; i++) {
            dataValueProperties.get(i).bindBidirectional(model.getDataValueProperties().get(i));
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }

    public void handleBarChartData() {

        XYChart.Series displaySet = new XYChart.Series();
        barChart.getData().clear();

        for (int i = 0; i < 5; i++) {
            if (dataValueProperties.get(i) != null) {
                System.out.println("UserView: handleBarChartData: loop " + i);
                displaySet.getData().add(new XYChart.Data(dataNameProperties.get(i).getValue(), dataValueProperties.get(i).getValue()));
            }
        }
        barChart.getData().addAll(displaySet);
        System.out.println("Data loaded.");
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
        for (int i = 0; i < dataNameProperties.size(); i++) {
            System.out.println("UserView: nextPage: data name " + i + " is: " + dataNameProperties.get(i).getValue());
        }
    }

    @FXML
    public void previousPage() {
        System.out.println("Loading previous page..");
        //refresh();
        System.out.println("Page loaded.");
    }

    /*
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
    */
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
