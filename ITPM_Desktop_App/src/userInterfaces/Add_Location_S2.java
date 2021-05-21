package userInterfaces;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.ConsecSessionsDAOImpl;
import dao.LecturerDAOImpl;
import dao.Manage_Session_RoomDAOImpl;
import dao.SessionDAOImpl;
import models.Active_Days;
import models.ConsecutiveSessions;
import models.Lecturer;
import models.Manage_Session_Room;
import models.Session;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Add_Location_S2 {

	private JFrame frame;
	private JTable table_Room_Session, consecSessionsTable;
	private int id;
	JButton btnViewRoom,btnRefresh;
	public JPanel panel_S2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Location_S2 window = new Add_Location_S2();
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
	public Add_Location_S2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 864, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_S2 = new JPanel();
		frame.getContentPane().add(panel_S2, BorderLayout.CENTER);
		panel_S2.setLayout(null);		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 56, 800, 320);
		panel_S2.add(tabbedPane);
		
		JPanel Session_panel = new JPanel();
		tabbedPane.addTab("Session", null, Session_panel, null);
		Session_panel.setLayout(null);
		
		JPanel Con_panel = new JPanel();
		tabbedPane.addTab("Consecutive Session Rooms", null, Con_panel, null);
		Con_panel.setLayout(null);
		
		JPanel not_panel = new Not_Available_Location().Add_NotAvailable_Location_Panel;
		tabbedPane.addTab("Not Available Times", null, not_panel, null);
		not_panel.setLayout(null);
		
		DefaultTableModel modal = new DefaultTableModel(new String[] {"ID","Lecturer 1","Lecturer 2","Subject","Group ID","No.Students","Tag","Day","Room"},0);
		table_Room_Session = new JTable(modal);
		table_Room_Session.setBounds(10, 11, 800, 116);
		JTableHeader header = table_Room_Session.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table_Room_Session.setModel(modal);

		JScrollPane pane = new JScrollPane(table_Room_Session);
		pane.setBounds(10, 11, 773, 272);
		
		Session_panel.add(pane); 
		
		// select a record
		table_Room_Session.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowId = table_Room_Session.getSelectedRow();
				SessionDAOImpl sessionDao = new SessionDAOImpl();
				Manage_Session_RoomDAOImpl dao = new Manage_Session_RoomDAOImpl();

				id = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
				Session session = sessionDao.getSessionById(id);
				
				Manage_Session_Room manage_rooms = dao.getSessionRoomListById(session.getId());

			}
		});
		
		//call table
		sessionRoomsDataTable();
		
		btnViewRoom = new JButton("View Session Room");
		btnViewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionDAOImpl dao = new SessionDAOImpl();
				Manage_Session_RoomDAOImpl manage = new Manage_Session_RoomDAOImpl();
				JOptionPane.showMessageDialog(null, dao.getSessionById(id).getId() + " - " +  dao.getSessionById(id).getFirstLecturer() + " "+
						 dao.getSessionById(id).getSecLecturer()+" "+  dao.getSessionById(id).getSubject() + " " +
						 dao.getSessionById(id).getGroupId()+ " "+ dao.getSessionById(id).getTag() + " " + manage.getSessionRoomListById(id).getRoomName());
			}
		});
		btnViewRoom.setBounds(278, 416, 162, 25);
		panel_S2.add(btnViewRoom);
		
		
		DefaultTableModel consecModal = new DefaultTableModel(new String[] {"ID","Session Code","Lecturer 1","Lecturer 2","Subject","Group ID","Tag","No.Students","Day","Duration"},0);
		consecSessionsTable = new JTable(consecModal);
		consecSessionsTable.setBounds(10, 11, 800, 116);
		JTableHeader headerConsec = consecSessionsTable.getTableHeader();
		headerConsec.setBackground(SystemColor.activeCaption);
		consecSessionsTable.setModel(consecModal);

		JScrollPane pane2 = new JScrollPane(consecSessionsTable);
		pane2.setBounds(10, 11, 773, 129);
		
		Con_panel.add(pane2); 
		
		// select a record
		consecSessionsTable.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowId = consecSessionsTable.getSelectedRow();
				SessionDAOImpl sessionDao = new SessionDAOImpl();
				Manage_Session_RoomDAOImpl dao = new Manage_Session_RoomDAOImpl();

				id = Integer.parseInt(modal.getValueAt(rowId, 0).toString());
				Session session = sessionDao.getSessionById(id);
				
				Manage_Session_Room manage_rooms = dao.getSessionRoomListById(session.getId());

			}
		});
		
		//call table
		conSessionDataTable();
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(488, 416, 97, 25);
		panel_S2.add(btnRefresh);
		
		
		
		
	}
	

	public void sessionRoomsDataTable() {
		SessionDAOImpl sessionDAOImpl = new SessionDAOImpl();
		ArrayList<Session> session_list = sessionDAOImpl.getSessionList();

		/// newly added
		Manage_Session_RoomDAOImpl dao = new Manage_Session_RoomDAOImpl();
		ArrayList<Manage_Session_Room> roomDataList = dao.getSessionRoom();

		DefaultTableModel modal = (DefaultTableModel) table_Room_Session.getModel();

		Object[] row = new Object[9];

		for (int i = 0; i < session_list.size(); i++) {
			row[0] = roomDataList.get(i).getSessionID();
			row[1] = session_list.get(i).getFirstLecturer();
			row[2] = session_list.get(i).getSecLecturer();
			row[3] = session_list.get(i).getSubject();
			row[4] = session_list.get(i).getGroupId();
			row[5] = session_list.get(i).getNoOfStudents();
			row[6] = session_list.get(i).getTag();
			row[7] = session_list.get(i).getDay();
			row[8] = roomDataList.get(i).getRoomName();

			modal.addRow(row);
		}
	}
	
	public void conSessionDataTable() {
		ConsecSessionsDAOImpl consecDAOImpl = new ConsecSessionsDAOImpl();
		ArrayList<ConsecutiveSessions> session_list = consecDAOImpl.getSessionList();
		
		SessionDAOImpl sessionDao = new SessionDAOImpl();

		/// newly added

		DefaultTableModel modal = (DefaultTableModel) consecSessionsTable.getModel();

		Object[] row = new Object[11];
		Object[] row2 = new Object[11];
		
		for (int i = 0; i < session_list.size(); i++) {
			
			int lecture = session_list.get(i).getLectureSessionId();
			int tute = session_list.get(i).getTuteSessionId();
			
			row[0] = sessionDao.getSessionById(lecture).getId();
			row[1] = session_list.get(i).getSessionCode();
			row[2] = sessionDao.getSessionById(lecture).getFirstLecturer(); 
			row[3] = sessionDao.getSessionById(lecture).getSecLecturer(); 
			row[4] = sessionDao.getSessionById(lecture).getSubject();
			row[5] = sessionDao.getSessionById(lecture).getGroupId();
			row[6] = sessionDao.getSessionById(lecture).getTag();
			row[7] = sessionDao.getSessionById(lecture).getNoOfStudents();
			row[8] = sessionDao.getSessionById(lecture).getDay();
			row[9] = sessionDao.getSessionById(lecture).getDuration();
			
			row2[0] = sessionDao.getSessionById(tute).getId();
			row2[1] = session_list.get(i).getSessionCode();
			row2[2] = sessionDao.getSessionById(tute).getFirstLecturer(); 
			row2[3] = sessionDao.getSessionById(tute).getSecLecturer(); 
			row2[4] = sessionDao.getSessionById(tute).getSubject();
			row2[5] = sessionDao.getSessionById(tute).getGroupId();
			row2[6] = sessionDao.getSessionById(tute).getTag();
			row2[7] = sessionDao.getSessionById(tute).getNoOfStudents();
			row2[8] = sessionDao.getSessionById(tute).getDay();
			row2[9] = sessionDao.getSessionById(tute).getDuration();
			
			modal.addRow(row);
			modal.addRow(row2);
		}
	}
	
	
	
}
