package userInterfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import userInterfaces.Add_Student_Groups;
import userInterfaces.Add_Tags;
import userInterfaces.Manage_Student_Groups;
import userInterfaces.Manage_Tags;

import java.awt.CardLayout;
import java.awt.SystemColor;

public class Home_Page {

	private JFrame frame;
	JPanel addStudentGroupsPanel, manageGroupsPanel, addTagsPanel, manageTagsPanel;
	JPanel addLecturerPanel,manageLecturerPanel,addSubjectPanel,manageSubjectPanel;
	private JMenuItem mntmManageSubject;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_Page window = new Home_Page();
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
	public Home_Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("dashboard");
		frame.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(SystemColor.activeCaption);
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Lecturer");
		mnNewMenu.setBackground(SystemColor.activeCaption);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem addLecturer_menu = new JMenuItem("Add Lecturer");
		addLecturer_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addLecturerPanel = new Add_Lecturer().panel_addLecturer;
				frame.getContentPane().add(addLecturerPanel, "name_513472506769600");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Lecturer");
			}
		});
		mnNewMenu.add(addLecturer_menu);
		
		JMenuItem mntmManageLec = new JMenuItem("Manage Lecturer");
		mntmManageLec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageLecturerPanel = new Manage_Lecturer().panel_manageLecturer;
				frame.getContentPane().add(manageLecturerPanel, "name_513472506769600");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Lecturer");
			}
		});
		mnNewMenu.add(mntmManageLec);
		
		JMenu mnNewMenu_1 = new JMenu("Student Groups");
		mnNewMenu_1.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem addGroups_item = new JMenuItem("Add Student Groups");
		addGroups_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.getContentPane().removeAll();
				addStudentGroupsPanel = new Add_Student_Groups().panel_addStudentGroups;
				frame.getContentPane().add(addStudentGroupsPanel, "name_513472506769600");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				//addStudentGroupsPanel.setVisible(true);
				//manageGroupsPanel.setVisible(false);
				frame.setTitle("Add Student Groups");
			}
		});
		mnNewMenu_1.add(addGroups_item);
		
		JMenuItem manageGroups_item = new JMenuItem("Manage Student Groups");
		manageGroups_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageGroupsPanel = new Manage_Student_Groups().panel_manageStudentGroups;
				frame.getContentPane().add(manageGroupsPanel, "name_513457040055000");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				
				frame.setTitle("Manage Student Groups");
				//manageGroupsPanel.setVisible(true);
			
			}
		});
		mnNewMenu_1.add(manageGroups_item);
		
		JMenu Tags = new JMenu("Tags");
		Tags.setForeground(Color.WHITE);
		menuBar.add(Tags);
		
		JMenuItem addTags_item = new JMenuItem("Add Tags");
		addTags_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addTagsPanel = new Add_Tags().panel_addTags;
				frame.getContentPane().add(addTagsPanel, "name_573098162615800");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Tags");
			}
		});
		Tags.add(addTags_item);
		
		JMenuItem manageTags_item = new JMenuItem("Manage Tags");
		manageTags_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.getContentPane().removeAll();
				manageTagsPanel = new Manage_Tags().panel_manageTags;
				frame.getContentPane().add(manageTagsPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Tags");
			}
		});
		Tags.add(manageTags_item);
		
		JMenu mnNewMenu_3 = new JMenu("Subject");
		mnNewMenu_3.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmAddSubject = new JMenuItem("Add Subject");
		mntmAddSubject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.getContentPane().removeAll();
				addSubjectPanel = new Add_Subject().panel_AddSubject;
				frame.getContentPane().add(addSubjectPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Subjects");
			}
		});
		mnNewMenu_3.add(mntmAddSubject);
		
		//Home page subject menu
		mntmManageSubject = new JMenuItem("Manage Subject");
		mntmManageSubject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.getContentPane().removeAll();
				manageSubjectPanel = new Manage_Subject().panel_manageSubject;
				frame.getContentPane().add(manageSubjectPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Subjects");
			}
		});
		mnNewMenu_3.add(mntmManageSubject);
		
		JMenu mnNewMenu_2 = new JMenu("Working Days & Hours");
		mnNewMenu_2.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmAddDays = new JMenuItem("Add Days & Hours");
		mnNewMenu_2.add(mntmAddDays);
		
		JMenuItem mntmManageDays = new JMenuItem("Manage Days & Hours");
		mnNewMenu_2.add(mntmManageDays);
		
		JMenuItem mntmCreateTimeSlots = new JMenuItem("Create Time Slots");
		mnNewMenu_2.add(mntmCreateTimeSlots);
		
		JMenuItem mntmAddTimeForSessions = new JMenuItem("Add Session Time Slots ");
		mnNewMenu_2.add(mntmAddTimeForSessions);
		
		JMenu mnLocation = new JMenu("Location");
		mnLocation.setForeground(Color.WHITE);
		menuBar.add(mnLocation);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Location");
		mnLocation.add(mntmNewMenuItem);
		
		JMenuItem mntmManageLocation = new JMenuItem("Manage Location");
		mnLocation.add(mntmManageLocation);
		
		JMenu mnStatistics = new JMenu("Statistics");
		mnStatistics.setForeground(Color.WHITE);
		menuBar.add(mnStatistics);
		
		JMenuItem mntmShowStat = new JMenuItem("Show Statistics");
		mnStatistics.add(mntmShowStat);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
//		addStudentGroupsPanel = new Add_Student_Groups().panel_addStudentGroups;
//		frame.getContentPane().add(addStudentGroupsPanel, "name_513472506769600");
		
//		manageGroupsPanel = new Manage_Student_Groups().panel_manageStudentGroups;
//		frame.getContentPane().add(manageGroupsPanel, "name_513457040055000");
	}

}

