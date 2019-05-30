package ViewModel;

import Model.Domain.ChartManager;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ProgramListViewModel {

    private ObservableModel model;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    private SimpleIntegerProperty countProperty;

    public ProgramListViewModel(ObservableModel model) {
        this.model = model;
        countProperty = new SimpleIntegerProperty();
        dataNameProperties = new ArrayList<>();
    }

    /*public void loadData() {

        dataNameProperties.clear(); // clear all remaining data

        countProperty.set(model.getLocalData(ChartManager.SORTTYPE.RAW).size());  // retrieve task count number from data storage
        //countProperty.set(2);  // dummy task count

        initializeProperties(); // we initialize objects with this method

        for (int i = 0; i < countProperty.intValue(); i++) // as many as there are tasks:
        {
            //dataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId()); // get data name from data storage
            dataNameProperties.get(i).set("Testing program " + i); // dummy get data name from storage
        }
    }

    public void initializeProperties() {
        for (int i = 0; i < countProperty.intValue(); i++) // as many as there are tasks:
        {
            dataNameProperties.add(new SimpleStringProperty());  // create simpleStringProperty
        }
    }

    public SimpleIntegerProperty getCountProperty() {
        return countProperty;
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }
    */
}
