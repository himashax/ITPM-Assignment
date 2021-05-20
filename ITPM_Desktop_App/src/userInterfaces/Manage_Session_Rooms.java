package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dao.Manage_Session_RoomDAOImpl;
import dao.SessionDAOImpl;
import models.Manage_Session_Room;
import models.Session;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Manage_Session_Rooms implements ActionListener{
	private JFrame frame;
	private JTextField textField_selectSession;
	private JButton submit_session;
	private JButton clear_session;
	private JComboBox comboBox_RoomName;
	private JComboBox comboBox_SessionID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Session_Rooms window = new Manage_Session_Rooms();
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
	public Manage_Session_Rooms() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 632, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(66, 62, 505, 295);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSelectSession = new JLabel("Select Room");
		lblSelectSession.setBounds(58, 122, 114, 16);
		panel_1.add(lblSelectSession);
		
		JLabel lblSelectSession_1 = new JLabel("Select Session");
		lblSelectSession_1.setBounds(58, 80, 114, 16);
		panel_1.add(lblSelectSession_1);
		
		JLabel lblSelectedSection = new JLabel("Selected Section");
		lblSelectedSection.setBounds(58, 164, 114, 16);
		panel_1.add(lblSelectedSection);
		
		textField_selectSession = new JTextField();
		textField_selectSession.setColumns(10);
		textField_selectSession.setBounds(221, 161, 154, 45);
		panel_1.add(textField_selectSession);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(174, 242, 6, 22);
		panel_1.add(textPane);
		
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
		
		
		comboBox_SessionID = new JComboBox(test.toArray());
		comboBox_SessionID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_selectSession.setText(" " + comboBox_SessionID.getSelectedItem().toString() +" - " + comboBox_RoomName.getSelectedItem().toString());
			}
		});
		comboBox_SessionID.setBounds(221, 80, 154, 22);
		panel_1.add(comboBox_SessionID);
		
		Manage_Session_RoomDAOImpl sessionRooms = new Manage_Session_RoomDAOImpl();
		
		ArrayList<String> rooms = sessionRooms.getSessionRoomList();
		
		comboBox_RoomName = new JComboBox(rooms.toArray());
		comboBox_RoomName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_selectSession.setText(" " + comboBox_SessionID.getSelectedItem().toString() + " - " + comboBox_RoomName.getSelectedItem().toString());
			}
		});
		comboBox_RoomName.setBounds(221, 119, 154, 22);
		panel_1.add(comboBox_RoomName);
		
		
		
		submit_session = new JButton("Submit");
		submit_session.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		submit_session.setBounds(288, 390, 97, 25);
		submit_session.addActionListener(this);
		panel.add(submit_session);
		
		clear_session = new JButton("Clear");
		clear_session.setBounds(433, 390, 97, 25);
		clear_session.addActionListener(this);
		panel.add(clear_session);
	}
	
	public void resetField() {
		comboBox_SessionID.setSelectedIndex(0);
		comboBox_RoomName.setSelectedIndex(1);
		textField_selectSession.setText(null);


		}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj= e.getSource();
		
		if(obj == submit_session) {
			Manage_Session_Room mgr = new Manage_Session_Room();
			
			mgr.setSessionID(Integer.parseInt(comboBox_SessionID.getSelectedItem().toString()));
			mgr.setRoomName(comboBox_RoomName.getSelectedItem().toString());
			
			Manage_Session_RoomDAOImpl mgrsession = new Manage_Session_RoomDAOImpl();
			mgrsession.insertSessionRoom(mgr);
			System.out.println("wada");
		}
		
		if(obj ==clear_session) {
			resetField();
		}
	}
	
}
