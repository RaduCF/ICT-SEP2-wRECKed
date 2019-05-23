package Model.Domain;

import java.util.ArrayList;


public class LocalData extends ChartManager {
	private ArrayList<DataPoint> data;
	private String user;
	private int LastActiveIndex = -1;
	private ChartManager chartManager;
    private TaskSpy taskSpy = new TaskSpy(); 

	public LocalData(String user) {
		this.data = new ArrayList<DataPoint>();
		this.user = user;
		this.chartManager = new ChartManager();
		
		Thread taskspy = new Thread(taskSpy);
		taskspy.start();
	}
	
	/**
	 * updates local dataPoints and creates new if new programs are used
	 * @param CurrentActiveAPP
	 * @param isActive
	 */
	public void updateLocal() {
		String currentActiveAPP = taskSpy.incoming;
		taskSpy.incoming = "";
		if (taskSpy.incoming == "") {
			return;
		}
		if (LastActiveIndex == -1) {
			return;
		}
		for (int i = 0; i < data.size(); i++) {
			if (currentActiveAPP.equals(data.get(i).getId())) {
				continue;
			}
			data.get(LastActiveIndex).DeFocused();
			data.get(i).Focused();
			LastActiveIndex = i;
			return;
		}
		data.get(LastActiveIndex).DeFocused();
		data.add(new DataPoint(currentActiveAPP, user));
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
	
	public ArrayList<DataPoint> getData(SORTTYPE type){
		return this.getData(type, data);
	}
	
	public ArrayList<DataPoint> getSpecific(String[] apps){
		return this.getSpecific(apps);
	}
	
	
}
