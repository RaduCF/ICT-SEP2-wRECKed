package View;

import ViewModel.UserViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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

    private DoubleProperty dataValueProperty1;
    private DoubleProperty dataValueProperty2;
    private DoubleProperty dataValueProperty3;
    private DoubleProperty dataValueProperty4;
    private DoubleProperty dataValueProperty5;

    private StringProperty dataNameProperty1;
    private StringProperty dataNameProperty2;
    private StringProperty dataNameProperty3;
    private StringProperty dataNameProperty4;
    private StringProperty dataNameProperty5;


    public void init(MainView parent , UserViewModel model , Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        dataValueProperty1 = new SimpleDoubleProperty();
        dataValueProperty2 = new SimpleDoubleProperty();
        dataValueProperty3 = new SimpleDoubleProperty();
        dataValueProperty4 = new SimpleDoubleProperty();
        dataValueProperty5 = new SimpleDoubleProperty();

        dataNameProperty1 = new SimpleStringProperty();
        dataNameProperty2 = new SimpleStringProperty();
        dataNameProperty3 = new SimpleStringProperty();
        dataNameProperty4 = new SimpleStringProperty();
        dataNameProperty5 = new SimpleStringProperty();

        dataValueProperty1.bindBidirectional(model.getDataValueProperty(1));
        dataValueProperty2.bindBidirectional(model.getDataValueProperty(2));
        dataValueProperty3.bindBidirectional(model.getDataValueProperty(3));
        dataValueProperty4.bindBidirectional(model.getDataValueProperty(4));
        dataValueProperty5.bindBidirectional(model.getDataValueProperty(5));

        dataNameProperty1.bindBidirectional(model.getDataNameProperty(1));
        dataNameProperty2.bindBidirectional(model.getDataNameProperty(2));
        dataNameProperty3.bindBidirectional(model.getDataNameProperty(3));
        dataNameProperty4.bindBidirectional(model.getDataNameProperty(4));
        dataNameProperty5.bindBidirectional(model.getDataNameProperty(5));

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
        displaySet.getData().add( new XYChart.Data(dataValueProperty1.doubleValue(), dataNameProperty1.getValue()));
        //displaySet.getData().add( new XYChart.Data(dataValueProperty2.doubleValue(), dataNameProperty2.getValue()));
        //displaySet.getData().add( new XYChart.Data(dataValueProperty3.doubleValue(), dataNameProperty3.getValue()));
        //displaySet.getData().add( new XYChart.Data(dataValueProperty4.doubleValue(), dataNameProperty4.getValue()));
        //displaySet.getData().add( new XYChart.Data(dataValueProperty5.doubleValue(), dataNameProperty5.getValue()));

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
        displaySet.getData().add(new XYChart.Data(dataValueProperty1.doubleValue(), dataNameProperty1.getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperty2.doubleValue(), dataNameProperty2.getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperty3.doubleValue(), dataNameProperty3.getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperty4.doubleValue(), dataNameProperty4.getValue()));
        displaySet.getData().add(new XYChart.Data(dataValueProperty5.doubleValue(), dataNameProperty5.getValue()));

        barChart.getData().addAll(displaySet);

        System.out.println("Data loaded.");
    }

}
