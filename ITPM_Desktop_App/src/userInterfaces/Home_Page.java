
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
//
import userInterfaces.Add_Student_Groups;
import userInterfaces.Add_Tags;
import userInterfaces.Manage_Student_Groups;
import userInterfaces.Manage_Tags;

import java.awt.CardLayout;
import java.awt.SystemColor;

public class Home_Page {

	private JFrame frame;
	JPanel addStudentGroupsPanel, manageGroupsPanel, addTagsPanel, manageTagsPanel;


	JPanel addWorkingDaysPanel,manageDaysPanel,timeslotPanel,addNotAvailableTimePanel,addNotAvailableLocation,manageNotAvailableTimePanel,tPanel;

	JPanel manageLocationPanel, displayStatPanel,addLocationPanel;


	
	
	JPanel sessionRoomPanel,viewSessionRoomPanel;


	JPanel addLecturerPanel,manageLecturerPanel,addSubjectPanel,manageSubjectPanel,addSession,manageSession;



	JPanel sessionsListPanel, categoriesPanel;


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

		frame.setTitle("Home");

		frame.setLocationRelativeTo(null);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(SystemColor.activeCaption);
		frame.setJMenuBar(menuBar);
		
		//menu for Lecturer
		JMenu mnNewMenu = new JMenu("Lecturer");
		mnNewMenu.setBackground(SystemColor.activeCaption);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		//menu item 'Add Lecturer' for the menu Lecturer
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
		
		//menu item 'Manage Lecturer' for the menu Lecturer
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
		
