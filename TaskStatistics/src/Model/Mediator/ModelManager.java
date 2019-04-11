package Model.Mediator;

import java.beans.PropertyChangeListener;

import Model.Domain.ChartManager.SORTTYPE;
import Model.Domain.DataPoint;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements ObservableModel, ClientModel {

    private PropertyChangeAction<Package, Boolean> property;
    private Client Client;

    public ModelManager(Client Client){
        this.Client = Client;
        this.property = new PropertyChangeProxy<>(this);
    }

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<DataPoint> getLocalData(SORTTYPE type) {
		return this.getLocalData(type);
	}

	
}
