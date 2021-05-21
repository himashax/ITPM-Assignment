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
import javax.swing.JComponent;
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
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Manage_NotAvailable_Time implements ActionListener{

	private JFrame frame;
	private DefaultTableModel manageTime;
	private JTable manageTimeSet;
	private JScrollPane scPane1;
	private JButton btnDelete1;
	private JButton btnView;
	private JButton btnRefresh;
	private int id;
	private int duration;
	private JComboBox sesId;
	private JTextField fLec;
	private JTextField secLec;
	private JTextField grp;
	private JTextField day;
	private JComboBox time1;
	private JComboBox time2;
	public JPanel Manage_NotAvailableTime_Panel;
	
	

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
		frame.setTitle("MANAGE NOT AVAILABLE TIME");
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		Manage_NotAvailableTime_Panel = new JPanel();
		Manage_NotAvailableTime_Panel.setBackground(new Color(240, 248, 255));
		Manage_NotAvailableTime_Panel.setBounds(10, 23, 816, 423);
		Manage_NotAvailableTime_Panel.setLayout(null);
		
		//Retrieve the fields related to not available time for sessions
		manageTime = new DefaultTableModel(new String[]{"id","duration","session_ID", "first_lecturer", "second_lecturer","group_ID","day","sTime","eTime"}, 0);
		manageTimeSet = new JTable(manageTime);
		manageTimeSet.setBounds(622, 154, -500, -93);
		
		
		
		
		JTableHeader head = manageTimeSet.getTableHeader();
		head.setBackground(new Color(102, 153, 255));
		head.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.setForeground(Color.white);
		
		
		scPane1 = new JScrollPane(manageTimeSet);
		scPane1.setBounds(61,39,774,264);
		NotAvailableTimeList();
		
		//delete button
		btnDelete1 = new JButton("DELETE");
		btnDelete1.setBackground(new Color(153, 204, 255));
		btnDelete1.setBounds(535, 334, 123, 45);
		btnDelete1.addActionListener(this);
		Manage_NotAvailableTime_Panel.add(btnDelete1);
		
		//view button
		btnView = new JButton("VIEW");
		btnView.setBackground(new Color(255, 255, 255));
		btnView.setBounds(237, 334, 123, 45);
		btnView.addActionListener(this);
		Manage_NotAvailableTime_Panel.add(btnView);
		
		
		
		
	
		
		manageTimeSet.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row= manageTimeSet.getSelectedRow();
				id = Integer.parseInt(manageTime.getValueAt(row, 0).toString());
				duration = Integer.parseInt(manageTime.getValueAt(row, 1).toString());
				sesId.setSelectedItem(manageTime.getValueAt(row, 2).toString());
				fLec.setText(manageTime.getValueAt(row, 3).toString());
				secLec.setText(manageTime.getValueAt(row, 4).toString());
				grp.setText(manageTime.getValueAt(row, 5).toString());
				day.setText(manageTime.getValueAt(row, 6).toString());
				time1.setSelectedItem(manageTime.getValueAt(row,7).toString());
				time2.setSelectedItem(manageTime.getValueAt(row,8).toString());
				
			}});
		
		
		Manage_NotAvailableTime_Panel.add(scPane1);
		frame.getContentPane().add(Manage_NotAvailableTime_Panel);
		
		
		
		
		
		
	}
	
	

	
	
	//take the values to not available time for sessions in the table 
	public void NotAvailableTimeList() {
		NotAvailableDAOImpl nt = new NotAvailableDAOImpl();
		ArrayList<NotAvailable> notAvailableList = nt.notAvailableTimeList();
		DefaultTableModel tableModel1 = (DefaultTableModel)manageTimeSet.getModel();
		Object[] row = new Object[9];
		for(int i=0;i<notAvailableList.size();i++) {
			row[0]=notAvailableList.get(i).getId();
			row[1]=notAvailableList.get(i).getDur();
			row[2]=notAvailableList.get(i).getSessionID();
			row[3]=notAvailableList.get(i).getFistLecturer();
			row[4]=notAvailableList.get(i).getSecondLecturer();
			row[5]=notAvailableList.get(i).getGroupID();
			row[6]=notAvailableList.get(i).getDay();			
			row[7]=notAvailableList.get(i).getTime();
			row[8]=notAvailableList.get(i).getEndTm();
			
			tableModel1.addRow(row);
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		//view all the details related to that record
		if(ob == btnView) {
				NotAvailableDAOImpl dh = new NotAvailableDAOImpl();
				//display all the values as a pop up box
				JOptionPane.showMessageDialog(frame,dh.getByNotAvailableId(id).getSessionID()+"  "+dh.getByNotAvailableId(id).getDur()+" "+dh.getByNotAvailableId(id).getFistLecturer()+" "
				+dh.getByNotAvailableId(id).getSecondLecturer()+" "+dh.getByNotAvailableId(id).getGroupID()+" "+dh.getByNotAvailableId(id).getDay()+" "+dh.getByNotAvailableId(id).getTime()+" "+dh.getByNotAvailableId(id).getEndTm());
				
				
			
			}
			
			
		
		//delete button to remove records
		if(ob == btnDelete1) {
			
			//confirmation message to clarify that we need to delete the particular record
			int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete your data?","Delete Data",JOptionPane.YES_NO_OPTION);
			
			//delete the record to the given id
			if(result == JOptionPane.YES_OPTION) {
				NotAvailableDAOImpl dh = new NotAvailableDAOImpl();
				dh.deleteNotAvailableTime(id);
				
				DefaultTableModel tModel = (DefaultTableModel)manageTimeSet.getModel();
	            tModel.setRowCount(0);
	            NotAvailableTimeList();
			}
			
			
		}
		
		
		
	}
	}

