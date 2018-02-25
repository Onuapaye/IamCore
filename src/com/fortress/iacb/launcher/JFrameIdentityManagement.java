package com.fortress.iacb.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.datamodel.IdentityBismark;
import com.fortress.iacb.exceptions.RecordSearchException;
import com.fortress.iacb.services.IBAppUserBusinessLogic;
import com.fortress.iacb.services.IdentityBismarkBusinessLogic;
import com.fortress.iacb.services.IdentityBismarkDAOCRUD;
import com.fortress.iacb.services.IdentityBismarkLogger;
import com.fortress.iacb.services.UtilityClass;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class JFrameIdentityManagement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUniqueID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTable tableListOfIdentities;

	private IdentityBismarkBusinessLogic iBBLogic = null;
	private IdentityBismark iBismark = null;
	private IdentityBismarkDAOCRUD iBismarkCRUD = null;

	private final Integer FIELD_LEN_45 = 45;
	private final Integer FIELD_LEN_10 = 10;
	private JTextField txtEmail;

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IdentityBismarkDAOCRUD.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameIdentityManagement frame = new JFrameIdentityManagement();
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
	public JFrameIdentityManagement() {
		initComponents();
		UtilityClass.CenterAnyJFrame(this);
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 970, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 102, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 970, 37);
		contentPane.add(panel);

		JLabel lblIdentityManagement = new JLabel("Identity Management");
		lblIdentityManagement.setForeground(new Color(0, 102, 0));
		lblIdentityManagement.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		panel.add(lblIdentityManagement);

		txtUniqueID = new JTextField();
		txtUniqueID.setColumns(10);
		txtUniqueID.setBounds(119, 78, 306, 29);
		contentPane.add(txtUniqueID);

		JLabel lblUniqueId = new JLabel("Unique ID");
		lblUniqueId.setForeground(Color.WHITE);
		lblUniqueId.setBounds(28, 81, 81, 14);
		contentPane.add(lblUniqueId);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(28, 120, 81, 14);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(28, 165, 81, 14);
		contentPane.add(lblLastName);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(28, 205, 81, 14);
		contentPane.add(lblEmail);

		txtLastName = new JTextField();
		txtLastName.setBounds(119, 162, 306, 28);
		contentPane.add(txtLastName);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(119, 120, 306, 32);
		contentPane.add(txtFirstName);

		txtEmail = new JTextField();
		txtEmail.setBounds(119, 201, 306, 29);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JScrollPane scrollPaneListOfIdentities = new JScrollPane();
		scrollPaneListOfIdentities.setBounds(457, 69, 493, 309);
		contentPane.add(scrollPaneListOfIdentities);

		tableListOfIdentities = new JTable();
		tableListOfIdentities.setFont(new Font("Arial", Font.BOLD, 10));
		tableListOfIdentities.setForeground(new Color(255, 255, 255));
		tableListOfIdentities.setBackground(new Color(102, 204, 0));
		tableListOfIdentities.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Unique ID", "First Name", "Last Name", "Em-mail" }));
		tableListOfIdentities.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableListOfIdentities.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableListOfIdentities.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableListOfIdentities.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableListOfIdentities.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPaneListOfIdentities.setViewportView(tableListOfIdentities);

		JPanel panelListOfIdentities = new JPanel();
		panelListOfIdentities.setForeground(Color.YELLOW);
		panelListOfIdentities.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"List Of Identity Records", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelListOfIdentities.setBackground(new Color(0, 102, 0));
		panelListOfIdentities.setBounds(447, 48, 513, 341);
		contentPane.add(panelListOfIdentities);

		JComboBox<String> comboBoxReadSearchBy = new JComboBox<String>();
		comboBoxReadSearchBy.setModel(new DefaultComboBoxModel<String>(new String[] { "All", "First Name", "Last Name",
				"E-mail", "Unique ID", "Fuzzy Search (Approximate)" }));
		comboBoxReadSearchBy.setBounds(119, 264, 306, 25);
		contentPane.add(comboBoxReadSearchBy);

		JLayeredPane layeredPaneCRUDoperations = new JLayeredPane();
		layeredPaneCRUDoperations.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"CRUD Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		layeredPaneCRUDoperations.setBounds(10, 302, 430, 87);
		contentPane.add(layeredPaneCRUDoperations);

		JRadioButton radioButtonCreate = new JRadioButton("Create");
		radioButtonCreate.setForeground(Color.WHITE);
		radioButtonCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButtonCreate.setBackground(new Color(0, 102, 0));
		radioButtonCreate.setBounds(24, 18, 63, 23);
		layeredPaneCRUDoperations.add(radioButtonCreate);

		JRadioButton radioButtonRead = new JRadioButton("Read");
		radioButtonRead.setForeground(Color.WHITE);
		radioButtonRead.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButtonRead.setBackground(new Color(0, 102, 0));
		radioButtonRead.setBounds(135, 18, 55, 23);
		layeredPaneCRUDoperations.add(radioButtonRead);

		JRadioButton radioButtonUpdate = new JRadioButton("Update");
		radioButtonUpdate.setForeground(Color.WHITE);
		radioButtonUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButtonUpdate.setBackground(new Color(0, 102, 0));
		radioButtonUpdate.setBounds(238, 18, 67, 23);
		layeredPaneCRUDoperations.add(radioButtonUpdate);

		JRadioButton radioButtonDelete = new JRadioButton("Delete");
		radioButtonDelete.setForeground(Color.WHITE);
		radioButtonDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButtonDelete.setBackground(new Color(0, 102, 0));
		radioButtonDelete.setBounds(341, 18, 63, 23);
		layeredPaneCRUDoperations.add(radioButtonDelete);

		ButtonGroup jbtnGroup = new ButtonGroup();
		jbtnGroup.add(radioButtonCreate);
		jbtnGroup.add(radioButtonRead);
		jbtnGroup.add(radioButtonUpdate);
		jbtnGroup.add(radioButtonDelete);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeIdentityManager();
			}
		});
		btnClose.setBounds(13, 45, 101, 31);
		layeredPaneCRUDoperations.add(btnClose);

		JButton btnCommitAction = new JButton("Commit Action");
		btnCommitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// instantiate the IdentitBismarkBusinessLogic() object
				iBBLogic = new IdentityBismarkBusinessLogic();

				// instantiate the IdentityBismark() object and set their values
				iBismark = new IdentityBismark();
				iBismark.setFirstName(txtFirstName.getText());
				iBismark.setLastName(txtLastName.getText());
				iBismark.setEmail(txtEmail.getText());
				iBismark.setUniqueId(txtUniqueID.getText());
				iBismark.setDisplayName(txtFirstName.getText(), txtLastName.getText());

				// validation of TextBox fields are checked here for emptiness
				if (radioButtonCreate.isSelected() || radioButtonUpdate.isSelected()) {
					if (iBismark.getFirstName().isEmpty()) {
						UtilityClass.showMessageBox("First name field is empty", "Empty Field Detected", 2);
						txtFirstName.grabFocus();
						return;
					}

					if (iBismark.getLastName().isEmpty()) {
						UtilityClass.showMessageBox("Last name field is empty", "Empty Field Detected", 2);
						txtLastName.grabFocus();
						return;
					}

					if (iBismark.getEmail().isEmpty()) {
						UtilityClass.showMessageBox("E-mail field is empty", "Empty Field Detected", 2);
						txtEmail.grabFocus();
						return;
					}

					// check for duplicate unique id when creating new record
					if (radioButtonCreate.isSelected()) {
						if (iBBLogic.IsUniqueIDAlreadyInDatabase(iBismark) == true) {
							UtilityClass.showMessageBox(
									"Duplicate Unique ID :" + iBismark.getUniqueId().toUpperCase() + " record found.",
									"Duplicate Unique ID Record", 2);
							return;
						}
					}
					

					// validate e-mail address
					if (iBBLogic.IsEmailAddressValid(iBismark) == false) {
						UtilityClass.showMessageBox("The E-mail: " + iBismark.getEmail().toUpperCase() + " is invalid",
								"Invalid E-mail", 2);
						return;
					}

				}

				if (iBismark.getUniqueId().isEmpty()) {
					UtilityClass.showMessageBox("Unique ID field is empty", "Empty Field Detected", 2);
					txtUniqueID.grabFocus();
					return;
				}

				// validation of TextBox fields are done here for over character length
				if (iBismark.getFirstName().length() > FIELD_LEN_45) {
					UtilityClass.showMessageBox(
							"First name field cannot be more than " + FIELD_LEN_45.toString() + " characters",
							"Field Length Exceeded", 2);
					txtFirstName.grabFocus();
					return;
				} else if (iBismark.getLastName().length() > FIELD_LEN_45) {
					UtilityClass.showMessageBox(
							"Last name field cannot be more than " + FIELD_LEN_45.toString() + " characters.",
							"Field Length Exceeded", 2);
					txtLastName.grabFocus();
					return;
				} else if (iBismark.getEmail().length() > FIELD_LEN_45) {
					UtilityClass.showMessageBox(
							"E-mail field cannot be more than " + FIELD_LEN_45.toString() + " characters.",
							"Field Length Exceeded", 2);
					txtEmail.grabFocus();
					return;
				} else if (iBismark.getUniqueId().length() > FIELD_LEN_10) {
					UtilityClass.showMessageBox(
							"Unique ID field cannot be more than " + FIELD_LEN_45.toString() + " characters.",
							"Field Length Exceeded", 2);
					txtUniqueID.grabFocus();
					return;
				}

				IBAppUserBusinessLogic userBLogic = new IBAppUserBusinessLogic();
				if (!userBLogic.getCRUDOperationToPerform(radioButtonCreate, radioButtonRead, radioButtonUpdate,
						radioButtonDelete)) {
					txtUniqueID.grabFocus();
					return;
				}

				// set default combobox selected value to prevent potential out of index error
				if (comboBoxReadSearchBy.getSelectedIndex() == -1) {
					comboBoxReadSearchBy.setSelectedIndex(0);
				}

				
				// check user group for the operations allowed to be performed
				if (radioButtonCreate.isSelected()) {
					if (IBApplicationUser.getLoggedInUserGroup().equals(IBApplicationUser.getUG_VIEWER())) {
						UtilityClass.showMessageBox(
								"Your User account group level does not permit you a Create operation in the application",
								"Create Operation Restricted", 1);
						return;
					}
				}
				if (radioButtonUpdate.isSelected()) {
					if (IBApplicationUser.getLoggedInUserGroup().equals(IBApplicationUser.getUG_VIEWER())) {
						UtilityClass.showMessageBox(
								"Your User account group level does not permit you an Update operation in the application",
								"Update Operation Restricted", 1);
						return;
					}
				}
				if (radioButtonDelete.isSelected()) {
					if (IBApplicationUser.getLoggedInUserGroup().equals(IBApplicationUser.getUG_POWER())
							|| IBApplicationUser.getLoggedInUserGroup().equals(IBApplicationUser.getUG_VIEWER())) {
						UtilityClass.showMessageBox(
								"Your User account group level does not permit you a Delete operation in the application",
								"Delete Operation Restricted", 1);
						return;
					}
				}

				// check for which operation type is selected; create, read, update, or delete
				performRequiredCRUDOperation(comboBoxReadSearchBy, radioButtonCreate, radioButtonRead,
						radioButtonUpdate, radioButtonDelete, iBismark);

			}
		});
		btnCommitAction.setBounds(288, 44, 132, 32);
		layeredPaneCRUDoperations.add(btnCommitAction);
		btnCommitAction.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetJComponentsToEmpty(comboBoxReadSearchBy, radioButtonCreate, radioButtonRead, radioButtonUpdate,
						radioButtonDelete);
			}
		});
		btnClearFields.setBounds(124, 45, 101, 31);
		layeredPaneCRUDoperations.add(btnClearFields);

		JPanel panelIdentityDetails = new JPanel();
		panelIdentityDetails.setForeground(Color.YELLOW);
		panelIdentityDetails.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Identity Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelIdentityDetails.setBackground(new Color(0, 102, 0));
		panelIdentityDetails.setBounds(10, 48, 430, 194);
		contentPane.add(panelIdentityDetails);

		JPanel panelReadSearchBy = new JPanel();
		panelReadSearchBy.setForeground(Color.YELLOW);
		panelReadSearchBy.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Read or Search By",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelReadSearchBy.setBackground(new Color(0, 102, 0));
		panelReadSearchBy.setBounds(10, 250, 430, 47);
		contentPane.add(panelReadSearchBy);

		this.resetJComponentsToEmpty(comboBoxReadSearchBy, radioButtonCreate, radioButtonRead, radioButtonUpdate,
				radioButtonDelete);

		comboBoxReadSearchBy.setSelectedIndex(0);

		// load initial database records at component initialisation
		getAllRecords();

	}

	private void resetJTableContents() {
		tableListOfIdentities.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Unique ID", "First Name", "Last Name", "Em-mail" }));
		tableListOfIdentities.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableListOfIdentities.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableListOfIdentities.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableListOfIdentities.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableListOfIdentities.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	/**
	 * 
	 */
	private void getAllRecords() {

		this.resetJTableContents();

		iBismarkCRUD = new IdentityBismarkDAOCRUD();
		try {
			List<IdentityBismark> ibList = iBismarkCRUD.searchIdentityBismarkRecords();
			this.setTableDataFromSearchResults(ibList, ibList.size());
		} catch (RecordSearchException e1) {
			IBLOGGER.error("Error " + e1.getMessage().toUpperCase() + " ocurred when loading records");
		}
	}

	/**
	 * This method accepts a list of search results from a read operation and loops
	 * through the list to set the appropriate values to the respective columns of a
	 * JTable component
	 * 
	 * @param iBismarkList
	 *            The list of the search results
	 * 
	 *            <pre>
	 * {@code
	 * 		IdentityBismarkDAOCRUD iBismarkCRUD = new IdentityBismarkDAOCRUD();
	 * 		List<IdentityBismark> listUser = iBismarkCRUD.deleteIdentityBismarkRecord(iBismark);
	 * 			final int listSize = iBismarkList.size();	
	 * 				for (int i = 0; i < listSize; i++) {
	 * 					newRow[0] = listUser.get(i).getUserName();
	 *            	}
	 *            }
	 *            </pre>
	 * 
	 * @param listSize
	 *            The length or size of the list as integer
	 * 
	 *            <pre>
	 * {@code 
	 * 		final int listSize = iBismarkList.size();
	 * }
	 *            </pre>
	 * 
	 * @return
	 */
	private void setTableDataFromSearchResults(List<IdentityBismark> iBismarkList, final int listSize) {
		DefaultTableModel tm = (DefaultTableModel) tableListOfIdentities.getModel();

		Object newRow[] = new Object[4];

		for (int i = 0; i < listSize; i++) {

			// 1. get identity unique id
			newRow[0] = iBismarkList.get(i).getUniqueId();

			// 2. get identity first name
			newRow[1] = iBismarkList.get(i).getFirstName();

			// 32. get identity last name
			newRow[2] = iBismarkList.get(i).getLastName();

			// 4. get identity email
			newRow[3] = iBismarkList.get(i).getEmail();

			// 5. add the row items to the table model
			tm.addRow(newRow);
		}
	}

	/**
	 * This method gets a selected list item from the JComboBox elements index value
	 * 
	 * @param comboBoxReadSearchBy
	 *            It takes the JComboBox component as its parameter
	 * @return Returns an integer of the selected item's index in the list
	 */
	private int getSelectedReadSearchByValue(JComboBox<String> comboBoxReadSearchBy) {
		int getSelectedIndex;
		switch (comboBoxReadSearchBy.getSelectedIndex()) {
		case 0:
			getSelectedIndex = 0;
			break;
		case 1:
			getSelectedIndex = 1;
			break;
		case 2:
			getSelectedIndex = 2;
			break;
		case 3:
			getSelectedIndex = 3;
			break;
		default:
			getSelectedIndex = 4;
			break;
		}
		return getSelectedIndex;
	}

	private void performRequiredCRUDOperation(JComboBox<String> comboBoxReadSearchBy, JRadioButton radioButtonCreate,
			JRadioButton radioButtonRead, JRadioButton radioButtonUpdate, JRadioButton radioButtonDelete,
			IdentityBismark iBismark) {

		iBismarkCRUD = new IdentityBismarkDAOCRUD();

		// ===---- CREATE OPERATION ----===
		if (radioButtonCreate.isSelected()) {
			try {
				final int createSuccess = iBismarkCRUD.createIdentityBismarkRecord(iBismark);
				if (createSuccess > 0) {
					UtilityClass.showMessageBox(
							"Record created successfully for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Create Operation Succeeded", 1);

					// show the list of records
					getListElementsOfIdentityBismark(comboBoxReadSearchBy, iBismark);

					this.resetJComponentsToEmpty(comboBoxReadSearchBy, radioButtonCreate, radioButtonRead,
							radioButtonUpdate, radioButtonDelete);

				} else {
					UtilityClass.showMessageBox(
							"Record not created for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Create Opereation Unsucceeded", 0);
					return;
				}
			} catch (Exception e1) {
				IBLOGGER.error(
						"Error " + e1.getMessage().toUpperCase() + " ocurred when creating a new Identity record for "
								+ iBismark.getDisplayName().toUpperCase());
			}

		}

		// ===---- READ OPERATION ----===
		if (radioButtonRead.isSelected()) {
			getListElementsOfIdentityBismark(comboBoxReadSearchBy, iBismark);
		}

		// ===---- UPDATE OPERATION ----===
		if (radioButtonUpdate.isSelected()) {
			try {
				final int updateSuccess = iBismarkCRUD.updateIdentityBismarkRecord(iBismark);
				if (updateSuccess > 0) {
					UtilityClass.showMessageBox(
							"Record updated successfully for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Update Operation Succeeded", 1);

					// show the list of records
					getListElementsOfIdentityBismark(comboBoxReadSearchBy, iBismark);

				} else {
					UtilityClass.showMessageBox(
							"Record not updated for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Update Opereation Unsucceeded", 2);
					return;
				}
			} catch (Exception e1) {
				IBLOGGER.error("Error " + e1.getMessage().toUpperCase()
						+ " ocurred when updating the Identity record for " + iBismark.getDisplayName().toUpperCase());
			}
		}

		// ===---- DELETE OPERATOIN ----===
		if (radioButtonDelete.isSelected()) {
			try {
				
				final int deleteSuccess = iBismarkCRUD.deleteIdentityBismarkRecord(iBismark);
				
				if (deleteSuccess > 0) {
					UtilityClass.showMessageBox(
							"Record deleted successfully for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Delete Operation Succeeded", 1);

					// show the list of records
					getListElementsOfIdentityBismark(comboBoxReadSearchBy, iBismark);

					this.resetJComponentsToEmpty(comboBoxReadSearchBy, radioButtonCreate, radioButtonRead,
							radioButtonUpdate, radioButtonDelete);

				} else {
					UtilityClass.showMessageBox(
							"Record not deleted for the Identity " + iBismark.getDisplayName().toUpperCase(),
							"Delete Opereation Unsucceeded", 0);
					return;
				}
			} catch (Exception e1) {
				IBLOGGER.error("Error " + e1.getMessage().toUpperCase()
						+ " ocurred when deleting the Identity record for " + iBismark.getDisplayName().toUpperCase());
			}
		}

	}

	/**
	 * @param comboBoxReadSearchBy
	 */
	private void getListElementsOfIdentityBismark(JComboBox<String> comboBoxReadSearchBy, IdentityBismark iBismark) {

		this.resetJTableContents();

		iBismarkCRUD = new IdentityBismarkDAOCRUD();

		final int getSelectedIndex = getSelectedReadSearchByValue(comboBoxReadSearchBy);
		switch (getSelectedIndex) {
		case 0:
			getAllRecords();
			break;
		case 1:
		case 2:
		case 3:
		case 4:
			try {
				List<IdentityBismark> ibList = iBismarkCRUD.searchIdentityBismarkRecords(iBismark, getSelectedIndex);

				if (ibList.size() > 0) {
					this.setTableDataFromSearchResults(ibList, ibList.size());
				}
			} catch (RecordSearchException e) {
				IBLOGGER.error("Error " + e.getMessage().toUpperCase() + " ocurred when search Identity records for "
						+ iBismark.getDisplayName().toUpperCase());
			}
			break;
		default:
			try {
				List<IdentityBismark> ibList = iBismarkCRUD.searchIdentityBismarkRecords(iBismark);
				System.out.println(ibList.size());
				if (ibList.size() > 0) {
					this.setTableDataFromSearchResults(ibList, ibList.size());
				}
			} catch (Exception e) {
				IBLOGGER.error("Error " + e.getMessage().toUpperCase()
						+ " ocurred when performing FUZZY search on Identity records for "
						+ iBismark.getDisplayName().toUpperCase());
			}
			break;
		}
	}

	private void closeIdentityManager() {
		UtilityClass.CloseAnyJFrame(this);
	}

	/**
	 * This method will simply reset all indicated JComponents that are passed as
	 * parameters including JTextBox will have their property
	 * {@code JTextBox.setText("")} values set to empty or
	 * {@code JRadioButton.setSelected(false)} for JRadioButtons and
	 * {@code JComboBox.setSelectedIndex(-1)} for JComboBox
	 * 
	 * @param comboBoxReadSearchBy
	 *            JComboBox component
	 * @param radioButtonCreate
	 *            JRadioButton component for Create operation
	 * @param radioButtonRead
	 *            JRadioButton component for Read operation
	 * @param radioButtonUpdate
	 *            JRadioButton component for Update operation
	 * @param radioButtonDelete
	 *            JRadioButton component for Delete operation
	 */
	private void resetJComponentsToEmpty(JComboBox<String> comboBoxReadSearchBy, JRadioButton radioButtonCreate,
			JRadioButton radioButtonRead, JRadioButton radioButtonUpdate, JRadioButton radioButtonDelete) {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtUniqueID.setText("");
		comboBoxReadSearchBy.setSelectedIndex(-1);
		if (radioButtonCreate.isSelected()) {
			radioButtonCreate.setSelected(false);
		}
		if (radioButtonRead.isSelected()) {
			radioButtonRead.setSelected(false);
		}
		if (radioButtonUpdate.isSelected()) {
			radioButtonUpdate.setSelected(false);
		}
		if (radioButtonDelete.isSelected()) {
			radioButtonDelete.setSelected(false);
		}
	}
}
