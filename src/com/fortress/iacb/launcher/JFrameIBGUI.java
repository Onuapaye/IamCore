package com.fortress.iacb.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.services.IBAppUserBusinessLogic;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class JFrameIBGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					JFrameIBGUI frame = new JFrameIBGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameIBGUI() {
		initComponents();
	}

	//constructor with a string paramer
	public JFrameIBGUI(String userName) {
		
		//get user information at login
		IBAppUserBusinessLogic.getLoggedInUserInformation(userName);
		
		//Initialise components
		initComponents();	
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		
		setTitle("I Am Core Bismark :: Identity Management System");		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit Application");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnOperations = new JMenu("Operations");
		menuBar.add(mnOperations);
		
		JMenuItem mntmUserOperations = new JMenuItem("User Operations");
		mntmUserOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a new instance of the JFrameIdentityManagement class
				//and then calls the setVisible() method by passing true as the parameter
				JFrameUserManagement uMgt = new JFrameUserManagement();
				
				uMgt.setVisible(true);
			}
		});
		mnOperations.add(mntmUserOperations);
		
		JMenuItem mntmIdentityOperations = new JMenuItem("Identity Operations");
		mntmIdentityOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a new instance of the JFrameIdentityManagement class
				//and then calls the setVisible() method by passing true as the parameter
				JFrameIdentityManagement iMgt = new JFrameIdentityManagement();
				
				iMgt.setVisible(true);
			}
		});
		mnOperations.add(mntmIdentityOperations);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmContents = new JMenuItem("Contents");
		mnHelp.add(mntmContents);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogAbout about = new JDialogAbout();
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelStatusBars = new JPanel();
		panelStatusBars.setBackground(new Color(0, 0, 0));
		panelStatusBars.setBounds(0, 660, 1362, 32);
		contentPane.add(panelStatusBars);
		panelStatusBars.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setForeground(new Color(255, 255, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(10, 11, 78, 14);
		panelStatusBars.add(lblUsername);
		
		JLabel lblUserLevel = new JLabel("User Level:");
		lblUserLevel.setForeground(new Color(255, 255, 0));
		lblUserLevel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserLevel.setBounds(283, 12, 94, 14);
		panelStatusBars.add(lblUserLevel);
		
		JLabel lblLoggedinuser = new JLabel(IBApplicationUser.getLoggedInUsername());
		lblLoggedinuser.setForeground(new Color(0, 102, 0));
		lblLoggedinuser.setBounds(82, 11, 196, 15);
		lblLoggedinuser.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelStatusBars.add(lblLoggedinuser);
		
		String userGroup = "";
		if (IBApplicationUser.getUG_DEFAULT() == IBApplicationUser.getLoggedInUserGroup()) {
			userGroup = IBApplicationUser.DEF_USER;
		}else if(IBApplicationUser.getUG_ADMIN() == IBApplicationUser.getLoggedInUserGroup()) {
			userGroup = IBApplicationUser.ADM_USER;
		}else if(IBApplicationUser.getUG_POWER() == IBApplicationUser.getLoggedInUserGroup()) {
			userGroup = IBApplicationUser.POW_USER;
		}else if(IBApplicationUser.getUG_VIEWER() == IBApplicationUser.getLoggedInUserGroup()) {
			userGroup = IBApplicationUser.REA_USER;
		}
		
		JLabel lblLoggedinlevel = new JLabel(userGroup);
		lblLoggedinlevel.setForeground(new Color(0, 102, 0));
		lblLoggedinlevel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoggedinlevel.setBounds(367, 11, 374, 14);
		panelStatusBars.add(lblLoggedinlevel);
		 
		JFrameIBGUI.myLookAndFeel();
	}
	// this method simply changes the look and feel of the window
	private static void myLookAndFeel() {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "Look & Feel Error", 1);
		}
	}

}
