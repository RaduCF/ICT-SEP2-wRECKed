package View;

import Model.Domain.DataPoint;
import ViewModel.UserViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class UserView implements PropertyChangeListener {
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

        model.addListener(this);

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
      //  handleInfo();

    }

    public void updateValueData()
    {
        model.getValueProperty();
    }

    public UserView() {

    }


    public void handleInfo(Object data) {

        ArrayList<DataPoint> dataArrayList= new ArrayList<>();
        dataArrayList.addAll((ArrayList<DataPoint>) data);

        XYChart.Series displaySet = new XYChart.Series();

        for(int i=0;i<dataArrayList.size();i++)
        {
            System.out.println("UserView: handleInfo: loop "+i);
            displaySet.getData().add(new XYChart.Data( dataArrayList.get(i).getId(), dataArrayList.get(i).getHours() ));
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
    public void comparison(ActionEvent event){
        parent.openComparisonView();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
                    System.out.println("UserView: propertyChange: A property change has been detected!");
                    switch (evt.getPropertyName()){
                        case "chartUpdate" : {
                            System.out.println("UserView: propertyChange: Changing bar chart data "+ evt.getNewValue().toString());
                            handleInfo(evt.getNewValue());
                        }
                    }
                }
        );
    }

}
