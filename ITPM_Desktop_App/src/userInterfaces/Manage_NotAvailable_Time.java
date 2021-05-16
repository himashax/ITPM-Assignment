package userInterfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.NotAvailableDAOImpl;
import dao.WorkingDaysDAOImpl;
import models.DaysndHours;
import models.NotAvailable;

import javax.swing.JTable;
import javax.swing.JTextField;

public class Manage_NotAvailable_Time implements ActionListener{

	private JFrame frame;
	private DefaultTableModel manageTime;
	private JTable manageTimeSet;
	private JScrollPane scPane1;
	private JButton btnDelete1;
	private int id;
	private JComboBox sesId;
	private JComboBox lec;
	private JComboBox grp;
	private JComboBox subGrp;
	private JTextField times;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_NotAvailable_Time window = new Manage_NotAvailable_Time();
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
	public Manage_NotAvailable_Time() {
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
		panel.setBounds(47, 23, 726, 468);
		panel.setLayout(null);
		
		manageTime = new DefaultTableModel(new String[]{"id","session_ID", "lecturer", "group_ID","subGroup_ID","time"}, 0);
		manageTimeSet = new JTable(manageTime);
		manageTimeSet.setBounds(622, 154, -500, -93);
		
		
		JTableHeader head = manageTimeSet.getTableHeader();
		head.setBackground(new Color(102, 153, 255));
		head.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.setForeground(Color.white);
		
		
		scPane1 = new JScrollPane(manageTimeSet);
		scPane1.setBounds(67,10,521,146);
		NotAvailableTimeList();
		panel.add(scPane1);
		
		
		btnDelete1 = new JButton("DELETE");
		btnDelete1.setBounds(249, 287, 105, 33);
		btnDelete1.addActionListener(this);
		panel.add(btnDelete1);
		
		
		
		
		
		manageTimeSet.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row= manageTimeSet.getSelectedRow();
				id = Integer.parseInt(manageTime.getValueAt(row, 0).toString());
				sesId.setSelectedItem(manageTime.getValueAt(row, 1).toString());
				lec.setSelectedItem(manageTime.getValueAt(row, 2).toString());
				grp.setSelectedItem(manageTime.getValueAt(row, 3).toString());
				subGrp.setSelectedItem(manageTime.getValueAt(row, 4).toString());
				times.setText(manageTime.getValueAt(row,5).toString());
				
			}});
		
		
		JButton btnRefresh1 = new JButton("REFRESH");
		btnRefresh1.setBounds(91, 287, 105, 33);
		panel.add(btnRefresh1);
		
		JButton btnBack1 = new JButton("BACK");
		btnBack1.setBounds(427, 287, 105, 33);
		panel.add(btnBack1);
		
		
		
	}
	
	public void NotAvailableTimeList() {
		NotAvailableDAOImpl dh = new NotAvailableDAOImpl();
		ArrayList<NotAvailable> dayList = dh.notAvailableTimeList();
		DefaultTableModel tableModel1 = (DefaultTableModel)manageTimeSet.getModel();
		Object[] row = new Object[5];
		for(int i=0;i<dayList.size();i++) {
			row[0]=dayList.get(i).getId();
			row[1]=dayList.get(i).getDur();
			row[2]=dayList.get(i).getSessionID();
			row[3]=dayList.get(i).getLecturer();
			row[4]=dayList.get(i).getGroupID();
			row[5]=dayList.get(i).getDay();			
			row[6]=dayList.get(i).getTime();
			
			tableModel1.addRow(row);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob1 = e.getSource();
		
		if(ob1 == btnDelete1) {
			
			int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete your data?","Delete Data",JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				NotAvailableDAOImpl dh = new NotAvailableDAOImpl();
				dh.deleteNotAvailableTime(id);
				
				DefaultTableModel tModel1 = (DefaultTableModel)manageTimeSet.getModel();
	            tModel1.setRowCount(0);
	            NotAvailableTimeList();
			}
			
			
		}
	}
}
