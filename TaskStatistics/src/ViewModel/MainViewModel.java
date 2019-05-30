package ViewModel;

import Model.Mediator.ObservableModel;
import View.MainView;
import javafx.application.Platform;
import sun.applet.Main;

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
        mainView = MainView.getInstance(this);
        comparisonViewModel = new ComparisonViewModel(model);
        programListViewModel = new ProgramListViewModel(model);
        System.out.println("MainViewModel: Constructor: adding listener to ObservableModel");
        model.addListener(this);
        System.out.println("MainViewModel: Constructor: added listener to ObservableModel");
        userViewModel.getLocalData();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Platform.runLater(() -> {
                    System.out.println("MainViewModel: propertyChange: A property change has been detected!");
            if (evt.getPropertyName().equals("dataUpdate")){
                    System.out.println("MainViewModel: propertyChange: loading userViewModel local data "+ evt.getNewValue().toString());
                    userViewModel.loadLocalData(evt.getNewValue());
                    mainView.loadData();
            }
            else if(evt.getPropertyName().equals("Moredata")){
                System.out.println("MainViewModel: propertyChange: loading userViewModel local data "+ evt.getNewValue().toString());
                userViewModel.loadLocalData(evt.getNewValue());
                mainView.loadData();
            }
        });

    }

    public LoginViewModel getViewModelLogin() { return viewModelLogin; }

    public UserViewModel getUserViewModel() { return userViewModel; }

    public ComparisonViewModel getComparisonViewModel() { return comparisonViewModel;}

    public ProgramListViewModel getProgramListViewModel(){ return programListViewModel;}

    public ReportBugViewModel getReportBugViewModel() { return reportBugViewModel; }
}
