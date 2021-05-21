package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.Manage_Session_RoomDAOImpl;
import dao.Mem03_LocationDAOImpl;
import dao.NotAvailableDAOImpl;
import dao.SessionDAOImpl;
import dao.WorkingDaysDAOImpl;
import models.Manage_Session_Room;
import models.Session;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class Not_Available_Location implements ActionListener{

	private JFrame frame;
	private JComboBox sess_1;
	private JComboBox sTime,eTime;
	private JButton btnAddSession;
	private JButton btnClear01;
	public 	JPanel Add_NotAvailable_Location_Panel;
	private JTextField room;
	private JTextField day;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Not_Available_Location window = new Not_Available_Location();
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
	public Not_Available_Location() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ADD LOCATION");
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Add_NotAvailable_Location_Panel = new JPanel();
		Add_NotAvailable_Location_Panel.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(Add_NotAvailable_Location_Panel, BorderLayout.CENTER);
		Add_NotAvailable_Location_Panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(10, 10, 778, 272);
		Add_NotAvailable_Location_Panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(28, 30, 689, 217);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		
		
		
		//create object in sessionDAOImpl class to retrieve the sessionID
		SessionDAOImpl obj = new SessionDAOImpl();
		
		ArrayList<Session> list = obj.getSessionList();
		
		ArrayList<String> test1 = new ArrayList<String>();
		//String[] test = new String[list.size()];
		//String[] test = null;
		for(int i=0;i<list.size();i++) {
			test1.add(String.valueOf(list.get(i).getId()));
			System.out.println(String.valueOf(list.get(i).getId()));
		}
		System.out.println(test1);
		
	
		
		
		
		
//		
//		NotAvailableDAOImpl nt2 = new NotAvailableDAOImpl();
//		
		
		btnClear01 = new JButton("CLEAR");
		btnClear01.setBackground(new Color(255, 255, 255));
		btnClear01.setBounds(397, 168, 116, 36);
		btnClear01.addActionListener(this);
		panel_2.add(btnClear01);
		
		
		
		day = new JTextField();
		day.setEditable(false);
		day.setColumns(10);
		day.setBounds(138, 118, 193, 28);
		panel_2.add(day);
		
		room = new JTextField();
		room.setEditable(false);
		room.setBounds(138, 66, 193, 28);
		panel_2.add(room);
		room.setColumns(10);
		
		//retrieve time for start time
		Mem03_LocationDAOImpl nt1 = new Mem03_LocationDAOImpl();
		ArrayList<String> ob2 = nt1.retrieveTimeSeperate1();
		sTime = new JComboBox(ob2.toArray());
		sTime.setBounds(476, 17, 164, 28);
		panel_2.add(sTime);
		
		//retrieve time for end time
		ArrayList<String> ob3 = nt1.retrieveTimeSeperate2();
		eTime = new JComboBox(ob3.toArray());
		eTime.setBounds(476, 66, 164, 29);
		panel_2.add(eTime);
		
		
		
		Manage_Session_RoomDAOImpl mng = new Manage_Session_RoomDAOImpl();
		sess_1 = new JComboBox(test1.toArray());
		sess_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session ses =obj.getSessionById(Integer.parseInt(sess_1.getSelectedItem().toString()));
				day.setText(ses.getDay());
				Manage_Session_Room mn = mng.getSessionRoomListById(Integer.parseInt(sess_1.getSelectedItem().toString()));
				room.setText(mn.getRoomName());
				//sTime.setText(ses.);
				//System.out.println(ses.getDay());
				
			}
		});
		sess_1.setBounds(138, 17, 193, 28);
		panel_2.add(sess_1);
		
		JLabel lblNewLabel = new JLabel("Select Room");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(24, 68, 104, 21);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Day");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(24, 121, 104, 19);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Start Time");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(367, 22, 74, 17);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("End Time");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(367, 70, 74, 17);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Select Session");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(24, 20, 104, 21);
		panel_2.add(lblNewLabel_4);
		
		
		
		
		btnAddSession = new JButton("ADD SESSION");
		btnAddSession.setBounds(204, 168, 116, 36);
		btnAddSession.addActionListener(this);
		panel_2.add(btnAddSession);
		btnAddSession.setBackground(new Color(153, 204, 255));
		
		
		
	
		
	}
	
	//clear method 
	public void clear() {
		sess_1.setSelectedIndex(0);
		room.setText(null);
		day.setText(null);
		sTime.setSelectedIndex(0);
		eTime.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the clickable
		Object ob = e.getSource();
		//submit the values
		if(ob == btnAddSession) {
			
			//validate the start time and end time, as they cannot be the same time
			if (sTime.getSelectedItem().toString().equals(eTime.getSelectedItem().toString()) || eTime.getSelectedItem().toString().equals(sTime.getSelectedItem().toString() )) {
				JOptionPane.showMessageDialog(null,"Cannot be same start and end time","Alert",JOptionPane.WARNING_MESSAGE);
				} 
			
			else {
					
					//set values
					String rooms =room.getText().toString() ;
					String days = day.getText().toString();
					String sessions = sess_1.getSelectedItem().toString();
					String startingTime = sTime.getSelectedItem().toString();
					String endingTime =eTime.getSelectedItem().toString();
					
					
					Mem03_LocationDAOImpl nat = new Mem03_LocationDAOImpl();
					
					//validate that the room cannot be reserve to the previously allocated time period
					if(nat.checkTime(rooms, days, startingTime, endingTime)) {
						JOptionPane.showMessageDialog(null,"Already the room is scheduled for this timeslot","Alert",JOptionPane.WARNING_MESSAGE);
					}
					
			else {
						//pop up the confirmation message to submit the data
						int results = JOptionPane.showConfirmDialog(frame,"Are you sure you want to add a session?","Submit Data",JOptionPane.YES_NO_OPTION);
						
						if(results == JOptionPane.YES_OPTION) {
						nat.insertNotAvailableLocation(Integer.parseInt(sessions),rooms, days, startingTime, endingTime);
				}
			
			}
			}
			
	}
		//clear the values
		if(ob == btnClear01 ) {
			
			//call the clear method
			clear();
		}
	}
}
