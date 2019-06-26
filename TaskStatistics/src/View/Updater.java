package View;

import static java.lang.Thread.sleep;

public class Updater implements Runnable {
    private UserView userView;
    private long counter;

    public Updater(UserView userView) {
        this.userView = userView;
        counter=0;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(counter!=userView.getModel().getCounter()) {
                System.out.println("Counter difference found. Starting to load new data...");
                userView.initializeProperties();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Data initialised.");
                userView.bindProperties();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Data bound.");
                userView.handleBarChartData();
                System.out.println("Data loaded.");
                counter = userView.getModel().getCounter();
                System.out.println("counter equals: " + counter);
            }
        }
    }
}
