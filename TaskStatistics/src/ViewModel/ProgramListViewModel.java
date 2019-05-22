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

   public ProgramListViewModel(ObservableModel model)
    {
        this.model = model;
        countProperty = new SimpleIntegerProperty();
        dataNameProperties = new ArrayList<>();
    }

    public void loadData()
    {
        //countProperty.set(model.getLocalData(ChartManager.SORTTYPE.RAW).size());
        countProperty.set(1);
        for(int i=0;i<countProperty.intValue();i++)
        {
            dataNameProperties.add(new SimpleStringProperty());
            //dataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId());
            dataNameProperties.get(i).set("Testing program 1");
        }
    }

    public SimpleIntegerProperty getCountProperty()
    {
        return countProperty;
    }
    public ArrayList<SimpleStringProperty> getDataNameProperties()
    {
        return dataNameProperties;
    }
}
