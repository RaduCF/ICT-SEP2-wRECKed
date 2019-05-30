package Model.Mediator;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;
import Model.Domain.ChartManager.SORTTYPE;

import static java.lang.Thread.sleep;

public class ModelManager implements ObservableModel, ClientModel {

    private PropertyChangeSupport property;
    private Client client;

    public ModelManager(Client client) {
        this.client = client;
        this.property = new PropertyChangeSupport(this);
    }

    public void sendLocalData(SORTTYPE type)
    {
        System.out.println("ModelManager: sendLocalData: firing propertyChange " + type.toString());

        property.firePropertyChange("dataUpdate", client.getLocalData(type), client.getLocalData(type));

        System.out.println("ModelManager: sendLocalData: Property change fired!" + type.toString());
    }
    @Override
    public void getLocalData(SORTTYPE type) {
        System.out.println("ModelManager: getLocalData: getLocalData called");
         sendLocalData(type);
    }

    @Override
    public void getMoreData(SORTTYPE type) {
        System.out.println("rquirig data");
        property.firePropertyChange("Moredata", client.getMoreData(type), client.getMoreData(type));
    }

    @Override
    public boolean attemptLogin(String password) {
        return client.attemptLogin(password);
    }

    @Override
    public void sendReport(String comment) {
        //client.sendReport(comment);
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
