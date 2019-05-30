package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.IOException;

public class ReportBugViewModel {
    private ObservableModel model;
    private StringProperty comment;
    private StringProperty submitted;

    public ReportBugViewModel(ObservableModel model) {
        this.model = model;
        this.comment = new SimpleStringProperty();
        this.submitted = new SimpleStringProperty();
    }

    public void sendReport() throws IOException {
        model.sendReport(comment.getValue());
    }

    public StringProperty submittedProperty() {
        return submitted;
    }

    public StringProperty commentProperty() {
        return comment;
    }
}
