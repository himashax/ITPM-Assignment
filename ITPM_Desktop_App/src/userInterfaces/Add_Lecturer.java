package userInterfaces;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import dao.LecturerDAOImpl;
import models.Active_Days;
import models.Lecturer;

import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.UIManager;

public class Add_Lecturer implements ActionListener {

	public JPanel panel_addLecturer;
	
	private JFrame frmAddLecturer;
	private JTextField textFieldName;
	private JLabel lblFaculty, lblDept, lblCampuscentre;
	private JComboBox comboBox_dept, comboBox_fac, comboBox_build;
	private JComboBox comboBox_centre, comboBox_level;
	private JLabel lblBuilding, lblEmpId, lblLevel, lblRank;
	private JLabel lblDay;
	private JLabel lblName;
	private JTextField textFieldEmpId;
	private JTextField textField_Rank;
	private JPanel panel;
	private JButton btnClear, btnSave, btnRank;
	private JSpinner spinner_Mon, spinner_Tue, spinner_Wed, spinner_Thur, spinner_Fri, spinner_Sat, spinner_Sun;
	private JLabel lbl_Weekend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Lecturer window = new Add_Lecturer();
					window.frmAddLecturer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Add_Lecturer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddLecturer = new JFrame();
		frmAddLecturer.setTitle("ADD LECTURER");
		frmAddLecturer.setBounds(100, 100, 897, 487);
		frmAddLecturer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddLecturer.getContentPane().setLayout(null);
		frmAddLecturer.setLocationRelativeTo(null);
		
		panel_addLecturer = new JPanel();
		panel_addLecturer.setBackground(Color.WHITE);
		panel_addLecturer.setLayout(null);
		panel_addLecturer.setBounds(0, 0, 881, 448);

		SpinnerModel monday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel tuesday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel wednesday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel thursday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel friday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel saturday = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerModel sunday = new SpinnerNumberModel(0, 0, 8, 1);
		

		textFieldName = new JTextField();
		textFieldName.setBounds(148, 32, 244, 20);
		panel_addLecturer.add(textFieldName);
		textFieldName.setColumns(10);

		lblName = new JLabel("NAME");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblName.setBounds(34, 30, 74, 25);
		panel_addLecturer.add(lblName);

		lblFaculty = new JLabel("FACULTY");
		lblFaculty.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFaculty.setBounds(34, 84, 74, 25);
		panel_addLecturer.add(lblFaculty);

		lblDept = new JLabel("DEPARTMENT");
		lblDept.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDept.setBounds(34, 147, 86, 25);
		panel_addLecturer.add(lblDept);

		String[] fac = { "Computing", "Engineering","Business" };
		comboBox_fac = new JComboBox(fac);
		comboBox_fac.setEditable(true);
		comboBox_fac.setBounds(148, 86, 244, 20);
		panel_addLecturer.add(comboBox_fac);

		String[] com_dep = { "Software Engineering", "CSSE" };
		String[] eng_dep = { "Electrical", "Mechanical" };
		String[] business_dep = { "Accounting", "Logistics" };
		// String[] eng_dep = {"Electrical","Mechanical"};

