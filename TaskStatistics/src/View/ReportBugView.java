package View;

import ViewModel.ReportBugViewModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import static java.lang.Thread.sleep;

public class ReportBugView {

    private MainView parent;
    private ReportBugViewModel model;
    private Scene scene;
    private String title;
    @FXML
    public TextArea Comment;
    @FXML
    public Label submitted;

    public ReportBugView(){}

    public void init(MainView parent , ReportBugViewModel model , Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        submitted.requestFocus();
        Comment.textProperty().bindBidirectional(model.commentProperty());
        submitted.textProperty().bindBidirectional(model.subbmitedProperty());
        model.commentProperty().setValue("");
        model.subbmitedProperty().setValue("");
    }

    public void SendReport() {
        model.sendReport();
        parent.openUserView();
    }

    public void CancelPressed() {
        parent.openUserView();
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
