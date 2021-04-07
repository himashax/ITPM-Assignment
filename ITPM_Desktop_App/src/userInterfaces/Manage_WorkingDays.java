package userInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.WorkingDaysDAOImpl;
import models.DaysndHours;


public class Manage_WorkingDays implements ActionListener {

	private JFrame frame;
	private JTable table;
	private JTable manageDays;
	private DefaultTableModel manageModel;
	private JScrollPane scPane;
	private JTextField hours;
	private JTextField min;
	private JButton btnUpdate,btnDelete;
	private JComboBox noOfDays;
	private int id;
	private JCheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_WorkingDays window = new Manage_WorkingDays();
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
	public Manage_WorkingDays() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(48, 69, 726, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		manageModel = new DefaultTableModel(new String[]{"id","workingDays", "workingTimePerDay", "minutes","noOfWorkingDays"}, 0);
		manageDays = new JTable(manageModel);
		manageDays.setBounds(622, 154, -500, -93);
		
		
		JTableHeader head = manageDays.getTableHeader();
		head.setBackground(new Color(102, 153, 255));
		head.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.setForeground(Color.white);
		
		scPane = new JScrollPane(manageDays);
		scPane.setBounds(67,20,521,146);
		workingDayList();
		panel.add(scPane);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBounds(598, 40, 85, 35);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setBounds(598, 110, 85, 35);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);
		
		manageDays.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row= manageDays.getSelectedRow();
				id = Integer.parseInt(manageModel.getValueAt(row, 0).toString());
				noOfDays.setSelectedItem(manageModel.getValueAt(row, 4).toString());
				hours.setText(manageModel.getValueAt(row,2).toString());
				min.setText(manageModel.getValueAt(row, 3).toString());
				String day = manageModel.getValueAt(row, 1).toString();
				//System.out.println(day);
				
