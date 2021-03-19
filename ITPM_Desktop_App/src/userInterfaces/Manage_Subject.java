package userInterfaces;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Subject;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Manage_Subject implements ActionListener {

	private JFrame frmManageSubjects;
	private JTable table;
	private JTextField textField_mSubCode;
	private JTextField textField_mSubName;
	private JRadioButton rdbtn_mFSem,rdbtnSecSem;
	private JSpinner spinner_mLecHrs,spinner_mTuteHrs,spinner_mLabHrs,spinner_mEvlHrs;
	private JComboBox comboBox_mYear;
	private JLabel lbl_mYear;
	private JButton btnSubUpdate,btnSubDelete,btnSubClear;
	private int id;
	private ButtonGroup bg;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Subject window = new Manage_Subject();
					window.frmManageSubjects.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manage_Subject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManageSubjects = new JFrame();
		frmManageSubjects.setTitle("MANAGE SUBJECTS");
		frmManageSubjects.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmManageSubjects.setBounds(100, 100, 875, 491);
		frmManageSubjects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageSubjects.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 839, 156);
		frmManageSubjects.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		DefaultTableModel modal = new DefaultTableModel(new String[] {"ID","Year","Semester","Subject Code","Subject Name","Lec Hours","Tute Hours","Lab Hours","Eval.Hours"},0);
		table = new JTable(modal);
		table.setBounds(0, 0, 839, 156);
		JTableHeader header = table.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table.setModel(modal);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 712, 134);
		
