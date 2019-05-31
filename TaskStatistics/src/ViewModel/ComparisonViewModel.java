package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ComparisonViewModel {
    private ObservableModel model;
    private ArrayList<SimpleStringProperty> localDataNameProperties;
    private ArrayList<SimpleFloatProperty> localDataValueProperties;
    private ArrayList<SimpleStringProperty> globalDataNameProperties;
    private ArrayList<SimpleFloatProperty> globalDataValueProperties;
    private ArrayList<Float> temporaryDataValues;


    public ComparisonViewModel(ObservableModel model) {
        this.model = model;
        localDataNameProperties = new ArrayList<>();
        localDataValueProperties = new ArrayList<>();
        globalDataNameProperties = new ArrayList<>();
        globalDataValueProperties = new ArrayList<>();
        temporaryDataValues = new ArrayList<>();
    }

    public ArrayList<SimpleStringProperty> getLocalDataNameProperties() {
        return localDataNameProperties;
    }

    public ArrayList<SimpleFloatProperty> getLocalDataValueProperties() {
        return localDataValueProperties;
    }

    public ArrayList<SimpleStringProperty> getGlobalDataNameProperties() {
        return globalDataNameProperties;
    }

    public ArrayList<SimpleFloatProperty> getGlobalDataValueProperties() {
        return globalDataValueProperties;
    }



    public void receiveGlobalDataValue(Float receivedValue) {
        System.out.println("ComparisonViewModel: receiveGlobalData: received value: "+receivedValue);
        temporaryDataValues.add(receivedValue);
        if(globalDataValueProperties.size() == temporaryDataValues.size())
        {
            System.out.println("ComparisonViewModel: receiveGlobalDataVale: done loading into temporary. Loading into properties");
            storeGlobalDataValue();
        }
    }

    public void storeGlobalDataValue()
    {
        for(int i=0;i<globalDataNameProperties.size();i++)
        {
            globalDataValueProperties.get(i).setValue(temporaryDataValues.get(i));
            System.out.println("ComparisonViewModel: storeGlobalDataValue: loop:" +i+" property value set: "+globalDataValueProperties.get(i).getValue());
        }
        temporaryDataValues.clear();
    }

    public void getGlobalData(ArrayList<String> dataNames) {
        System.out.println("ComparisonViewModel: getGlobalData: retrieving global data from server");
        for(int i=0;i<dataNames.size();i++)
        {
            globalDataNameProperties.add(new SimpleStringProperty());
            globalDataValueProperties.add(new SimpleFloatProperty());
            globalDataNameProperties.get(globalDataNameProperties.size()-1).set(dataNames.get(i));
            model.getGlobalData(dataNames.get(i));

        }
    }
}
