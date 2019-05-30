package ViewModel;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class UserViewModel {

    private ArrayList<SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;
    private StringProperty title;

    ArrayList<SimpleStringProperty> hours = new ArrayList<>();

    private ObservableModel observableModel;

    public UserViewModel(ObservableModel observableModel)
    {
        this.observableModel = observableModel;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        title = new SimpleStringProperty();
        initializeProperties();
    }

    public void getMoreData(){
        System.out.println("UserViewModel: getMoreData: Calling ObservableModel getMoreData ");
        observableModel.getMoreData(ChartManager.SORTTYPE.RAW);
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
           dataValueProperties.get(i).setValue(list.get(i).getHours());
            System.out.println("UserViewModel: setValueProperties: loop: "+i+" value: "+dataValueProperties.get(i).getValue());
        }
    }

    public void setNameProperties(ArrayList<DataPoint> list)
    {
        System.out.println("UserViewModel: setNameProperties: list size is: "+list.size());
        for(int i=0;i<list.size();i++)
        {
            dataNameProperties.get(i).setValue(list.get(i).getId());
            System.out.println("UserViewModel: setNameProperties: loop: "+i+" name: "+dataNameProperties.get(i).getValue());
        }
    }

    public void loadLocalData(Object data)
    {
        ArrayList<DataPoint> newArrayList = new ArrayList<>();
        newArrayList.addAll((ArrayList<DataPoint>)data);

        setValueProperties(newArrayList);
        setNameProperties(newArrayList);
    }

    public ArrayList<SimpleDoubleProperty> getDataValueProperties() {
        return dataValueProperties;
    }

    public ArrayList<SimpleStringProperty> getDataNameProperties() {
        return dataNameProperties;
    }

    public StringProperty titleProperty() {
        return title;
    }
}
