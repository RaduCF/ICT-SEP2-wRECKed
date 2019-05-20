package Model.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartManager {
	
	public enum SORTTYPE{
		BYHOURS,
		RAW
	}
	
	
	public ChartManager() {
	}
	
	public ArrayList<DataPoint> getData(SORTTYPE sorttype, ArrayList<DataPoint> data){
		ArrayList<DataPoint> Data = data;
		switch (sorttype) {
		case BYHOURS:
			
			 Collections.sort(Data);
			 return Data;

		case RAW :
			return Data;
		}
		
		return null;
	}
	
}
