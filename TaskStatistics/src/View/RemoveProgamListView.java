package View;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class RemoveProgamListView {
    private Scene scene;
    private String title;
    private MainView parent;

    @FXML
    private ListView listView;

    public void init(MainView parent, Scene scene, String title) {
        this.parent = parent;
        this.scene = scene;
        this.title = title;
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void loadData(ArrayList<String> usedDataNameProperties) {
        System.out.println("RemoveProgramListView: The amount of used data is: " + usedDataNameProperties.size());
        for (int i = 0; i < usedDataNameProperties.size(); i++) {
            listView.getItems().add(usedDataNameProperties.get(i));
        }
    }

    @FXML
    public void removePrograms() {
        ObservableList<String> programs;
        programs = listView.getSelectionModel().getSelectedItems();
        parent.getComparisonView().removeData(programs);
        parent.closeProgramRemoveListView();
    }

    public void cancelPressed() {
        parent.closeProgramRemoveListView();
    }
}
