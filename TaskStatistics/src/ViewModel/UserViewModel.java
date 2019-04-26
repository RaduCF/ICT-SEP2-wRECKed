package ViewModel;

import Model.Domain.ChartManager;
import Model.Mediator.ObservableModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class UserViewModel {

    private DoubleProperty dataValueProperty1;
    private DoubleProperty dataValueProperty2;
    private DoubleProperty dataValueProperty3;
    private DoubleProperty dataValueProperty4;
    private DoubleProperty dataValueProperty5;

    private StringProperty dataNameProperty1;
    private StringProperty dataNameProperty2;
    private StringProperty dataNameProperty3;
    private StringProperty dataNameProperty4;
    private StringProperty dataNameProperty5;

    private ObservableModel observableModel;

    public UserViewModel()
    {
        dataValueProperty1 = new SimpleDoubleProperty();
        dataValueProperty2 = new SimpleDoubleProperty();
        dataValueProperty3 = new SimpleDoubleProperty();
        dataValueProperty4 = new SimpleDoubleProperty();
        dataValueProperty5 = new SimpleDoubleProperty();
        //dataValueProperty1.set(6658);


        dataNameProperty1 = new SimpleStringProperty();
        dataNameProperty2 = new SimpleStringProperty();
        dataNameProperty3 = new SimpleStringProperty();
        dataNameProperty4 = new SimpleStringProperty();
        dataNameProperty5 = new SimpleStringProperty();

        loadLocalData();
    }

    public void loadLocalData()
    {
        dataValueProperty1.set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(0).getHours());
        dataNameProperty1.set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(0).getId());

        dataValueProperty2.set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(1).getHours());
        dataNameProperty2.set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(1).getId());

        dataValueProperty3.set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(2).getHours());
        dataNameProperty3.set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(2).getId());

        dataValueProperty4.set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(3).getHours());
        dataNameProperty4.set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(3).getId());

        dataValueProperty5.set( (double) observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(4).getHours());
        dataNameProperty5.set(observableModel.getLocalData(ChartManager.SORTTYPE.RAW).get(4).getId());


    }

    public DoubleProperty getDataValueProperty(int index)
    {
        switch (index){
            case 1 : return dataValueProperty1;
            case 2 : return dataValueProperty2;
            case 3 : return dataValueProperty3;
            case 4 : return dataValueProperty4;
            case 5 : return dataValueProperty5;
        }
        return null;
    }

    public StringProperty getDataNameProperty(int index)
    {
        switch (index){
            case 1 : return dataNameProperty1;
            case 2 : return dataNameProperty2;
            case 3 : return dataNameProperty3;
            case 4 : return dataNameProperty4;
            case 5 : return dataNameProperty5;
        }
        return null;
    }


}
