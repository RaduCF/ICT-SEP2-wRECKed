package Model.Mediator;
import java.util.ArrayList;
import Model.Domain.ChartManager.SORTTYPE;
import Model.Domain.DataPoint;

public interface ClientModel {
	public ArrayList<DataPoint> getLocalData(SORTTYPE type);
}
