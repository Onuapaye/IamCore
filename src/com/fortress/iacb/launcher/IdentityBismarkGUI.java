package com.fortress.iacb.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IdentityBismarkGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame IdentityBismarkGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentityBismarkGUI window = new IdentityBismarkGUI();
					window.IdentityBismarkGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IdentityBismarkGUI() {
		initialize();
		myLookAndFeel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		IdentityBismarkGUI = new JFrame();
		IdentityBismarkGUI.setTitle("I Am Core Bismark :: Identity Management System");
		IdentityBismarkGUI.setBounds(100, 100, 575, 300);
		IdentityBismarkGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IdentityBismarkGUI.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		IdentityBismarkGUI.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//exit the application
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnPerformAction = new JMenu("Perform Action");
		menuBar.add(mnPerformAction);
		
		JMenu mnIdentity = new JMenu("Identity Management");
		mnPerformAction.add(mnIdentity);
		
		JMenuItem mntmCreateIdentity = new JMenuItem("Create");
		mntmCreateIdentity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creates a new instance of the JFrameIdentityManagement class
				//and then calls the setVisible() method by passing true as the parameter
				JFrameIdentityManagement iMgt = new JFrameIdentityManagement();
				
				iMgt.setVisible(true);
			}
		});
		mnIdentity.add(mntmCreateIdentity);
		
		JMenuItem mntmReadIdentity = new JMenuItem("Read");
		mnIdentity.add(mntmReadIdentity);
		
		JMenuItem mntmUpdateIdentity = new JMenuItem("Update");
		mnIdentity.add(mntmUpdateIdentity);
		
		JMenuItem mntmDeleteIdentity = new JMenuItem("Delete");
		mnIdentity.add(mntmDeleteIdentity);
		
		JMenu mnUser = new JMenu("User Management");
		mnPerformAction.add(mnUser);
		
		JMenuItem mntmCreateUser = new JMenuItem("Create");
		mntmCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameUserManagement uMgt = new JFrameUserManagement();
				uMgt.setVisible(true);
				
			}
		});
		mnUser.add(mntmCreateUser);
		
		JMenuItem mntmReadUser = new JMenuItem("Read");
		mnUser.add(mntmReadUser);
		
		JMenuItem mntmUpdateUser = new JMenuItem("Update");
		mnUser.add(mntmUpdateUser);
		
		JMenuItem mntmDeleteUser = new JMenuItem("Delete");
		mnUser.add(mntmDeleteUser);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmContent = new JMenuItem("Content");
		mnHelp.add(mntmContent);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		IdentityBismarkGUI.getContentPane().setLayout(null);
	}

	//this method simply changes the look and feel of the window
	private static void myLookAndFeel() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
            JOptionPane.showMessageDialog(null, e.getMessage(), "Look & Feel Error", 1);
        }
    }
}
