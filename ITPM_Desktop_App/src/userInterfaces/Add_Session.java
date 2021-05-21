package userInterfaces;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import dao.SessionDAOImpl;
import models.Session;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Add_Session implements ActionListener {

	public static JFrame frmAddSession;
	private JTextField textFieldSelectedLecturer;
	private JTextField textFieldNoOfStudents;
	private JTextField textFieldDuration;
	public JPanel panel_addSession;
	private JButton btnSubmit, btnClear;
	private JComboBox comboBoxSubject, comboBoxLec1, comboBoxLec2, comboBoxTag, comboBoxGroup, comboBoxSelectDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Session window = new Add_Session();
					window.frmAddSession.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Add_Session() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddSession = new JFrame();
		frmAddSession.setTitle("ADD SESSION");
		frmAddSession.setBounds(100, 100, 891, 500);
		frmAddSession.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddSession.getContentPane().setLayout(null);

		panel_addSession = new JPanel();
		panel_addSession.setBackground(Color.WHITE);
		panel_addSession.setBounds(0, 0, 875, 461);
		panel_addSession.setVisible(true);
		panel_addSession.setLayout(null);

		SessionDAOImpl sessionDAO = new SessionDAOImpl();

		JLabel lblLecTags = new JLabel("SELECT LECTURERS & TAGS");
		lblLecTags.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecTags.setBounds(20, 28, 220, 14);
		panel_addSession.add(lblLecTags);

		JLabel lblSelectGroup = new JLabel("SELECT GROUP & SUBJECT");
		lblSelectGroup.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectGroup.setBounds(20, 237, 220, 14);
		panel_addSession.add(lblSelectGroup);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(20, 53, 834, 138);
		panel_addSession.add(panel_1);
		panel_1.setLayout(null);

		// get list of lecturers
		ArrayList<String> lecturers = sessionDAO.getLecturers();

		//set the combo box of lecturers with lecturer list array
		comboBoxLec1 = new JComboBox(lecturers.toArray());
		comboBoxLec1.setBounds(143, 11, 247, 20);
		panel_1.add(comboBoxLec1);

		comboBoxLec2 = new JComboBox(lecturers.toArray());
		comboBoxLec2.setBounds(565, 11, 247, 20);
		panel_1.add(comboBoxLec2);

		JLabel lblSelectLecturer1 = new JLabel("SELECT LECTURER 1");
		lblSelectLecturer1.setBounds(10, 14, 118, 14);
		panel_1.add(lblSelectLecturer1);

		JLabel lblSelectLecturer2 = new JLabel("SELECT LECTURER 2");
		lblSelectLecturer2.setBounds(437, 14, 118, 14);
		panel_1.add(lblSelectLecturer2);

		JLabel lblSelectTag = new JLabel("SELECT TAG");
		lblSelectTag.setBounds(10, 62, 118, 14);
		panel_1.add(lblSelectTag);

		//array of list of tags
		String tag[] = { "Lecture", "Tutorial", "Lab|Practical", "Evaluation" };
		comboBoxTag = new JComboBox(tag);
		comboBoxTag.setBounds(143, 59, 247, 20);
		panel_1.add(comboBoxTag);

		//the selected lecturers will be displayed
		textFieldSelectedLecturer = new JTextField();
		textFieldSelectedLecturer.setEditable(false);
		textFieldSelectedLecturer.setBounds(161, 107, 651, 20);
		panel_1.add(textFieldSelectedLecturer);
		textFieldSelectedLecturer.setColumns(10);

		// default values for lecturer 1 and 2
		comboBoxLec1.setSelectedIndex(0);
		comboBoxLec2.setSelectedIndex(1);
		
		// select lecturer1
		comboBoxLec1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lec1 = comboBoxLec1.getSelectedItem().toString();
				String lec2 = comboBoxLec2.getSelectedItem().toString();

				//set the text field values
				textFieldSelectedLecturer.setText(lec1 + ", " + lec2);

			}

		});

		// select lecturer 2
		comboBoxLec2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lec1 = comboBoxLec1.getSelectedItem().toString();
				String lec2 = comboBoxLec2.getSelectedItem().toString();
				
				//set the text field values
				textFieldSelectedLecturer.setText(lec1 + ", " + lec2);

			}

		});

		JLabel lblSelectedLecturers = new JLabel("SELECTED LECTURERS");
		lblSelectedLecturers.setBounds(10, 110, 141, 14);
		panel_1.add(lblSelectedLecturers);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(20, 262, 834, 138);
		panel_addSession.add(panel_2);

		// get all the group ids
		ArrayList<String> groupIdList = sessionDAO.getGroupIdList();
		String[] groupArr = new String[groupIdList.size()];

		//get the arraylist data to an array
		for (int i = 0; i < groupIdList.size(); i++) {
			groupArr[i] = groupIdList.get(i);
		}

		// get all the sub group ids
		ArrayList<String> subGroupIdList = sessionDAO.getSubGroupIdList();
		String[] subGroupArr = new String[subGroupIdList.size()];

		//get the arraylist data to an array
		for (int i = 0; i < subGroupIdList.size(); i++) {
			subGroupArr[i] = subGroupIdList.get(i);
		}

		comboBoxGroup = new JComboBox();
		comboBoxGroup.setBounds(143, 11, 247, 20);
		panel_2.add(comboBoxGroup);

		// according to the value of the tag changing group id
		comboBoxTag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxGroup.removeAllItems();
				//if lab is selected then assign subgroup id list for the combo box
				if (comboBoxTag.getSelectedItem().toString().equals("Lab|Practical")) {

					for (String subGroups : subGroupArr) {
						comboBoxGroup.addItem(subGroups);
					}
				//for lecture, tutorial and evaluation is selected then assign group id list for the combo box
				} else {
					for (String groups : groupArr) {
						comboBoxGroup.addItem(groups);
					}
				}

			}
		});

		JLabel lblGroupSelect = new JLabel("SELECT GROUP");
		lblGroupSelect.setBounds(10, 14, 118, 14);
		panel_2.add(lblGroupSelect);

		JLabel lblSelectSubject = new JLabel("SELECT SUBJECT");
		lblSelectSubject.setBounds(10, 62, 118, 14);
		panel_2.add(lblSelectSubject);

		//get all the subjects from the database
		ArrayList<String> subjectInfo = sessionDAO.getSubjectInfoList();
		
		//set the list of subjects by converting to an array
		comboBoxSubject = new JComboBox(subjectInfo.toArray());
		comboBoxSubject.setBounds(143, 59, 247, 20);
		panel_2.add(comboBoxSubject);

		textFieldNoOfStudents = new JTextField();
		textFieldNoOfStudents.setColumns(10);
		textFieldNoOfStudents.setBounds(143, 107, 247, 20);
		panel_2.add(textFieldNoOfStudents);

		JLabel lblNuOfStudents = new JLabel("NO. OF STUDENTS");
		lblNuOfStudents.setBounds(10, 110, 130, 14);
		panel_2.add(lblNuOfStudents);

		textFieldDuration = new JTextField();
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(531, 107, 213, 20);
		panel_2.add(textFieldDuration);

		JLabel lblDurationHours = new JLabel("DURATION");
		lblDurationHours.setBounds(445, 110, 70, 14);
		panel_2.add(lblDurationHours);

		JLabel lblHrs = new JLabel("hrs");
		lblHrs.setBounds(754, 110, 70, 14);
		panel_2.add(lblHrs);

		JLabel lblDay = new JLabel("DAY");
		lblDay.setBounds(445, 42, 93, 14);
		panel_2.add(lblDay);

		//string array for the day combo box
		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		comboBoxSelectDay = new JComboBox(days);
		comboBoxSelectDay.setBounds(531, 39, 277, 20);
		panel_2.add(comboBoxSelectDay);

		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBackground(SystemColor.activeCaption);
		btnSubmit.setBounds(552, 421, 97, 29);
		btnSubmit.addActionListener(this);
		panel_addSession.add(btnSubmit);

		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(701, 421, 97, 29);
		btnClear.addActionListener(this);
		panel_addSession.add(btnClear);

		frmAddSession.getContentPane().add(panel_addSession);
	}

	//the function to clear the fields and set the default values to the combo boxes
	public void resetField() {
		comboBoxLec1.setSelectedIndex(0);
		comboBoxLec2.setSelectedIndex(1);
		comboBoxTag.setSelectedIndex(0);
		textFieldSelectedLecturer.setText(null);
		comboBoxGroup.setSelectedIndex(0);
		comboBoxSubject.setSelectedIndex(0);
		comboBoxSelectDay.setSelectedIndex(0);
		textFieldNoOfStudents.setText(null);
		textFieldDuration.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// to see what is clicked
		Object obj = e.getSource();

		//submit button is clicked
		if (obj == btnSubmit) {
			if (comboBoxLec1.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Lecturer", "Alert", JOptionPane.WARNING_MESSAGE);
				//check if both the selected lecturers are the same
			} else if (comboBoxLec1.getSelectedItem().equals(comboBoxLec2.getSelectedItem().toString())
					|| comboBoxLec2.getSelectedItem().equals(comboBoxLec1.getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(null, "Lecturer 1 and Lecturer 2 are the same", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else if (comboBoxTag.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Tag", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if (comboBoxGroup.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Group", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if (comboBoxSubject.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Subject", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if (comboBoxSelectDay.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Day", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if (textFieldNoOfStudents.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please give the number of students", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else if (textFieldDuration.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please give the duration", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if(Integer.parseInt(textFieldDuration.getText().toString()) < 0 || Integer.parseInt(textFieldNoOfStudents.getText().toString()) < 0) {
				JOptionPane.showMessageDialog(null, "Add a positive integer value", "Alert", JOptionPane.WARNING_MESSAGE);
			} else {
				
				//set the values
				Session session = new Session();

				session.setFirstLecturer((String) comboBoxLec1.getSelectedItem());
				session.setSecLecturer((String) comboBoxLec2.getSelectedItem());
				session.setTag((String) comboBoxTag.getSelectedItem());
				session.setGroupId((String) comboBoxGroup.getSelectedItem());
				session.setSubject((String) comboBoxSubject.getSelectedItem());
				session.setNoOfStudents(Integer.parseInt(textFieldNoOfStudents.getText()));
				session.setDay((String) comboBoxSelectDay.getSelectedItem());
				session.setDuration(Integer.parseInt(textFieldDuration.getText()));
				
				//display confirm message to save the session
				int confirm = JOptionPane.showConfirmDialog(null, "Save the record ?",
						"Save the new Lecturer", JOptionPane.YES_NO_OPTION);
				
				//if confirmed using YES
				if (confirm == JOptionPane.YES_OPTION) {
					SessionDAOImpl sessionDaoObj = new SessionDAOImpl();
					try {
						//check whether same values are there for two session id
						if (sessionDaoObj.getDuplicates(session).size() > 0) {
							JOptionPane.showMessageDialog(null, "You cannot enter duplicate records!");
						//check whether same group, same subject has two sessions for the same tag
						}else if(sessionDaoObj.getDuplicateSessions(session).size()>0) {
							JOptionPane.showMessageDialog(null, "You cannot enter duplicate sessions!");
						//if validated save the session 
						} else {
							sessionDaoObj.insertSession(session);
							//display the successfully saved message
							JOptionPane.showMessageDialog(null, "Saved successfully");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			}

		}

		//clear button is clicked
		if (obj == btnClear) {
			//call the resetField()
			resetField();
			//Display the cleared message
			JOptionPane.showMessageDialog(null, "Cleared!");
		}
	}
}
