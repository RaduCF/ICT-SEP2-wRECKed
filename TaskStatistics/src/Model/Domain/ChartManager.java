package Model.Domain;

import java.util.ArrayList;

public class ChartManager {
	private LocalData localData;
	
	public enum SORTTYPE{
		BYHOURS,
		ALPHABETICAL,
		RAW
	}
	
	
	public ChartManager(LocalData localData) {
		this.localData = localData;
	}
	
	public ArrayList<DataPoint> getRawData(){
		return localData.getData();
	}
	
	public ArrayList<DataPoint> getData(SORTTYPE sorttype){
		ArrayList<DataPoint> data = localData.getData();
		ArrayList<DataPoint> out = new ArrayList<DataPoint>();
		
		switch (sorttype) {
		case BYHOURS:
			
			//Magical sort to happen here
			
			break;

		case ALPHABETICAL:
			
			//and here
			
			break;
			
		case RAW :
			return localData.getData();
		}
		
		return null;
	}
	
}
