package com.cb.system.tray;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.logging.LogManager;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class TimeManager {
		
	private static Logger LOGGER = Logger.getLogger(TimeManager.class);
	
	public static void main(String args[]){		
		    Runnable runner = new Runnable() {
		      public void run() {
		        if (SystemTray.isSupported()) {
		          SystemTray tray = SystemTray.getSystemTray();
		          URL localImage = getClass().getResource("/exclamation.png");
		          Image image = Toolkit.getDefaultToolkit().getImage(localImage);
		          
		          MouseListener mouseListener = new MouseListener() {
		        	  @Override
		              public void mouseClicked(MouseEvent e) {
		                  LOGGER.info("Tray Icon - Mouse clicked!");
		              }
		        	  @Override
		              public void mouseEntered(MouseEvent e) {//doesn't work
		                  LOGGER.info("Tray Icon - Mouse entered!");
		              }
		        	  @Override
		              public void mouseExited(MouseEvent e) {//doesn't work
		                  LOGGER.info("Tray Icon - Mouse exited!");
		              }
		        	  @Override
		              public void mousePressed(MouseEvent e) {
		                  LOGGER.info("Tray Icon - Mouse pressed!");
		              }
		        	  @Override
		              public void mouseReleased(MouseEvent e) {
		                  LOGGER.info("Tray Icon - Mouse released!");
		              }
		          };
		          
		          ActionListener exitListener = new ActionListener() {
		              public void actionPerformed(ActionEvent e) {
		                  LOGGER.info("Exiting...");
		                  System.exit(0);
		              }
		          };

		          PopupMenu popup = new PopupMenu();
		          MenuItem exitItem = new MenuItem("Exit");
		          exitItem.addActionListener(exitListener);
		          popup.add(exitItem);
		          
		          ActionListener reminderListener = new ActionListener() {
		              public void actionPerformed(ActionEvent e) {
		            	  JOptionPane.showMessageDialog(null, "Hello!");		            	  
		              }
		          };

		          MenuItem item = new MenuItem("Create");
		          item.addActionListener(reminderListener);
		          popup.add(item);

		          final TrayIcon trayIcon = new TrayIcon(image, "Reminder", popup);
		          trayIcon.displayMessage("Action Event", "Hi", TrayIcon.MessageType.INFO);

		          trayIcon.setImageAutoSize(true);		          
		          trayIcon.addMouseListener(mouseListener);
		          try {
		            tray.add(trayIcon);
		          } catch (AWTException e) {
		            LOGGER.error("Can't add to tray");
		          }
		        } else {
		          LOGGER.error("Tray unavailable");
		        }
		      }
		    };
		    EventQueue.invokeLater(runner);
	}
}