package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramListView {
    public TextField search;
    private MainView parent;
    private Scene scene;
    private String title;
    private int count;
    private ArrayList<SimpleStringProperty> dataNameProperties;

    private ArrayList<String> usedDataNames;
    @FXML
    private ListView<String> listView;

    public void init( MainView parent, Scene scene, String title) {
        this.parent = parent;
        this.scene = scene;
        this.title = title;

        search.setText("");

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        count = parent.getUserView().getDataNameProperties().size();

        usedDataNames = new ArrayList<>();

        this.dataNameProperties = new ArrayList<>();
        this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
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

        System.out.println("ProgramListView: submit: Selected program amount: " + programs.size());
        System.out.println("ProgramListView: submit: Number of all programs (loaded from UserView): " + dataNameProperties.size());
        System.out.println("ProgramListView: submit: All existent program data: " + dataNameProperties.toString());
        for (int i = 0; i < programs.size(); i++) {
            for (int j = 0; j < dataNameProperties.size(); j++) {
                if (programs.get(i).equals(dataNameProperties.get(j).getValue())) {
                    selected.add(dataNameProperties.get(j));
                }
            }
            sendableProgramNames.add(selected.get(i).getValue());
        }

        for (int i = 0; i < selected.size(); i++) {
            for (int j = 0; j < parent.getUserView().getDataNameProperties().size(); j++) {
                if (selected.get(i).getValue().equals(parent.getUserView().getDataNameProperties().get(j).getValue())) {
                    sendableProgramValues.add(parent.getUserView().getDataValueProperties().get(j).getValue());
                }
            }
        }

        System.out.println("Sending program names and values to the ComparisonView");
        try {
            parent.getComparisonView().loadData(sendableProgramNames, sendableProgramValues);
            System.out.println("ProgramListView: Submit: sending program values, values are: "+sendableProgramValues.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.getSelectionModel().clearSelection();
        parent.closeProgramListView();
    }

    public void loadData() {
        listView.getSelectionModel().clearSelection();

        boolean add;

        for (int i = 0; i < count; i++) {
            add = true;
            if (dataNameProperties.size() != 0) {
                for (int j = 0; j < usedDataNames.size(); j++) {
                    if (dataNameProperties.get(i).getValue().equals(usedDataNames.get(j)) || dataNameProperties.get(i).getValue().equals("EMPTY")) {
                        add = false;
                    }
                }
                if (add == true) {
                    listView.getItems().add(dataNameProperties.get(i).getValue());
                }

            }
        }
    }

    public void cancel() {
        parent.closeProgramListView();
    }

    public void setUsedDataNames(ArrayList<String> usedDataNames) {
        if (usedDataNames.size() != 0) {
            this.usedDataNames.addAll(usedDataNames);
        }
        loadData();
    }

    public void searchApp() {
        if (search.getText().equals("")) {
            this.dataNameProperties.clear();
            listView.getItems().clear();
            count = parent.getUserView().getDataNameProperties().size();
            this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
            loadData();
        } else {
            this.dataNameProperties.clear();
            listView.getItems().clear();
            int count = 0;
            for (int i = 0; i < parent.getUserView().getDataNameProperties().size(); i++) {
                if (search.getText().equals(parent.getUserView().getDataNameProperties().get(i).getValue())) {
                    this.dataNameProperties.add(parent.getUserView().getDataNameProperties().get(i));
                    count++;
                }
            }
            if (count != 0) {
                this.count = dataNameProperties.size();
                loadData();
            } else {
                this.count = parent.getUserView().getDataNameProperties().size();
                this.dataNameProperties.addAll(parent.getUserView().getDataNameProperties());
                loadData();
            }
        }
    }

    public void OnEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            searchApp();
        }
    }
}