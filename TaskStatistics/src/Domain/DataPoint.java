package Domain;

import java.util.Calendar;

public class DataPoint {
	private String id;
	private boolean isActive;
	private float hoursActive;
	private Calendar LastUpdated;
	private String user;
	
	public DataPoint(String id, String user) {
		this.id = id;
		this.isActive = false;
		this.hoursActive = 0;
		this.user = user;
	}
	
	public void update(boolean isActive, Calendar timeStamp) {

		if ( isActive == false) {
			this.isActive = false;
		}
		else {
			if (this.isActive != isActive)
				this.isActive = true;
			
			this.hoursActive += (timeStamp.getTimeInMillis() - this.LastUpdated.getTimeInMillis()) / 3600000;
		}
	}
	
	public float getHours() {
		return this.hoursActive;
	}
	
	public String getId() {
		return this.id;
	}
}
