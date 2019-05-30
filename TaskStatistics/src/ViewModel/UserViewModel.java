package ViewModel;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;



public class UserViewModel {

    private ArrayList<SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;


    ArrayList<SimpleStringProperty> hours = new ArrayList<>();

    private ObservableModel observableModel;

    public UserViewModel(ObservableModel observableModel)
    {
        this.observableModel = observableModel;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        initializeProperties();
    }


    public void getLocalData() {
        System.out.println("UserViewModel: getLocalData: Calling ObservableModel getLocalData ");
        observableModel.getLocalData(ChartManager.SORTTYPE.RAW);
    }

    public void initializeProperties()
    {
        System.out.println("UserViewModel: initializeProperties: initializing the properties");
        for(int i=0;i<5;i++)
        {
            dataValueProperties.add(new SimpleDoubleProperty());
            dataNameProperties.add(new SimpleStringProperty());
        }
    }

    public void setValueProperties(ArrayList<DataPoint> list)
    {
        for(int i=0;i<list.size();i++)
        {
            System.out.println("UserViewModel: setValueProperties: loop: "+i+" value: "+list.get(i).getHours());
           dataValueProperties.get(i).set(list.get(i).getHours());
        }
    }

    public void setNameProperties(ArrayList<DataPoint> list)
    {
        System.out.println("UserViewModel: setNameProperties: list size is: "+list.size());
        for(int i=0;i<list.size();i++)
        {
            System.out.println("UserViewModel: setNameProperties: loop: "+i+" name: "+list.get(i).getId());
            dataNameProperties.get(i).set(list.get(i).getId());
        }
    }


    public void loadLocalData(Object data)
    {
        ArrayList<DataPoint> newArrayList = new ArrayList<>();
        newArrayList.addAll((ArrayList<DataPoint>)data);

        setValueProperties(newArrayList);
        setNameProperties(newArrayList);
    }


    public ArrayList<SimpleDoubleProperty> getDataValueProperties() { return dataValueProperties; }
    public ArrayList<SimpleStringProperty> getDataNameProperties() { return dataNameProperties; }




}
