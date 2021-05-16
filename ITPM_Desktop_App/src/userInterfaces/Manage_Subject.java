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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.SubjectDAOImpl;
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
	public JPanel panel_manageSubject;
	
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
		frmManageSubjects.setBounds(100, 100, 898, 491);
		frmManageSubjects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageSubjects.getContentPane().setLayout(null);
		frmManageSubjects.setLocationRelativeTo(null);
		
		panel_manageSubject = new JPanel();
		panel_manageSubject.setLayout(null);
		panel_manageSubject.setBounds(0, 0, 882, 452);
		
		SpinnerModel model_1 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_2 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_3 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_4 = new SpinnerNumberModel(0, 0, 12, 1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 862, 156);

		panel.setLayout(null);
		
		
		DefaultTableModel modal = new DefaultTableModel(new String[] {"ID","Year","Semester","Subject Code","Subject Name","Lec Hours","Tute Hours","Lab Hours","Eval.Hours"},0);
		table = new JTable(modal);
		table.setBounds(0, 0, 839, 156);
		JTableHeader header = table.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table.setModel(modal);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 730, 134);
		

		subjectGroupTable();
		panel.add(scrollPane);
		
		
		table.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		int rowId = table.getSelectedRow();
	   
	    		SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
	    		
	    		id = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
	    		System.out.println(id);
	    		Subject subject = subjectDAOImpl.getSubjectById(id);
	    		
	    		comboBox_mYear.setSelectedItem(subject.getYear());
	     		String value = subject.getSemester();
	     		System.out.println(value);
	    		if(value.equals("First Semester")) {
	    			rdbtn_mFSem.setSelected(true);
	    			rdbtnSecSem.setSelected(false);
	    		}else {
	    			rdbtnSecSem.setSelected(true);
	    			rdbtn_mFSem.setSelected(false);
	    		}
	    		textField_mSubCode.setText(subject.getSubjectCode());
	    		textField_mSubName.setText(subject.getSubjectName());
	    		spinner_mLecHrs.setValue(subject.getNoOfLecHrs());
	    		spinner_mTuteHrs.setValue(subject.getNoOfTuteHrs());
	    		spinner_mLabHrs.setValue(subject.getNoOfLabHrs());
	    		spinner_mEvlHrs.setValue(subject.getNoOfEvalHRs());
	    	}
	    });
		
		
		
		
		btnSubUpdate = new JButton("UPDATE");
		btnSubUpdate.setBackground(SystemColor.activeCaption);
		btnSubUpdate.setBounds(750, 27, 89, 23);
		btnSubUpdate.addActionListener(this);
		panel.add(btnSubUpdate);
		
		btnSubDelete = new JButton("DELETE");
		btnSubDelete.setBackground(SystemColor.activeCaption);
		btnSubDelete.setBounds(750, 67, 89, 23);
		btnSubDelete.addActionListener(this);
		panel.add(btnSubDelete);
		
		btnSubClear = new JButton("CLEAR");
		btnSubClear.setBackground(SystemColor.activeCaption);
		btnSubClear.setBounds(750, 111, 89, 23);
		btnSubClear.addActionListener(this);
		panel.add(btnSubClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 178, 862, 249);
		
		panel_1.setLayout(null);
		
		textField_mSubCode = new JTextField();
		textField_mSubCode.setColumns(10);
		textField_mSubCode.setBounds(162, 141, 243, 20);
		panel_1.add(textField_mSubCode);
		
		textField_mSubName = new JTextField();
		textField_mSubName.setColumns(10);
		textField_mSubName.setBounds(162, 192, 243, 20);
		panel_1.add(textField_mSubName);
		
		bg = new ButtonGroup();
		rdbtn_mFSem = new JRadioButton("1st Semester");
		rdbtn_mFSem.setBounds(162, 87, 109, 23);
		panel_1.add(rdbtn_mFSem);
		
		rdbtnSecSem = new JRadioButton("2nd Semester");
		rdbtnSecSem.setBounds(296, 87, 109, 23);
		panel_1.add(rdbtnSecSem);
		
		bg.add(rdbtn_mFSem);
		bg.add(rdbtnSecSem);
		
		spinner_mLecHrs = new JSpinner(model_1);
		spinner_mLecHrs.setBounds(626, 30, 204, 20);
		panel_1.add(spinner_mLecHrs);
		
		spinner_mTuteHrs = new JSpinner(model_2);
		spinner_mTuteHrs.setBounds(626, 88, 204, 20);
		panel_1.add(spinner_mTuteHrs);
		
		spinner_mLabHrs = new JSpinner(model_3);
		spinner_mLabHrs.setBounds(626, 141, 204, 20);
		panel_1.add(spinner_mLabHrs);
		
		spinner_mEvlHrs = new JSpinner(model_4);
		spinner_mEvlHrs.setBounds(626, 192, 204, 20);
		panel_1.add(spinner_mEvlHrs);
		
		String[] year = {"1","2","3","4"};
		comboBox_mYear = new JComboBox(year);
		comboBox_mYear.setEditable(true);
		comboBox_mYear.setBounds(162, 30, 243, 20);
		panel_1.add(comboBox_mYear);
		
		lbl_mYear = new JLabel("OFFERED YEAR");
		lbl_mYear.setBounds(23, 33, 108, 14);
		panel_1.add(lbl_mYear);
		
		JLabel lbl_mSem = new JLabel("OFFERED SEMESTER");
		lbl_mSem.setBounds(23, 91, 133, 14);
		panel_1.add(lbl_mSem);
		
		JLabel lbl_mSubCode = new JLabel("SUBJECT CODE");
		lbl_mSubCode.setBounds(23, 144, 108, 14);
		panel_1.add(lbl_mSubCode);
		
		JLabel lbl_mSubName = new JLabel("SUBJECT NAME");
		lbl_mSubName.setBounds(23, 195, 108, 14);
		panel_1.add(lbl_mSubName);
		
		JLabel lbl_mLecHrs = new JLabel("NO OF LEC HOURS");
		lbl_mLecHrs.setBounds(452, 33, 143, 14);
		panel_1.add(lbl_mLecHrs);
		
		JLabel lbl_mTuteHrs = new JLabel("NO OF TUTE HOURS");
		lbl_mTuteHrs.setBounds(453, 91, 117, 14);
		panel_1.add(lbl_mTuteHrs);
		
		JLabel lbl_mLabHrs = new JLabel("NO OF LAB HOURS");
		lbl_mLabHrs.setBounds(452, 144, 133, 14);
		panel_1.add(lbl_mLabHrs);
		
		JLabel lbl_mEvalHrs = new JLabel("NO OF EVALUATION HOURS");
		lbl_mEvalHrs.setBounds(452, 195, 143, 14);
		panel_1.add(lbl_mEvalHrs);
		
		panel_manageSubject.add(panel);
		panel_manageSubject.add(panel_1);
		frmManageSubjects.getContentPane().add(panel_manageSubject);
	}
	
	
	public void subjectGroupTable(){
		SubjectDAOImpl subjectDAOImpl= new SubjectDAOImpl();
		ArrayList<Subject> subject_list = subjectDAOImpl.listSubjects();
		
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
		
		Object ob = e.getSource();
		
		if(ob == btnSubUpdate) {
			
			if(id == 0) {
				JOptionPane.showMessageDialog(null, "Select a row to Update!");
			}else if(textField_mSubCode.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill the subject code!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else if(textField_mSubName.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill the subject name!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else {
				
				Subject subject = new Subject();
				
				subject.setYear(Integer.parseInt(comboBox_mYear.getSelectedItem().toString()));
				String sem ="";
				if(rdbtn_mFSem.isSelected()) {
					subject.setSemester("First Semester");
				}else {
					subject.setSemester("Second Semester");
				}
				subject.setSubjectCode(textField_mSubCode.getText());
				subject.setSubjectName(textField_mSubName.getText());
				subject.setNoOfLecHrs((int) spinner_mLecHrs.getValue());
				subject.setNoOfTuteHrs((int) spinner_mTuteHrs.getValue());
				subject.setNoOfLabHrs((int) spinner_mLabHrs.getValue());
				subject.setNoOfEvalHRs((int) spinner_mEvlHrs.getValue());
				subject.setId(id);
				
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure you want to update subject "+ subject.getSubjectName() +" ?","Update subject",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					SubjectDAOImpl subjectDAOImpl= new SubjectDAOImpl();
					
					subjectDAOImpl.updateSubjects(subject);
					System.out.println("wenawadaa?");
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					System.out.println("nadda");
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
				SubjectDAOImpl subjectDAOImpl= new SubjectDAOImpl();
				int confirm =JOptionPane.showConfirmDialog(null,"Delete subject "+ subjectDAOImpl.getSubjectById(id).getSubjectName()  +" ?","Delete Subject",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					
					subjectDAOImpl.deleteSubject(id);
					
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
