package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportBugViewModel {
    private ObservableModel model;
    private StringProperty comment;
    private StringProperty subbmited;


    public ReportBugViewModel(ObservableModel model){
        this.model=model;
        this.comment=new SimpleStringProperty();
        this.subbmited=new SimpleStringProperty();

    }

    public void sendReport(){
        model.sendReport(comment.getValue());
    }

    public StringProperty subbmitedProperty() {
        return subbmited;
    }

    public StringProperty commentProperty(){
        return comment;
    }
}
