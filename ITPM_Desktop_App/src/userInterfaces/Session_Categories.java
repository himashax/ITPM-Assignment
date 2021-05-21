package userInterfaces;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.ConsecSessionsDAOImpl;
import dao.NonOverlapSessionDAOImpl;
import dao.ParallelSessionDAOImpl;
import dao.SessionDAOImpl;
import models.ConsecutiveSessions;
import models.NonOverlapSession;
import models.ParallelSessions;
import models.Session;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Session_Categories {

	public JPanel Categoriespanel; 
	private JFrame frame;
	private DefaultTableModel tableModel, parTableModel, nonOverlapTableModel;
	private JTable table, parTable, nonOverlapTable;
	private JScrollPane scrollpane, parScrollPane, nonOverlapScrollPane;
	
	private String selectedConSessions, selectedParSessions, nonOverlapSessions;
	private int conSessionID, parSessionID, nonOverSessionID; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session_Categories window = new Session_Categories();
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
	public Session_Categories() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 80, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Categoriespanel = new JPanel();
		Categoriespanel.setBounds(0, 0, 854, 461);
		frame.getContentPane().add(Categoriespanel);
		Categoriespanel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 11, 864, 439);
		panel_5.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 34, 818, 356);
		
		
		JPanel consecutive = new JPanel();
		
		tableModel = new DefaultTableModel(new String[]{"ID","Session Code","Lecture1", "Lecture2", "Subject","Group ID","Tag", "Students", "Day", "Duration"}, 0);
		table = new JTable(tableModel);
		table.setBackground(SystemColor.menu);
		JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(102, 153, 255));
	    header.setFont(new Font("Tahoma", Font.BOLD, 11));
	    header.setForeground(Color.white);
	    
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(10,5,793,312);
	
		consecSessionsTable();
		consecutive.setLayout(null);
		consecutive.add(scrollpane);
		
		table.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				int selectedRecord = table.getSelectedRow();	
				conSessionID = (int) tableModel.getValueAt(selectedRecord, 0);
				selectedConSessions = tableModel.getValueAt(selectedRecord, 1).toString();
			}
	    });
		
		tabbedPane.addTab("Consecutive Sessions", null, consecutive, null);
		

		JPanel Parallel = new JPanel();
		
		parTableModel = new DefaultTableModel(new String[]{"ID","Session Code","Lecture1", "Lecture2", "Subject","Group ID","Tag", "Students", "Day", "Duration"}, 0);
		parTable = new JTable(parTableModel);
		parTable.setBackground(SystemColor.menu);
		JTableHeader parHeader = parTable.getTableHeader();
		parHeader.setBackground(new Color(102, 153, 255));
		parHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		parHeader.setForeground(Color.white);
	    
		parScrollPane = new JScrollPane(parTable);
		parScrollPane.setBounds(10,5,793,312);
	
		parSessionsTable();
		Parallel.setLayout(null);
		Parallel.add(parScrollPane);
		
		parTable.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				int selectedRecord = parTable.getSelectedRow();	
				parSessionID = (int) parTableModel.getValueAt(selectedRecord, 0);
				selectedParSessions = parTableModel.getValueAt(selectedRecord, 1).toString();
			}
	    });
		
		tabbedPane.addTab("Parallel Sessions", null, Parallel, null);
		
		
		//Non overlapping
		JPanel NonOverlapping = new JPanel();
		NonOverlapping.setLayout(null);
		
		nonOverlapTableModel = new DefaultTableModel(new String[]{"ID","Session Code","Lecture1", "Lecture2", "Subject","Group ID","Tag", "Students", "Day", "Duration"}, 0);
		nonOverlapTable = new JTable(nonOverlapTableModel);
		nonOverlapTable.setBackground(SystemColor.menu);
		JTableHeader nonOverlapHeader = nonOverlapTable.getTableHeader();
		nonOverlapHeader.setBackground(new Color(102, 153, 255));
		nonOverlapHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		nonOverlapHeader.setForeground(Color.white);
		
		nonOverlapSessionsTable();
		nonOverlapScrollPane = new JScrollPane(nonOverlapTable);
		nonOverlapScrollPane.setBounds(10,5,793,312);
		
		NonOverlapping.add(nonOverlapScrollPane);
		
		nonOverlapTable.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				int selectedRecord = nonOverlapTable.getSelectedRow();	
				nonOverSessionID = (int) nonOverlapTable.getValueAt(selectedRecord, 0);
				nonOverlapSessions = nonOverlapTable.getValueAt(selectedRecord, 1).toString();
			}
	    });
		
		
		
		tabbedPane.addTab("Non Overlapping Sessions", null, NonOverlapping, null);

		JPanel panel_4 = new Not_AvailableTime().ad;
		tabbedPane.addTab("Shalage eka", null, panel_4, null);

		
		JButton viewBtn = new JButton("View Session");
		viewBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewBtn.setForeground(Color.WHITE);
		viewBtn.setBackground(new Color(102, 153, 255));
		viewBtn.setBounds(520, 397, 148, 30);
		viewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SessionDAOImpl sessionDao =  new SessionDAOImpl();
				Session object;
				
				if(conSessionID == 0 && parSessionID == 0 && nonOverSessionID == 0) {
					JOptionPane.showMessageDialog(Categoriespanel,"Please select the record you want to view","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else if(conSessionID != 0) {
					object = sessionDao.getSessionById(conSessionID);
					JOptionPane.showMessageDialog(Categoriespanel,displayDetails(object),"Session Details",JOptionPane.PLAIN_MESSAGE);
					
				}else if(parSessionID != 0) {
					object = sessionDao.getSessionById(parSessionID);
					JOptionPane.showMessageDialog(Categoriespanel,displayDetails(object),"Session Details",JOptionPane.PLAIN_MESSAGE);
					
				}else if(nonOverSessionID != 0) {
					object = sessionDao.getSessionById(nonOverSessionID);
					JOptionPane.showMessageDialog(Categoriespanel,displayDetails(object),"Session Details",JOptionPane.PLAIN_MESSAGE);
					
				}
				
			}
		});
		panel_5.add(viewBtn);
		
		Categoriespanel.add(tabbedPane);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectedConSessions == null && selectedParSessions == null && nonOverlapSessions == null) {
					JOptionPane.showMessageDialog(Categoriespanel,"Please select the record you want to delete","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
						
					int confirm = JOptionPane.showConfirmDialog(Categoriespanel,"Are you sure you want to permenantly delete your record?","Delete Record",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						
						
						if(selectedConSessions != null) {
							ConsecSessionsDAOImpl daoObj = new ConsecSessionsDAOImpl();
							daoObj.deleteSession(selectedConSessions);
							
							DefaultTableModel model = (DefaultTableModel)table.getModel();
				            model.setRowCount(0);
				            consecSessionsTable();
				            
						}else if(selectedParSessions != null) {
							ParallelSessionDAOImpl parDaoObj = new ParallelSessionDAOImpl();
							parDaoObj.deleteSession(selectedParSessions);
							

							DefaultTableModel model = (DefaultTableModel)parTable.getModel();
				            model.setRowCount(0);
				            parSessionsTable();
							
						}else {
							NonOverlapSessionDAOImpl nonOverDaoObj = new NonOverlapSessionDAOImpl();
							nonOverDaoObj.deleteSession(nonOverlapSessions);
							
							DefaultTableModel model = (DefaultTableModel)nonOverlapTable.getModel();
				            model.setRowCount(0);
				            nonOverlapSessionsTable();
						}
					}
				}
			}
		});
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn.setBounds(181, 398, 148, 30);
		panel_5.add(deleteBtn);
		
		
		Categoriespanel.add(panel_5);
	}
	
	
	public void consecSessionsTable() {
		SessionDAOImpl sessionDao = new SessionDAOImpl();
		ConsecSessionsDAOImpl daoObj = new ConsecSessionsDAOImpl();
		ArrayList<ConsecutiveSessions> session_list = daoObj.getSessionList();
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		Object[] row = new Object[11];
		Object[] row2 = new Object[11];
		
		for(int i=0;i<session_list.size();i++) {
			
			int lecSession = session_list.get(i).getLectureSessionId();
			int tuteSession = session_list.get(i).getTuteSessionId();
			
			row[0] = sessionDao.getSessionById(lecSession).getId();
			row[1] = session_list.get(i).getSessionCode();
			row[2] = sessionDao.getSessionById(lecSession).getFirstLecturer(); 
			row[3] = sessionDao.getSessionById(lecSession).getSecLecturer(); 
			row[4] = sessionDao.getSessionById(lecSession).getSubject();
			row[5] = sessionDao.getSessionById(lecSession).getGroupId();
			row[6] = sessionDao.getSessionById(lecSession).getTag();
			row[7] = sessionDao.getSessionById(lecSession).getNoOfStudents();
			row[8] = sessionDao.getSessionById(lecSession).getDay();
			row[9] = sessionDao.getSessionById(lecSession).getDuration();
			
			row2[0] = sessionDao.getSessionById(tuteSession).getId();
			row2[1] = session_list.get(i).getSessionCode();
			row2[2] = sessionDao.getSessionById(tuteSession).getFirstLecturer(); 
			row2[3] = sessionDao.getSessionById(tuteSession).getSecLecturer(); 
			row2[4] = sessionDao.getSessionById(tuteSession).getSubject();
			row2[5] = sessionDao.getSessionById(tuteSession).getGroupId();
			row2[6] = sessionDao.getSessionById(tuteSession).getTag();
			row2[7] = sessionDao.getSessionById(tuteSession).getNoOfStudents();
			row2[8] = sessionDao.getSessionById(tuteSession).getDay();
			row2[9] = sessionDao.getSessionById(tuteSession).getDuration();
			
			tableModel.addRow(row);
			tableModel.addRow(row2);
			
		} 
	}
	
	public void parSessionsTable() {
		SessionDAOImpl sessionDao = new SessionDAOImpl();
		ParallelSessionDAOImpl daoObj = new ParallelSessionDAOImpl();
		ArrayList<ParallelSessions> parallelList = daoObj.getSessionList();
		DefaultTableModel tableModel = (DefaultTableModel)parTable.getModel();
		Object[] row = new Object[10];
		
		for(int i=0;i<parallelList.size();i++) {
			
			Session parallelSeObj = sessionDao.getSessionById(parallelList.get(i).getParallelSessionID());
			row[0] = parallelSeObj.getId();
			row[1] = parallelList.get(i).getSessionCode();
			row[2] = parallelSeObj.getFirstLecturer();
			row[3] = parallelSeObj.getSecLecturer();
			row[4] = parallelSeObj.getSubject();
			row[5] = parallelSeObj.getGroupId();
			row[6] = parallelSeObj.getTag();
			row[7] = parallelSeObj.getNoOfStudents();
			row[8] = parallelSeObj.getDay();
			row[9] = parallelSeObj.getDuration();
			
			tableModel.addRow(row);
		}
	}
	
	
	public void nonOverlapSessionsTable() {
		SessionDAOImpl sessionDao = new SessionDAOImpl();
		NonOverlapSessionDAOImpl daoObj = new NonOverlapSessionDAOImpl();
		ArrayList<NonOverlapSession> nonOverlapList = daoObj.getSessionList();
		DefaultTableModel tableModel = (DefaultTableModel)nonOverlapTable.getModel();
		Object[] row = new Object[10];
		
		for(int i=0;i<nonOverlapList.size();i++) {
			
			Session nonOverlapObj = sessionDao.getSessionById(nonOverlapList.get(i).getNonOverlapSessionID());
			row[0] = nonOverlapObj.getId();
			row[1] = nonOverlapList.get(i).getSessionCode();
			row[2] = nonOverlapObj.getFirstLecturer();
			row[3] = nonOverlapObj.getSecLecturer();
			row[4] = nonOverlapObj.getSubject();
			row[5] = nonOverlapObj.getGroupId();
			row[6] = nonOverlapObj.getTag();
			row[7] = nonOverlapObj.getNoOfStudents();
			row[8] = nonOverlapObj.getDay();
			row[9] = nonOverlapObj.getDuration();
			
			tableModel.addRow(row);
		}
	}
	
	public String displayDetails(Session object) {
		return "ID: " + object.getId() +
		"\nFirst Lecturer: " + object.getFirstLecturer()+
		"\nSecond Lecturer: " + object.getSecLecturer()+
		"\nSubject: "+ object.getSubject()+
		"\nGroup: " + object.getGroupId()+
		"\nTag: " + object.getTag()+
		"\nNo of Students: "+ object.getNoOfStudents()+
		"\nDay : "+ object.getDay()+
		"\nDuration: "+object.getDuration();
	}
	
}
