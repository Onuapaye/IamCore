package com.fortress.iacb.services;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class UtilityClass {

	/**
	 * This method centers the login window and any window that would be using 
	 * or calling the method
	 * @param thisFrame
	 */
	public static void CenterAnyJFrame(JFrame thisFrame) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		thisFrame.setLocation(dim.width / 2 - thisFrame.getSize().width / 2,
				dim.height / 2 - thisFrame.getSize().height / 2);

	}
	
	/**
	 * <h3>Method Description</h4>
	 * This method closes the present window when called and provided with an object of
	 * type JFrame. One has to set the default close operation to DISPOSE_ON_CLOSE (as shown in the code snippet)
 	 * so that the closing of the applied JFrame object does not quit or exit the application as a whole
 	 * 
 	 * <h4>Code Snippet</h4>
	 * <pre>
	 * {@code
	 * setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 * OR
	 * jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 * }
	 * </pre>
	 * @param thisFrame
	 */
	public static void CloseAnyJFrame(Object thisFrame) {
		WindowEvent winClosingEvent = new WindowEvent((Window) thisFrame, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);;
	}
	
	/**
	 * This method helps to change the default look and feel of the Eclipse windowBuilder controls.
	 * It does that by making use of the UIManager.
	 */
	public static void setLookAndFeelOfUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method makes use of the JOption class and the calls its showMessageDialog method to show message to users
	 * when a specific activity occurs. It has three parameters which are the 
	 * <ul>
	 * <li>Message</li><li>Message Title</li><li>Type of Message</li>
	 * </ul>
	 * The type of message are accepted as integers as the following;
	 * <ol>
	 * <li>ERROR_MESSAGE,</li>
	 * <li>INFORMATION_MESSAGE,</li>
	 * <li>WARNING_MESSAGE,</li>
	 * <li>QUESTION_MESSAGE, or</li>
	 * <li>PLAIN_MESSAGE</li>
	 * </ol>
	 * @param message
	 * A message to show to users. e.g. Invalid Username or password entered
	 * @param title
	 * e.g. Authentication Failed
	 * @param type
	 * e.g. 2 (for information)
	 */
	public static void showMessageBox(String message, String title, int type) {
		JOptionPane.showMessageDialog(null, message, title, type);
	}
}