				if(day.indexOf("Monday") != -1){
					System.out.println("select monday");
					chkMonday.setSelected(true);
				}else {
					chkMonday.setSelected(false);
				}
				if(day.indexOf("Tuesday") != -1) {
					System.out.println("tuesday");
					chkTuesday.setSelected(true);
				}else {
					chkTuesday.setSelected(false);
				}
				if(day.indexOf("Wednesday") != -1) {
					System.out.println("Wednesday");
					chkWednesday.setSelected(true);
				}else {
					chkWednesday.setSelected(false);
				}
				if(day.indexOf("Thursday") != -1) {
					System.out.println("Thursday");
					chkThursday.setSelected(true);
				}else {
					System.out.println("Thursday");
					chkThursday.setSelected(false);
				}
				if(day.indexOf("Friday") != -1) {
					System.out.println("Friday");
					chkFriday.setSelected(true);
				}else{
					System.out.println("Friday");
					chkFriday.setSelected(false);
				}
				if(day.indexOf("Saturday") != -1) {
					System.out.println("Saturday");
					chkSaturday.setSelected(true);
				}else {
					chkSaturday.setSelected(false);
				}
				if(day.indexOf("Sunday") != -1) {
					System.out.println("Sunday");
					chkSunday.setSelected(true);
				}else {
					chkSunday.setSelected(false);
				}
				}
			
			});
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(65, 105, 225)), "Update | Delete Working Days and Hours", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(48, 270, 732, 250);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNoOfWorking = new JLabel("No of Working Days");
		lblNoOfWorking.setBounds(59, 50, 113, 23);
		panel_1.add(lblNoOfWorking);
		
		JLabel lblWorkingDays = new JLabel("Working Days");
		lblWorkingDays.setBounds(59, 100, 90, 23);
		panel_1.add(lblWorkingDays);
		
		String[] data= {"5","2"};
		noOfDays = new JComboBox(data);
		noOfDays.setBounds(210, 50, 167, 23);
		panel_1.add(noOfDays);
		
		chkMonday = new JCheckBox("Monday");
		chkMonday.setBounds(210, 101, 77, 21);
		panel_1.add(chkMonday);
		
		chkTuesday = new JCheckBox("Tuesday");
		chkTuesday.setBounds(296, 101, 81, 21);
		panel_1.add(chkTuesday);
		
		chkWednesday = new JCheckBox("Wednesday");
		chkWednesday.setBounds(390, 101, 93, 21);
		panel_1.add(chkWednesday);
		
		chkThursday = new JCheckBox("Thursday");
		chkThursday.setBounds(496, 101, 93, 21);
		panel_1.add(chkThursday);
		
		chkFriday = new JCheckBox("Friday");
		chkFriday.setBounds(603, 101, 93, 21);
		panel_1.add(chkFriday);
		
		chkSaturday = new JCheckBox("Saturday");
		chkSaturday.setBounds(210, 145, 77, 21);
		panel_1.add(chkSaturday);
		
		chkSunday = new JCheckBox("Sunday");
		chkSunday.setBounds(296, 145, 80, 21);
		panel_1.add(chkSunday);
		
		JLabel lblWorkingTimePer = new JLabel("Working Time Per Day");
		lblWorkingTimePer.setBounds(59, 199, 130, 23);
		panel_1.add(lblWorkingTimePer);
		
		hours = new JTextField();
		hours.setBounds(210, 199, 96, 23);
		panel_1.add(hours);
		hours.setColumns(10);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(316, 204, 45, 13);
		panel_1.add(lblHours);
		
		min = new JTextField();
		min.setBounds(390, 199, 96, 23);
		panel_1.add(min);
		min.setColumns(10);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setBounds(496, 204, 55, 14);
		panel_1.add(lblMinutes);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "WORKING DAYS AND HOURS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 153, 255)));
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(10, 49, 804, 490);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(102, 153, 255));
		panel_3.setBounds(10, 10, 804, 28);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE WORKING DYAS AND HOURS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 10, 242, 13);
		panel_3.add(lblNewLabel);
		
	}
	
	public void workingDayList() {
		WorkingDaysDAOImpl dh = new WorkingDaysDAOImpl();
		ArrayList<DaysndHours> dayList = dh.workingDayList();
		DefaultTableModel tableModel = (DefaultTableModel)manageDays.getModel();
		Object[] row = new Object[5];
		for(int i=0;i<dayList.size();i++) {
			row[0]=dayList.get(i).getId();
			row[1]=dayList.get(i).getWorkingDays();
			row[2]=dayList.get(i).getWorkingTimePerDay();
			row[3]=dayList.get(i).getMin();
			row[4]=dayList.get(i).getNoOfWorkingDays();			
			
			tableModel.addRow(row);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if(ob == btnUpdate) {
			int hr = Integer.parseInt(hours.getText().toString());
			int mint = Integer.parseInt(min.getText().toString());
			int day= Integer.parseInt(noOfDays.getSelectedItem().toString());
			
			String selectedDays = "";
			
			if(chkMonday.isSelected()) {
				selectedDays = "Monday";
			}if(chkTuesday.isSelected()) {
				selectedDays = selectedDays + " Tuesday";
			}if(chkWednesday.isSelected()) {
				selectedDays = selectedDays + " Wednesday";
			}if(chkThursday.isSelected()) {
				selectedDays = selectedDays + " Thursday";
			}if(chkFriday.isSelected()) {
				selectedDays = selectedDays + " Friday";
			}if(chkSaturday.isSelected()) {
				selectedDays = selectedDays + " Saturday";
			}if(chkSunday.isSelected()) {
				selectedDays = selectedDays + " Sunday";
			}
			

			System.out.println(id + selectedDays);
			
			int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to submit your data?","Submit Data",JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				WorkingDaysDAOImpl dh = new WorkingDaysDAOImpl();
				dh.updateWorkingDays(id,selectedDays, hr,mint, day );
				
				DefaultTableModel tModel = (DefaultTableModel)manageDays.getModel();
	            tModel.setRowCount(0);
	            workingDayList();
			}
			
		}
		
		
		if(ob == btnDelete) {
			
			int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete your data?","Delete Data",JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				WorkingDaysDAOImpl dh = new WorkingDaysDAOImpl();
				dh.deleteWorkingDays(id);
				
				DefaultTableModel tModel = (DefaultTableModel)manageDays.getModel();
	            tModel.setRowCount(0);
	            workingDayList();
			}
			
			
		}
		
		
	}
}
