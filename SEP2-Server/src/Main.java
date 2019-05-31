import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Server server = new Server();
            Thread serverThread = new Thread(server);
            serverThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
