package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.Mem03_LocationDAOImpl;
import dao.NotAvailableDAOImpl;
import dao.WorkingDaysDAOImpl;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class Not_Available_Location implements ActionListener{

	private JFrame frame;
	private JTextField sTime;
	private JTextField eTime;
	private JComboBox day;
	private JButton btnAddSession;
	private JButton btnClear01;
	public 	JPanel Add_NotAvailable_Location_Panel;
	private JTextField room;

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
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Add_NotAvailable_Location_Panel = new JPanel();
		Add_NotAvailable_Location_Panel.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(Add_NotAvailable_Location_Panel, BorderLayout.CENTER);
		Add_NotAvailable_Location_Panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(21, 21, 592, 312);
		Add_NotAvailable_Location_Panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(49, 42, 489, 234);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		String[] data= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		day= new JComboBox(data);
		day.setBackground(new Color(240, 255, 255));
		day.setBounds(127, 103, 110, 21);
		panel_2.add(day);
		
		
		
		sTime = new JTextField();
		sTime.setBounds(367, 35, 96, 19);
		panel_2.add(sTime);
		sTime.setColumns(10);
		
		eTime = new JTextField();
		eTime.setBounds(367, 104, 96, 19);
		panel_2.add(eTime);
		eTime.setColumns(10);
		
		room = new JTextField();
		room.setBounds(127, 35, 110, 19);
		panel_2.add(room);
		room.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Select Room");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(27, 34, 104, 21);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Day");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(27, 104, 104, 19);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Start Time");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(283, 38, 74, 17);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("End Time");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(283, 107, 74, 17);
		panel_2.add(lblNewLabel_3);
		
		
		
		
		btnAddSession = new JButton("ADD SESSION");
		btnAddSession.setBounds(97, 172, 116, 36);
		btnAddSession.addActionListener(this);
		panel_2.add(btnAddSession);
		btnAddSession.setBackground(new Color(153, 204, 255));
		
		btnClear01 = new JButton("CLEAR");
		btnClear01.setBounds(283, 172, 116, 36);
		panel_2.add(btnClear01);
		btnClear01.setBackground(new Color(255, 255, 255));
		
		
		
	}
	
	public void clear() {
		room.setText(null);
		day.setSelectedIndex(0);
		sTime.setText(null);
		eTime.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if(ob == btnAddSession) {
			
			String rooms =room.getText().toString() ;
			String days = (String)day.getSelectedItem();
			String startingTime = sTime.getText().toString();
			String endingTime =eTime.getText().toString();
			
			Mem03_LocationDAOImpl nat = new Mem03_LocationDAOImpl();
			nat.insertNotAvailableLocation(rooms, days, startingTime, endingTime);
			System.out.println((String)day.getSelectedItem());
	}

		if(ob == btnClear01 ) {
			
			clear();
		}
	}
}
