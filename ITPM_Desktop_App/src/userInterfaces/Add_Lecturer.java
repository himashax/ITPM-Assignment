package userInterfaces;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import models.Active_Days;
import models.Lecturer;

import javax.swing.border.LineBorder;

public class Add_Lecturer implements ActionListener {

	private JFrame frmAddLecturer;
	private JTextField textFieldName;
	private JLabel lblFaculty,lblDept,lblCampuscentre;
	private JComboBox comboBox_dept,comboBox_fac,comboBox_build;
	private JComboBox comboBox_centre,comboBox_level;
	private JComboBox comboBox_day;
	private JComboBox comboBox_hour;
	private JLabel lblBuilding,lblEmpId,lblLevel,lblRank;
	private JLabel lblDay;
	private JLabel lblName;
	private JLabel lblHours;
	private JLabel lblSelectedDays;
	private JTextField textFieldEmpId;
	private JTextField textField_selected;
	private JTextField textField_Rank;
	private JPanel panel;
	private JButton btnAddDays;
	private JButton btnClear,btnSave,btnRank;
	

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
		frmAddLecturer.setBounds(100, 100, 790, 469);
		frmAddLecturer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddLecturer.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(148, 32, 192, 20);
		frmAddLecturer.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		lblName = new JLabel("NAME");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblName.setBounds(34,30,74,25);
		frmAddLecturer.getContentPane().add(lblName);
		
		lblFaculty = new JLabel("FACULTY");
		lblFaculty.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFaculty.setBounds(34, 84, 74, 25);
		frmAddLecturer.getContentPane().add(lblFaculty);
		
		lblDept = new JLabel("DEPARTMENT");
		lblDept.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDept.setBounds(34, 132, 86, 25);
		frmAddLecturer.getContentPane().add(lblDept);
		

		String[] fac = {"Computing" , "Engineering"};
		comboBox_fac = new JComboBox(fac);
		comboBox_fac.setEditable(true);
		comboBox_fac.setBounds(148,86,192,20);
		frmAddLecturer.getContentPane().add(comboBox_fac);
		

		String[] com_dep= {"Software Engineering","CSSE"};
//		String[] eng_dep = {"Electrical","Mechanical"};

		comboBox_dept = new JComboBox(com_dep);
		comboBox_dept.setEditable(true);
		comboBox_dept.setBounds(148,132,192,20);
		frmAddLecturer.getContentPane().add(comboBox_dept);
		
		
		
		lblCampuscentre = new JLabel("CAMPUS/CENTRE");
		lblCampuscentre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCampuscentre.setBounds(391, 30, 113, 25);
		frmAddLecturer.getContentPane().add(lblCampuscentre);
		
		String[] cen = {"Malabe","Metro Campus","Kandy","Matara","Jaffna"};
		comboBox_centre = new JComboBox(cen);
		comboBox_centre.setEditable(true);
		comboBox_centre.setBounds(519, 32, 205, 20);
		frmAddLecturer.getContentPane().add(comboBox_centre);
		
		String[] Malabe_build = {"New building","Main building"};
		comboBox_build = new JComboBox(Malabe_build);
		comboBox_build.setEditable(true);
		comboBox_build.setBounds(519,86,205,20);
		frmAddLecturer.getContentPane().add(comboBox_build);
		
		
		lblBuilding = new JLabel("BUILDING");
		lblBuilding.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBuilding.setBounds(391, 89, 113, 25);
		frmAddLecturer.getContentPane().add(lblBuilding);
		
		String[] level = {"1","2","3","4","5","6"};
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(391, 132, 333, 220);
		frmAddLecturer.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblDay = new JLabel("DAY");
		lblDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDay.setBounds(10, 28, 69, 25);
		panel.add(lblDay);
		
