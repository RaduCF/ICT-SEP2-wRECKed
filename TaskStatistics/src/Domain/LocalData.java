package Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class LocalData {
	private ArrayList<DataPoint> data;
	private int lastActiveIndex = 0;
	private String user;

	public LocalData(String user) {
		this.data = new ArrayList<DataPoint>();
		this.user = user;
	}
	
	/**
	 * updates local dataPoints and creates new if new programs are used
	 * @param CurrentActiveAPP
	 * @param isActive
	 */
	public void updateLocal(String CurrentActiveAPP, Boolean isActive) {
		Calendar now = Calendar.getInstance();
 		if (data.get(lastActiveIndex).getId().equals(CurrentActiveAPP)) {
			data.get(lastActiveIndex).update(isActive, now);
			return;
		}
		for (int i = 0; i < data.size(); i++) {
			if (CurrentActiveAPP.equals(data.get(i).getId())) {
				data.get(i).update(isActive, now);
				lastActiveIndex = i;
				return;
			}
		}
		data.add(new DataPoint(CurrentActiveAPP, user));
		lastActiveIndex = data.size()-1;
		data.get(lastActiveIndex).update(isActive, now);
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
	
	
}
