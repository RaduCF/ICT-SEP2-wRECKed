package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportBugViewModel {
    private ObservableModel model;
    private StringProperty comment;

    public ReportBugViewModel(ObservableModel model){
        this.model=model;
        this.comment=new SimpleStringProperty();
    }

    public StringProperty commentProperty(){
        return comment;
    }
    public void sendReport(){
        model.sendReport(comment.getValue());
    }
}
