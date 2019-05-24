package Model.Domain;

import java.util.ArrayList;


public class LocalData extends ChartManager {
	private ArrayList<DataPoint> data;
	private String user;
	private int LastActiveIndex = -1;
	private ChartManager chartManager;
    private TaskSpy taskSpy; 
    private Thread thread;

	public LocalData(String user) {
		this.data = new ArrayList<DataPoint>();
		this.user = user;
		this.chartManager = new ChartManager();
		taskSpy = new TaskSpy();
		
		thread = new Thread(taskSpy);
		thread.start();
	}
	
	/**
	 * updates local dataPoints and creates new if new programs are used
	 * @param CurrentActiveAPP
	 * @param isActive
	 */
	public void updateLocal() {
		synchronized (chartManager) {
			if (taskSpy.getIncoming().equals("")){
				return;
			}
			String currentActiveAPP = taskSpy.getIncoming();
			taskSpy.nullIncoming();
			System.out.println("Data added: " + currentActiveAPP);
			for (int i = 0; i < data.size(); i++) {
				if (currentActiveAPP.equals(data.get(i).getId())) {
					if (LastActiveIndex != -1) {
						data.get(LastActiveIndex).DeFocused();	
					}
					data.get(i).Focused();
					LastActiveIndex = i;
					return;
				}
			}
			DataPoint point = new DataPoint(currentActiveAPP);
			point.Focused();
			data.add(point);
			if (LastActiveIndex != -1) {
				data.get(LastActiveIndex).DeFocused();
			}
			LastActiveIndex = data.indexOf(point);	
			System.out.println(this.toString());
		}
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
	
	public String toString() {
		String out = user + " \n";
		for (int i = 0; i < data.size(); i++) {
			out += data.get(i).toString() + " \n";
		}
		return out;
	}
	
	public ArrayList<DataPoint> getData(SORTTYPE type){
		return this.getData(type, data);
	}
	
	public ArrayList<DataPoint> getSpecific(String[] apps){
		return this.getSpecific(apps);
	}
	
	
}
