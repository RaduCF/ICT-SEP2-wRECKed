package ViewModel;

import Model.Mediator.ObservableModel;
import View.MainView;
import javafx.application.Platform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener {

    private LoginViewModel viewModelLogin;
    private UserViewModel userViewModel;
    private ObservableModel model;
    private MainView mainView;
    private ComparisonViewModel comparisonViewModel;
    private ProgramListViewModel programListViewModel;
    private ReportBugViewModel reportBugViewModel;

    public MainViewModel(ObservableModel model) {
        this.model = model;

        reportBugViewModel=new ReportBugViewModel(model);
        userViewModel = new UserViewModel(model);
        viewModelLogin = new LoginViewModel(model);
        comparisonViewModel = new ComparisonViewModel(model);
        programListViewModel = new ProgramListViewModel(model);
        model.addListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

        });
    }

    public LoginViewModel getViewModelLogin() { return viewModelLogin; }
    
    public UserViewModel getUserViewModel() { return userViewModel; }

    public ComparisonViewModel getComparisonViewModel() { return comparisonViewModel;}

    public ProgramListViewModel getProgramListViewModel(){ return programListViewModel;}

    public ReportBugViewModel getReportBugViewModel() { return reportBugViewModel; }
}