		comboBox_dept = new JComboBox();
		comboBox_dept.setEditable(true);
		comboBox_dept.setBounds(148, 149, 244, 20);
		panel_addLecturer.add(comboBox_dept);
		
		
		comboBox_fac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_dept.removeAllItems();
				if(comboBox_fac.getSelectedItem().toString().equals("Computing")) {
					for (String string : com_dep) {
						comboBox_dept.addItem(string);
					}
				}else if(comboBox_fac.getSelectedItem().toString().equals("Engineering")) {
					for (String string : eng_dep) {
						comboBox_dept.addItem(string);
					}
				}else if(comboBox_fac.getSelectedItem().toString().equals("Business")) {
					for (String string : business_dep) {
						comboBox_dept.addItem(string);
					}
				}
				
			}
		});

		lblCampuscentre = new JLabel("CAMPUS/CENTRE");
		lblCampuscentre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCampuscentre.setBounds(427, 30, 113, 25);
		panel_addLecturer.add(lblCampuscentre);

		String[] cen = { "Malabe", "Metro Campus", "Kandy", "Matara", "Jaffna" };
		comboBox_centre = new JComboBox(cen);
		comboBox_centre.setBounds(620, 32, 233, 20);
		panel_addLecturer.add(comboBox_centre);

		String[] Malabe_build = { "Malabe New building", "Malabe Main building", "Malabe BM" };
		String[] kandy_build = { "Kandy building 1", "Kandy Main building", "kandy MB" };
		String[] matara_build = { "Matara building 1", "Matara Main building", "Matara BM" };
		String[] jaffna_build = { "Jaffna building 1", "Jaffna Main building", "Jaffna BM" };
		String[] metro_build = { "Metro building 1", "Metro Main building", "Metro BM" };
		
		comboBox_build = new JComboBox();
		comboBox_build.setBounds(620, 86, 233, 20);
		panel_addLecturer.add(comboBox_build);

		lblBuilding = new JLabel("BUILDING");
		lblBuilding.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBuilding.setBounds(427, 84, 113, 25);
		panel_addLecturer.add(lblBuilding);

		comboBox_centre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_build.removeAllItems();
				if (comboBox_centre.getSelectedItem().toString().equals("Malabe")) {
					for (String string : Malabe_build) {
						comboBox_build.addItem(string);
					}
				} else if (comboBox_centre.getSelectedItem().toString().equals("Kandy")) {
					for (String string : kandy_build) {
						comboBox_build.addItem(string);
					}
				} else if (comboBox_centre.getSelectedItem().toString().equals("Matara")) {
					for (String string : matara_build) {
						comboBox_build.addItem(string);
					}
				} else if (comboBox_centre.getSelectedItem().toString().equals("Metro Campus")) {
					for (String string : metro_build) {
						comboBox_build.addItem(string);
					}
				} else if (comboBox_centre.getSelectedItem().toString().equals("Jaffna")) {
					for (String string : jaffna_build) {
						comboBox_build.addItem(string);
					}
				}

			}
		});

		String[] level = { "1", "2", "3", "4", "5", "6" };

		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(495, 197, 358, 195);
		panel_addLecturer.add(panel);
		panel.setLayout(null);

		lblDay = new JLabel("Mon");
		lblDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDay.setBounds(10, 46, 39, 25);
		panel.add(lblDay);

		spinner_Mon = new JSpinner(monday);
		spinner_Mon.setBounds(59, 48, 54, 20);
		panel.add(spinner_Mon);

		JLabel lblTue = new JLabel("Tue");
		lblTue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTue.setBounds(10, 86, 39, 25);
		panel.add(lblTue);

		spinner_Tue = new JSpinner(tuesday);
		spinner_Tue.setBounds(59, 88, 54, 20);
		panel.add(spinner_Tue);

		spinner_Wed = new JSpinner(wednesday);
		spinner_Wed.setBounds(254, 8, 54, 20);
		panel.add(spinner_Wed);

		JLabel lblWed = new JLabel("Wed");
		lblWed.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblWed.setBounds(182, 6, 39, 25);
		panel.add(lblWed);

		spinner_Thur = new JSpinner(thursday);
		spinner_Thur.setBounds(254, 48, 54, 20);
		panel.add(spinner_Thur);

		JLabel lblThur = new JLabel("Thur");
		lblThur.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblThur.setBounds(182, 46, 39, 25);
		panel.add(lblThur);

		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFriday.setBounds(182, 86, 39, 25);
		panel.add(lblFriday);

		spinner_Fri = new JSpinner(friday);
		spinner_Fri.setBounds(254, 88, 54, 20);
		panel.add(spinner_Fri);

		JLabel lblSat = new JLabel("Sat");
		lblSat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSat.setBounds(10, 160, 39, 25);
		panel.add(lblSat);

		spinner_Sat = new JSpinner(saturday);
		spinner_Sat.setBounds(59, 162, 54, 20);
		panel.add(spinner_Sat);

		JLabel lblSun = new JLabel("Sun");
		lblSun.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSun.setBounds(171, 160, 39, 25);
		panel.add(lblSun);

		spinner_Sun = new JSpinner(sunday);
		spinner_Sun.setBounds(254, 162, 54, 20);
		panel.add(spinner_Sun);

		JLabel lbl_Weekday = new JLabel("WEEKDAY");
		lbl_Weekday.setForeground(SystemColor.activeCaptionText);
		lbl_Weekday.setBackground(Color.WHITE);
		lbl_Weekday.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lbl_Weekday.setBounds(10, 11, 71, 14);
		panel.add(lbl_Weekday);

		lbl_Weekend = new JLabel("WEEKEND");
		lbl_Weekend.setForeground(Color.BLACK);
		lbl_Weekend.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lbl_Weekend.setBackground(Color.WHITE);
		lbl_Weekend.setBounds(10, 135, 71, 14);
		panel.add(lbl_Weekend);

		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(564, 412, 113, 25);
		btnClear.addActionListener(this);
		panel_addLecturer.add(btnClear);

		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "Saved Successfully!");
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSave.setBackground(SystemColor.activeCaption);
		btnSave.setBounds(735, 412, 118, 25);
		btnSave.addActionListener(this);
		panel_addLecturer.add(btnSave);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(34, 197, 358, 195);
		panel_addLecturer.add(panel_1);
		panel_1.setLayout(null);

		lblEmpId = new JLabel("EMPLOYEE ID");
		lblEmpId.setBounds(10, 31, 80, 14);
		panel_1.add(lblEmpId);
		lblEmpId.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		textFieldEmpId = new JTextField();
		textFieldEmpId.setBounds(128, 28, 206, 20);
		panel_1.add(textFieldEmpId);
		textFieldEmpId.setColumns(10);

		lblLevel = new JLabel("LEVEL");
		lblLevel.setBounds(10, 66, 55, 25);
		panel_1.add(lblLevel);
		lblLevel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBox_level = new JComboBox(level);
		comboBox_level.setBounds(128, 68, 206, 20);
		panel_1.add(comboBox_level);
		comboBox_level.setEditable(true);

		textField_Rank = new JTextField();
		textField_Rank.setEditable(false);
		textField_Rank.setColumns(10);
		textField_Rank.setBounds(128, 119, 206, 20);
		panel_1.add(textField_Rank);

		lblRank = new JLabel("RANK");
		lblRank.setBounds(10, 117, 55, 25);
		panel_1.add(lblRank);
		lblRank.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		btnRank = new JButton("GENERATE RANK");
		btnRank.setBounds(165, 160, 149, 23);
		panel_1.add(btnRank);
		btnRank.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRank.setBackground(SystemColor.activeCaption);

		JLabel lblSelectDays = new JLabel("ACTIVE DAYS & HOURS");
		lblSelectDays.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSelectDays.setBounds(427, 147, 149, 25);
		panel_addLecturer.add(lblSelectDays);
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldEmpId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an Employee ID!", "Alert",
							JOptionPane.WARNING_MESSAGE);
				} else if (textFieldEmpId.getText().length() != 6) {
					JOptionPane.showMessageDialog(null, "Check the Employee ID", "Alert", JOptionPane.WARNING_MESSAGE);

				} else {
					String level, empID, rank;
					try {
						level = (String) comboBox_level.getSelectedItem();
						empID = textFieldEmpId.getText();

						Integer.parseInt(empID);

						if (comboBox_level.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "Select a level!", "Alert",
									JOptionPane.WARNING_MESSAGE);
						} else {
							rank = level + "." + empID;
							System.out.println("Rank " + rank);
							textField_Rank.setText(rank);
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
		

		
		frmAddLecturer.getContentPane().add(panel_addLecturer);

	}

	public void resetField() {
		comboBox_fac.setSelectedIndex(0);
		comboBox_dept.setSelectedIndex(0);
		comboBox_centre.setSelectedIndex(0);
		comboBox_build.setSelectedIndex(0);
		comboBox_level.setSelectedIndex(0);
		textFieldName.setText(null);
		textFieldEmpId.setText(null);
		textField_Rank.setText(null);
		spinner_Mon.setValue(0);
		spinner_Tue.setValue(0);
		spinner_Wed.setValue(0);
		spinner_Thur.setValue(0);
		spinner_Fri.setValue(0);
		spinner_Sat.setValue(0);
		spinner_Mon.setValue(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource(); // to see what is clicked

		if (obj == btnSave) {

			if (textFieldName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the name field!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else if (comboBox_dept.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Please choose a department!", "Alert",
						JOptionPane.WARNING_MESSAGE);
				
			}else if (textFieldEmpId.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the employee id!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else if (textField_Rank.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Generate the rank!", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if(comboBox_build.getSelectedItem() == null){
				JOptionPane.showMessageDialog(null, "Please choose a building!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else {
				Lecturer lecturer = new Lecturer();
				lecturer.setLecName((String) textFieldName.getText());
				lecturer.setFaculty((String) comboBox_fac.getSelectedItem());
				lecturer.setDepart((String) comboBox_dept.getSelectedItem());
				lecturer.setEmployeeId(Integer.parseInt(textFieldEmpId.getText().toString()));

				if (comboBox_level.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Select a level!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				lecturer.setLevel(Integer.parseInt((String) comboBox_level.getSelectedItem()));
				lecturer.setRank((String) textField_Rank.getText());
				lecturer.setCampus((String) comboBox_centre.getSelectedItem());
				lecturer.setBuilding((String) comboBox_build.getSelectedItem());

				String test = lecturer.getLevel() + "." + lecturer.getEmployeeId();
				System.out.println("New rank" + test);


				int mon = (int) spinner_Mon.getValue();
				int tue = (int) spinner_Tue.getValue();
				int wed = (int) spinner_Wed.getValue();
				int thur = (int) spinner_Thur.getValue();
				int fri = (int) spinner_Fri.getValue();
				int sat = (int) spinner_Sat.getValue();
				int sun = (int) spinner_Sun.getValue();

				if (lecturer.getRank().equals(test) == false) {
					JOptionPane.showMessageDialog(null, "Please click the 'Generate the rank!'", "Alert",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int confirm = JOptionPane.showConfirmDialog(null, "Save the record "+lecturer.getLecName()+ " ?", "Save the new Lecturer",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						LecturerDAOImpl lecturerDAOImpl = new LecturerDAOImpl();

						try {
							lecturerDAOImpl.insertLecturers(lecturer);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

						// newly added
						lecturerDAOImpl.insertActiveDays(lecturer.getEmployeeId(), mon, tue, wed, thur, fri, sat, sun);
					}

					resetField();
				}

			}

		}

		if (obj == btnClear) {
			resetField();
			JOptionPane.showMessageDialog(null, "Cleared!");
		}

	}
}
