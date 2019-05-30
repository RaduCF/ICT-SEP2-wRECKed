package ViewModel;

import Model.Mediator.ObservableModel;
import View.MainView;
import javafx.application.Platform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainViewModel implements PropertyChangeListener {

    private LoginViewModel viewModelLogin;
    private UserViewModel userViewModel;
    private ObservableModel model;
    private MainView mainView;
    private ComparisonViewModel comparisonViewModel;
    private ProgramListViewModel programListViewModel;

    public MainViewModel(ObservableModel model) {
        this.model = model;
        
        userViewModel = new UserViewModel(model);
        viewModelLogin = new LoginViewModel(model);
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
            switch (evt.getPropertyName()){
                case "dataUpdate" : {
                    System.out.println("MainViewModel: propertyChange: loading userViewModel local data "+ evt.getNewValue().toString());
                    userViewModel.loadLocalData(evt.getNewValue());
                }
            }
        }
        );

    }


    public LoginViewModel getViewModelLogin() {
        return viewModelLogin;
    }
    
    public UserViewModel getUserViewModel() {
    return userViewModel;
    }

    public ComparisonViewModel getComparisonViewModel() {return comparisonViewModel;}

    public ProgramListViewModel getProgramListViewModel(){return programListViewModel;}


}