		String day[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		comboBox_day = new JComboBox(day);
		comboBox_day.setEditable(true);
		comboBox_day.setBounds(131, 29, 192, 22);
		panel.add(comboBox_day);
		
		lblHours = new JLabel("HOURS");
		lblHours.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblHours.setBounds(10, 75, 69, 25);
		panel.add(lblHours);
		
		String[] hrs = {"08:00:00 - 17:00:00", "08:00:00 - 12:00:00" ,"12:00:00 - 17:00:00"};
		comboBox_hour = new JComboBox(hrs);
		comboBox_hour.setEditable(true);
		comboBox_hour.setBounds(131, 76, 192, 22);
		panel.add(comboBox_hour);
		
		textField_selected = new JTextField();
		textField_selected.setEditable(false);
		textField_selected.setBounds(152, 137, 171, 77);
		panel.add(textField_selected);
		textField_selected.setColumns(10);
		
		lblSelectedDays = new JLabel("SELECTED\r\n DAYS & HRS");
		lblSelectedDays.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSelectedDays.setBounds(10, 163, 132, 25);
		panel.add(lblSelectedDays);
		
		btnAddDays = new JButton("+");
		btnAddDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_selected.setText(comboBox_day.getSelectedItem() + ", "+ comboBox_hour.getSelectedItem() );
			}
		});
		btnAddDays.setFont(new Font("Times New Roman", Font.BOLD, 8));
		btnAddDays.setBackground(SystemColor.activeCaption);
		btnAddDays.setBounds(278, 109, 45, 23);
		panel.add(btnAddDays);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(391, 382, 149, 23);
		btnClear.addActionListener(this);
		frmAddLecturer.getContentPane().add(btnClear);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Saved Successfully!");
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSave.setBackground(SystemColor.activeCaption);
		btnSave.setBounds(575, 382, 149, 23);
		btnSave.addActionListener(this);
		frmAddLecturer.getContentPane().add(btnSave);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(25, 199, 330, 206);
		frmAddLecturer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblEmpId = new JLabel("EMPLOYEE ID");
		lblEmpId.setBounds(10, 31, 80, 14);
		panel_1.add(lblEmpId);
		lblEmpId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.setBounds(128, 28, 192, 20);
		panel_1.add(textFieldEmpId);
		textFieldEmpId.setColumns(10);
		
		lblLevel = new JLabel("LEVEL");
		lblLevel.setBounds(10, 66, 55, 25);
		panel_1.add(lblLevel);
		lblLevel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBox_level = new JComboBox(level);
		comboBox_level.setBounds(128, 68, 192, 20);
		panel_1.add(comboBox_level);
		comboBox_level.setEditable(true);
		
		textField_Rank = new JTextField();
		textField_Rank.setEditable(false);
		textField_Rank.setColumns(10);
		textField_Rank.setBounds(128, 119, 192, 20);
		panel_1.add(textField_Rank);
		
		lblRank = new JLabel("RANK");
		lblRank.setBounds(10, 117, 55, 25);
		panel_1.add(lblRank);
		lblRank.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnRank = new JButton("GENERATE RANK");
		btnRank.setBounds(78, 172, 149, 23);
		panel_1.add(btnRank);
		btnRank.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRank.setBackground(new Color(176, 224, 230));
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldEmpId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter an Employee ID!","Alert",JOptionPane.WARNING_MESSAGE);
				}else if(textFieldEmpId.getText().length() != 6) {
					JOptionPane.showMessageDialog(null,"Check the Employee ID","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
					String level,empID,rank;
					try {
						level = (String) comboBox_level.getSelectedItem();
						empID = textFieldEmpId.getText();
						
					    Integer.parseInt(empID);
						
					    if(comboBox_level.getSelectedItem()== null){
							JOptionPane.showMessageDialog(null,"Select a level!","Alert",JOptionPane.WARNING_MESSAGE);
						}else {
							rank = level + "." +empID;
							System.out.println("Rank " + rank);
							textField_Rank.setText(rank);
							JOptionPane.showMessageDialog(null, "Generated Successfully!");
						}

					}
					catch (NumberFormatException n) {
					     //Not an integer
						JOptionPane.showMessageDialog(null,"Invalid Characters in empID!","Alert",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		
	}
	
	public void resetField() {
		comboBox_fac.setSelectedIndex(0);
		comboBox_dept.setSelectedIndex(0);
		comboBox_centre.setSelectedIndex(0);
		comboBox_build.setSelectedIndex(0);
		comboBox_level.setSelectedIndex(0);
		comboBox_day.setSelectedIndex(0);
		comboBox_hour.setSelectedIndex(0);
		textFieldName.setText(null);
		textFieldEmpId.setText(null);
		textField_Rank.setText(null);
	    textField_selected.setText(null);
	    //rdbtn.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();  //to see what is clicked
		
		if(obj == btnSave) {
			
			if(textFieldName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the name field!","Alert",JOptionPane.WARNING_MESSAGE);
			}else if(textFieldEmpId.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill the employee id!","Alert",JOptionPane.WARNING_MESSAGE);
			}else if(textField_Rank.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please Generate the rank!","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
//				String[] com_dep= {"Software Engineering","CSSE"};
//				String[] eng_dep = {"Electrical","Mechanical"};
				
				String name = (String)textFieldName.getText();
				String faculty = (String)comboBox_fac.getSelectedItem();
				
//				if(comboBox_fac.getSelectedItem().equals("Computing")) {
//					comboBox_dept = new JComboBox(com_dep);
//				}else if(comboBox_fac.getSelectedItem().equals("Engineering")){
//					comboBox_dept = new JComboBox(com_dep);
//				}
				
				String dept = (String)comboBox_dept.getSelectedItem();
				int empid = Integer.parseInt(textFieldEmpId.getText().toString());
				
				if(comboBox_level.getSelectedItem()== null){
					JOptionPane.showMessageDialog(null,"Select a level!","Alert",JOptionPane.WARNING_MESSAGE);
				}
				int level = Integer.parseInt((String) comboBox_level.getSelectedItem());
				String rank = (String)textField_Rank.getText();
				String campus = (String)comboBox_centre.getSelectedItem();
				String build = (String)comboBox_build.getSelectedItem();
				
				String test = level + "." + empid;
				System.out.println("New rank" + test);
				
				
				//newly added
				String day = (String)comboBox_day.getSelectedItem();
				String hours = (String) comboBox_hour.getSelectedItem();

				
				if(rank.equals(test) == false){
					JOptionPane.showMessageDialog(null, "Please click the 'Generate the rank!'","Alert",JOptionPane.WARNING_MESSAGE);
				}else {
					int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						Lecturer lec = new Lecturer();
						
						//newly added
						Active_Days days = new Active_Days();
						lec.insertLecturers(name, faculty, dept,empid, level, rank, campus, build);
						
						//newly added
						days.insertActiveDays(empid, day, hours);
					}
					
					resetField();
				}
				

			}
			
		}
		
		if(obj == btnClear) {
			resetField();
			JOptionPane.showMessageDialog(null, "Cleared!");
		}
		
	}
}
