package userInterfaces;

import java.awt.Color;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.ConsecSessionsDAOImpl;
import dao.SessionDAOImpl;
import models.ConsecutiveSessions;
import models.Session;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Sessions_List implements ActionListener{

	public JPanel sessions_panel;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JButton addConSessions;
	private JLabel resultLabel;
	private ConsecSessionsDAOImpl consecDaoObj;
	private SessionDAOImpl daoObj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sessions_List window = new Sessions_List();
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
	public Sessions_List() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		sessions_panel = new JPanel();
		sessions_panel.setBounds(0, 0, 884, 481);
		frame.getContentPane().add(sessions_panel);
		sessions_panel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 864, 448);
		
		resultLabel = new JLabel("");
		resultLabel.setBounds(301, 387, 309, 14);
		
		tableModel = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return Integer.class;
				case 8:
					return String.class;
				default:
					return Integer.class;
				}
			}
		};
			
		JTable table = new JTable(tableModel);
		tableModel.addColumn("Select");
		tableModel.addColumn("ID");
		tableModel.addColumn("Lecturer 1");
		tableModel.addColumn("Lecturer 2");
		tableModel.addColumn("Tag");
		tableModel.addColumn("Group ID");
		tableModel.addColumn("Subject");
		tableModel.addColumn("Noof Students");
		tableModel.addColumn("Day");
		tableModel.addColumn("Duration");
		table.setBackground(SystemColor.menu);
		

		Session session = new Session();
		daoObj = new SessionDAOImpl();
		consecDaoObj = new ConsecSessionsDAOImpl();
		ArrayList<Session> sessionList = daoObj.getSessionList();
		
		for(int i=0;i<sessionList.size();i++) {
			
			tableModel.addRow(new Object[0]);
		
			tableModel.setValueAt(false, i,0);
			tableModel.setValueAt(sessionList.get(i).getId(), i, 1);
			tableModel.setValueAt(sessionList.get(i).getFirstLecturer(), i, 2);
			tableModel.setValueAt(sessionList.get(i).getSecLecturer(), i, 3);
			tableModel.setValueAt(sessionList.get(i).getTag(), i, 4);
			tableModel.setValueAt(sessionList.get(i).getGroupId(), i, 5);
			tableModel.setValueAt(sessionList.get(i).getSubject(), i, 6);
			tableModel.setValueAt(sessionList.get(i).getNoOfStudents(), i, 7);
			tableModel.setValueAt(sessionList.get(i).getDay(), i, 8);
			tableModel.setValueAt(sessionList.get(i).getDuration(), i, 9);
		}
		
		JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(102, 153, 255));
	    header.setFont(new Font("Tahoma", Font.BOLD, 11));
	    header.setForeground(Color.white);
		panel.setLayout(null);
	    
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10,24,699,318);
		panel.add(scrollPane);
		
		
		addConSessions = new JButton("Add Consecutive Sessions");
		addConSessions.setFont(new Font("Tahoma", Font.PLAIN, 9));
		addConSessions.setBackground(SystemColor.activeCaption);
		addConSessions.setBounds(713, 79, 141, 23);
		addConSessions.addActionListener(this); 
		panel.add(addConSessions);
		sessions_panel.add(panel);
		
		
		//Parallel Sessions
		JButton parSessionBtn = new JButton("Add Parallel Sessions");
		parSessionBtn.addActionListener(this);
		parSessionBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		parSessionBtn.setBackground(SystemColor.activeCaption);
		parSessionBtn.setBounds(713, 142, 141, 23);
		panel.add(parSessionBtn);
	
		
		JButton nonOverlapBtn = new JButton("Add Non Overalapping Sessions");
		nonOverlapBtn.addActionListener(this);
		nonOverlapBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nonOverlapBtn.setBackground(SystemColor.activeCaption);
		nonOverlapBtn.setBounds(713, 200, 141, 23);
		panel.add(nonOverlapBtn);
		
		panel.add(resultLabel);
	}
	
	
	public boolean validateParallelSession(List<String> list) {
		for(String value: list) {
			if(!value.equals(list.get(0)))
				return false;
		}return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	 
	
}
