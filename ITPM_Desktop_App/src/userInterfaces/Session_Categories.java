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
import dao.SessionDAOImpl;
import models.ConsecutiveSessions;
import models.Session;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Session_Categories {

	public JPanel Categoriespanel; 
	private JFrame frame;
	private DefaultTableModel tableModel, parTableModel;
	private JTable table, parTable;
	private JScrollPane scrollpane, parScrollPane;
	
	private String selectedSessions, selectedParSessions;

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
		frame.setBounds(100, 100, 900, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Categoriespanel = new JPanel();
		Categoriespanel.setBounds(0, 0, 884, 481);
		frame.getContentPane().add(Categoriespanel);
		Categoriespanel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 11, 864, 459);
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
				selectedSessions = tableModel.getValueAt(selectedRecord, 1).toString();
			}
	    });
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectedSessions == null) {
					JOptionPane.showMessageDialog(Categoriespanel,"Please select the record you want to delete","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
					int confirm = JOptionPane.showConfirmDialog(Categoriespanel,"Are you sure you want to permenantly delete your record?","Delete Record",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						ConsecSessionsDAOImpl daoObj = new ConsecSessionsDAOImpl();
						
						daoObj.deleteSession(selectedSessions);
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();
			            model.setRowCount(0);
			            consecSessionsTable();
					}
				}
			}
		});
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn.setBounds(183, 403, 148, 30);
		panel_5.add(deleteBtn);
		
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
	
		//parSessionsTable();
		Parallel.setLayout(null);
		Parallel.add(parScrollPane);
		
		parTable.addMouseListener((MouseListener) new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				int selectedRecord = parTable.getSelectedRow();	
				selectedParSessions = parTableModel.getValueAt(selectedRecord, 1).toString();
			}
	    });
		
		tabbedPane.addTab("Parallel Sessions", null, Parallel, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);

		
		
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(520, 403, 148, 30);
		panel_5.add(btnNewButton_1);
		
		Categoriespanel.add(tabbedPane);
		Categoriespanel.add(panel_5);
	}
	
	
	public void consecSessionsTable() {
		SessionDAOImpl sessionDao = new SessionDAOImpl();
		ConsecSessionsDAOImpl daoObj = new ConsecSessionsDAOImpl();
		List<ConsecutiveSessions> session_list = daoObj.getSessionList();
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
	
//	public void parSessionsTable() {
//		SessionDAOImpl sessionDao = new SessionDAOImpl();
//		ParellelSessionsDAOImpl daoObj = new ParellelSessionsDAOImpl();
//		List<ParellelSessions> parallelList = daoObj.getSessionList();
//		DefaultTableModel tableModel = (DefaultTableModel)parTable.getModel();
//		Object[] row = new Object[7];
//		
//		for(int i=0;i<parallelList.size();i++) {
//			row[0] = parallelList.get(i).getId();
//			row[1] = parallelList.get(i).getSessionCode();
//			row[2] = parallelList.get(i).getId1();
//			row[3] = sessionDao.getSessionById(parallelList.get(i).getId()).getTag();
//			row[4] = sessionDao.getSessionById(parallelList.get(i).getId()).getSubject();
//			tableModel.addRow(row);
//		}
//	}
}
