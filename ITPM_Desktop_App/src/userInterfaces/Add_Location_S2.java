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

import dao.Manage_Session_RoomDAOImpl;
import dao.SessionDAOImpl;
import models.Manage_Session_Room;
import models.Session;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Location_S2 {

	private JFrame frame;
	private JTable table_Room_Session;
	private int id;
	JButton btnViewRoom,btnRefresh;

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
		frame.setBounds(100, 100, 680, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		

		
		DefaultTableModel modal = new DefaultTableModel(new String[] {"ID","Lecturer 1","Lecturer 2","Subject","Group ID","Tag","Room"},0);
		table_Room_Session = new JTable(modal);
		table_Room_Session.setBounds(10, 11, 800, 116);
		JTableHeader header = table_Room_Session.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table_Room_Session.setModel(modal);

		JScrollPane pane = new JScrollPane(table_Room_Session);
		pane.setBounds(10, 11, 736, 129);
		
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
		
		
		JScrollPane scroll = new JScrollPane((Component) null);
		scroll.setBounds(34, 97, 605, 160);
		
		//call table
		sessionRoomsDataTable();
		panel.add(scroll);
		
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
		btnViewRoom.setBounds(300, 387, 129, 25);
		panel.add(btnViewRoom);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(486, 387, 97, 25);
		panel.add(btnRefresh);
	}
	

	public void sessionRoomsDataTable() {
		SessionDAOImpl sessionDAOImpl = new SessionDAOImpl();
		ArrayList<Session> session_list = sessionDAOImpl.getSessionList();

		/// newly added
		Manage_Session_RoomDAOImpl dao = new Manage_Session_RoomDAOImpl();
		ArrayList<Manage_Session_Room> roomDataList = dao.getSessionRoom();

		DefaultTableModel modal = (DefaultTableModel) table_Room_Session.getModel();

		Object[] row = new Object[7];

		for (int i = 0; i < session_list.size(); i++) {
			row[0] = roomDataList.get(i).getSessionID();
			row[1] = session_list.get(i).getFirstLecturer();
			row[2] = session_list.get(i).getSecLecturer();
			row[3] = session_list.get(i).getSubject();
			row[4] = session_list.get(i).getGroupId();
			row[5] = session_list.get(i).getTag();
			row[6] = roomDataList.get(i).getRoomName();

			modal.addRow(row);
		}
	}
}