		//menu for Subject
		JMenu mnNewMenu_3 = new JMenu("Subject");
		mnNewMenu_3.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_3);
		
		//menu item 'Add Subject' for the menu Subject
		JMenuItem mntmAddSubject = new JMenuItem("Add Subject");
		mntmAddSubject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addSubjectPanel = new Add_Subject().panel_AddSubject;
				frame.getContentPane().add(addSubjectPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Subjects");
			}
		});
		mnNewMenu_3.add(mntmAddSubject);
		
		//menu item 'Manage Subject' for the menu Subject
		mntmManageSubject = new JMenuItem("Manage Subject");
		mntmManageSubject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageSubjectPanel = new Manage_Subject().panel_manageSubject;
				frame.getContentPane().add(manageSubjectPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Subjects");
			}
		});
		mnNewMenu_3.add(mntmManageSubject);
		
		
		//menu for Working Days and Hours
		JMenu mnNewMenu_2 = new JMenu("Working Days & Hours");
		mnNewMenu_2.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_2);
		
		
		//Add days and hours
		JMenuItem Add_WorkingDays_Item = new JMenuItem("Add Days & Hours");
		Add_WorkingDays_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				addWorkingDaysPanel = new Add_WorkingDays().Add_WorkingDays_Panel;
				frame.getContentPane().add(addWorkingDaysPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Working Days");
			}
		});
		mnNewMenu_2.add(Add_WorkingDays_Item);
		
		//manage working days and hours
		JMenuItem Manage_WorkingDays_Item = new JMenuItem("Manage Days & Hours");
		Manage_WorkingDays_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageDaysPanel = new Manage_WorkingDays().Manage_WorkingDays_Panel;
				frame.getContentPane().add(manageDaysPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Working Days");
			}
		});
		mnNewMenu_2.add(Manage_WorkingDays_Item);
		
		
		//create timeslots for timetable
		JMenuItem TimeSlot_Item = new JMenuItem("Create Time Slots");
		TimeSlot_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				timeslotPanel = new Add_Manage_TimeSlot().AddnManage_Timeslot;
				frame.getContentPane().add(timeslotPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add TimeSlot");
			}
		});
		mnNewMenu_2.add(TimeSlot_Item);
		
		
		//add not available time for sessions
		JMenuItem NotAvailableT_Item = new JMenuItem("Add Not Available Time for Sessions");
		NotAvailableT_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addNotAvailableTimePanel = new Not_AvailableTime().ad;
				frame.getContentPane().add(addNotAvailableTimePanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Not Available Time for Sessions");
			}
		});
		mnNewMenu_2.add(NotAvailableT_Item);
		
		
		//manage not available time for sessions
		JMenuItem NotAvailableTime_Item = new JMenuItem("Manage Not Available Time for Sessions");
		NotAvailableTime_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageNotAvailableTimePanel = new Manage_NotAvailable_Time().Manage_NotAvailableTime_Panel;
				frame.getContentPane().add(manageNotAvailableTimePanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Not Available Time for Sessions");
			}
		});
		mnNewMenu_2.add(NotAvailableTime_Item);
		
		
		//add not available rooms
		JMenuItem NotAvailableRoom_Item = new JMenuItem("Add Not Available Rooms");
		NotAvailableRoom_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addNotAvailableLocation = new Not_Available_Location().Add_NotAvailable_Location_Panel;
				frame.getContentPane().add(addNotAvailableLocation, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Not Available Rooms");
			}
		});
		mnNewMenu_2.add(NotAvailableRoom_Item);
		
		JMenuItem mntmAddTimeForSessions = new JMenuItem("Add Session Time Slots ");
		mntmAddTimeForSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				tPanel = new Manage_Timeslot_For_Session().mng;
				frame.getContentPane().add(tPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Not Available Time for Sessions");
			}
		});
		mnNewMenu_2.add(mntmAddTimeForSessions);
		
		
		JMenu mnLocation = new JMenu("Location");
		mnLocation.setForeground(Color.WHITE);
		menuBar.add(mnLocation);
		
		JMenuItem mntmAddLocation = new JMenuItem("Add Location");
		mntmAddLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addLocationPanel = new Add_Location().addLocPanel;
				frame.getContentPane().add(addLocationPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Location");
			}
		});
		mnLocation.add(mntmAddLocation);
		
		JMenuItem mntmManageLocation = new JMenuItem("Manage Location");
		mntmManageLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageLocationPanel = new Manage_Location().manLocation_panel;
				frame.getContentPane().add(manageLocationPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Location");
			}
		});
		mnLocation.add(mntmManageLocation);
		
		JMenu mnStatistics = new JMenu("Statistics");
		mnStatistics.setForeground(Color.WHITE);
		menuBar.add(mnStatistics);
		
		JMenuItem mntmShowStat = new JMenuItem("Show Statistics");
		mntmShowStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				displayStatPanel = new Display_Statistics().statPanel;
				frame.getContentPane().add(displayStatPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Show Statistics");
			}
		});
		mnStatistics.add(mntmShowStat);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		//menu for Session
		JMenu mnSessions = new JMenu("Session");
		mnSessions.setForeground(Color.WHITE);
		mnSessions.setBackground(SystemColor.activeCaption);
		menuBar.add(mnSessions);
		
		//menu item 'Add Session' for the menu Session
		JMenuItem mntmAddSesssion = new JMenuItem("Add Session");
		mntmAddSesssion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addSession = new Add_Session().panel_addSession;
				frame.getContentPane().add(addSession, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Add Session");
			}
		});
		mnSessions.add(mntmAddSesssion);
		
		//menu item 'Manage Session' for the menu Session
		JMenuItem mntmManageSession = new JMenuItem("Manage Session");
		mntmManageSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				manageSession = new Manage_Session().panel_ManageSession;
				frame.getContentPane().add(manageSession, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("Manage Session");
			}
		});
		mnSessions.add(mntmManageSession);

	
	JMenu sessionRoom = new JMenu("Session Rooms");
	sessionRoom.setForeground(Color.WHITE);
	menuBar.add(sessionRoom);
	
	JMenuItem manageSessionRom = new JMenuItem("Manage Session Room");
	manageSessionRom.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.getContentPane().removeAll();
			sessionRoomPanel = new Manage_Session_Rooms().panel;
			frame.getContentPane().add(sessionRoomPanel, "name_573107097077500");
			frame.getContentPane().repaint();
			frame.getContentPane().revalidate();
			frame.setTitle("Manage Session Room");
		}
	});
	sessionRoom.add(manageSessionRom);
	
	JMenuItem addLocationS2 = new JMenuItem("View Session Rooms");
	addLocationS2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.getContentPane().removeAll();
			viewSessionRoomPanel = new Add_Location_S2().panel_S2;
			frame.getContentPane().add(viewSessionRoomPanel, "name_573107097077500");
			frame.getContentPane().repaint();
			frame.getContentPane().revalidate();
			frame.setTitle("View Session Rooms");
		}
	});
	sessionRoom.add(addLocationS2);

		
		JMenu sessionsAnsNotAv = new JMenu("Sessions|Not Available Times");
		sessionsAnsNotAv.setForeground(Color.WHITE);
		sessionsAnsNotAv.setBackground(SystemColor.activeCaption);
		menuBar.add(sessionsAnsNotAv);
		
		JMenuItem viewSessions = new JMenuItem("View Sesssions");
		viewSessions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				sessionsListPanel = new Sessions_List().sessions_panel;
				frame.getContentPane().add(sessionsListPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("View Sessions");
			}
		});
		sessionsAnsNotAv.add(viewSessions);
		
		JMenuItem sessionCate = new JMenuItem("Sessions | Not Available Times");
		sessionCate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				categoriesPanel = new Session_Categories().Categoriespanel;
				frame.getContentPane().add(categoriesPanel, "name_573107097077500");
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				frame.setTitle("View Sessions");
			}
		});
		sessionsAnsNotAv.add(sessionCate);
	}


}


