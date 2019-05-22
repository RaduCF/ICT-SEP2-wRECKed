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
            Image image = Toolkit.getDefaultToolkit().getImage("Notification.jpg");
            TrayIcon trayIcon = new TrayIcon(image,"Tray");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("Task Statistics");
            tray.add(trayIcon);
            trayIcon.displayMessage("You are stupid!", "I'm joking, you're awesome!", MessageType.INFO);
        } else {
            System.err.println("System tray not supported!");
        }
    }
}
