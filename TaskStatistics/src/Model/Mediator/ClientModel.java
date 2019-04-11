package Model.Mediator;

import java.util.ArrayList;

import Model.Domain.ChartManager.SORTTYPE;
import Model.Domain.DataPoint;

public interface ClientModel {
    ArrayList<DataPoint> getLocalData(SORTTYPE type);

    boolean attemptLogin(String password);
}
