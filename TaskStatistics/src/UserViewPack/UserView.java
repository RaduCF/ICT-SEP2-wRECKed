package UserViewPack;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UserView {
    private MainView parent;
    private UserViewModel model;
    private Scene scene;
    private String title;
    private Package data;

    @FXML
    private BarChart barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


    public void init(MainView parent , UserViewModel model , Scene scene, String title/*  Package data */) {
        this.parent = parent;
       // this.model = model;
        this.title = title;
        this.data = data;
        this.scene = scene;

        handleInfo();

    }

    public UserView() {

    }

    public void handleInfo() {

        yAxis.setLabel("Time");
        yAxis.setTickLabelRotation(90);
        xAxis.setLabel("Program");

        XYChart.Series displaySet = new XYChart.Series();



        for(int i=0;i<data.getList().size();i++)
        {
            displaySet.getData().add(new XYChart.Data(data.getList().timeToInt(), data.getList().getName()));
        }

            System.out.println("Adding info!");
            /*  DUMMY DATA
        displaySet.getData().add( new XYChart.Data("austria", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data("austria2", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data("austria3", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data(68.34, "kelan") );
        displaySet.getData().add( new XYChart.Data(9847, "rasasine") );
            */
        barChart.getData().addAll(displaySet);


    }

    public Scene getScene()
    {

        return scene;
    }

    public String getTitle()
    {
        return title;
    }
}
