package Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class LocalData {
	private ArrayList<DataPoint> data;
	private String user;
	private int LastActiveIndex = 0;

	public LocalData(String user) {
		this.data = new ArrayList<DataPoint>();
		this.user = user;
	}
	
	/**
	 * updates local dataPoints and creates new if new programs are used
	 * @param CurrentActiveAPP
	 * @param isActive
	 */
	public void updateLocal(String CurrentActiveAPP) {
		for (int i = 0; i < data.size(); i++) {
			if (CurrentActiveAPP.equals(data.get(i).getId())) {
				continue;
			}
			data.get(LastActiveIndex).DeFocused();
			data.get(i).Focused();
			LastActiveIndex = i;
			return;
		}
		data.get(LastActiveIndex).DeFocused();
		data.add(new DataPoint(CurrentActiveAPP, user));
		LastActiveIndex = data.size()-1;
		data.get(LastActiveIndex).Focused();
	}
	
	/**
	 *  gets fresh data from DB
	 */
	public void refresh() {
		
		/*Magic upload/Download code*/
		
	}
	
	/**
	 * published data to db
	 */
	public void publish() {
		
	}
	
	public ArrayList<DataPoint> getData(){
		return this.data;
	}
	
	
}
