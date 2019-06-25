package ViewModel;

import Model.Mediator.ObservableModel;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener {

    private UserViewModel userViewModel;
    private ObservableModel model;
    private ComparisonViewModel comparisonViewModel;
    private ProgramListViewModel programListViewModel;
    private ReportBugViewModel reportBugViewModel;

    public MainViewModel(ObservableModel model) {
        this.model = model;

        reportBugViewModel = new ReportBugViewModel(model);
        userViewModel = new UserViewModel(model);

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
            if (evt.getPropertyName().equals("dataUpdate")) {
                System.out.println("MainViewModel: propertyChange: loading userViewModel local data " + evt.getNewValue().toString());
                userViewModel.loadLocalData(evt.getNewValue());
            }
            else if (evt.getPropertyName().equals("Moredata")) {
                System.out.println("MainViewModel: propertyChange: loading userViewModel local data " + evt.getNewValue().toString());
                userViewModel.loadLocalData(evt.getNewValue());
            }
            else if(evt.getPropertyName().equals("Globaldata")) {
                System.out.println("MainViewModel: propertyChange: sending global data to the userViewModel,  value is:" + evt.getNewValue() );
                System.out.println("MainViewModel: propertyChange: sending global data to the userViewModel, name is:" + evt.getPropertyName() );

                comparisonViewModel.receiveGlobalDataValue(((float) evt.getNewValue()));
            }
        });
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public ComparisonViewModel getComparisonViewModel() {
        return comparisonViewModel;
    }

    public ProgramListViewModel getProgramListViewModel() {
        return programListViewModel;
    }

    public ReportBugViewModel getReportBugViewModel() {
        return reportBugViewModel;
    }
}
