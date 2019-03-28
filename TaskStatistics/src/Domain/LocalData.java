package Domain;

import java.util.ArrayList;


public class LocalData {
	private ArrayList<String> data;
	private ArrayList<String> _new;

	public LocalData() {
		this.data = updateFromDatabase();
		this._new = new ArrayList<String>();
	}
	
	public void updateLocal() {
		/*Get TaskSpy data*/
	}
	
	/**
	 * uploads all data and get's fresh data from DB
	 */
	public void uploadLocal() {
		
		/*Magic upload code*/
		
		this.data.clear();
	}
	
	public ArrayList<String> updateFromDatabase() {
		
		
	}
	
}
