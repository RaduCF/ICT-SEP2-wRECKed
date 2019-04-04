package UserViewPack;

<<<<<<< HEAD
import javafx.event.ActionEvent;
=======
>>>>>>> master
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UserView {
    private MainView parent;
<<<<<<< HEAD
    //private UserViewModel model;
=======
    private UserViewModel model;
>>>>>>> master
    private Scene scene;
    private String title;
    private Package data;

    @FXML
    private BarChart barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


<<<<<<< HEAD
    public void init(MainView parent /*, UserViewModel model */, Scene scene, String title/*  Package data */) {
=======
    public void init(MainView parent , UserViewModel model , Scene scene, String title/*  Package data */) {
>>>>>>> master
        this.parent = parent;
       // this.model = model;
        this.title = title;
        this.data = data;
        this.scene = scene;

<<<<<<< HEAD
        loadFirstPage();
=======
        handleInfo();
>>>>>>> master

    }

    public UserView() {

    }

<<<<<<< HEAD
    public void loadFirstPage() {
=======
    public void handleInfo() {
>>>>>>> master

        yAxis.setLabel("Time");
        yAxis.setTickLabelRotation(90);
        xAxis.setLabel("Program");

        XYChart.Series displaySet = new XYChart.Series();



<<<<<<< HEAD
       /* for(int i=0;i<data.getList().size();i++)
=======
        for(int i=0;i<data.getList().size();i++)
>>>>>>> master
        {
            displaySet.getData().add(new XYChart.Data(data.getList().timeToInt(), data.getList().getName()));
        }

            System.out.println("Adding info!");
<<<<<<< HEAD
         */


        displaySet.getData().add( new XYChart.Data(68.34, "kelan") );
        displaySet.getData().add( new XYChart.Data(8000, "kelan5") );
        displaySet.getData().add( new XYChart.Data(6000, "kelan64") );
        displaySet.getData().add( new XYChart.Data(9847, "rasasine") );

=======
            /*  DUMMY DATA
        displaySet.getData().add( new XYChart.Data("austria", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data("austria2", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data("austria3", 25601.34 ) );
        displaySet.getData().add( new XYChart.Data(68.34, "kelan") );
        displaySet.getData().add( new XYChart.Data(9847, "rasasine") );
            */
>>>>>>> master
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
<<<<<<< HEAD

    @FXML
    public void login(ActionEvent event)
    {
        System.out.println("Login button pressed.");
    }

    @FXML
    public void nextPage(ActionEvent event)
    {
        System.out.println("next button pressed.");
        barChart.getData().clear();
        XYChart.Series displaySet = new XYChart.Series();
        //displaySet.getData().add(new XYChart.Data(data.getList().timeToInt(), data.getList().getName()));
        barChart.getData().addAll(displaySet);
    }

    @FXML
    public void previousPage(ActionEvent event)
    {
        System.out.println("previous button pressed.");
        barChart.getData().clear();
        XYChart.Series displaySet = new XYChart.Series();
        //displaySet.getData().add(new XYChart.Data(data.getList().timeToInt(), data.getList().getName()));
        barChart.getData().addAll(displaySet);
    }

=======
>>>>>>> master
}
