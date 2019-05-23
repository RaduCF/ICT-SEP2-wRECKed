package ViewModel;

import Model.Domain.ChartManager;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ComparisonViewModel {
    private SimpleIntegerProperty countProperty;
    private ObservableModel model;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    private ArrayList<SimpleDoubleProperty> dataValueProperties;

    public ComparisonViewModel(ObservableModel model){
        this.model = model;
        dataNameProperties = new ArrayList<>();
        dataValueProperties = new ArrayList<>();
        countProperty = new SimpleIntegerProperty();
    }

    public SimpleIntegerProperty getCountProperty(){return countProperty;}
    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }
    public ArrayList<SimpleDoubleProperty> getDataValueProperties() { return dataValueProperties; }


    public void loadData()
    {

       dataNameProperties.clear(); // clear all remaining data
        dataValueProperties.clear();

        initializeProperties(); // we initialize objects with this method

        for(int i=0;i<countProperty.intValue();i++) // as many as there are tasks:
        {
            //dataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId()); // get data name from data storage
            //dataValueProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getHours()); // get data value from data storage
            dataNameProperties.get(i).set("Testing program "+i); // dummy get data name from storage
            dataValueProperties.get(i).set(30+i*20); // dummy get data value from storage
        }
    }

    public void initializeProperties()
    {
        for(int i=0;i< countProperty.intValue();i++)
        {
            dataNameProperties.add(new SimpleStringProperty());
            dataValueProperties.add(new SimpleDoubleProperty());
        }
    }

}
