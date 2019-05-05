package ViewModel;

import Model.Domain.ChartManager;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;


public class UserViewModel {

    private ArrayList<SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;

    private ObservableModel observableModel;

    public UserViewModel(ObservableModel observableModel)
    {
        this.observableModel = observableModel;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();

        loadLocalData();
    }

    public void loadLocalData()
    {

        for(int i=0;i<5;i++)
        {
            dataValueProperties.get(i).set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getHours());
            dataNameProperties.get(i).set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId());
        }
    }

    public SimpleDoubleProperty getDataValueProperty(int index)
    {

        return dataValueProperties.get(index);
    }

    public SimpleStringProperty getDataNameProperty(int index)
    {
        return dataNameProperties.get(index);
    }


}
