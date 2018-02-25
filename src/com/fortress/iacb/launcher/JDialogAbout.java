package com.fortress.iacb.launcher;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fortress.iacb.services.UtilityClass;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogAbout extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogAbout dialog = new JDialogAbout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogAbout() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("About I AM CORE BISMARK");
		setBounds(100, 100, 591, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 102, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextPane txtpnAboutiabc = new JTextPane();
		txtpnAboutiabc.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnAboutiabc.setBackground(new Color(0, 102, 0));
		txtpnAboutiabc.setForeground(new Color(255, 255, 255));
		txtpnAboutiabc.setText(
				"This is a simple Identity Management application named I AM CORE BISMARK. It emanated as a tutorial in class and as mini semester project, student like me Bismark, were suppose to re-create the application from scratch with features such as that which was taught in class.\r\n\r\nThis application is not fully test but at least more than eighty percent (80%) of the functionalities are tested and working.\r\n\r\nA document is generated on it with a java-doc as well which is published on my github with all references duly acknowledged where possible.\r\n\r\nFeel free to use the application in any way that would help you to develop your programming skills especially Java.\r\n\r\nThank you");
		txtpnAboutiabc.setBounds(10, 112, 555, 201);
		contentPanel.add(txtpnAboutiabc);

		JTextPane txtpnAboutdev = new JTextPane();
		txtpnAboutdev.setBackground(new Color(0, 102, 0));
		txtpnAboutdev.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnAboutdev.setForeground(new Color(255, 255, 0));
		txtpnAboutdev.setText(
				"Developer : Bismark A. FRIMPONG\r\nCountry : Ghana (leaves in Paris, France)\r\nSchool : EPITA Graduate School of Computer Science & Advance Technologies\r\nProgram : MSc Computer Science - Software Engineering\r\nPeriod : Fall 2017\r\nCourse : UML & Java Fundamentals");
		txtpnAboutdev.setBounds(10, 11, 555, 90);
		contentPanel.add(txtpnAboutdev);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 204, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeIdentityManager();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private void closeIdentityManager() {
		UtilityClass.CloseAnyJFrame(this);
	}
}
