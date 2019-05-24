package Model.Domain;

import java.util.Calendar;

public class DataPoint implements Comparable<DataPoint> {
	private String id;
	private boolean isActive;
	private float minutesActive;
	private Calendar LastUpdated;

	public DataPoint(String id) {
		this.id = id;
		this.isActive = false;
		this.minutesActive = 0;
	}
	
	public void Focused() {
		Calendar now = Calendar.getInstance();
		isActive = true;
		LastUpdated = now;
		

	}
	
	public float getHours() {
		return this.minutesActive;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void DeFocused() {
		Calendar now = Calendar.getInstance();
		this.minutesActive += (now.getTimeInMillis() - this.LastUpdated.getTimeInMillis()) / 60000f;	
		LastUpdated = now;
		this.isActive = false;
		
	}
	
	public String toString() {
		return id + ": " + minutesActive;
	}

	public int compareTo(DataPoint DP) {
		return (int)(DP.getHours()-this.getHours());
	}

}
