package userInterfaces;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Student_Group;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JSpinner;

public class Manage_Student_Groups implements ActionListener {
	
	private JFrame frame;
	private JLabel yearAndSem, prog, gno, sub_gno, gid ,sub_gid;
	private JTextField groupID, subGroupID;
	private JSpinner groupNo , subGroupNo;
	private JComboBox yearAndSemList, progList;
	private JButton generateIDsBtn, updateBtn, deleteBtn, clearBtn, addMoreGroupsBtn;
	private JTable table;
	private DefaultTableModel tableModel;
	private String selectedID;
	private JScrollPane scrollpane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Student_Groups window = new Manage_Student_Groups();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manage_Student_Groups() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 870, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 63, 820, 387);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Group Details");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 11, 148, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Update And Delete Student Group Details\r\n");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 195, 264, 15);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(20, 219, 636, 157);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		yearAndSem = new JLabel("Academic Year And Semester");
		yearAndSem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		yearAndSem.setBounds(40, 12, 169, 14);
		panel_1.add(yearAndSem);
		
		String year_semValues[]={"","(Y1.S1) Year 1 Semester 1","(Y1.S2) Year 1 Semester 2","(Y2.S1) Year 2 Semester 1",
				"(Y2.S2) Year 2 Semester 2","(Y3.S1) Year 3 Semester 1",
				 "(Y3.S2) Year 3 Semester 2", "(Y4.S1) Year 4 Semester 1", "(Y4.S2) Year 4 Semester 2"}; 
		yearAndSemList = new JComboBox(year_semValues);
		yearAndSemList.setBackground(Color.WHITE);
		yearAndSemList.setBounds(40, 28, 245, 22);
		panel_1.add(yearAndSemList);
		
		prog = new JLabel("Programme");
		prog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		prog.setBounds(350, 12, 169, 14);
		panel_1.add(prog);
		
		String[] programmes = {"","IT", "SE", "DS", "CSNE", "ICS"};
		progList = new JComboBox(programmes);
		progList.setBackground(Color.WHITE);
		progList.setBounds(350, 28, 245, 22);
		panel_1.add(progList);
		
		gno = new JLabel("Group Number");
		gno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gno.setBounds(40, 61, 169, 14);
		panel_1.add(gno);
		
		SpinnerModel model_1 = new SpinnerNumberModel(0, 0, 40, 1);
		groupNo = new JSpinner(model_1);
		groupNo.setBounds(40, 78, 245, 22);
		panel_1.add(groupNo);
		
		sub_gno = new JLabel("Sub Group Number");
		sub_gno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sub_gno.setBounds(350, 61, 169, 14);
		panel_1.add(sub_gno);
		
		SpinnerModel model_2 = new SpinnerNumberModel(0, 0, 40, 1);
		subGroupNo = new JSpinner(model_2);
		subGroupNo.setBounds(350, 79, 245, 22);
		panel_1.add(subGroupNo);
		
		gid = new JLabel("Group ID");
		gid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gid.setBounds(40, 108, 169, 14);
		panel_1.add(gid);
		
		groupID = new JTextField();
		groupID.setEditable(false);
		groupID.setColumns(10);
		groupID.setBounds(40, 126, 170, 22);
		panel_1.add(groupID);
		
		sub_gid = new JLabel("Sub Group ID");
		sub_gid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sub_gid.setBounds(233, 108, 85, 14);
		panel_1.add(sub_gid);
		
		subGroupID = new JTextField();
		subGroupID.setEditable(false);
		subGroupID.setColumns(10);
		subGroupID.setBounds(232, 126, 170, 22);
		panel_1.add(subGroupID);
		
		generateIDsBtn = new JButton("Generate IDs");
		generateIDsBtn.setBackground(Color.LIGHT_GRAY);
		generateIDsBtn.setForeground(Color.DARK_GRAY);
		generateIDsBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		generateIDsBtn.setBounds(448, 124, 147, 25);
		generateIDsBtn.addActionListener(this);
		panel_1.add(generateIDsBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBackground(SystemColor.activeCaption);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBounds(670, 226, 131, 25);
		updateBtn.addActionListener(this);
		panel.add(updateBtn);
		
	    deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(new Color(102, 153, 255));
		deleteBtn.setBounds(670, 279, 131, 25);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBackground(Color.LIGHT_GRAY);
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearBtn.setForeground(Color.DARK_GRAY);
		clearBtn.setBounds(670, 333, 131, 25);
		clearBtn.addActionListener(this);
		panel.add(clearBtn);
		
		tableModel = new DefaultTableModel(new String[]{"ID","Academic Year|Semester", "Programme", "Group No","Sub Group No","Group ID","Sub Group ID"}, 0);
		table = new JTable(tableModel);
		table.setBackground(SystemColor.menu);
		
		JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(102, 153, 255));
	    header.setFont(new Font("Tahoma", Font.BOLD, 11));
	    header.setForeground(Color.white);
	    
		//table.setModel(tableModel);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(20,37,780,144);
	
		studentGroupTable();
		panel.add(scrollpane);
		
		table.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		
				int selectedRecord = table.getSelectedRow();	
				selectedID = tableModel.getValueAt(selectedRecord, 0).toString();
				yearAndSemList.setSelectedItem(tableModel.getValueAt(selectedRecord, 1).toString());
				progList.setSelectedItem(tableModel.getValueAt(selectedRecord, 2).toString());
				groupNo.setValue(Integer.parseInt(tableModel.getValueAt(selectedRecord, 3).toString()));
				subGroupNo.setValue(Integer.parseInt(tableModel.getValueAt(selectedRecord, 4).toString()));
				groupID.setText(tableModel.getValueAt(selectedRecord, 5).toString());
				subGroupID.setText(tableModel.getValueAt(selectedRecord, 6).toString());
			}
	    });
		
		addMoreGroupsBtn = new JButton("Add More Groups");
		addMoreGroupsBtn.setBackground(new Color(102, 153, 255));
		addMoreGroupsBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		addMoreGroupsBtn.setForeground(Color.WHITE);
		addMoreGroupsBtn.setBounds(690, 36, 140, 23);
		addMoreGroupsBtn.addActionListener(this);
		frame.getContentPane().add(addMoreGroupsBtn);
	}
	
	public void studentGroupTable() {
		Student_Group student_group = new Student_Group();
		ArrayList<Student_Group> group_list = student_group.listStudentGroups();
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		Object[] row = new Object[7];
		
		for(int i=0;i<group_list.size();i++) {
			row[0] = group_list.get(i).getId();
			row[1] = group_list.get(i).getYearAndSemester();
			row[2] = group_list.get(i).getProgramme();
			row[3] = group_list.get(i).getGroupNO();
			row[4] = group_list.get(i).getSubGroupNo();
			row[5] = group_list.get(i).getGroupID();
			row[6] = group_list.get(i).getSubGroupID();
			tableModel.addRow(row);
		}
			
//			 while (rs.next()) 
//			 { 
//			 String id = Integer.toString(rs.getInt("id")); 
//			 String yearsem = rs.getString("year_sem"); 
//			 String prog = rs.getString("prog"); 
//			 String gno = Integer.toString(rs.getInt("gno"));
//			 String sno = Integer.toString(rs.getInt("sgno"));
//			 String gid = rs.getString("gid");
//			 String sid = rs.getString("sid");
//			 ((DefaultTableModel) tableModel).addRow(new Object[]{id,yearsem,prog,gno,sno,gid,sid});
//		 } 
//		 con.close(); 
//		 } 
//		catch (Exception e) 
//		 { 
//		 System.err.println(e.getMessage()); 
//		 } 
	}
	
	public void resetFields() {
		selectedID = null;
		yearAndSemList.setSelectedIndex(0);
		progList.setSelectedIndex(0);
		groupNo.setValue(0);
		subGroupNo.setValue(0);
		groupID.setText("");
		subGroupID.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == generateIDsBtn) {
			if(selectedID == null) {
				JOptionPane.showMessageDialog(frame,"Please select the record","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else {
				String year_sem = (String)yearAndSemList.getSelectedItem();
				String programme = (String) progList.getSelectedItem();
				String group_no = groupNo.getValue().toString();
				String sub_group_no = subGroupNo.getValue().toString();
				
				if(year_sem.isEmpty() || programme.isEmpty() || group_no.isEmpty() || sub_group_no.isEmpty()) {
					
					JOptionPane.showMessageDialog(frame,"Please enter all the details","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
					String gid = year_sem.substring(1,6) +"."+programme+"."+group_no;
					
					groupID.setText(gid);
					subGroupID.setText(gid + "."+sub_group_no);
				}
			}
		}
		else if(obj == updateBtn) {
			if(selectedID == null) {
				JOptionPane.showMessageDialog(frame,"Please select the record you want to edit","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				
				String yearAndSem = (String)yearAndSemList.getSelectedItem();
				String programme = (String) progList.getSelectedItem();
				int grNo = (Integer)(groupNo.getValue());
				int subGrNo =(Integer)(subGroupNo.getValue());
				String gID = groupID.getText();
				String subGID = subGroupID.getText();
				
				String unsavedSubGroupID = yearAndSem.substring(1,6) +"."+programme+"."+grNo + "." +subGrNo;
				System.out.println(unsavedSubGroupID);
				
				if(gID.isEmpty() || subGID.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Please generate Group and Sub Group IDs","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else if(subGID.equals(unsavedSubGroupID) == false) {
					JOptionPane.showMessageDialog(frame,"Please generate Group and Sub Group IDs","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
					Student_Group student_group = new Student_Group();
					int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to edit your data?","Update Record",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) { 
						
						student_group.updateStudentGroup(Integer.parseInt(selectedID), yearAndSem, programme, grNo, subGrNo, gID, subGID);
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();
			            model.setRowCount(0);
						studentGroupTable();
						
						resetFields();
					}				
				}
			}
			
		}else if(obj == deleteBtn) {
			if(subGroupID.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Please select the record you want to delete","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else {
				int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to permenantly delete your record?","Delete Record",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					Student_Group student_group = new Student_Group();
					student_group.deleteStudentGroup(Integer.parseInt(selectedID));
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
		            model.setRowCount(0);
		            studentGroupTable();
		            
		            resetFields();
				}
			}
			
		}else if(obj == clearBtn) {
			resetFields();
		}
		
	}
}
