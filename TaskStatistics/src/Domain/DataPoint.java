package Domain;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;

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
	
	public void Focused() {
		Calendar now = Calendar.getInstance();
		isActive = true;
		LastUpdated = now;
		

	}
	
	public float getHours() {
		return this.hoursActive;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void DeFocused() {
		Calendar now = Calendar.getInstance();
		this.hoursActive += (now.getTimeInMillis() - this.LastUpdated.getTimeInMillis()) / 3600000;	
		LastUpdated = now;
		this.isActive = false;
		
	}
}
