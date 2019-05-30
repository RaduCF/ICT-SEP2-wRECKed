package View;

import ViewModel.ProgramListViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ProgramListView {
    private ProgramListViewModel model;
    private MainView parent;
    private Scene scene;
    private String title;
    private SimpleIntegerProperty countProperty;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    private ArrayList<SimpleStringProperty> usedDataNameProperties;
    @FXML
    private ListView<String> listView;

    public void init(ArrayList<SimpleStringProperty> usedDataNameProperties, ProgramListViewModel model, MainView parent, Scene scene, String title)
    {
        this.model = model;
        this.parent = parent;
        this.scene = scene;
        this.title = title;
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countProperty = new SimpleIntegerProperty();
        this.dataNameProperties = new ArrayList<>();
        this.usedDataNameProperties = usedDataNameProperties;
        loadData();

    }



    public Scene getScene(){return scene;}
    public String getTitle(){return title;}

    @FXML
    public void submit() // sends data names to the comparisonView
    {
        ObservableList<String> programs;
        ArrayList<String> sendablePrograms = new ArrayList<>();
        programs = listView.getSelectionModel().getSelectedItems();
        System.out.println("ProgramListView: submit: Selected program ammount: "+ programs.size());
        for(int i=0;i<programs.size();i++)
        {
            sendablePrograms.add(programs.get(i));
        }
        parent.getComparisonView().loadData(sendablePrograms);
        listView.getSelectionModel().clearSelection();
        parent.closeProgramListView();
    }

    public void loadData() {
        model.loadData();
        bindProperties();
        boolean add;
        for(int i = 0; i< countProperty.intValue(); i++)
        {
            add=true;
            if(usedDataNameProperties.size()!=0) {
                for(int j=0;j<usedDataNameProperties.size();j++) {
                    if(dataNameProperties.get(i).getValue().equals(usedDataNameProperties.get(j).getValue()))
                    {
                       add=false;
                    }
                }
                if(add==true) {
                     listView.getItems().add(dataNameProperties.get(i).getValue());
                }
            }
            else {
                listView.getItems().add(dataNameProperties.get(i).getValue());
            }
        }
    }

    public void bindProperties() // creates and binds this class properties to the existing model properties
    {
        countProperty.bindBidirectional(model.getCountProperty());
        dataNameProperties.clear();

        for(int i = 0; i< countProperty.intValue(); i++) {
            dataNameProperties.add(new SimpleStringProperty());
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }
}
