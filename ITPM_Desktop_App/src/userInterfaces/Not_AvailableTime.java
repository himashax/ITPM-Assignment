package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.NotAvailableDAOImpl;
import dao.SessionDAOImpl;
import dao.WorkingDaysDAOImpl;
import models.Session;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class Not_AvailableTime implements ActionListener{

	private JFrame frame;
	private JComboBox session;
	private JComboBox time;
	private JComboBox endT;
	private JButton btnClear;
	private JButton btnSubmit;
	private JTextField day;
	private JTextField duration;
	private JTextField group;
	private JTextField fLec;
	private JTextField secLec;
	public JPanel ad;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Not_AvailableTime window = new Not_AvailableTime();
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
	public Not_AvailableTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("SESSIONS and NOT AVAILABLE TIME ALLOCATION");
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ad = new JPanel();
		frame.getContentPane().add(ad, BorderLayout.CENTER);
		ad.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(57, 10, 704, 309);
		ad.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(62, 20, 388, 279);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		
		//create object in sessionDAOImpl class to retrieve the sessionID
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
	
		
		//retrieve values according to the session id
		session = new JComboBox(test.toArray());
		session.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session ses =obj.getSessionById(Integer.parseInt(session.getSelectedItem().toString()));
				group.setText(ses.getGroupId());
				day.setText(ses.getDay());
				duration.setText(String.valueOf(ses.getDuration()));
				fLec.setText(ses.getFirstLecturer());
				secLec.setText(ses.getSecLecturer());
			}
		});
		session.setBounds(199, 11, 143, 22);
		panel_2.add(session);
		
		
//		ArrayList<String> test1 = new ArrayList<String>();
//		//String[] test = new String[list.size()];
//		//String[] test = null;
//		for(int i=0;i<list.size();i++) {
//			test.add(String.valueOf(list.get(i).getFirstLecturer()));
//			System.out.println(String.valueOf(list.get(i).getId()));
//		}
//		System.out.println(test);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("Select Session ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(9, 10, 112, 23);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select first Lecturer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(9, 43, 136, 23);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Selected Group / Sub Group");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(9, 113, 182, 40);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Selected Day");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(9, 163, 121, 28);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TIME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(9, 242, 42, 23);
		panel_2.add(lblNewLabel_4);
		
		day = new JTextField();
		day.setEditable(false);
		day.setBounds(199, 168, 143, 22);
		panel_2.add(day);
		day.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Allocated Duration");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(9, 211, 136, 19);
		panel_2.add(lblNewLabel_5);
		
		duration = new JTextField();
		duration.setEditable(false);
		duration.setColumns(10);
		duration.setBounds(199, 211, 143, 22);
		panel_2.add(duration);
		
		//retrieve time values as timeslots
		NotAvailableDAOImpl notAvailable = new NotAvailableDAOImpl();
		ArrayList<String> ob2 = notAvailable.retrieveTime1();
		time = new JComboBox(ob2.toArray());
		time.setBounds(61, 243, 143, 22);
		panel_2.add(time);
		
		group = new JTextField();
		group.setEditable(false);
		group.setColumns(10);
		group.setBounds(199, 124, 143, 22);
		panel_2.add(group);
		
		fLec = new JTextField();
		fLec.setEditable(false);
		fLec.setColumns(10);
		fLec.setBounds(199, 45, 143, 22);
		panel_2.add(fLec);
		
		JLabel lblNewLabel_6 = new JLabel("Select second Lecturer");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(9, 76, 160, 27);
		panel_2.add(lblNewLabel_6);
		
		secLec = new JTextField();
		secLec.setEditable(false);
		secLec.setColumns(10);
		secLec.setBounds(199, 80, 143, 22);
		panel_2.add(secLec);
		
		ArrayList<String> ob3 = notAvailable.retrieveTime2();
		endT = new JComboBox(ob3.toArray());
		endT.setBounds(224, 243, 143, 23);
		panel_2.add(endT);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBackground(new Color(153, 204, 255));
//		btnSubmit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}		
//		});
		btnSubmit.setBounds(498, 83, 130, 38);
		btnSubmit.addActionListener(this);
		panel_1.add(btnSubmit);
		
		btnClear = new JButton("CLEAR");
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setBounds(498, 198, 130, 38);
		btnClear.addActionListener(this);
		panel_1.add(btnClear);
		
		
	}
	
	//method to clear the values
	public void clear() {
		session.setSelectedIndex(0);
		fLec.setText(null);
		secLec.setText(null);
		time.setSelectedIndex(0);
		group.setText(null);
		duration.setText(null);
		day.setText(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e1) {
		// get the clickable
		Object ob1 = e1.getSource();
		//submit the values
		if(ob1 == btnSubmit) {
			
			//set values
			String sessions =session.getSelectedItem().toString();
			String lecturers1 =fLec.getText().toString();
			String lecturers2 =secLec.getText().toString();
			String tTime = time.getSelectedItem().toString();
			String groups =group.getText().toString();
			String days =day.getText().toString();
			String dur = duration.getText().toString();
			String eTime = endT.getSelectedItem().toString();
			
			//System.out.println(tTime.substring(0, 2));
			
			//grab the first two characters from the string
			int time = Integer.valueOf(tTime.substring(0, 2));
			int time2 = Integer.valueOf(eTime.substring(0, 2));
			//System.out.println(tTime+" "+eTime);
			
			//see the time difference
			int timeDif = time2 - time;
			
			if(Integer.parseInt(dur) != timeDif) {
				
				//validate the duration and timeslots
				JOptionPane.showMessageDialog(null,"Time given is incompatible with duration","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				
				//show confirmation message to submit the data
				int results = JOptionPane.showConfirmDialog(frame,"Are you sure you want to submit your data?","Submit Data",JOptionPane.YES_NO_OPTION);
				if(results == JOptionPane.YES_OPTION) {
					NotAvailableDAOImpl na = new NotAvailableDAOImpl();
					na.insertNotAvailableTime(Integer.parseInt(dur),Integer.parseInt(sessions),lecturers1,lecturers2,groups,days,tTime,eTime);
				}
				
				
			}
			
			
			
		}
		
	
		//clear the values
		if(ob1 == btnClear ) {
			
			//call the clear method
			clear();
		}
	}
}
