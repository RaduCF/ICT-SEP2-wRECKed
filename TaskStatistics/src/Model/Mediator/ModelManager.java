package Model.Mediator;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;
import java.io.IOException;

import Model.Domain.ChartManager.SORTTYPE;


public class ModelManager implements ObservableModel, ClientModel {

    private PropertyChangeSupport property;
    private Client client;


    public ModelManager(Client client) {
        this.client = client;
        this.property = new PropertyChangeSupport(this);
    }

    public void sendLocalData(SORTTYPE type) {
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
        System.out.println("Requiring more data...");
        System.out.println("The data received by MM is: " + client.getLocalData(type).toString());
        property.firePropertyChange("Moredata", client.getLocalData(type), client.getLocalData(type));
        System.out.println("Property fired in MM!");
    }

    @Override
    public void sendReport(String comment) throws IOException {
        client.reportBug(comment);
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

    @Override
    public void getGlobalData(String dataName)
    {
        float value = 0;
        try {
            value = client.getAvgHours(dataName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ModelManager: getGlobalData: firing event, value: " + value);
        property.firePropertyChange("Globaldata", null, value);
        System.out.println("ModelManager: getGlobalData: event fired, value: "+value);

    }
}
