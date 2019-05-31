import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User implements Runnable
{
    private String UserID;
    private Socket socket;
    private DataOutputStream ostream;
    private DataInputStream istream;
    private Server server;

    public User(Server server,Socket socket) throws IOException {
        this.socket = socket;
        ostream = new DataOutputStream(socket.getOutputStream());
        istream = new DataInputStream(socket.getInputStream());
        this.server = server;
    }

    @Override
    public void run() {


        try {
            //Wait for user id
            UserID  = istream.readUTF();

            String program;
            float time;

            while (true)
            {
                int packetType = istream.readInt();
                if (packetType == 0) //Login
                {
                    String password = "password";
                    ostream.writeBoolean(password.equals(istream.readUTF()));
                }
                else if (packetType == 1) //Update table
                {
                    program = istream.readUTF();
                    time = istream.readFloat();

                    //Send to database
                    server.updateUsage(UserID,program, time);

                }
                else if (packetType == 2) //Request avg hours for a program
                {
                    program = istream.readUTF();
                    ostream.writeFloat(server.requestAvgHours(program));
                }
                else if (packetType == 3) //Bug report
                {
                    String bug = istream.readUTF();
                    server.reportBug(UserID, bug);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User disconnected");
        }


    }
}
