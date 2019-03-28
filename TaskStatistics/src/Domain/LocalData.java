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
	
	public void uploadLocal() {
		
	}
	
	public void updateFromDatabase() {
		/*Imagine this is from the database*/ ArrayList<String> dbData = new ArrayList<String>();
		
		uploadLocal();
		for (int i = 0; i < dbData.size(); i++) {
			
		}
	
	}
	
}
