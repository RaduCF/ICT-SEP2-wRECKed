import java.beans.Expression;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private String password = "password";
    private Connection connection;

    public Server() throws Exception {
        serverSocket = new ServerSocket(5001);

        //Setup server connection
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskSpy","postgres", "qwerty");
        connection.prepareStatement("SET SCHEMA 'TaskStatistics';").executeUpdate();

    }

    public void run() {

        System.out.println("Server started, waiting for connections.");
        while (true)
        {

            Socket socket = null;
            try {
                socket = serverSocket.accept();
                Thread T = new Thread(new User(this, socket));
                T.start();
                System.out.println("Client connected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean checkPassword(String pw)
    {
        return  password.equals(pw);
    }

    private void registerTask(String task) throws Exception {
        //Check if Task has been seen before

        String sql = "SELECT COUNT(*) FROM Task WHERE Name_ = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, task);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        //If the task is in the database dont add it again
        if (resultSet.getInt(1) != 0)
            return;

        //Program is new
        sql = "INSERT INTO Task VALUES ( ? , 0.0 );";
        PreparedStatement insert = connection.prepareStatement(sql);
        insert.setString(1, task);
        insert.executeUpdate();
    }

    public float requestAvgHours(String program) throws SQLException {
        String sql = "SELECT AverageHours FROM Task WHERE Name_ = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,program);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getFloat(1);
    }

    public void reportBug(String UserID, String comment) throws SQLException {

        if (comment.length() > 512)
            return;

        String sql = "INSERT INTO BugReport VALUES ( ? , ? );";
        PreparedStatement insert = connection.prepareStatement(sql);
        insert.setString(1, UserID);
        insert.setString(2, comment);
        insert.executeUpdate();
    }

    public void updateUsage(String userID, String program, float time) throws Exception {

        //Check if Task has been seen before
        registerTask(program);

        String sql = "SELECT COUNT(*) FROM Usage WHERE UserID = ? AND TaskName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        statement.setString(2, program);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        //If the tsak for this user is in the database just update it
        if (resultSet.getInt(1) != 0)
        {
            //Update row
            sql = "UPDATE Usage SET Hours = ? WHERE UserID = ? AND TaskName = ?";
            PreparedStatement update = connection.prepareStatement(sql);
            update.setDouble(1, time);
            update.setString(2, userID);
            update.setString(3, program);
            update.executeUpdate();
        }
        else
        {
            //Insert row
            sql = "INSERT INTO Usage VALUES ( ? , ? , ? );";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userID);
            insert.setString(2, program);
            insert.setDouble(3, time);
            insert.executeUpdate();



        }



    }


}
