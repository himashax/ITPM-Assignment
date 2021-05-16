
package userInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.LecturerDAOImpl;
import models.Active_Days;
import models.Lecturer;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.UIManager;

public class Manage_Lecturer implements ActionListener {

	private JFrame frmManageLecturer;
	private JTable table;
	private JPanel panel_1;
	private JTextField textField_mName;
	private JTextField textField_mEId;
	private JTextField textField_mRank;
	private JComboBox comboBox_mCampus;
	private JComboBox comboBox_mDepartment;
	private JComboBox comboBox_mBuilding;
	private JComboBox comboBox_mFaculty;
	private JComboBox comboBox_mLevel;
	private int idValue, delEmpID;
	private JButton btnUpdate, btnDelete, btnClear,btnModifyRank;
	private JSpinner spinner_mMon, spinner_mTue, spinner_mWed, spinner_mThur, spinner_mFri, spinner_mSat, spinner_mSun;
	public JPanel panel_manageLecturer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Lecturer window = new Manage_Lecturer();
					window.frmManageLecturer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manage_Lecturer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManageLecturer = new JFrame();
		frmManageLecturer.setTitle("MANAGE LECTURER");
		frmManageLecturer.getContentPane().setBackground(SystemColor.activeCaptionBorder);
		frmManageLecturer.setBounds(100, 100, 807, 478);
		frmManageLecturer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageLecturer.setSize(888, 500);
		frmManageLecturer.getContentPane().setLayout(null);
		frmManageLecturer.setLocationRelativeTo(null);
		
		panel_manageLecturer = new JPanel();
		panel_manageLecturer.setBounds(0, 0, 872, 461);
		panel_manageLecturer.setLayout(null);
		