//		Subject subject = new Subject();
//		Connection con = subject.connect();
//		
//		String retrieveQuery = "select * from subject";
//		
//		
//		
//		try {
//			Statement st = con.createStatement();
//			ResultSet res = st.executeQuery(retrieveQuery);
//			
//			while(res.next()) {
//				int year = res.getInt("Year");
//				String sem = res.getString("Semester");
//				String code = res.getString("Subject_Code");
//				String subName = res.getString("Subject_Name");
//				int lec = res.getInt("Lecture_Hours");
//				int tute = res.getInt("Tute_Hours");
//				int lab = res.getInt("Lab_Hours");
//				int eval = res.getInt("Evaluation_Hours");
//				
//				((DefaultTableModel)modal).addRow(new Object[] {year,sem,code,subName,lec,tute,lab,eval});
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		subjectGroupTable();
		panel.add(scrollPane);
		
		
		table.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		int rowId = table.getSelectedRow();
	   
	    		id = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
	    		comboBox_mYear.setSelectedItem(modal.getValueAt(rowId, 1));
	     		String value = modal.getValueAt(rowId, 2).toString();
	    		if(value.equals("First Semester")) {
	    			rdbtn_mFSem.setSelected(true);
	    			rdbtnSecSem.setSelected(false);
	    		}else {
	    			rdbtnSecSem.setSelected(true);
	    			rdbtn_mFSem.setSelected(false);
	    		}
	    		textField_mSubCode.setText(modal.getValueAt(rowId, 3).toString());
	    		textField_mSubName.setText(modal.getValueAt(rowId, 4).toString());
	    		spinner_mLecHrs.setValue(modal.getValueAt(rowId, 5));
	    		spinner_mTuteHrs.setValue(modal.getValueAt(rowId, 6));
	    		spinner_mLabHrs.setValue(modal.getValueAt(rowId, 7));
	    		spinner_mEvlHrs.setValue(modal.getValueAt(rowId, 8));
	    	}
	    });
		
		
		
		
		btnSubUpdate = new JButton("UPDATE");
		btnSubUpdate.setBackground(SystemColor.activeCaption);
		btnSubUpdate.setBounds(732, 38, 89, 23);
		btnSubUpdate.addActionListener(this);
		panel.add(btnSubUpdate);
		
		btnSubDelete = new JButton("DELETE");
		btnSubDelete.setBackground(SystemColor.activeCaption);
		btnSubDelete.setBounds(732, 72, 89, 23);
		btnSubDelete.addActionListener(this);
		panel.add(btnSubDelete);
		
		btnSubClear = new JButton("CLEAR");
		btnSubClear.setBackground(SystemColor.activeCaption);
		btnSubClear.setBounds(732, 106, 89, 23);
		btnSubClear.addActionListener(this);
		panel.add(btnSubClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 178, 839, 249);
		frmManageSubjects.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_mSubCode = new JTextField();
		textField_mSubCode.setColumns(10);
		textField_mSubCode.setBounds(149, 141, 243, 20);
		panel_1.add(textField_mSubCode);
		
		textField_mSubName = new JTextField();
		textField_mSubName.setColumns(10);
		textField_mSubName.setBounds(149, 192, 243, 20);
		panel_1.add(textField_mSubName);
		
		bg = new ButtonGroup();
		rdbtn_mFSem = new JRadioButton("1st Semester");
		rdbtn_mFSem.setBounds(149, 87, 109, 23);
		panel_1.add(rdbtn_mFSem);
		
		rdbtnSecSem = new JRadioButton("2nd Semester");
		rdbtnSecSem.setBounds(283, 87, 109, 23);
		panel_1.add(rdbtnSecSem);
		
		bg.add(rdbtn_mFSem);
		bg.add(rdbtnSecSem);
		
		spinner_mLecHrs = new JSpinner();
		spinner_mLecHrs.setBounds(603, 30, 186, 20);
		panel_1.add(spinner_mLecHrs);
		
		spinner_mTuteHrs = new JSpinner();
		spinner_mTuteHrs.setBounds(603, 90, 186, 20);
		panel_1.add(spinner_mTuteHrs);
		
		spinner_mLabHrs = new JSpinner();
		spinner_mLabHrs.setBounds(603, 141, 186, 20);
		panel_1.add(spinner_mLabHrs);
		
		spinner_mEvlHrs = new JSpinner();
		spinner_mEvlHrs.setBounds(603, 192, 186, 20);
		panel_1.add(spinner_mEvlHrs);
		
		String[] year = {"1","2","3","4"};
		comboBox_mYear = new JComboBox(year);
		comboBox_mYear.setEditable(true);
		comboBox_mYear.setBounds(149, 30, 243, 20);
		panel_1.add(comboBox_mYear);
		
		lbl_mYear = new JLabel("OFFERED YEAR");
		lbl_mYear.setBounds(10, 33, 108, 14);
		panel_1.add(lbl_mYear);
		
		JLabel lbl_mSem = new JLabel("OFFERED SEMESTER");
		lbl_mSem.setBounds(10, 91, 133, 14);
		panel_1.add(lbl_mSem);
		
		JLabel lbl_mSubCode = new JLabel("SUBJECT CODE");
		lbl_mSubCode.setBounds(10, 144, 108, 14);
		panel_1.add(lbl_mSubCode);
		
		JLabel lbl_mSubName = new JLabel("SUBJECT NAME");
		lbl_mSubName.setBounds(10, 195, 108, 14);
		panel_1.add(lbl_mSubName);
		
		JLabel lbl_mLecHrs = new JLabel("NO OF LEC HOURS");
		lbl_mLecHrs.setBounds(437, 33, 143, 14);
		panel_1.add(lbl_mLecHrs);
		
		JLabel lbl_mTuteHrs = new JLabel("NO OF TUTE HOURS");
		lbl_mTuteHrs.setBounds(437, 91, 117, 14);
		panel_1.add(lbl_mTuteHrs);
		
		JLabel lbl_mLabHrs = new JLabel("NO OF LAB HOURS");
		lbl_mLabHrs.setBounds(437, 144, 133, 14);
		panel_1.add(lbl_mLabHrs);
		
		JLabel lbl_mEvalHrs = new JLabel("NO OF EVALUATION HOURS");
		lbl_mEvalHrs.setBounds(437, 195, 143, 14);
		panel_1.add(lbl_mEvalHrs);
	}
	
	
	public void subjectGroupTable(){
		Subject subject_group = new Subject();
		ArrayList<Subject> subject_list = subject_group.listSubjects();
		
		DefaultTableModel modal = (DefaultTableModel) table.getModel();
		
		Object[] row = new Object[9];
		
		for(int i=0; i< subject_list.size(); i++) {
			
			row[0] = subject_list.get(i).getId();
			row[1] = subject_list.get(i).getYear();
			row[2] = subject_list.get(i).getSemester();
			row[3] = subject_list.get(i).getSubjectCode();
			row[4] = subject_list.get(i).getSubjectName();
			row[5] = subject_list.get(i).getNoOfLecHrs();
			row[6] = subject_list.get(i).getNoOfTuteHrs();
			row[7] = subject_list.get(i).getNoOfLabHrs();
			row[8] = subject_list.get(i).getNoOfEvalHRs();
			
			modal.addRow(row);
		}
		
	}
	
	public void resetFields() {
		id = 0;
		comboBox_mYear.setSelectedIndex(0);
		rdbtn_mFSem.setSelected(false);
		rdbtnSecSem.setSelected(false);
		textField_mSubCode.setText(null);
		textField_mSubName.setText(null);
		spinner_mLecHrs.setValue(0);
		spinner_mTuteHrs.setValue(0);
		spinner_mLabHrs.setValue(0);
		spinner_mEvlHrs.setValue(0);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		if(ob == btnSubUpdate) {
			
			if(id == 0) {
				JOptionPane.showMessageDialog(null, "Select a row to Update!");
			}else {
				int year = Integer.parseInt(comboBox_mYear.getSelectedItem().toString());
				String sem ="";
				if(rdbtn_mFSem.isSelected()) {
					sem = "First Semester";
				}else {
					sem ="Second Semester";
				}
				String subCode = textField_mSubCode.getText();
				String subName = textField_mSubName.getText();
				int lecHrs = (int) spinner_mLecHrs.getValue();
				int tuteHrs = (int) spinner_mTuteHrs.getValue();
				int labHrs =(int) spinner_mLabHrs.getValue();
				int evlHrs = (int) spinner_mEvlHrs.getValue();
				
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					Subject sub= new Subject();
					sub.updateSubjects(id,year, sem, subCode, subName, lecHrs, tuteHrs, labHrs, evlHrs);
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					subjectGroupTable();
				}
				
				resetFields();
			}

		}
		
		if(ob == btnSubDelete) {
			if(id == 0) {
				JOptionPane.showMessageDialog(null, "Select a specific record to delete!");
			}else {
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					Subject sub= new Subject();
					sub.deleteSubject(id);
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					subjectGroupTable();
				}
			}
			
		}
		
		if(ob == btnSubClear) {
			JOptionPane.showMessageDialog(null, "Cleared!");
			resetFields();
		}
	}
	
}
