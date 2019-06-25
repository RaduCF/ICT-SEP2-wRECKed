package View;

import static java.lang.Thread.sleep;

public class UpdateProgramList implements Runnable{
    private ProgramListView programListView;
    private long counter;

    public UpdateProgramList(ProgramListView programListView) {
        this.programListView = programListView;
        counter=0;
    }


    @Override
    public void run() {
        while(true){
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(counter!=programListView.getModel().getCount()) {
                programListView.loadData();
            }
        }
    }
}
