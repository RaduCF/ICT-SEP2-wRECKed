package View;

import ViewModel.ReportBugViewModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class ReportBugView {

    private MainView parent;
    private ReportBugViewModel model;
    private Scene scene;
    private String title;
    @FXML
    public TextArea Comment;

    public ReportBugView(){}

    public void init(MainView parent , ReportBugViewModel model , Scene scene, String title) {
        this.parent = parent;
        this.model = model;
        this.title = title;
        this.scene = scene;

        Comment.setText(null);
        Comment.textProperty().bindBidirectional(model.commentProperty());
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