		SpinnerModel monday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel tuesday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel wednesday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel thursday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel friday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel saturday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel sunday = new SpinnerNumberModel(0, 0, 8, 1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 852, 151);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 173, 852, 277);
		
		panel_1.setLayout(null);

		textField_mName = new JTextField();
		textField_mName.setBounds(113, 11, 256, 20);
		panel_1.add(textField_mName);
		textField_mName.setColumns(10);

		String[] fac = { "Computing", "Engineering","Business" };
		comboBox_mFaculty = new JComboBox(fac);
		comboBox_mFaculty.setEditable(true);
		comboBox_mFaculty.setBounds(113, 58, 256, 20);
		panel_1.add(comboBox_mFaculty);

		String[] level = { "1", "2", "3", "4", "5", "6" };


		String[] com_dep = { "Software Engineering", "CSSE" };
		String[] eng_dep = { "Electrical", "Mechanical" };
		String[] business_dep = { "Accounting", "Logistics" };
		
		comboBox_mDepartment = new JComboBox();
		comboBox_mDepartment.setEditable(true);
		comboBox_mDepartment.setBounds(589, 11, 235, 20);
		panel_1.add(comboBox_mDepartment);
		
		comboBox_mFaculty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_mDepartment.removeAllItems();
				
				if(comboBox_mFaculty.getSelectedItem().toString().equals("Computing")) {
					for (String string : com_dep) {
						comboBox_mDepartment.addItem(string);
					}
				}else if(comboBox_mFaculty.getSelectedItem().toString().equals("Engineering")) {
					for (String string : eng_dep) {
						comboBox_mDepartment.addItem(string);
					}
				}else if(comboBox_mFaculty.getSelectedItem().toString().equals("Business")) {
					for (String string : business_dep) {
						comboBox_mDepartment.addItem(string);
					}
				}
				
			}
		});
		
		
		String[] Malabe_build = { "Malabe New building", "Malabe Main building", "Malabe BM" };
		String[] kandy_build = { "Kandy building 1", "Kandy Main building", "kandy MB" };
		String[] matara_build = { "Matara building 1", "Matara Main building", "Matara BM" };
		String[] jaffna_build = { "Jaffna building 1", "Jaffna Main building", "Jaffna BM" };
		String[] metro_build = { "Metro building 1", "Metro Main building", "Metro BM" };
		
		String[] cen = { "Malabe", "Metro Campus", "Kandy", "Matara", "Jaffna" };
		comboBox_mCampus = new JComboBox(cen);
		comboBox_mCampus.setEditable(true);
		comboBox_mCampus.setBounds(589, 58, 235, 20);
		panel_1.add(comboBox_mCampus);
		

		comboBox_mBuilding = new JComboBox();
		comboBox_mBuilding.setEditable(true);
		comboBox_mBuilding.setBounds(589, 103, 235, 20);
		panel_1.add(comboBox_mBuilding);
		
		
		comboBox_mCampus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_mBuilding.removeAllItems();
				if (comboBox_mCampus.getSelectedItem().toString().equals("Malabe")) {
					for (String string : Malabe_build) {
						comboBox_mBuilding.addItem(string);
					}
				}else if (comboBox_mCampus.getSelectedItem().toString().equals("Kandy")) {
					for (String string : kandy_build) {
						comboBox_mBuilding.addItem(string);
					}
				} else if (comboBox_mCampus.getSelectedItem().toString().equals("Matara")) {
					for (String string : matara_build) {
						comboBox_mBuilding.addItem(string);
					}
				} else if (comboBox_mCampus.getSelectedItem().toString().equals("Metro Campus")) {
					for (String string : metro_build) {
						comboBox_mBuilding.addItem(string);
					}
				} else if (comboBox_mCampus.getSelectedItem().toString().equals("Jaffna")) {
					for (String string : jaffna_build) {
						comboBox_mBuilding.addItem(string);
					}
				}
				
			}
		});



		JLabel lblNew_mName = new JLabel("NAME");
		lblNew_mName.setBounds(36, 14, 46, 14);
		panel_1.add(lblNew_mName);

		JLabel lblNew_mFaculty = new JLabel("FACULTY");
		lblNew_mFaculty.setBounds(35, 61, 68, 14);
		panel_1.add(lblNew_mFaculty);

		JLabel lblNew_mCampus = new JLabel("CAMPUS/CENTRE");
		lblNew_mCampus.setBounds(444, 61, 105, 14);
		panel_1.add(lblNew_mCampus);

		JLabel lblNew_mDept = new JLabel("DEPARTMENT");
		lblNew_mDept.setBounds(444, 14, 105, 14);
		panel_1.add(lblNew_mDept);

		JLabel lblNew_mBuilding = new JLabel("BUILDING");
		lblNew_mBuilding.setBounds(444, 106, 105, 14);
		panel_1.add(lblNew_mBuilding);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(428, 164, 396, 102);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lbl_Mmonday = new JLabel("Monday");
		lbl_Mmonday.setBounds(10, 11, 46, 14);
		panel_2.add(lbl_Mmonday);

		JLabel lbl_Mtues = new JLabel("Tuesday");
		lbl_Mtues.setBounds(10, 44, 46, 14);
		panel_2.add(lbl_Mtues);

		JLabel lbl_Mwed = new JLabel("Wednesday");
		lbl_Mwed.setBounds(10, 78, 62, 14);
		panel_2.add(lbl_Mwed);

		JLabel lbl_Mthur = new JLabel("Thursday");
		lbl_Mthur.setBounds(151, 11, 46, 14);
		panel_2.add(lbl_Mthur);

		JLabel lbl_Mfri = new JLabel("Friday");
		lbl_Mfri.setBounds(151, 44, 46, 14);
		panel_2.add(lbl_Mfri);

		JLabel lbl_Msat = new JLabel("Saturday");
		lbl_Msat.setBounds(151, 78, 46, 14);
		panel_2.add(lbl_Msat);

		JLabel lbl_Msun = new JLabel("Sunday");
		lbl_Msun.setBounds(284, 11, 46, 14);
		panel_2.add(lbl_Msun);

		spinner_mMon = new JSpinner(monday);
		spinner_mMon.setBounds(84, 8, 36, 20);
		panel_2.add(spinner_mMon);

		spinner_mTue = new JSpinner(tuesday);
		spinner_mTue.setBounds(84, 41, 36, 20);
		panel_2.add(spinner_mTue);

		spinner_mWed = new JSpinner(wednesday);
		spinner_mWed.setBounds(84, 75, 36, 20);
		panel_2.add(spinner_mWed);

		spinner_mThur = new JSpinner(thursday);
		spinner_mThur.setBounds(224, 8, 36, 20);
		panel_2.add(spinner_mThur);

		spinner_mFri = new JSpinner(friday);
		spinner_mFri.setBounds(224, 41, 36, 20);
		panel_2.add(spinner_mFri);

		spinner_mSat = new JSpinner(saturday);
		spinner_mSat.setBounds(224, 75, 36, 20);
		panel_2.add(spinner_mSat);

		spinner_mSun = new JSpinner(sunday);
		spinner_mSun.setBounds(329, 8, 36, 20);
		panel_2.add(spinner_mSun);

		JLabel lblNew_mBuilding_1 = new JLabel("ACTIVE DAYS & HOURS");
		lblNew_mBuilding_1.setBounds(444, 143, 131, 20);
		panel_1.add(lblNew_mBuilding_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		panel_3.setBounds(35, 103, 334, 163);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		textField_mEId = new JTextField();
		textField_mEId.setEditable(false);
		textField_mEId.setBounds(81, 11, 224, 20);
		panel_3.add(textField_mEId);
		textField_mEId.setColumns(10);

		JLabel lblNew_mEmpId = new JLabel("EMP ID");
		lblNew_mEmpId.setBounds(10, 14, 46, 14);
		panel_3.add(lblNew_mEmpId);
		
		JLabel lblNew_mLevel = new JLabel("LEVEL");
		lblNew_mLevel.setBounds(10, 51, 46, 14);
		panel_3.add(lblNew_mLevel);

		JLabel lblNew_mRank = new JLabel("RANK");
		lblNew_mRank.setBounds(10, 94, 46, 14);
		panel_3.add(lblNew_mRank);
		comboBox_mLevel = new JComboBox(level);
		comboBox_mLevel.setBounds(81, 48, 224, 20);
		panel_3.add(comboBox_mLevel);
		comboBox_mLevel.setEditable(true);
						
		textField_mRank = new JTextField();
		textField_mRank.setEditable(false);
		textField_mRank.setBounds(81, 91, 224, 20);
		panel_3.add(textField_mRank);
		textField_mRank.setColumns(10);
		
		btnModifyRank = new JButton("GENERATE RANK");
		btnModifyRank.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (textField_mEId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select the Employee ID!", "Alert",
							JOptionPane.WARNING_MESSAGE);
				} else if (textField_mEId.getText().length() != 6) {
					JOptionPane.showMessageDialog(null, "Check the Employee ID", "Alert", JOptionPane.WARNING_MESSAGE);

				} else {
					String level, empID, rank;
					try {
						level = (String) comboBox_mLevel.getSelectedItem();
						empID = textField_mEId.getText();

						Integer.parseInt(empID);

						if (comboBox_mLevel.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "Select a level!", "Alert",
									JOptionPane.WARNING_MESSAGE);
						} else {
							rank = level + "." + empID;
							System.out.println("Rank " + rank);
							textField_mRank.setText(rank);
							JOptionPane.showMessageDialog(null, "Generated Successfully!");
						}

					} catch (NumberFormatException n) {
						// Not an integer
						JOptionPane.showMessageDialog(null, "Invalid Characters in empID!", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});
		btnModifyRank.setBackground(SystemColor.activeCaption);
		btnModifyRank.setBounds(175, 129, 130, 23);
		panel_3.add(btnModifyRank);
		
		//frmManageLecturer.setVisible(true);

		// newly added two columns

		DefaultTableModel modal = new DefaultTableModel(new String[] { "Id", "Name", "Faculty", "Department", "EmpID",
				"Level", "Rank", "Centre", "Building", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun" }, 0);
		table = new JTable(modal);
		table.setBounds(10, 11, 800, 116);
		JTableHeader header = table.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table.setModel(modal);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 11, 736, 129);

		// call method
		lecturerGroupTable();

		panel.add(pane);

		// select a record
		table.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowId = table.getSelectedRow();
				LecturerDAOImpl lecturerDAOImpl = new LecturerDAOImpl();

				idValue = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
				Lecturer lecturer = lecturerDAOImpl.getLecturerById(idValue);
				// System.out.println(idValue);
				delEmpID = Integer.parseInt(modal.getValueAt(rowId, 4).toString());

				textField_mName.setText(lecturer.getLecName());
				comboBox_mFaculty.setSelectedItem(lecturer.getFaculty());
				comboBox_mDepartment.setSelectedItem(lecturer.getDepart());
				textField_mEId.setText(Integer.toString(lecturer.getEmployeeId()));
				comboBox_mLevel.setSelectedItem(lecturer.getLevel());
				textField_mRank.setText(lecturer.getRank());
				comboBox_mCampus.setSelectedItem(lecturer.getCampus());
				comboBox_mBuilding.setSelectedItem(lecturer.getBuilding());

				Active_Days active_Days = lecturerDAOImpl.getActiveDaysByEmployeeID(lecturer.getEmployeeId());

				spinner_mMon.setValue(active_Days.getMon());
				spinner_mTue.setValue(active_Days.getTue());
				spinner_mWed.setValue(active_Days.getWed());
				spinner_mThur.setValue(active_Days.getThur());
				spinner_mFri.setValue(active_Days.getFri());
				spinner_mSat.setValue(active_Days.getSat());
				spinner_mSun.setValue(active_Days.getSun());

				// comboBox_mDays.setSelectedItem(modal.getValueAt(rowId, 9));
				// comboBox_mHours.setSelectedItem(modal.getValueAt(rowId, 10));

				// textField_mEId.setText(modal.getValueAt(rowId, 0).toString());
			}
		});

		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUpdate.setBackground(SystemColor.activeCaption);
		btnUpdate.setBounds(756, 25, 86, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);

		btnDelete = new JButton("DELETE");
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.setBounds(756, 70, 86, 23);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		btnClear = new JButton("CLEAR");
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(756, 117, 86, 23);
		btnClear.addActionListener(this);
		panel.add(btnClear);
		
		panel_manageLecturer.add(panel);
		panel_manageLecturer.add(panel_1);
		frmManageLecturer.getContentPane().add(panel_manageLecturer);

	}

	public void lecturerGroupTable() {
		LecturerDAOImpl lecturerDAOImpl = new LecturerDAOImpl();
		ArrayList<Lecturer> lecturer_list = lecturerDAOImpl.listLecturer();

		/// newly added

		Active_Days acDay_group = new Active_Days();
		ArrayList<Active_Days> active_list = lecturerDAOImpl.listDays();

		DefaultTableModel modal = (DefaultTableModel) table.getModel();

		Object[] row = new Object[16];

		for (int i = 0; i < lecturer_list.size(); i++) {
			row[0] = lecturer_list.get(i).getEid();
			row[1] = lecturer_list.get(i).getLecName();
			row[2] = lecturer_list.get(i).getFaculty();
			row[3] = lecturer_list.get(i).getDepart();
			row[4] = lecturer_list.get(i).getEmployeeId();
			row[5] = lecturer_list.get(i).getLevel();
			row[6] = lecturer_list.get(i).getRank();
			row[7] = lecturer_list.get(i).getCampus();
			row[8] = lecturer_list.get(i).getBuilding();

			// newly added
			row[9] = active_list.get(i).getMon();
			row[10] = active_list.get(i).getTue();
			row[11] = active_list.get(i).getWed();
			row[12] = active_list.get(i).getThur();
			row[13] = active_list.get(i).getFri();
			row[14] = active_list.get(i).getSat();
			row[15] = active_list.get(i).getSun();

			System.out.println(active_list.get(i).getMon());
			modal.addRow(row);
		}

		// i is related with the db
		// for(int i=0; i<active_list.size(); i++) {
		// row[9] = active_list.get(i).getDays();
		// row[10] = active_list.get(i).getHours();
		//
		// modal.addRow(row);
		// }
	}

	public void resetField() {
		idValue = 0;
		comboBox_mFaculty.setSelectedIndex(0);
		comboBox_mDepartment.setSelectedIndex(0);
		comboBox_mCampus.setSelectedIndex(0);
		comboBox_mBuilding.setSelectedIndex(0);
		comboBox_mLevel.setSelectedIndex(0);
		textField_mName.setText(null);
		textField_mEId.setText(null);
		textField_mRank.setText(null);
		spinner_mMon.setValue(0);
		spinner_mTue.setValue(0);
		spinner_mWed.setValue(0);
		spinner_mThur.setValue(0);
		spinner_mFri.setValue(0);
		spinner_mSat.setValue(0);
		spinner_mSun.setValue(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();

		if (obj == btnUpdate) {
			if (idValue == 0) {
				JOptionPane.showMessageDialog(null, "Please select a record to update");
			}else if (textField_mName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the name field!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else {
				Lecturer lecturer = new Lecturer();
				lecturer.setLecName(textField_mName.getText());
				lecturer.setFaculty((String) comboBox_mFaculty.getSelectedItem());
				lecturer.setDepart((String) comboBox_mDepartment.getSelectedItem());
				lecturer.setEmployeeId(Integer.parseInt(textField_mEId.getText()));
				lecturer.setEid(idValue);
				lecturer.setLevel(Integer.parseInt(comboBox_mLevel.getSelectedItem().toString()));
				lecturer.setRank((String) textField_mRank.getText());
				lecturer.setCampus((String) comboBox_mCampus.getSelectedItem());
				lecturer.setBuilding((String) comboBox_mBuilding.getSelectedItem());

				int mon = (int) spinner_mMon.getValue();
				int tue = (int) spinner_mTue.getValue();
				int wed = (int) spinner_mWed.getValue();
				int thur = (int) spinner_mThur.getValue();
				int fri = (int) spinner_mFri.getValue();
				int sat = (int) spinner_mSat.getValue();
				int sun = (int) spinner_mSun.getValue();

				//test the rank of the lecturer
				String test = lecturer.getLevel() + "." + lecturer.getEmployeeId();
				System.out.println("New rank" + test);
				
				//check the rank with the previous rank
				if (lecturer.getRank().equals(test) == false) {
					JOptionPane.showMessageDialog(null, "Please click the 'Generate the rank!'", "Alert",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update " + lecturer.getLecName() + " ?", "Confirm Update",
							JOptionPane.YES_NO_OPTION);
	
					if (confirm == JOptionPane.YES_OPTION) {
						LecturerDAOImpl lecturerDAOImpl = new LecturerDAOImpl();
						// newly added
						Active_Days acDays = new Active_Days();
	
						lecturerDAOImpl.updateLecturers(lecturer);
	
						// newly added
						lecturerDAOImpl.updateActiveDays(lecturer.getEmployeeId(), mon, tue, wed, thur, fri, sat, sun);
	
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0);
						lecturerGroupTable();
					}
	
					resetField();
				}
			}

		}

		if (obj == btnDelete) {
			
			if (idValue == 0) {
				JOptionPane.showMessageDialog(null, "Please select a record to delete!");
			}else if (textField_mName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the name field!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else {
				LecturerDAOImpl lecturerDAOImpl = new LecturerDAOImpl();
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure, you want to delete "+lecturerDAOImpl.getLecturerById(idValue).getLecName()+" ?", "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
	
				if (confirm == JOptionPane.YES_OPTION) {
					// newly added
					Active_Days actDays = new Active_Days();
					lecturerDAOImpl.deleteLec(idValue);
	
					lecturerDAOImpl.deleteActiveDays(delEmpID);
	
					// to display remaining table with data
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					lecturerGroupTable();
				}
				resetField();
			}
		}
		
		if (obj == btnClear) {
			resetField();
		}
	}
}

