package Model.Mediator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements ObservableModel{
    //private Client client;
    private PropertyChangeSupport property;

    public ModelManager(/*Client client*/)
    {
        //this.client = client
        this.property = new PropertyChangeSupport(this);
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
