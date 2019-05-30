package Model.Domain;

import java.util.Calendar;

public class DataPoint implements Comparable<DataPoint> {
	private String id;
	private boolean isActive;
	private float hoursActive;
	private Calendar LastUpdated;

	public DataPoint(String id) {
		this.id = id;
		this.isActive = false;
		this.hoursActive = 0;
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
		this.hoursActive += (now.getTimeInMillis() - this.LastUpdated.getTimeInMillis()) / 3600000f;
		LastUpdated = now;
		this.isActive = false;

	}

	public String toString() {
		return id + ": " + hoursActive;
	}

	public int compareTo(DataPoint DP) {
		if (0 >= DP.getHours()-this.getHours()) {
			return -1;
		}
		return 1;
	}

}
