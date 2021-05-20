package userInterfaces;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.LecturerDAOImpl;
import dao.Manage_Session_RoomDAOImpl;
import dao.SessionDAOImpl;
import models.Active_Days;
import models.Lecturer;
import models.Manage_Session_Room;
import models.Session;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;



public class Manage_Session_Rooms implements ActionListener{

	private JFrame frame;
	private JTextField textField_selectSession;
	private JButton submit_session;
	private JButton clear_session;
	private JComboBox comboBox_RoomName;
	private JComboBox comboBox_SessionID;
	private JTextField textFieldSelectedTag;
	private JTextField textFieldRoomType;
	private JLabel lblSelectedRoomType;
	public JPanel panel;
	String id;
	

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
		
		panel = new JPanel();
		panel.setBounds(0, 0, 632, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(66, 62, 505, 295);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSelectSession = new JLabel("Select Room");
		lblSelectSession.setBounds(58, 179, 114, 16);
		panel_1.add(lblSelectSession);
		
		JLabel lblSelectSession_1 = new JLabel("Select Session");
		lblSelectSession_1.setBounds(58, 48, 114, 16);
		panel_1.add(lblSelectSession_1);
		
		JLabel lblSelectedSection = new JLabel("Selected Section");
		lblSelectedSection.setBounds(58, 235, 114, 16);
		panel_1.add(lblSelectedSection);
		
		textField_selectSession = new JTextField();
		textField_selectSession.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_selectSession.setColumns(10);
		textField_selectSession.setBounds(211, 219, 183, 45);
		panel_1.add(textField_selectSession);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(174, 242, 6, 22);
		panel_1.add(textPane);
		
		SessionDAOImpl obj = new SessionDAOImpl();
		ArrayList<Session> list = obj.getSessionList();

		ArrayList<String> test = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
		test.add(String.valueOf(list.get(i).getId()));
		}

		Manage_Session_RoomDAOImpl sessionRooms = new Manage_Session_RoomDAOImpl();
		
		comboBox_SessionID = new JComboBox(test.toArray());
		comboBox_SessionID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionDAOImpl sdao = new SessionDAOImpl();
				Session s = sdao.getSessionById(Integer.parseInt(comboBox_SessionID.getSelectedItem().toString()));
				
				id = comboBox_SessionID.getSelectedItem().toString();
				textFieldSelectedTag.setText(s.getTag());
				
				if(textFieldSelectedTag.getText().equals("Lab|Practical")){
					textFieldRoomType.setText("lab");
					
					System.out.println(textFieldRoomType.getText());
					
				}else {
					textFieldRoomType.setText("lecturehall");
				}
				

				textField_selectSession.setText(" " + comboBox_SessionID.getSelectedItem().toString() +" - " + comboBox_RoomName.getSelectedItem().toString());
			}
		});
		comboBox_SessionID.setBounds(221, 45, 154, 22);
		panel_1.add(comboBox_SessionID);
		
	
		ArrayList<String> rooms = sessionRooms.getSessionRoomList();

		comboBox_RoomName = new JComboBox(rooms.toArray());
		comboBox_RoomName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_selectSession.setText(" " + comboBox_SessionID.getSelectedItem().toString() + " - " + comboBox_RoomName.getSelectedItem().toString());
			}
		});
		
		comboBox_RoomName.setBounds(221, 176, 154, 22);
		panel_1.add(comboBox_RoomName);
		
		JLabel lblSelectedTag = new JLabel("Selected Tag");
		lblSelectedTag.setBounds(58, 90, 114, 16);
		panel_1.add(lblSelectedTag);

		
		textFieldSelectedTag = new JTextField();
		textFieldSelectedTag.setEditable(false);
		textFieldSelectedTag.setBounds(221, 88, 154, 20);
		panel_1.add(textFieldSelectedTag);
		textFieldSelectedTag.setColumns(10);
		
		textFieldRoomType = new JTextField();
		textFieldRoomType.setEditable(false);
		textFieldRoomType.setColumns(10);
		textFieldRoomType.setBounds(221, 132, 154, 20);
		panel_1.add(textFieldRoomType);
		

		lblSelectedRoomType = new JLabel("Selected Room Type");
		lblSelectedRoomType.setBounds(58, 135, 122, 16);
		panel_1.add(lblSelectedRoomType);
		

		
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
			
			mgrsession.getSessionRoomListById(mgr.getSessionID());
			System.out.println("wada");
		}
		
		if(obj ==clear_session) {
			resetField();
		}
	}
}
