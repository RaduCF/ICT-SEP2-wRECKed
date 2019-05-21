package ViewModel;

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

    public void initializeProperties()
    {
        for(int i=0;i< countProperty.intValue();i++)
        {
            dataNameProperties.add(new SimpleStringProperty());
            dataValueProperties.add(new SimpleDoubleProperty());
        }
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }

    public ArrayList<SimpleDoubleProperty> getDataValueProperties()
    {
        return dataValueProperties;
    }

    public void loadData()
    {
        // implement data loading
    }
}
