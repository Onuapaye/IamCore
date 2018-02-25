package com.fortress.iacb.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.exceptions.RecordCreationException;
import com.fortress.iacb.exceptions.RecordDeletionException;
import com.fortress.iacb.exceptions.RecordSearchException;
import com.fortress.iacb.exceptions.RecordUpdateException;
import com.fortress.iacb.services.IBAppUserBusinessLogic;
import com.fortress.iacb.services.IBApplicationUserADOCRUD;
import com.fortress.iacb.services.IdentityBismarkLogger;
import com.fortress.iacb.services.UtilityClass;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.rmi.CORBA.Util;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JCheckBox;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class JFrameUserManagement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JPasswordField txtRepassword;
	private JTable tableUsersList;

	IBAppUserBusinessLogic userBLogic = null;
	IBApplicationUser ibUser = null;

	IBApplicationUserADOCRUD ibaCRUD = null;

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IBApplicationUserADOCRUD.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameUserManagement frame = new JFrameUserManagement();
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
	public JFrameUserManagement() {
		setResizable(false);
		setTitle("User Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 850, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(34, 76, 81, 26);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(32, 114, 82, 25);
		contentPane.add(lblPassword);

		JLabel lblRepassword = new JLabel("Re-Password");
		lblRepassword.setForeground(new Color(255, 255, 255));
		lblRepassword.setBounds(34, 154, 81, 25);
		contentPane.add(lblRepassword);

		JLabel lblUserGroup = new JLabel("User Group");
		lblUserGroup.setForeground(new Color(255, 255, 255));
		lblUserGroup.setBounds(34, 195, 81, 25);
		contentPane.add(lblUserGroup);

		txtUserName = new JTextField();
		txtUserName.setBounds(125, 76, 287, 29);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JComboBox<String> cboUserGroup = new JComboBox<String>();
		cboUserGroup.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Default User", "Administrator", "Power", "Viewer" }));
		cboUserGroup.setBounds(125, 192, 289, 28);
		contentPane.add(cboUserGroup);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(123, 114, 289, 28);
		contentPane.add(txtPassword);

		txtRepassword = new JPasswordField();
		txtRepassword.setBounds(125, 151, 289, 31);
		contentPane.add(txtRepassword);

		JPanel panelFrameTitle = new JPanel();
		panelFrameTitle.setBorder(new LineBorder(new Color(0, 102, 0)));
		panelFrameTitle.setBackground(Color.WHITE);
		panelFrameTitle.setBounds(0, 0, 850, 37);
		contentPane.add(panelFrameTitle);

		JLabel lblUserManagement = new JLabel("User Management");
		lblUserManagement.setForeground(new Color(0, 102, 0));
		lblUserManagement.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		panelFrameTitle.add(lblUserManagement);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(null, "CRUD Operations", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 0)));
		layeredPane.setBounds(10, 277, 424, 82);
		contentPane.add(layeredPane);

		JRadioButton rdbtnCreate = new JRadioButton("Create");
		rdbtnCreate.setBounds(24, 19, 63, 23);
		layeredPane.add(rdbtnCreate);
		rdbtnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnCreate.setForeground(new Color(255, 255, 255));
		rdbtnCreate.setBackground(new Color(0, 102, 0));

		JRadioButton rdbtnRead = new JRadioButton("Read");
		rdbtnRead.setBounds(135, 19, 55, 23);
		layeredPane.add(rdbtnRead);
		rdbtnRead.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnRead.setForeground(new Color(255, 255, 255));
		rdbtnRead.setBackground(new Color(0, 102, 0));

		JRadioButton rdbtnUpdate = new JRadioButton("Update");
		rdbtnUpdate.setBounds(242, 19, 67, 23);
		layeredPane.add(rdbtnUpdate);
		rdbtnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnUpdate.setForeground(new Color(255, 255, 255));
		rdbtnUpdate.setBackground(new Color(0, 102, 0));

		JRadioButton rdbtnDelete = new JRadioButton("Delete");
		rdbtnDelete.setBounds(341, 19, 63, 23);
		layeredPane.add(rdbtnDelete);
		rdbtnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnDelete.setForeground(new Color(255, 255, 255));
		rdbtnDelete.setBackground(new Color(0, 102, 0));

		JCheckBox chckbxEnableApplicationLogin = new JCheckBox("");
		chckbxEnableApplicationLogin.setForeground(new Color(255, 255, 255));
		chckbxEnableApplicationLogin.setBackground(new Color(0, 102, 0));
		chckbxEnableApplicationLogin.setBounds(125, 229, 21, 23);
		contentPane.add(chckbxEnableApplicationLogin);

		ButtonGroup jbtnGroup = new ButtonGroup();
		jbtnGroup.add(rdbtnCreate);
		jbtnGroup.add(rdbtnRead);
		jbtnGroup.add(rdbtnUpdate);
		jbtnGroup.add(rdbtnDelete);

		JButton btnCloseWindow = new JButton("Close Window");
		btnCloseWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeUserManager();
			}
		});
		btnCloseWindow.setBounds(17, 49, 99, 23);
		layeredPane.add(btnCloseWindow);

		JButton btnCommittAction = new JButton("Commit Action");
		btnCommittAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// instantiate the object
				ibUser = new IBApplicationUser();

				// this variable is used to hold return values of CRUD operations
				int crudSucess = -1;

				// set instance values
				ibUser.setUserName(txtUserName.getText());
				ibUser.setUserPassword(txtPassword.getText());
				if (chckbxEnableApplicationLogin.isSelected() == true) {
					ibUser.setUserEnableLogin(IBApplicationUser.ENABLE_LOGIN_VAL_YES);
				} else {
					ibUser.setUserEnableLogin(IBApplicationUser.ENABLE_LOGIN_VAL_NO);
				}

				final int comboValue = cboUserGroup.getSelectedIndex();
				if (comboValue == IBApplicationUser.getUG_DEFAULT()) {
					ibUser.setUserGroup(IBApplicationUser.getUG_DEFAULT());

				} else if (comboValue == IBApplicationUser.getUG_ADMIN()) {
					ibUser.setUserGroup(IBApplicationUser.getUG_ADMIN());

				} else if (comboValue == IBApplicationUser.getUG_POWER()) {
					ibUser.setUserGroup(IBApplicationUser.getUG_POWER());

				} else {
					ibUser.setUserGroup(IBApplicationUser.getUG_VIEWER());
				}

				// instantiate the business logic class of user
				userBLogic = new IBAppUserBusinessLogic();

				/*
				 * CREATE A NEW RECORD IF CREATE OPERATION IS SELECTED
				 */
				if (rdbtnCreate.isSelected() == true) {

					// check if there is a username already existing
					if (userBLogic.IsUniqueIDExisting(ibUser) == true) {
						UtilityClass.showMessageBox(
								"The Username : " + ibUser.getUserName().toUpperCase() + " already exist.",
								"Duplicate Username", 2);
						txtUserName.grabFocus();
						return;
					}

					if (isFieldValidationComplete() == false) {
						return;
					}
					if (isUserAllowedCRUDOperation() == false) {
						return;
					}

					crudSucess = createUserRecordAndReturnSearchResultsToTable();
					if (crudSucess > 0) {
						UtilityClass.showMessageBox("User record successfully created", "Record Created", 1);
						getSearchResultsListIntoJTable(ibaCRUD);
					} else {
						UtilityClass.showMessageBox("User record not created", "Record Not Created", 1);
						return;
					}

					// UPDATE AN EXISTING RECORD
				} else if (rdbtnUpdate.isSelected() == true) {

					if (isFieldValidationComplete() == false) {
						return;
					}
					if (isUserAllowedCRUDOperation() == false) {
						return;
					}

					crudSucess = updateUserRecordAndReturnSearchResultsToTable();
					if (crudSucess > 0) {
						getSearchResultsListIntoJTable(ibaCRUD);
						UtilityClass.showMessageBox("User record successfully created", "Record Created", 1);
					} else {
						UtilityClass.showMessageBox("User record not created", "Record Not Created", 1);
						return;
					}

				} else if (rdbtnDelete.isSelected() == true) {

					if (txtUserName.getText().isEmpty()) {
						UtilityClass.showMessageBox(
								"Enter Username of the record to be deleted to perform this operation",
								"Empty Field Detected", 1);
						txtUserName.grabFocus();
						return;
					}
					if (isUserAllowedCRUDOperation() == false) {
						return;
					}
					// DELETE OPERATION
					crudSucess = deleteUserRecordAndReturnSearchResultsToTable();
					if (crudSucess > 0) {
						UtilityClass.showMessageBox("User record successfully deleted", "Record Deleted", 1);
						getSearchResultsListIntoJTable(ibaCRUD);
					} else {
						UtilityClass.showMessageBox("User record not deleted", "Record Not Deleted", 1);
						return;
					}

					// SEARCH OR READ OPERATION
				} else if (rdbtnRead.isSelected() == true) {
					searchUserRecordAndReturnSearchResultsToTable();
				} else {
					// check for which operation type is selected; create, read, update, or delete
					if (!userBLogic.getCRUDOperationToPerform(rdbtnCreate, rdbtnRead, rdbtnUpdate, rdbtnDelete)) {
						txtUserName.grabFocus();
						return;
					}
				}

				// reset all the textboxes and other components
				clearFields(cboUserGroup, chckbxEnableApplicationLogin);
			}

		});
		btnCommittAction.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCommittAction.setBounds(281, 49, 123, 23);
		layeredPane.add(btnCommittAction);

		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields(cboUserGroup, chckbxEnableApplicationLogin);
			}
		});
		btnClearFields.setBounds(135, 49, 97, 23);
		layeredPane.add(btnClearFields);

		JLabel lblEnableLogin = new JLabel("Enable Login");
		lblEnableLogin.setForeground(new Color(255, 255, 255));
		lblEnableLogin.setBounds(34, 231, 81, 19);
		contentPane.add(lblEnableLogin);

		JPanel panelUserInfo = new JPanel();
		panelUserInfo.setForeground(new Color(255, 255, 0));
		panelUserInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelUserInfo.setBackground(new Color(0, 102, 0));
		panelUserInfo.setBounds(10, 48, 423, 218);
		contentPane.add(panelUserInfo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 68, 381, 282);
		contentPane.add(scrollPane);

		tableUsersList = new JTable();
		tableUsersList.setFont(new Font("Arial", Font.BOLD, 11));
		tableUsersList.setForeground(new Color(255, 255, 255));
		scrollPane.setViewportView(tableUsersList);
		tableUsersList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Login Username", "User Group", "Password", "Can Login" }));
		tableUsersList.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableUsersList.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableUsersList.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableUsersList.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableUsersList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tableUsersList.setBorder(null);
		tableUsersList.setBackground(new Color(102, 204, 0));
		tableUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel panelUserRecords = new JPanel();
		panelUserRecords.setForeground(Color.YELLOW);
		panelUserRecords.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "List Of User Records",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelUserRecords.setBackground(new Color(0, 102, 0));
		panelUserRecords.setBounds(441, 48, 399, 311);
		contentPane.add(panelUserRecords);

		UtilityClass.CenterAnyJFrame(this);

		ibaCRUD = new IBApplicationUserADOCRUD();
		getSearchResultsListIntoJTable(ibaCRUD);

		this.clearFields(cboUserGroup, chckbxEnableApplicationLogin);
		/*
		 * LOGGED_IN_USER = IBApplicationUser.getLoggedInUsername(); LOGGED_IN_USERGROUP
		 * = IBApplicationUser.getLoggedInUserGroup();
		 */

		/*
		 * System.out.println(LOGGED_IN_USER); System.out.println(LOGGED_IN_USERGROUP);
		 */
	}

	private void closeUserManager() {
		UtilityClass.CloseAnyJFrame(this);
	}

	/*
	 * 
	 */
	private int createUserRecordAndReturnSearchResultsToTable() {
		ibaCRUD = new IBApplicationUserADOCRUD();
		int result = 0;
		try {
			result = ibaCRUD.createIdentityBismarkUserRecord(ibUser);
		} catch (RecordCreationException e1) {
			IBLOGGER.error("An error of : " + e1.getMessage().toUpperCase()
					+ " occured when calling the method to create the User record for "
					+ ibUser.getUserName().toUpperCase());
		}

		return result;
	}

	private int deleteUserRecordAndReturnSearchResultsToTable() {
		ibaCRUD = new IBApplicationUserADOCRUD();
		int result = -1;
		if (!IBApplicationUser.getLoggedInUsername().equals(ibUser.getUserName())) {
			final int selOption = JOptionPane.showConfirmDialog(null,
					"Are you sure to Delete User record for " + ibUser.getUserName().toUpperCase() + "?",
					"Delete Record", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (selOption == JOptionPane.YES_OPTION) {
				try {
					result = ibaCRUD.deleteIdentityBismarkUserRecord(ibUser.getUserName());
				} catch (RecordDeletionException e1) {
					IBLOGGER.error("An error of : " + e1.getMessage().toUpperCase()
							+ " occured when calling the method to delete the User record for "
							+ ibUser.getUserName().toUpperCase());
				}
			} else {
				UtilityClass.showMessageBox("Delete operation cancelled successfully", "Operation Cancelled",
						JOptionPane.INFORMATION_MESSAGE);
				return result;
			}
		} else {
			UtilityClass.showMessageBox(
					"Delete operation of the a User " + ibUser.getUserName().toUpperCase()
							+ " not allowed. The User is using the application currently.",
					"Operation Stopped", JOptionPane.INFORMATION_MESSAGE);
			return result;
		}

		return result;
	}

	private int updateUserRecordAndReturnSearchResultsToTable() {
		ibaCRUD = new IBApplicationUserADOCRUD();
		int result = 0;
		try {
			result = ibaCRUD.updateIdentityBismarkUserRecord(ibUser);
		} catch (RecordUpdateException e1) {
			IBLOGGER.error("An error of : " + e1.getMessage().toUpperCase()
					+ " occured when calling the method to update the User record for "
					+ ibUser.getUserName().toUpperCase());
		}

		return result;
	}

	private void searchUserRecordAndReturnSearchResultsToTable() {
		// clear the jtable contents before adding new items
		clearResetTableContents();

		ibaCRUD = new IBApplicationUserADOCRUD();
		try {
			List<IBApplicationUser> listUser = ibaCRUD.searchIdentityBismarkUserRecords(ibUser);
			final int size = listUser.size();

			setTableDataFromSearchResults(listUser, size);
		} catch (RecordSearchException e1) {
			IBLOGGER.error("An error of : " + e1.getMessage().toUpperCase()
					+ " occured when calling the method to update the User record for "
					+ ibUser.getUserName().toUpperCase());
		}
	}

	/**
	 * <h3>Method Description</h3> This method gets all the Users records from the
	 * User table and returns them as a list of the object IBApplicationUser.
	 */
	private void getSearchResultsListIntoJTable(IBApplicationUserADOCRUD ibaCRUD) {

		// clear the jtable contents before adding new items
		clearResetTableContents();

		// log info before
		IBLOGGER.info("Initialising the process of retrieving all User records and passing them to list");

		// retrieve list of records including the just added record
		try {
			List<IBApplicationUser> listUser = ibaCRUD.searchIdentityBismarkUserRecords();
			final int listSize = listUser.size();
			if (listSize > 0) {
				setTableDataFromSearchResults(listUser, listSize);
			} else {
				clearResetTableContents();
				UtilityClass.showMessageBox("No record(s) found matching the search criteria", "Record Not Found", 1);
			}
		} catch (RecordSearchException e1) {
			IBLOGGER.error("The error : " + e1.getMessage().toUpperCase()
					+ " occured when loading list of users during Create operation");
		}
	}

	/**
	 * Clears the current list or data from jtable object before reloading it with
	 * new records or data
	 */
	private void clearResetTableContents() {
		tableUsersList.getColumnModel().getColumn(0).setPreferredWidth(350);
		tableUsersList.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableUsersList.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableUsersList.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableUsersList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableUsersList.setModel(
				new DefaultTableModel(null, new String[] { "Username", "User Group", "Password", "Can Login" }));
	}

	/**
	 * This method accepts a list of search results from a read operation and loops
	 * through the list to set the appropriate values to the respective columns of a
	 * JTable component
	 * 
	 * @param listUser
	 * The list of the search results
	 * <pre>
	 * {@code
	 * 	IBApplicationUserADOCRUD ibaCRUD = new IBApplicationUserADOCRUD();
	 * 	List<IBApplicationUser> listUser = ibaCRUD.searchIdentityBismarkUserRecords();
	 * 	final int listSize = listUser.size();	
	 * 		for (int i = 0; i < listSize; i++) {
	 * 			newRow[0] = listUser.get(i).getUserName();
	 *  	}
	 *  }
	 * </pre>
	 * @param listSize
	 *  The length or size of the list as integer
	 * <pre>
	 * {@code 
	 * final int listSize = listUser.size();
	 * }
	 * </pre>
	 */
	private boolean setTableDataFromSearchResults(List<IBApplicationUser> listUser, final int listSize) {
		DefaultTableModel tm = (DefaultTableModel) tableUsersList.getModel();

		Object newRow[] = new Object[4];

		for (int i = 0; i < listSize; i++) {

			// 1. get user
			newRow[0] = listUser.get(i).getUserName();

			// 2. get the user group type
			if (listUser.get(i).getUserGroup() == IBApplicationUser.getUG_DEFAULT()) {
				newRow[1] = IBApplicationUser.DEF_USER;
			} else if (listUser.get(i).getUserGroup() == IBApplicationUser.getUG_ADMIN()) {
				newRow[1] = IBApplicationUser.ADM_USER;
			} else if (listUser.get(i).getUserGroup() == IBApplicationUser.getUG_POWER()) {
				newRow[1] = IBApplicationUser.POW_USER;
			} else {
				newRow[1] = IBApplicationUser.REA_USER;
			}

			// 3. password field not show
			newRow[2] = "not shown";

			// 4. get the login status
			if (listUser.get(i).getUserEnableLogin() == IBApplicationUser.ENABLE_LOGIN_VAL_YES) {
				newRow[3] = IBApplicationUser.ENABLE_LOGIN_YES;
			} else {
				newRow[3] = IBApplicationUser.ENABLE_LOGIN_NO;
			}

			// 5. add the row items to the table model
			tm.addRow(newRow);
		}

		if (listSize > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Components or text fields validations are done by calling on this method. It
	 * returns (exit) the process if validation fails
	 */
	private boolean isFieldValidationComplete() {
		final boolean MAX_LENGTH = userBLogic.IsUserFieldLengthValid(ibUser);
		// check for empty field values
		if (userBLogic.IsAnyRequiredFieldEmpty(ibUser) == true) {
			UtilityClass.showMessageBox("Make sure all fields are properly filled.", "Empty Field Detected", 2);
			txtUserName.grabFocus();
			return false;
		} else if (MAX_LENGTH == true) {// check for correct field length
			UtilityClass.showMessageBox(
					"The length or number of character has exceeded for Username and Password.\nIt cannot exceed "
							+ MAX_LENGTH + " characters",
					"Field Length Exceeded", 2);
			txtUserName.grabFocus();
			return false;
		} else if (userBLogic.IsPasswordMatching(ibUser.getUserPassword(), txtRepassword.getText()) == false) {
			// check if password is matching or the same
			UtilityClass.showMessageBox("The two Passwords provided are not matching or the same.", "Unmatch Password",
					2);
			txtPassword.grabFocus();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method checks to see if the current application user has the right
	 * user group to perform the selected CRUD operation
	 */
	private boolean isUserAllowedCRUDOperation() {
		// check if the user has the right to create the record
		if (IBApplicationUser.getLoggedInUserGroup() == IBApplicationUser.getUG_POWER()
				|| IBApplicationUser.getLoggedInUserGroup() == IBApplicationUser.getUG_VIEWER()) {
			UtilityClass.showMessageBox("Your User account or profile : " + IBApplicationUser.getLoggedInUsername().toUpperCase()
					+ " is not allowed to perform this operation", "Restricted Operation", 2);
			txtUserName.grabFocus();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Clears or Reset some controls contents when called
	 * 
	 * @param cboUserGroup
	 *            Gets the Combobox controlled that its value will be reset to -1
	 * @param chckbxEnableApplicationLogin
	 *            The Checkbox component whose value will be reset to false if at
	 *            true or vice-versa
	 */
	private void clearFields(JComboBox<String> cboUserGroup, JCheckBox chckbxEnableApplicationLogin) {
		txtUserName.setText("");
		txtPassword.setText("");
		txtRepassword.setText("");
		cboUserGroup.setSelectedIndex(-1);
		if (chckbxEnableApplicationLogin.isSelected()) {
			chckbxEnableApplicationLogin.setSelected(false);
		}
	}
}
