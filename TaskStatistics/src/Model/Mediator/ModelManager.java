package Model.Mediator;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

import java.util.ArrayList;

import Model.Domain.ChartManager.SORTTYPE;
import Model.Domain.DataPoint;

public class ModelManager implements ObservableModel, ClientModel {

    private PropertyChangeSupport property;
    private Client client;

    public ModelManager(Client client) {
        this.client = client;
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public ArrayList<DataPoint> getLocalData(SORTTYPE type) {
        return this.getLocalData(type);
    }

    @Override
    public boolean attemptLogin(String password) {
        //return client.attemptLogin(password);
        return true;
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {

    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        this.property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        this.property.removePropertyChangeListener(listener);
    }
}
