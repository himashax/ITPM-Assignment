package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import models.Student_Group;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class Add_Student_Groups implements ActionListener{

	private JFrame frame;
	private JLabel academicYearSem, programme, groupNo, subgroupNo, groupId, subgroupId;  
	private JComboBox yearSem_list, programme_list;
	private JSpinner groupNumber, subGroupNumer;
	private JTextField groupIdInput, subGroupIdInput;
	private JButton genIds, clear, save;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Student_Groups window = new Add_Student_Groups();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Add_Student_Groups() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 885, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(67, 72, 727, 376);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		academicYearSem = new JLabel("Academic Year And Semester\r\n");
		academicYearSem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		academicYearSem.setBounds(44, 23, 171, 21);
		panel.add(academicYearSem);
		
		String year_semValues[]={"(Y1.S1) Year 1 Semester 1","(Y1.S2) Year 1 Semester 2","(Y2.S1) Year 2 Semester 1","(Y2.S2) Year 2 Semester 2","(Y3.S1) Year 3 Semester 1",
				 "(Y3.S2) Year 3 Semester 2", "(Y4.S1) Year 4 Semester 1", "(Y4.S2) Year 4 Semester 2"}; 
		yearSem_list = new JComboBox(year_semValues);
		yearSem_list.setBackground(Color.WHITE);
		yearSem_list.setBounds(44, 44, 636, 28);
		panel.add(yearSem_list);
		
		programme = new JLabel("Programme\r\n");
		programme.setFont(new Font("Tahoma", Font.PLAIN, 12));
		programme.setBounds(44, 93, 71, 14);
		panel.add(programme);
		
		String[] programmes = {"IT", "SE", "DS", "CSNE", "ICS"};
		programme_list = new JComboBox(programmes);
		programme_list.setBackground(Color.WHITE);
		programme_list.setBounds(44, 112, 636, 28);
		panel.add(programme_list);
		
		groupNo = new JLabel("Group Number\r\n");
		groupNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		groupNo.setBounds(44, 161, 90, 14);
		panel.add(groupNo);
		
		SpinnerModel model_1 = new SpinnerNumberModel(0, 0, 40, 1);
		groupNumber = new JSpinner(model_1);
		groupNumber.setBounds(44, 178, 268, 30);
		panel.add(groupNumber);
		
		subgroupNo = new JLabel("Sub Group Number");
		subgroupNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		subgroupNo.setBounds(412, 161, 122, 14);
		panel.add(subgroupNo);
		
		SpinnerModel model_2 = new SpinnerNumberModel(0, 0, 40, 1);
		subGroupNumer = new JSpinner(model_2);
		subGroupNumer.setBounds(412, 178, 268, 30);
		panel.add(subGroupNumer);
		
		groupId = new JLabel("Group ID");
		groupId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		groupId.setBounds(44, 227, 71, 14);
		panel.add(groupId);
		
		groupIdInput = new JTextField();
		groupIdInput.setEditable(false);
		groupIdInput.setBounds(44, 244, 190, 30);
		panel.add(groupIdInput);
		groupIdInput.setColumns(10);
		
		subgroupId = new JLabel("Sub Group ID");
		subgroupId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		subgroupId.setBounds(268, 227, 83, 14);
		panel.add(subgroupId);
		
		subGroupIdInput = new JTextField();
		subGroupIdInput.setEditable(false);
		subGroupIdInput.setColumns(10);
		subGroupIdInput.setBounds(268, 244, 190, 30);
		panel.add(subGroupIdInput);
		
		genIds = new JButton("Generate IDs");
		genIds.setBackground(Color.LIGHT_GRAY);
		genIds.setForeground(Color.DARK_GRAY);
		genIds.setFont(new Font("Tahoma", Font.BOLD, 12));
		genIds.setBounds(514, 244, 166, 28);
		genIds.addActionListener(this);
		panel.add(genIds);
		
		save = new JButton("Save");
		save.setBackground(new Color(102, 153, 255));
		save.setFont(new Font("Tahoma", Font.BOLD, 12));
		save.setForeground(Color.WHITE);
		save.setBounds(426, 318, 152, 28);
		save.addActionListener(this);
		panel.add(save);
		
		clear = new JButton("Clear");
		clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear.setBackground(Color.LIGHT_GRAY);
		clear.setForeground(Color.DARK_GRAY);
		clear.setBounds(158, 318, 152, 28);
		clear.addActionListener(this);
		panel.add(clear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 869, 37);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Student Groups");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(25, 11, 154, 14);
		panel_1.add(lblNewLabel);
		
	}

	public void resetFields() {
		yearSem_list.setSelectedIndex(0);
		programme_list.setSelectedIndex(0);
		groupNumber.setValue(0);
		subGroupNumer.setValue(0);
		groupIdInput.setText("");
		subGroupIdInput.setText(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == genIds) {
			
			if((Integer)groupNumber.getValue() == 0 || (Integer)subGroupNumer.getValue() == 0) {
				JOptionPane.showMessageDialog(frame,"Group number and Sub Group number cannot be empty.","Alert",JOptionPane.WARNING_MESSAGE);  
			}else {
				String gid = groupNumber.getValue().toString();
				String sid = subGroupNumer.getValue().toString();
				String year_semester = (String) yearSem_list.getSelectedItem();
				String prog = (String) programme_list.getSelectedItem();
				
				String groupIDFinal = year_semester.substring(1, 6)+"."+prog+"."+gid;
				String subGroupIDFinal = groupIDFinal + "." + sid;
				
				groupIdInput.setText(groupIDFinal);
				subGroupIdInput.setText(subGroupIDFinal);
			}
			
		}else if(obj == save) {
			
			String yearAndSemester = (String) yearSem_list.getSelectedItem();
			String programme = (String) programme_list.getSelectedItem();
			int gno = (Integer)groupNumber.getValue();
			int sgno = (Integer)subGroupNumer.getValue();
			String gid = groupIdInput.getText();
			String sid = subGroupIdInput.getText();
		
			String unsavedGroupID = yearAndSemester.substring(1, 6) + "." + programme +"."+ gno +"."+ sgno;
			
			if(gid.isEmpty() || sid.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Please generate Group and Sub Group IDs","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else if(sid.equals(unsavedGroupID) == false) {
				JOptionPane.showMessageDialog(frame,"Please generate Group and Sub Group IDs","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				Student_Group student_group = new Student_Group();
				
				if(student_group.manageGroups(sid) == false) {
					
					int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to submit your data?","Submit Data",JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						student_group.insertStudentGroup(yearAndSemester, programme, gno, gno, gid, sid);
						
						JOptionPane.showMessageDialog(frame,"Data added successfully"); 
						
						resetFields();
					}
					
				}else {
					JOptionPane.showMessageDialog(frame,"Already exists.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
						
		}else if(obj == clear) {
			int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to clear data?","Submit Data",JOptionPane.YES_NO_OPTION);
			
			if(confirm == JOptionPane.YES_OPTION) {
				resetFields();
			}
			
			
		}
	}
}
