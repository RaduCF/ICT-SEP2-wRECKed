package ViewModel;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ProgramListViewModel {

    private ObservableModel model;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    private long counter;

    public ProgramListViewModel(ObservableModel model) {
        this.model = model;
        dataNameProperties = new ArrayList<>();
        counter=0;
    }

    public void loadLocalData() {
        ArrayList<DataPoint> newArrayList = new ArrayList<>();
        newArrayList.addAll(model.getData(ChartManager.SORTTYPE.BYHOURS));
        initializeProperties(newArrayList);
        setPropertyData(newArrayList);
        counter++;
        System.out.println("Counter incremented: " + counter);
    }

    /*public void loadData() {

        dataNameProperties.clear(); // clear all remaining data

        for (int i = 0; i < countProperty.intValue(); i++) // as many as there are tasks:
        {
            dataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId()); // get data name from data storage
        }
    }*/

    public void initializeProperties(ArrayList<DataPoint> list) {
        System.out.println("UserViewModel: initializeProperties: initializing the properties");
        if(dataNameProperties.size()<list.size())
        {
            for (int i = dataNameProperties.size(); i < list.size(); i++) {
                dataNameProperties.add(new SimpleStringProperty());
            }
        }
    }

    public void setPropertyData(ArrayList<DataPoint> list)
    {
        System.out.println("UserViewModel: setPropertyData: list size is: " + list.size());
        for(int i=0;i<list.size();i++)
        {
            dataNameProperties.get(i).setValue(list.get(i).getId());
            System.out.println("UserViewModel: setPropertyData: loop: " + i + " name: " + dataNameProperties.get(i).getValue());
        }
    }

    public long getCount() {
        return counter;
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }
}
