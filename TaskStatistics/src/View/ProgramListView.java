package View;

import ViewModel.ProgramListViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private SimpleIntegerProperty count;
    private ArrayList<SimpleStringProperty> dataNameProperties;
    @FXML
    private ListView<String> listView;

    public void init(ProgramListViewModel model, MainView parent, Scene scene, String title)
    {
        this.model = model;
        this.parent = parent;
        this.scene = scene;
        this.title = title;
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        count = new SimpleIntegerProperty();
        dataNameProperties = new ArrayList<>();

        loadData();

    }



    public Scene getScene(){return scene;}
    public String getTitle(){return title;}

    @FXML
    public void submit(ActionEvent evt) // sends data names to the comparisonView
    {
        ObservableList<String> programs;
        programs = listView.getSelectionModel().getSelectedItems();
        parent.getComparisonView().loadData(programs);
    }

    public void loadData() {

        model.loadData();
        bindProperties();

        for(int i=0;i<count.intValue();i++)
        {
            listView.getItems().add(dataNameProperties.get(i).getValue());
        }
    }

    public void bindProperties() // creates and binds this class properties to the existing model properties
    {
        count.bindBidirectional(model.getCountProperty());
        dataNameProperties.clear();

        for(int i=0;i<count.intValue();i++)
        {
            dataNameProperties.add(new SimpleStringProperty());
            dataNameProperties.get(i).bindBidirectional(model.getDataNameProperties().get(i));
        }
    }
}
