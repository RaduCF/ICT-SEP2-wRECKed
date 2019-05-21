package View;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class Notification {
    public static void main(String[] args) throws AWTException, MalformedURLException {
        if (SystemTray.isSupported()) {
            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();

            //If the icon is a file
            Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\raduc\\OneDrive\\Desktop\\ICT-SEP2-wRECKed\\TaskStatistics\\src\\Notification.jpg");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image,"Tray");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("Task Statistics");
            tray.add(trayIcon);
            trayIcon.displayMessage("You are stupid!", "I'm joking, you're awesome!", TrayIcon.MessageType.INFO);
        } else {
            System.err.println("System tray not supported!");
        }
    }
}
