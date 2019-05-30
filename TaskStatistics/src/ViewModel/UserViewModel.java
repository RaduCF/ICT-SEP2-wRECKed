package ViewModel;

import Model.Domain.ChartManager;
import Model.Domain.DataPoint;
import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;



public class UserViewModel {

    private ArrayList<SimpleDoubleProperty> dataValueProperties;
    private ArrayList <SimpleStringProperty> dataNameProperties;
    private PropertyChangeSupport property;
    ArrayList<SimpleStringProperty> hours = new ArrayList<>();

    private ObservableModel observableModel;

    public UserViewModel(ObservableModel observableModel)
    {
        this.observableModel = observableModel;
        dataValueProperties = new ArrayList<>();
        dataNameProperties = new ArrayList<>();
        property = new PropertyChangeSupport(this);

        //calling  get local data...
    }


    public void getLocalData() {
        System.out.println("UserViewModel: getLocalData: Calling ObservableModel getLocalData ");
        observableModel.getLocalData(ChartManager.SORTTYPE.RAW);
    }


    public void createValueProperty(ArrayList<DataPoint> list)
    {

        for(int i=0;i<list.size();i++)
        {
            SimpleStringProperty newProperty = new SimpleStringProperty();
            newProperty.set(Float.toString(list.get(i).getHours()));
            hours.add(newProperty);
        }
        
    }

    public ArrayList<SimpleStringProperty> getValueProperty()
    {
        return hours;
    }

    public void loadLocalData(Object data)
    { System.out.println("UserViewModel: loadLocalData: "+data.toString());
        ArrayList<DataPoint> newArrayList = new ArrayList<>();

        newArrayList.addAll((ArrayList<DataPoint>)data);
        createValueProperty(newArrayList);
        for(int i=0;i<newArrayList.size();i++)
        {
            System.out.println("UserViewModel: loadLocalData: loop: "+i+" values: "+newArrayList.get(i).getId()+" "+newArrayList.get(i).getHours());
        }
        System.out.println("UserViewModel: loadLocalData: firing property change");
        property.firePropertyChange("chartUpdate", null, newArrayList);
        System.out.println("UserViewModel: loadLocalData: property change fired");

    }


    public SimpleDoubleProperty getDataValueProperty(int index) { return dataValueProperties.get(index); }
    public SimpleStringProperty getDataNameProperty(int index)
    {
        return dataNameProperties.get(index);
    }

    public void addListener(PropertyChangeListener listener) {
        this.property.addPropertyChangeListener(listener);
    }

}
