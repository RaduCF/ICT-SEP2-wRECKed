package ViewModel;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import Model.Mediator.ObservableModel;
import javafx.beans.property.*;

import java.util.ArrayList;

public class UserViewModel {

    private ArrayList<SimpleFloatProperty> dataValueProperties;
    private ArrayList<SimpleStringProperty> dataNameProperties;

    private ObservableModel observableModel;

    public UserViewModel(ObservableModel observableModel) {
        this.observableModel = observableModel;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
    }

    public void getMoreData() {
        System.out.println("UserViewModel: getMoreData: Calling ObservableModel getMoreData ");
        observableModel.getMoreData(ChartManager.SORTTYPE.BYHOURS);
    }

    public void getLocalData() {
        System.out.println("UserViewModel: getLocalData: Calling ObservableModel getLocalData ");
        observableModel.getLocalData(ChartManager.SORTTYPE.BYHOURS);
    }

    public void initializeProperties(ArrayList<DataPoint> list) {

        System.out.println("UserViewModel: initializeProperties: initializing the properties");
        if(dataNameProperties.size()<list.size())
        {
            for (int i = dataNameProperties.size(); i < list.size(); i++) {
                dataValueProperties.add(new SimpleFloatProperty());
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
            dataValueProperties.get(i).setValue(list.get(i).getHours());
            System.out.println("UserViewModel: setPropertyData: loop: " + i + " name: " + dataNameProperties.get(i).getValue()+" value: "+dataValueProperties.get(i).getValue());
        }
    }

    public void loadLocalData(Object data) {
        ArrayList<DataPoint> newArrayList = new ArrayList<>();
        newArrayList.addAll((ArrayList<DataPoint>) data);
        initializeProperties(newArrayList);
        setPropertyData(newArrayList);
    }

    public ArrayList<SimpleFloatProperty> getDataValueProperties() {
        return dataValueProperties;
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }


}
