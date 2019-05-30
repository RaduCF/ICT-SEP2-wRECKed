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
    private int countProperty;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    private ArrayList<SimpleStringProperty> usedDataNameProperties;
    @FXML
    private ListView<String> listView;

    public void init(ArrayList<String> usedDataNameProperties, ProgramListViewModel model, MainView parent, Scene scene, String title) {
        this.model = model;
        this.parent = parent;
        this.scene = scene;
        this.title = title;

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countProperty = parent.getUserView().getDataNameProperties().size();
        this.dataNameProperties = new ArrayList<>();
        this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
        loadData();
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    @FXML
    public void submit() // sends data names to the comparisonView
    {
        ObservableList<String> programs;
        ArrayList<String> sendablePrograms = new ArrayList<>();
        ArrayList<Double> sendableProgramsValues = new ArrayList<>();
        ArrayList<SimpleStringProperty> selected = new ArrayList<>();
        programs = listView.getSelectionModel().getSelectedItems();
        System.out.println("ProgramListView: submit: Selected program ammount: " + programs.size());
        System.out.println(programs.toString());
        System.out.println("Number of all apps: " + dataNameProperties.size());
        System.out.println("All existent apps: " + dataNameProperties.toString());
        for (int i = 0; i < programs.size(); i++) {
            for (int t = 0;t<dataNameProperties.size();t++) {
                if (programs.get(i).equals(dataNameProperties.get(t).getValue())){
                    selected.add(dataNameProperties.get(t));
                }
            }
            sendablePrograms.add(selected.get(i).getValue());
        }

        System.out.println("Programs sent to the comparison view: " + sendablePrograms.toString());
        for (int i = 0; i<selected.size();i++){
            if (selected.get(i).getValue().equals(parent.getUserView().getDataNameProperties().get(i).getValue())){
                sendableProgramsValues.add(parent.getUserView().getDataValueProperties().get(i).getValue());
            }
        }
        System.out.println("Hours per app sent: " + sendableProgramsValues.toString());
        System.out.println("Hours per all the existent apps: " + parent.getUserView().getDataValueProperties().toString());
        parent.getComparisonView().loadData(sendablePrograms, sendableProgramsValues);
        listView.getSelectionModel().clearSelection();
        parent.closeProgramListView();
    }

    public void loadData() {
        //model.loadData();
        //bindProperties();
        boolean add = true;
        for (int i = 0; i < countProperty; i++) {
            if (dataNameProperties.size() != 0) {
                //for (int j = 0; j < dataNameProperties.size(); j++) {
                //    if (dataNameProperties.get(i).getValue().equals(usedDataNameProperties.get(j).getValue())) {
                //        add = false;
                //    }
                //}
                if (add) {
                    listView.getItems().add(dataNameProperties.get(i).getValue());
                }
                //} else {
                //    listView.getItems().add(dataNameProperties.get(i).getValue());
                //}
            }
        }
    }

    /*public void bindProperties() // creates and binds this class properties to the existing model properties
    {
        countProperty.bindBidirectional(model.getCountProperty());
        dataNameProperties.clear();

        for (int i = 0; i < countProperty.intValue(); i++) {
            dataNameProperties.add(new SimpleStringProperty());
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }*/

    public void cancel() {
        parent.closeProgramListView();
    }
}
