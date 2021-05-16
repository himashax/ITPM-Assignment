package userInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.SessionDAOImpl;
import models.Session;
import models.Student_Group;

public class Sessions_List {

	public JPanel sessions_panel;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JButton addConSessions;

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
		panel.setBounds(10, 34, 864, 425);
		
		
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
		SessionDAOImpl daoObj = new SessionDAOImpl();
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
		scrollPane.setBounds(10,42,699,334);
		panel.add(scrollPane);
		
		
		addConSessions = new JButton("Add Consecutive Sessions");
		addConSessions.setBounds(719, 98, 135, 23);
		addConSessions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int checkedCount = 0;
				String[] idArray = {};
				ArrayList<String> array = new ArrayList<>();
				
				Session sessionObj = new Session();
				
				for(int i=0;i<table.getRowCount();i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
				
					if(checked) {
						checkedCount++;
						String checkedID = table.getValueAt(i, 1).toString();
						array.add(checkedID);
					}	
				}
			
				System.out.println(array);
		
				if(checkedCount < 2) {
					JOptionPane.showMessageDialog(sessions_panel,"Please select two records","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else if(checkedCount > 2){
					JOptionPane.showMessageDialog(sessions_panel,"Select only two records","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else{
					
					Session obj1 = daoObj.getSessionById(Integer.parseInt(array.get(0)));
					Session obj2 = daoObj.getSessionById(Integer.parseInt(array.get(1)));
					
					String tag1 = obj1.getTag();  //sg.getbyid(Integer.parseInt(array.get(0))).getProgramme();
					String tag2 = obj2.getTag();
					
					System.out.println(tag1+ tag2);
					
					//check the subject
					if(!obj1.getSubject().equals(obj2.getSubject())) {
						JOptionPane.showMessageDialog(sessions_panel,"Select same","Alert",JOptionPane.WARNING_MESSAGE);
						
					}
					else if((!tag1.equals("Lecture") && !tag2.equals("Tutorial")) || (!tag1.equals("Tutorial") && !tag2.equals("Lecture"))) {
						JOptionPane.showMessageDialog(sessions_panel,"Select lec and tute","Alert",JOptionPane.WARNING_MESSAGE);
					}
					else {
//						System.out.println(obj1.getId());
//						JOptionPane.showMessageDialog(sessions_panel,"ela","Alert",JOptionPane.WARNING_MESSAGE);
//						//do the insertion
//						
//						insertConsecSessions(Integer.parseInt(obj1.getId()), Integer.parseInt(obj2.getId()));
					}
					
				}
			}
		});
		panel.add(addConSessions);
		
		
		sessions_panel.add(panel);
	}
	

}
