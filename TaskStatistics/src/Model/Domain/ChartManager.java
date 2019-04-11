package Model.Domain;

import java.util.ArrayList;

public class ChartManager {
	
	public enum SORTTYPE{
		BYHOURS,
		ALPHABETICAL,
		RAW
	}
	
	
	public ChartManager() {
	}
	
	public ArrayList<DataPoint> getData(SORTTYPE sorttype, ArrayList<DataPoint> data){
		ArrayList<DataPoint> Data = data;
		ArrayList<DataPoint> out = new ArrayList<DataPoint>();
		
		switch (sorttype) {
		case BYHOURS:
			
			//Magical sort to happen here
			
			break;

		case ALPHABETICAL:
			
			//and here
			
			break;
			
		case RAW :
			return data;
		}
		
		return null;
	}
	
}
