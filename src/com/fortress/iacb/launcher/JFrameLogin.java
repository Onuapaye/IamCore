package com.fortress.iacb.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.services.IBAppUserBusinessLogic;
import com.fortress.iacb.services.IBApplicationUserADOCRUD;
import com.fortress.iacb.services.IdentityBismarkLogger;
import com.fortress.iacb.services.UtilityClass;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUsername;
	private JButton btnCancel;
	private JButton btnLogin;
	private JPasswordField txtPassword;

	private static IBApplicationUserADOCRUD ibUserCRUD = null;
	private static IBApplicationUser ibUser = null;
	private static IBAppUserBusinessLogic ibUserLogic = null;

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IBApplicationUserADOCRUD.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// check if there is a record existing for default user before. If no record is
					// found
					// create the default user account else jump straight to login-age
					ibUserCRUD = new IBApplicationUserADOCRUD();
					ibUserLogic = new IBAppUserBusinessLogic();

					final boolean recordFound = ibUserLogic.IsApplicationHavingUserRecords();
					if (recordFound == false) {
						ibUserCRUD.createDefaultApplicationUser();
					}

					// System.out.println("TOTAL INSERTED RECORDS: " + insertCount + " ARE RECORDS
					// EXISTING? " + recordFound);

					if (recordFound == true) {

						JFrameLogin frame = new JFrameLogin();
						frame.setVisible(true);
					} else {
						// JOptionPane.showMessageDialog(null, "Default account not created due to
						// error.", "Default Account Error", 1);
						IBLOGGER.info(
								"Default account not created due to unknown error. As tracking was done from login window");
						// exit the application after the error message
						System.exit(0);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameLogin() {
		setResizable(false);
		setTitle("Application Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 400, 210);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(33, 69, 70, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(33, 106, 70, 14);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(113, 66, 255, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(129, 154, 89, 34);
		contentPane.add(btnCancel);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// validate user login
				ibUser = new IBApplicationUser();
				ibUser.setUserName(txtUsername.getText());
				ibUser.setUserPassword(txtPassword.getText());

				// System.out.println("GET TEXT METHOD: " + txtPassword.getText() + " GET PWD
				// METHOD: " + txtPassword.getPassword().toString());
				ibUserCRUD = new IBApplicationUserADOCRUD();
				ibUserLogic = new IBAppUserBusinessLogic();

				final boolean isUserNamePwdValid = ibUserLogic.IsUserNamePasswordValid(ibUser);

				// prompt for null results
				if (isUserNamePwdValid == false) {
					// use a JOptionPane to show message to the user
					JOptionPane.showMessageDialog(null, "No record(s) found matching the Username: "
							+ ibUser.getUserName().toUpperCase() + " and Password: not show", "Record Not Found", 2);
					txtUsername.grabFocus();
					return;
				}

				// check if the user is allowed to login
				final boolean isLoginEnabled = ibUserLogic.IsUserLoginAllowed(ibUser.getUserName());
				if (isLoginEnabled == false) {
					JOptionPane.showMessageDialog(null, "The User account for " + ibUser.getUserName().toUpperCase()
							+ " is not Enabled to login to the application.", "Login Disabled", 2);
					txtUsername.grabFocus();
					return;
				}

				// show the main mdi window if all validations are complete and successful				
				new JFrameIBGUI(ibUser.getUserName()).setVisible(true);
				//System.out.println("FROM LOGGIN FORM: "+ ibUser.getUserName());
				
				// close the login window
				closeLogin();
			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(228, 154, 100, 34);
		contentPane.add(btnLogin);

		txtPassword = new JPasswordField();
		txtPassword.setText("");
		txtPassword.setBounds(113, 107, 255, 26);
		contentPane.add(txtPassword);

		JPanel panelLoginDetails = new JPanel();
		panelLoginDetails.setBorder(new LineBorder(new Color(0, 102, 0)));
		panelLoginDetails.setBackground(new Color(255, 255, 255));
		panelLoginDetails.setBounds(0, 0, 400, 37);
		contentPane.add(panelLoginDetails);

		JLabel lblApplicationLogin = new JLabel("Application Login");
		lblApplicationLogin.setForeground(new Color(0, 102, 0));
		lblApplicationLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		panelLoginDetails.add(lblApplicationLogin);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(new Color(0, 102, 0));
		panel.setBounds(10, 48, 380, 151);
		contentPane.add(panel);

		// set look and feel
		UtilityClass.setLookAndFeelOfUI();

		// center the jframe window
		UtilityClass.CenterAnyJFrame(this);
	}

	private void closeLogin() {
		UtilityClass.CloseAnyJFrame(this);
	}

	
}
