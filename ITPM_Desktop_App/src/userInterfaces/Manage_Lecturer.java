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

import models.Active_Days;
import models.Lecturer;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JComboBox comboBox_mDays;
	private JComboBox comboBox_mHours;
	private JComboBox comboBox_mFaculty;
	private JComboBox comboBox_mLevel;
	private int idValue, delEmpID;
	private JButton btnUpdate, btnDelete, btnClear;
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
	    frmManageLecturer.setSize(859,464);    
	    frmManageLecturer.getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(10, 11, 824, 151);
	    frmManageLecturer.getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    panel_1 = new JPanel();
	    panel_1.setBackground(Color.WHITE);
	    panel_1.setBounds(10, 173, 824, 241);
	    frmManageLecturer.getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    
	    textField_mName = new JTextField();
	    textField_mName.setBounds(93, 11, 256, 20);
	    panel_1.add(textField_mName);
	    textField_mName.setColumns(10);
	    
	    String[] fac = {"Computing" , "Engineering"};
	    comboBox_mFaculty = new JComboBox(fac);
	    comboBox_mFaculty.setEditable(true);
	    comboBox_mFaculty.setBounds(93, 58, 256, 20);
	    panel_1.add(comboBox_mFaculty);
	    
	    textField_mEId = new JTextField();
	    textField_mEId.setBounds(93, 106, 256, 20);
	    panel_1.add(textField_mEId);
	    textField_mEId.setColumns(10);
	    
	    textField_mRank = new JTextField();
	    textField_mRank.setBounds(93, 198, 256, 20);
	    panel_1.add(textField_mRank);
	    textField_mRank.setColumns(10);
	    
	    String[] level = {"1","2","3","4","5","6"};
	    comboBox_mLevel = new JComboBox(level);
	    comboBox_mLevel.setEditable(true);
	    comboBox_mLevel.setBounds(93, 152, 256, 20);
	    panel_1.add(comboBox_mLevel);
	    
	    String[] cen = {"Malabe","Metro Campus","Kandy"};
	    comboBox_mCampus = new JComboBox(cen);
	    comboBox_mCampus.setEditable(true);
	    comboBox_mCampus.setBounds(557, 11, 235, 20);
	    panel_1.add(comboBox_mCampus);
	    
	    String[] dep= {"Software Engineering","CSSE"};
	    comboBox_mDepartment = new JComboBox(dep);
	    comboBox_mDepartment.setEditable(true);
	    comboBox_mDepartment.setBounds(557, 58, 235, 20);
	    panel_1.add(comboBox_mDepartment);
	    
	    String[] build = {"New building","Main building"};
	    comboBox_mBuilding = new JComboBox(build);
	    comboBox_mBuilding.setEditable(true);
	    comboBox_mBuilding.setBounds(557, 106, 235, 20);
	    panel_1.add(comboBox_mBuilding);
	    
	    String day[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	    comboBox_mDays = new JComboBox(day);
	    comboBox_mDays.setEditable(true);
	    comboBox_mDays.setBounds(557, 152, 235, 20);
	    panel_1.add(comboBox_mDays);
	    
	    String[] hrs = {"8.00 - 5.00", "8.00 - 12.00" ,"12.00 - 5.00"};
	    comboBox_mHours = new JComboBox(hrs);
	    comboBox_mHours.setEditable(true);
	    comboBox_mHours.setBounds(557, 198, 235, 20);
	    panel_1.add(comboBox_mHours);
	    
	    JLabel lblNew_mName = new JLabel("NAME");
	    lblNew_mName.setBounds(10, 14, 46, 14);
	    panel_1.add(lblNew_mName);
	    
	    JLabel lblNew_mFaculty = new JLabel("FACULTY");
	    lblNew_mFaculty.setBounds(10, 61, 68, 14);
	    panel_1.add(lblNew_mFaculty);
	    
	    JLabel lblNew_mEmpId = new JLabel("EMP ID");
	    lblNew_mEmpId.setBounds(10, 109, 46, 14);
	    panel_1.add(lblNew_mEmpId);
	    
	    JLabel lblNew_mLevel = new JLabel("LEVEL");
	    lblNew_mLevel.setBounds(10, 155, 46, 14);
	    panel_1.add(lblNew_mLevel);
	    
	    JLabel lblNew_mRank = new JLabel("RANK");
	    lblNew_mRank.setBounds(10, 201, 46, 14);
	    panel_1.add(lblNew_mRank);
	    
	    JLabel lblNew_mCampus = new JLabel("CAMPUS/CENTRE");
	    lblNew_mCampus.setBounds(418, 14, 105, 14);
	    panel_1.add(lblNew_mCampus);
	    
	    JLabel lblNew_mDept = new JLabel("DEPARTMENT");
	    lblNew_mDept.setBounds(418, 61, 105, 14);
	    panel_1.add(lblNew_mDept);
	    
	    JLabel lblNew_mBuilding = new JLabel("BUILDING");
	    lblNew_mBuilding.setBounds(418, 109, 105, 14);
	    panel_1.add(lblNew_mBuilding);
	    
	    
	    JLabel lblNew_mDays = new JLabel("DAYS");
	    lblNew_mDays.setBounds(418, 155, 105, 14);
	    panel_1.add(lblNew_mDays);
	    
	    JLabel lblNew_mHours = new JLabel("HOURS");
	    lblNew_mHours.setBounds(418, 201, 105, 14);
	    panel_1.add(lblNew_mHours);
	    frmManageLecturer.setVisible(true); 
	    
	    //newly added two columns
	    
	    DefaultTableModel modal = new DefaultTableModel(new String[]{"Id", "Name", "Faculty", "Department","EmpID", "Level", "Rank", "Centre", "Building","Days","Hours"},0); 
	    table = new JTable(modal);
	    table.setBounds(10, 11, 800, 116);
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(SystemColor.activeCaption);
	    table.setModel(modal);
	    
	    JScrollPane pane = new JScrollPane(table);
	    pane.setBounds(10, 11, 700, 129);
	    
	    //call method
	    lecturerGroupTable();
	    
	    panel.add(pane);
	    
	    //select a record
	    table.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		int rowId = table.getSelectedRow();
	    		
	    		idValue = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
	    		//System.out.println(idValue);
	    		delEmpID = Integer.parseInt(modal.getValueAt(rowId, 4).toString());
	    		
	    		
	    		textField_mName.setText((String) modal.getValueAt(rowId, 1));
	    		comboBox_mFaculty.setSelectedItem(modal.getValueAt(rowId, 2));
	    		comboBox_mDepartment.setSelectedItem(modal.getValueAt(rowId, 3));
	    		textField_mEId.setText(modal.getValueAt(rowId, 4).toString());
	    		comboBox_mLevel.setSelectedItem(modal.getValueAt(rowId, 5));
	    		textField_mRank.setText((String) modal.getValueAt(rowId, 6));
	    		comboBox_mCampus.setSelectedItem(modal.getValueAt(rowId, 7));
	    		comboBox_mBuilding.setSelectedItem(modal.getValueAt(rowId, 8));
	    		comboBox_mDays.setSelectedItem(modal.getValueAt(rowId, 9));
	    		comboBox_mHours.setSelectedItem(modal.getValueAt(rowId, 10));
	    		//textField_mEId.setText(modal.getValueAt(rowId, 0).toString());
	    	}
	    });
	    
	    btnUpdate = new JButton("UPDATE");
	    btnUpdate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    btnUpdate.setBackground(SystemColor.activeCaption);
	    btnUpdate.setBounds(720, 28, 94, 23);
	    btnUpdate.addActionListener(this);
	    panel.add(btnUpdate);
	    
	    btnDelete = new JButton("DELETE");
	    btnDelete.setBackground(SystemColor.activeCaption);
	    btnDelete.setBounds(720, 71, 94, 23);
	    btnDelete.addActionListener(this);
	    panel.add(btnDelete);
	    
	    btnClear = new JButton("CLEAR");
	    btnClear.setBackground(SystemColor.activeCaption);
	    btnClear.setBounds(720, 117, 94, 23);
	    btnClear.addActionListener(this);
	    panel.add(btnClear);
	    
	    
	  }
	
	 public void lecturerGroupTable() {
		 Lecturer lec_group = new Lecturer();
		 ArrayList<Lecturer> lecturer_list = lec_group.listLecturer();
		 
		 ///newly added
		 
		 Active_Days acDay_group = new Active_Days();
		 ArrayList<Active_Days> active_list = acDay_group.listDays();
		 
		 DefaultTableModel modal = (DefaultTableModel)table.getModel();
		 
		 Object[] row = new Object[11];
		 
		 for(int i=0; i<lecturer_list.size(); i++) {
			 row[0] = lecturer_list.get(i).getEid();
			 row[1] = lecturer_list.get(i).getLecName();
			 row[2] = lecturer_list.get(i).getFaculty();
			 row[3] = lecturer_list.get(i).getDepart();
			 row[4] = lecturer_list.get(i).getEmployeeId();
			 row[5] = lecturer_list.get(i).getLevel();
			 row[6] = lecturer_list.get(i).getRank();
			 row[7] = lecturer_list.get(i).getCampus();
			 row[8] = lecturer_list.get(i).getBuilding();
			 
			 //newly added
			 row[9] = active_list.get(i).getDays();
			 row[10] = active_list.get(i).getHours();
			 
			 modal.addRow(row);
		 }
		 
		 //i is related with the db
//		 for(int i=0; i<active_list.size(); i++) {
//			 row[9] = active_list.get(i).getDays();
//			 row[10] = active_list.get(i).getHours();
//			 
//			 modal.addRow(row);
//		 }
	}

	public void resetField() {
		idValue = 0;
		comboBox_mFaculty.setSelectedIndex(0);
		comboBox_mDepartment.setSelectedIndex(0);
		comboBox_mCampus.setSelectedIndex(0);
		comboBox_mBuilding.setSelectedIndex(0);
		comboBox_mLevel.setSelectedIndex(0);
		comboBox_mDays.setSelectedIndex(0);
		comboBox_mHours.setSelectedIndex(0);
		textField_mName.setText(null);
		textField_mEId.setText(null);
		textField_mRank.setText(null);
	    //rdbtn.setSelected(false);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource(); 
		
		
		if(obj == btnUpdate) {
			if(idValue == 0) {
				JOptionPane.showMessageDialog(null, "Error!");
			}else {
				String name = (String)textField_mName.getText();
				String faculty = (String)comboBox_mFaculty.getSelectedItem();
				String dept = (String)comboBox_mDepartment.getSelectedItem();
				int empID = Integer.parseInt(textField_mEId.getText());
				int level = Integer.parseInt(comboBox_mLevel.getSelectedItem().toString());
				String rank = (String)textField_mRank.getText();
				String campus = (String)comboBox_mCampus.getSelectedItem();
				String build = (String)comboBox_mBuilding.getSelectedItem();
				
				String day =(String)comboBox_mDays.getSelectedItem();
				String hours = (String)comboBox_mHours.getSelectedItem();
				
				//***update eka id value ekaga enne empid eka**///
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					Lecturer lec = new Lecturer();
					//newly added
					Active_Days acDays = new  Active_Days();
					
					lec.updateLecturers(idValue, name, faculty, dept, empID, level, rank, campus, build);
					
					//newly added
					acDays.updateActiveDays(empID, day, hours);
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					lecturerGroupTable();
				}
				
				resetField();
			}
			
			
		}
		
		if(obj == btnDelete) {
			
			int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);
			
			if(confirm == JOptionPane.YES_OPTION) {
				Lecturer lec = new Lecturer();
				
				//newly added
				Active_Days actDays = new Active_Days();
				lec.deleteLec(idValue);
				
				actDays.deleteActiveDays(delEmpID);
				
				//to display remaining table with data 
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				lecturerGroupTable();
			}
			resetField();
		}
		
		if(obj == btnClear) {
			resetField();
		}
	}
	
	
	
	
	
}
