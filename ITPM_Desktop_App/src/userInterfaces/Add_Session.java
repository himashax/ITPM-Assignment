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

public class Add_Session implements ActionListener{

	private JFrame frmAddSession;
	private JTextField textFieldSelectedLecturer;
	private JTextField textFieldNoOfStudents;
	private JTextField textFieldDuration;
	public JPanel panel_addSession; 
	private JButton btnSubmit,btnClear;
	private JComboBox comboBoxSubject,comboBoxLec1,comboBoxLec2,comboBoxTag,comboBoxGroup,comboBoxSelectDay;

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
		frmAddSession.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmAddSession.setVisible(true);
		frmAddSession.getContentPane().setLayout(null);
		
		panel_addSession = new JPanel();
		panel_addSession.setBackground(Color.WHITE);
		panel_addSession.setBounds(0, 0, 875, 461);
		panel_addSession.setVisible(true);
		panel_addSession.setLayout(null);
		
		//new object
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
		
		//get list of lecturers
		ArrayList<String> lecturers = sessionDAO.getLecturers();
		
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
		
		String tag[] = {"Lecture", "Tutorial", "Lab|Practical", "Evaluation"};
		comboBoxTag = new JComboBox(tag);
		comboBoxTag.setBounds(143, 59, 247, 20);
		panel_1.add(comboBoxTag);
		
		textFieldSelectedLecturer = new JTextField();
		textFieldSelectedLecturer.setEditable(false);
		textFieldSelectedLecturer.setBounds(161, 107, 651, 20);
		panel_1.add(textFieldSelectedLecturer);
		textFieldSelectedLecturer.setColumns(10);
		
		//default values for lecturer 1 and 2
		comboBoxLec1.setSelectedIndex(0);
		comboBoxLec2.setSelectedIndex(1);
		//lecturer1
		comboBoxLec1.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				String lec1 = comboBoxLec1.getSelectedItem().toString();
				String lec2 = comboBoxLec2.getSelectedItem().toString();
				
				textFieldSelectedLecturer.setText(lec1 +", "+ lec2);

			}
			
		});
		
		//lecturer 2 
		comboBoxLec2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String lec1 = comboBoxLec1.getSelectedItem().toString();
				String lec2 = comboBoxLec2.getSelectedItem().toString();

				textFieldSelectedLecturer.setText(lec1 +", "+ lec2);
				
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
		
		//group ids
		ArrayList<String> groupIdList = sessionDAO.getGroupIdList();
		String[] groupArr = new String[groupIdList.size()];
		
		for(int i=0; i<groupIdList.size(); i++) {
			groupArr[i] = groupIdList.get(i);
		}

		//sub group ids
		ArrayList<String> subGroupIdList = sessionDAO.getSubGroupIdList();
		String[] subGroupArr = new String[subGroupIdList.size()];
		
		for(int i=0; i<subGroupIdList.size(); i++) {
			subGroupArr[i] = subGroupIdList.get(i);
		}

		comboBoxGroup = new JComboBox();
		comboBoxGroup.setBounds(143, 11, 247, 20);
		panel_2.add(comboBoxGroup);
		
		//when tag is clicked changing group ids
		comboBoxTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxGroup.removeAllItems();
				if(comboBoxTag.getSelectedItem().toString().equals("Lab|Practical")) {
					
					for(String subGroups : subGroupArr) {
						comboBoxGroup.addItem(subGroups);
					}

				}else {
					for(String groups : groupArr) {
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
		
		ArrayList<String> subjectInfo = sessionDAO.getSubjectInfoList();
		
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
		
		String[] days = { "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		comboBoxSelectDay = new JComboBox(days);
		comboBoxSelectDay.setBounds(531, 39, 277, 20);
		panel_2.add(comboBoxSelectDay);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SessionDAOImpl s = new SessionDAOImpl();
//				s.getLecturers();
//				s.getGroupIdList();
//				s.getSubGroupIdList();
//				s.getSubjectInfoList();
//				System.out.println("wada");
			}
		});
		btnSubmit.setBackground(SystemColor.activeCaption);
		btnSubmit.setBounds(552, 421, 97, 29);
		btnSubmit.addActionListener(this);
		panel_addSession.add(btnSubmit);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resetField();
			}
		});
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(701, 421, 97, 29);
		btnClear.addActionListener(this);
		panel_addSession.add(btnClear);
		
		frmAddSession.getContentPane().add(panel_addSession);
	}

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
		
		Object obj = e.getSource(); // to see what is clicked
		
		if(obj == btnSubmit) {
			if(comboBoxLec1.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Empty Lecturer", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxLec1.getSelectedItem().equals(comboBoxLec2.getSelectedItem().toString()) || comboBoxLec2.getSelectedItem().equals(comboBoxLec1.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(null, "Lecturer 1 and Lecturer 2 are the same", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxTag.getSelectedItem() == null){
				JOptionPane.showMessageDialog(null, "Empty Tag", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxGroup.getSelectedItem() == null){
				JOptionPane.showMessageDialog(null, "Empty Group", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxSubject.getSelectedItem() == null){
				JOptionPane.showMessageDialog(null, "Empty Subject", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxSelectDay.getSelectedItem() == null){
				JOptionPane.showMessageDialog(null, "Empty Day", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(textFieldNoOfStudents.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please give the number of students", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(textFieldDuration.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please give the duration", "Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				Session session = new Session();
				
				session.setFirstLecturer((String) comboBoxLec1.getSelectedItem());
				session.setSecLecturer((String) comboBoxLec2.getSelectedItem());
				session.setTag((String) comboBoxTag.getSelectedItem());
				session.setGroupId((String) comboBoxGroup.getSelectedItem());
				session.setSubject((String) comboBoxSubject.getSelectedItem());
				session.setNoOfStudents(Integer.parseInt(textFieldNoOfStudents.getText()));
				session.setDay((String) comboBoxSelectDay.getSelectedItem());
				session.setDuration(Integer.parseInt(textFieldDuration.getText()));
								
				int confirm = JOptionPane.showConfirmDialog(null, "Save the record "+session.getId()+ " ?", "Save the new Lecturer",
						JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					SessionDAOImpl sessionDaoObj = new SessionDAOImpl();
					try {
						sessionDaoObj.insertSession(session);
						JOptionPane.showMessageDialog(null, "Saved successfully");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				
				resetField();
				
			}
			
		}
		
		if (obj == btnClear) {
			resetField();
			JOptionPane.showMessageDialog(null, "Cleared!");
		}
	}
}
