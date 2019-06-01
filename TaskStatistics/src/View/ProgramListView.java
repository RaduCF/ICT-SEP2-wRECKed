package View;

import ViewModel.ProgramListViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class ProgramListView {
    public TextField search;
    private ProgramListViewModel model;
    private MainView parent;
    private Scene scene;
    private String title;
    private int count;
    private ArrayList<SimpleStringProperty> dataNameProperties;

    private ArrayList<SimpleStringProperty> usedDataNameProperties;
    @FXML
    private ListView<String> listView;

    public void init(ArrayList<String> usedDataNameProperties, ProgramListViewModel model, MainView parent, Scene scene, String title) {
        this.model = model;
        this.parent = parent;
        this.scene = scene;
        this.title = title;

        search.setText("");

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        count = parent.getUserView().getDataNameProperties().size();

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
        programs = listView.getSelectionModel().getSelectedItems();

        ArrayList<String> sendableProgramNames = new ArrayList<>();
        ArrayList<Float> sendableProgramValues = new ArrayList<>();

        ArrayList<SimpleStringProperty> selected = new ArrayList<>();


        System.out.println("ProgramListView: submit: Selected program ammount: " + programs.size());
        //System.out.println(programs.toString());  <-- too many printout statements, this works so removing it
        System.out.println("ProgramListView: submit: Number of all programs (loaded from UserView): " + dataNameProperties.size());
        System.out.println("ProgramListView: submit: All existent program data: " + dataNameProperties.toString());
        for (int i = 0; i < programs.size(); i++) {
            for (int t = 0;t<dataNameProperties.size();t++) {
                if (programs.get(i).equals(dataNameProperties.get(t).getValue())){
                    selected.add(dataNameProperties.get(t));
                }
            }
            sendableProgramNames.add(selected.get(i).getValue());
        }

        //System.out.println("Programs sent to the comparison view: " + sendableProgramNames.toString());
        for (int i = 0; i<selected.size();i++){
            for (int t=0;t<parent.getUserView().getDataNameProperties().size();t++) {
                if (selected.get(i).getValue().equals(parent.getUserView().getDataNameProperties().get(t).getValue())) {
                    sendableProgramValues.add(parent.getUserView().getDataValueProperties().get(i).getValue());
                }
            }
        }
        //System.out.println("Hours per app sent: " + sendableProgramValues.toString());
        //System.out.println("Hours per all the existent apps: " + parent.getUserView().getDataValueProperties().toString());
        System.out.println("Sending program names and values to the ComparisonView");
        parent.getComparisonView().loadData(sendableProgramNames, sendableProgramValues);
        listView.getSelectionModel().clearSelection();
        parent.closeProgramListView();
    }

    public void loadData() {
        listView.getSelectionModel().clearSelection();

        boolean add = true;
        for (int i = 0; i < count; i++) {
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



    public void cancel() {
        parent.closeProgramListView();
    }


    public void searchApp() {
        if (search.getText().equals("")){
            this.dataNameProperties.clear();
            listView.getItems().clear();
            count = parent.getUserView().getDataNameProperties().size();
            this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
            loadData();
        }
        else {
            this.dataNameProperties.clear();
            listView.getItems().clear();
            int count=0;
            for (int i = 0; i < parent.getUserView().getDataNameProperties().size(); i++) {
                if (search.getText().equals(parent.getUserView().getDataNameProperties().get(i).getValue())) {
                    this.dataNameProperties.add(parent.getUserView().getDataNameProperties().get(i));
                    count++;
                }
            }
            if (count!=0){
                this.count = dataNameProperties.size();
                loadData();
            }
            else{
                this.count = parent.getUserView().getDataNameProperties().size();
                this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
                loadData();
            }
        }
    }

    public void OnEnter(KeyEvent keyEvent) {
            if(keyEvent.getCode() == KeyCode.ENTER){
                searchApp();
            }
    }
}
