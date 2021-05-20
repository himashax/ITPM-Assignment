package userInterfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.NotAvailableDAOImpl;
import dao.SessionDAOImpl;
import dao.WorkingDaysDAOImpl;
import models.DaysndHours;
import models.NotAvailable;
import models.Session;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manage_Timeslot_For_Session {

	private JFrame frame;
	private JTable mngTSession;
	private DefaultTableModel mngTSessModel;
	private JScrollPane scPane;
	private JComboBox session,timeslot;
	private JButton btnAdd;
	private int id;
	public JPanel mng;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Timeslot_For_Session window = new Manage_Timeslot_For_Session();
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
	public Manage_Timeslot_For_Session() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MANAGE TIME SLOTS FOR SESSIONS");
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mng = new JPanel();
		frame.getContentPane().add(mng, BorderLayout.CENTER);
		mng.setLayout(null);
		
		SessionDAOImpl obj = new SessionDAOImpl();
		ArrayList<Session> list = obj.getSessionList();
		
		ArrayList<String> test = new ArrayList<String>();
		//String[] test = new String[list.size()];
		//String[] test = null;
		for(int i=0;i<list.size();i++) {
			test.add(String.valueOf(list.get(i).getId()));
			System.out.println(String.valueOf(list.get(i).getId()));
		}
		System.out.println(test);
	
		
		mngTSessModel = new DefaultTableModel(new String[]{"id","session", "timeslot"}, 0);
		mngTSession = new JTable(mngTSessModel);
		mngTSession.setBounds(622, 154, -500, -93);
		
		JTableHeader head = mngTSession.getTableHeader();
		head.setBackground(new Color(102, 153, 255));
		head.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.setForeground(Color.white);
		
		scPane = new JScrollPane(mngTSession);
		scPane.setBounds(67,10,521,146);
		sessionTimeList();
		mng.add(scPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(22, 10, 588, 331);
		mng.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(71, 157, 445, 164);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		session = new JComboBox(test.toArray());
		session.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		session.setBounds(238, 20, 171, 28);
		panel_2.add(session);
		
		NotAvailableDAOImpl notAvailable = new NotAvailableDAOImpl();
		ArrayList<String> ob2 = notAvailable.retrieveTime1();
		timeslot = new JComboBox(ob2.toArray());
		timeslot.setBounds(238, 75, 171, 28);
		panel_2.add(timeslot);
		
		JLabel lblNewLabel_1 = new JLabel("Timeslot");
		lblNewLabel_1.setBounds(98, 82, 98, 21);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("Session");
		lblNewLabel.setBounds(98, 27, 98, 21);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(153, 204, 255));
		btnAdd.setBounds(138, 120, 151, 34);
		//btnAdd.addActionListener(this);
		panel_2.add(btnAdd);
		
		
		mngTSession.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row= mngTSession.getSelectedRow();
				id = Integer.parseInt(mngTSessModel.getValueAt(row, 0).toString());
				session.setSelectedItem(mngTSessModel.getValueAt(row, 1).toString());
				timeslot.setSelectedItem(mngTSessModel.getValueAt(row,2).toString());
		
	}
		});
	}
	
	
	public void sessionTimeList() {
		NotAvailableDAOImpl nt = new NotAvailableDAOImpl();
		ArrayList<NotAvailable> notAvailableList = nt.notAvailableTimeList();
		DefaultTableModel tableModel1 = (DefaultTableModel)mngTSession.getModel();
		Object[] row = new Object[3];
		for(int i=0;i<notAvailableList.size();i++) {
			row[0]=notAvailableList.get(i).getId();
			row[1]=notAvailableList.get(i).getSessionID();
			row[2]=notAvailableList.get(i).getTime();
			
			
			tableModel1.addRow(row);
		}
		
	}
	
//	public void actionPerformed(ActionEvent e1) {
//		// TODO Auto-generated method stub
//		Object ob1 = e1.getSource();
//		if(ob1 == btnAdd) {
//			
//			String sessions =session.getSelectedItem().toString();
//			String times =timeslot.getSelectedItem().toString();
//			
//			NotAvailableDAOImpl na = new NotAvailableDAOImpl();
//			na.insertNotAvailableTime(Integer.parseInt(sessions),times);
//		}
//	
	
	
}

