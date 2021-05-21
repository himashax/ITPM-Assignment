package userInterfaces;

import java.awt.Color;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import dao.NonOverlapSessionDAOImpl;
import dao.ParallelSessionDAOImpl;
import dao.SessionDAOImpl;
import models.ConsecutiveSessions;
import models.Session;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Sessions_List implements ActionListener{

	public JPanel sessions_panel;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JButton ConSessionsBtn, parSessionBtn, nonOverlapBtn;
	private JLabel resultLabel;
	private ConsecSessionsDAOImpl consecDaoObj;
	private SessionDAOImpl daoObj;
	private int selectedRecord;

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
		sessions_panel.setBounds(0, 0, 854, 454);
		frame.getContentPane().add(sessions_panel);
		sessions_panel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 864, 432);
		
		resultLabel = new JLabel("");
		resultLabel.setBounds(274, 386, 435, 14);
		
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
			
		table = new JTable(tableModel);
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
		
		
		daoObj = new SessionDAOImpl();
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
		
		//Consecutive Sessions
		ConSessionsBtn = new JButton("Add Consecutive Sessions");
		ConSessionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ConSessionsBtn.setBackground(SystemColor.activeCaption);
		ConSessionsBtn.setBounds(713, 79, 141, 23);
		ConSessionsBtn.addActionListener(this);
		panel.add(ConSessionsBtn);
		sessions_panel.add(panel);
		
		//Parallel Sessions
		parSessionBtn = new JButton("Add Parallel Sessions");
		parSessionBtn.addActionListener(this); 
		parSessionBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		parSessionBtn.setBackground(new Color(102, 153, 255));
		parSessionBtn.setForeground(Color.WHITE);
		parSessionBtn.setBounds(713, 142, 141, 23);
		panel.add(parSessionBtn);
	
		//Non overlapping Sessions
		nonOverlapBtn = new JButton("Add Non Overalapping Sessions");
		nonOverlapBtn.addActionListener(this); 
		nonOverlapBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nonOverlapBtn.setBackground(Color.LIGHT_GRAY);
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
		
		Object obj = e.getSource();
		
		ArrayList<String> array = new ArrayList<>();
		
		for(int i=0;i<table.getRowCount();i++) {
			Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
		
			if(checked) {
				String checkedID = table.getValueAt(i, 1).toString();
				array.add(checkedID);
			}	
		}
		
		ArrayList<Session> selectedSessions = new ArrayList<>();
		ArrayList<String> days = new ArrayList<String>();
		ArrayList<String> duration = new ArrayList<String>();
		ArrayList<String> groupAndSubject = new ArrayList<String>();
		
		ArrayList<String> startTime = new ArrayList<String>();
		
		for(int i=0;i<array.size();i++) {
			Session object = daoObj.getSessionById(Integer.parseInt(array.get(i)));
			selectedSessions.add(object);
			days.add(object.getDay());
			duration.add(String.valueOf(object.getDuration()));
			groupAndSubject.add(object.getGroupId()+object.getSubject());
		}
		
		if(obj == ConSessionsBtn) {
			if(selectedSessions.size() < 2 || selectedSessions.size() > 2) {
				JOptionPane.showMessageDialog(sessions_panel,"Select only two Lecture ans Tutorial Sessions","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!selectedSessions.get(0).getTag().equals("Lecture") && !selectedSessions.get(1).getTag().equals("Tutorial")) {
				JOptionPane.showMessageDialog(sessions_panel,"Select Lecture and Tutorial Sessions","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(groupAndSubject)) {
				JOptionPane.showMessageDialog(sessions_panel,"Groups and Subject should be the same","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(days)) {
				JOptionPane.showMessageDialog(sessions_panel,"Sessions should be held in the same day","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else {
				ConsecSessionsDAOImpl consecDaoObj = new ConsecSessionsDAOImpl();
				int sessionID1 = daoObj.getSessionById(Integer.parseInt(array.get(0))).getId();
				int sessionID2 = daoObj.getSessionById(Integer.parseInt(array.get(1))).getId();
				
				if(consecDaoObj.checkExistence(sessionID1) || consecDaoObj.checkExistence(sessionID2)){
					JOptionPane.showMessageDialog(sessions_panel,"This Session already exists as a Consecutive Session","Alert",JOptionPane.WARNING_MESSAGE);
				
				}else if(consecDaoObj.checkExistence(sessionID1) || consecDaoObj.checkExistence(sessionID2)){
					JOptionPane.showMessageDialog(sessions_panel,"These Lecture and Tutorial Sessions exist as Consecutive Session","Alert",JOptionPane.WARNING_MESSAGE);
				
				}else {
					int confirm = JOptionPane.showConfirmDialog(sessions_panel,"Are you sure you want to add selected sessions?","Submit Data",JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						consecDaoObj.createConsecSessions(sessionID1, sessionID2);
						
						resultLabel.setText("Consecutive Sessions Added Successfully");	
					}
				}	
			}  
			
		}else if(obj == parSessionBtn ) {
			if(selectedSessions.size() < 2) {
				JOptionPane.showMessageDialog(sessions_panel,"Select at least two Parallel Sessions","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(days)) {
				JOptionPane.showMessageDialog(sessions_panel,"Parallel Sessions' days should be the same","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(duration)) {
				JOptionPane.showMessageDialog(sessions_panel,"Parallel Sessions' duration should be the same","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else {
				ParallelSessionDAOImpl parDaoObj = new ParallelSessionDAOImpl();
				String sessionCode = parDaoObj.generateSessionCode(parDaoObj.getSessionID());
				
				NonOverlapSessionDAOImpl nonOverObj = new NonOverlapSessionDAOImpl();
				
				int confirm = JOptionPane.showConfirmDialog(sessions_panel,"Are you sure you want to add selected sessions?","Submit Data",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					for(int i=0;i<selectedSessions.size();i++) {
						int sessionID = selectedSessions.get(i).getId();
						
						if(nonOverObj.checkExistence(sessionID)) {
							resultLabel.setText("Cannot Add Sessions that already Exist as Non Overlapping Session");
						}else {
							parDaoObj.createParallelSession(sessionCode, sessionID);
							resultLabel.setText("Parallel Sessions Added Successfully");
						}
					}
				}
			}
			
		}else if(obj == nonOverlapBtn) {
			if(selectedSessions.size() < 2) {
				JOptionPane.showMessageDialog(sessions_panel,"Select at least two Non Overlapping Sessions","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(days)) {
				JOptionPane.showMessageDialog(sessions_panel,"Non Overlapping Sessions' days should be the same","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else if(!validateParallelSession(duration)) {
				JOptionPane.showMessageDialog(sessions_panel,"Non Overlapping Sessions' duration should be the same","Alert",JOptionPane.WARNING_MESSAGE);
			
			}else {
				NonOverlapSessionDAOImpl nonOverDaoObj = new NonOverlapSessionDAOImpl();
				String sessionCode = nonOverDaoObj.generateSessionCode(nonOverDaoObj.getSessionID());
				
				ParallelSessionDAOImpl parObj = new ParallelSessionDAOImpl();
				
				int confirm = JOptionPane.showConfirmDialog(sessions_panel,"Are you sure you want to add selected sessions?","Submit Data",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					for(int i=0;i<selectedSessions.size();i++) {
						int sessionID = selectedSessions.get(i).getId();
						
						if(parObj.checkExistence(sessionID)) {
							resultLabel.setText("Cannot Add Sessions that Already exist as Parallel Session");
						}else {
							nonOverDaoObj.createNonOverlapSession(sessionCode, sessionID);
							resultLabel.setText("Non Overlapping Sessions Added Successfully");
						}
					}
					
					
				}
			}
			
		}else {
			System.out.println(selectedRecord);
		}

		
	}
}
