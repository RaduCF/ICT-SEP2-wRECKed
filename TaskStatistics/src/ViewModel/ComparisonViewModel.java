package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ComparisonViewModel {
    private ObservableModel model;
    private ArrayList<SimpleStringProperty> localDataNameProperties;
    private ArrayList<SimpleDoubleProperty> localDataValueProperties;
    private ArrayList<SimpleStringProperty> globalDataNameProperties;
    private ArrayList<SimpleDoubleProperty> globalDataValueProperties;



    public ComparisonViewModel(ObservableModel model){
        this.model = model;
        localDataNameProperties = new ArrayList<>();
        localDataValueProperties = new ArrayList<>();
        globalDataNameProperties = new ArrayList<>();
        globalDataValueProperties = new ArrayList<>();
    }

    public ArrayList<SimpleStringProperty> getLocalDataNameProperties() {
        return localDataNameProperties;
    }
    public ArrayList<SimpleDoubleProperty> getLocalDataValueProperties() { return localDataValueProperties; }

    public ArrayList<SimpleStringProperty> getGlobalDataNameProperties() {
        return globalDataNameProperties;
    }
    public ArrayList<SimpleDoubleProperty> getGlobalDataValueProperties() { return globalDataValueProperties; }



    public void loadData(ArrayList<String> dataNames, boolean global)
    {

       localDataNameProperties.clear(); // clear all remaining data
        localDataValueProperties.clear();
        globalDataNameProperties.clear();
        globalDataValueProperties.clear();

        boolean add;


        initializeProperties(dataNames.size(), global ); // we initialize objects with this method
        int counter=0;
        System.out.println("ComparisonViewModel: LoadData: received program ammount: "+dataNames.size());
        if (global == false) {
            for(int i=0;i<2;i++) {       // as many as there are tasks:

                System.out.println("ComparisonViewModel: LoadData: Loop: "+i);
                add=false;
                //localDataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId()); // get data name from data storage
                //localDataValueProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getHours()); // get data value from data storage
                String dataName = "Testing program "+i;
                for(int j=0;j<dataNames.size();j++) // CHECK AND FIX THIS
                {
                    if(dataName.equals(dataNames.get(j)))
                    {
                        add=true;
                    }

                }

                if(add==true)
                {
                    System.out.println("WORKS!!!");
                    localDataNameProperties.get(counter).set(dataName);
                    localDataValueProperties.get(counter).set(50+i*100);
                    counter++;
                }

            }
        }

        else if(global == true)
        {
            for(int i=0;i<2;i++) {       // as many as there are tasks:

                System.out.println("ComparisonViewModel: LoadData: Loop: "+i);
                add=false;
                //localDataNameProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getId()); // get data name from data storage
                //localDataValueProperties.get(i).set(model.getLocalData(ChartManager.SORTTYPE.RAW).get(i).getHours()); // get data value from data storage
                String dataName = "Testing program "+i;
                for(int j=0;j<dataNames.size();j++) // CHECK AND FIX THIS
                {
                    if(dataName.equals(dataNames.get(j)))
                    {
                        add=true;
                    }

                }

                if(add==true)
                {
                    System.out.println("WORKS!!!");
                    localDataNameProperties.get(counter).set(dataName);
                    localDataValueProperties.get(counter).set(50+i*100);
                    globalDataNameProperties.get(counter).set("global: "+dataName);
                    globalDataValueProperties.get(i).set(75+i*125);
                    counter++;
                }

            }
        }

    }

    public void initializeProperties(int size, boolean global)
    {
        if(global == false){
            for(int i=0;i< size;i++) {
                localDataNameProperties.add(new SimpleStringProperty());
                localDataValueProperties.add(new SimpleDoubleProperty());
            }
        }
        else if(global == true) {
            for(int i=0;i< size;i++) {
                localDataNameProperties.add(new SimpleStringProperty());
                localDataValueProperties.add(new SimpleDoubleProperty());
                globalDataNameProperties.add(new SimpleStringProperty());
                globalDataValueProperties.add(new SimpleDoubleProperty());
            }
        }

    }

}
