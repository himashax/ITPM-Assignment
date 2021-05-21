package userInterfaces;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.SessionDAOImpl;

import models.Session;


public class Manage_Session implements ActionListener{

	private JFrame frmManageSession;
	private JTable table_Session;
	private JTextField textField_mNoOfStud;
	private JTextField textField_mDuration;
	public JPanel panel_ManageSession;
	private JButton btnRefresh,btnUpdate,btnViewSession,btnAddNewSession,btnSearch,btnDelSession;
	JComboBox comboBoxSearchYear,comboBoxSearchLec,comboBoxLec1,comboBoxLec2,comboBoxSelectTag,comboBox_mSelectGroup,comboBox_mSelectSub,comboBox_mDay;
	private int idValue,countRows;
	private DefaultTableModel modal;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Session window = new Manage_Session();
					window.frmManageSession.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manage_Session() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManageSession = new JFrame();
		frmManageSession.setTitle("Manage Session");
		frmManageSession.setBounds(100, 100, 899, 496);
		frmManageSession.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageSession.getContentPane().setLayout(null);
		
		panel_ManageSession = new JPanel();
		panel_ManageSession.setBackground(Color.WHITE);
		panel_ManageSession.setBounds(0, 0, 883, 457);
		
		panel_ManageSession.setLayout(null);
		
		SessionDAOImpl sessionDAO = new SessionDAOImpl();
		
		JPanel panel_Search = new JPanel();
		panel_Search.setBackground(Color.WHITE);
		panel_Search.setBounds(126, 25, 499, 40);
		panel_ManageSession.add(panel_Search);
		panel_Search.setLayout(null);

		//academic year and semesters
		String year_semValues[]={"(Y1.S1) Year 1 Semester 1","(Y1.S2) Year 1 Semester 2","(Y2.S1) Year 2 Semester 1","(Y2.S2) Year 2 Semester 2","(Y3.S1) Year 3 Semester 1",
				 "(Y3.S2) Year 3 Semester 2", "(Y4.S1) Year 4 Semester 1", "(Y4.S2) Year 4 Semester 2"}; 
		comboBoxSearchYear = new JComboBox(year_semValues);
		comboBoxSearchYear.setEditable(true);
		comboBoxSearchYear.setBounds(10, 11, 179, 20);
		
		//auto complete function in the year combo box
		AutoCompleteDecorator.decorate(comboBoxSearchYear);
		panel_Search.add(comboBoxSearchYear);
		
		//get all the lecturer list to a string array list
		ArrayList<String> lecturerList = sessionDAO.getLecturers();
		
		//set the array list as a array in the combobox
		comboBoxSearchLec = new JComboBox(lecturerList.toArray());
		comboBoxSearchLec.setBounds(199, 11, 191, 20);
		
		//auto complete function in the lecturer combo box
		AutoCompleteDecorator.decorate(comboBoxSearchLec);
		panel_Search.add(comboBoxSearchLec);
		
		//search button
		btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSearch.setBackground(SystemColor.activeCaption);
		btnSearch.setBounds(400, 10, 89, 23);
		btnSearch.addActionListener(this);
		panel_Search.add(btnSearch);
		
		//button add new session
		btnAddNewSession = new JButton("ADD SESSION");
		btnAddNewSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewSession.setBackground(new Color(189, 183, 107));
		btnAddNewSession.setBounds(736, 11, 117, 33);
		btnAddNewSession.addActionListener(this);
		panel_ManageSession.add(btnAddNewSession);
		
		//JTable to retrieve all the session records		
		modal = new DefaultTableModel(new String[] {"ID","Lecturer 1","Lecturer 2","Tag","Group ID","Subject","No.Students","Day","Duration"},0);
		table_Session = new JTable();
		table_Session.setBounds(20, 195, 701, -102);
		JTableHeader header = table_Session.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table_Session.setModel(modal);
		
		JScrollPane scrollPane = new JScrollPane(table_Session);
		scrollPane.setBounds(20, 76, 730, 134);
	
		//call the method to retrieve the session records to the table
		sessionGroupTable();
		panel_ManageSession.add(scrollPane);
		
		//click the record in the table
       table_Session.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						int viewRow = table_Session.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                        	System.out.println("");
                        } else {
                            int modelRow = 
                                table_Session.convertRowIndexToModel(viewRow);
                            System.out.println(
                                String.format("Selected Row in view: %d. " +
                                    "Selected Row in model: %d.", 
                                    viewRow, modelRow));
                            
                            SessionDAOImpl sessionDao = new SessionDAOImpl();
                          //get the modal row id of the selected row
            				idValue = Integer.parseInt(modal.getValueAt(modelRow, 0).toString());
            				
            				//pass the id and get the values of the particular session
            				Session session = sessionDao.getSessionById(idValue);
            				System.out.println(idValue);
            				
            				//set the values from retrieved record data
            				comboBoxLec1.setSelectedItem(session.getFirstLecturer());
            				comboBoxLec2.setSelectedItem(session.getSecLecturer());
            				comboBoxSelectTag.setSelectedItem(session.getTag());
            				comboBox_mSelectGroup.setSelectedItem(session.getGroupId());
            				comboBox_mSelectSub.setSelectedItem(session.getSubject());
            				textField_mNoOfStud.setText(Integer.toString(session.getNoOfStudents()));
            				comboBox_mDay.setSelectedItem(session.getDay());
            				textField_mDuration.setText(Integer.toString(session.getDuration()));
                        }
						
					}
                }
        );
		
       //delete button
		btnDelSession = new JButton("DELETE");
		btnDelSession.setBackground(SystemColor.activeCaption);
		btnDelSession.setBounds(764, 143, 89, 23);
		btnDelSession.addActionListener(this);
		panel_ManageSession.add(btnDelSession);
		
		//view session button
		btnViewSession = new JButton("VIEW");
		btnViewSession.setBackground(SystemColor.activeCaption);
		btnViewSession.setBounds(764, 95, 89, 23);
		btnViewSession.addActionListener(this);
		panel_ManageSession.add(btnViewSession);
		
		JPanel panel_LecTags = new JPanel();
		panel_LecTags.setBounds(20, 238, 406, 169);
		panel_ManageSession.add(panel_LecTags);
		panel_LecTags.setLayout(null);
		
		//get list of lecturers
		ArrayList<String> lecturers = sessionDAO.getLecturers();
		
		//set the values as an array in the combo box of lecturer 1
		comboBoxLec1 = new JComboBox(lecturers.toArray());
		comboBoxLec1.setBounds(177, 28, 197, 20);
		panel_LecTags.add(comboBoxLec1);
		
		//set the values as an array in the combo box of lecturer 2
		comboBoxLec2 = new JComboBox(lecturers.toArray());
		comboBoxLec2.setBounds(177, 80, 197, 20);
		panel_LecTags.add(comboBoxLec2);
		
		//set default values for lecturer 1 and 2
		comboBoxLec1.setSelectedIndex(0);
		comboBoxLec2.setSelectedIndex(1);
	
		//tag array
		String tag[] = {"Lecture", "Tutorial", "Lab|Practical", "Evaluation"};
		comboBoxSelectTag = new JComboBox(tag);
		comboBoxSelectTag.setBounds(177, 126, 197, 20);
		panel_LecTags.add(comboBoxSelectTag);
		
		JLabel lbl_mSelectLec1 = new JLabel("SELECT LECTURER 1");
		lbl_mSelectLec1.setBounds(10, 31, 122, 14);
		panel_LecTags.add(lbl_mSelectLec1);
		
		JLabel lbl_mSelectLec2 = new JLabel("SELECT LECTURER 2");
		lbl_mSelectLec2.setBounds(10, 83, 122, 14);
		panel_LecTags.add(lbl_mSelectLec2);
		
		JLabel lblSelectTag = new JLabel("SELECT TAG");
		lblSelectTag.setBounds(10, 129, 79, 14);
		panel_LecTags.add(lblSelectTag);
		
		JPanel panel_GroupStudent = new JPanel();
		panel_GroupStudent.setBounds(447, 238, 406, 169);
		panel_ManageSession.add(panel_GroupStudent);
		panel_GroupStudent.setLayout(null);
		
		JLabel lbl_mSelectGroup = new JLabel("SELECT GROUP");
		lbl_mSelectGroup.setBounds(10, 21, 122, 14);
		panel_GroupStudent.add(lbl_mSelectGroup);
		
		JLabel lbl_mSelectLec1_2 = new JLabel("SELECT SUBJECT");
		lbl_mSelectLec1_2.setBounds(10, 59, 122, 14);
		panel_GroupStudent.add(lbl_mSelectLec1_2);
		
		JLabel lbl_mNoOfStud = new JLabel("NO.OF STUDENTS");
		lbl_mNoOfStud.setBounds(10, 93, 122, 14);
		panel_GroupStudent.add(lbl_mNoOfStud);
		
		JLabel lbl_mSelectLec1_4 = new JLabel("DURATION");
		lbl_mSelectLec1_4.setBounds(10, 131, 70, 14);
		panel_GroupStudent.add(lbl_mSelectLec1_4);
		
		JLabel lbl_mDay = new JLabel("DAY");
		lbl_mDay.setBounds(213, 131, 38, 14);
		panel_GroupStudent.add(lbl_mDay);
		
		// get all the group ids
		ArrayList<String> groupIdList = sessionDAO.getGroupIdList();
		String[] groupArr = new String[groupIdList.size()];
		
		//get the arraylist data to an array
		for(int i=0; i<groupIdList.size(); i++) {
			groupArr[i] = groupIdList.get(i);
		}

		// get all the sub group ids
		ArrayList<String> subGroupIdList = sessionDAO.getSubGroupIdList();
		String[] subGroupArr = new String[subGroupIdList.size()];
		
		//get the array list data to an array
		for(int i=0; i<subGroupIdList.size(); i++) {
			subGroupArr[i] = subGroupIdList.get(i);
		}
		
		comboBox_mSelectGroup = new JComboBox();
		comboBox_mSelectGroup.setBounds(165, 18, 197, 20);
		panel_GroupStudent.add(comboBox_mSelectGroup);
		
		
		// according to the value of the tag changing group id
		comboBoxSelectTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_mSelectGroup.removeAllItems();
				
				//if lab is selected then assign subgroup id list for the combo box
				if(comboBoxSelectTag.getSelectedItem().toString().equals("Lab|Practical")) {
					
					for(String subGroups : subGroupArr) {
						comboBox_mSelectGroup.addItem(subGroups);
					}
				//for lecture, tutorial and evaluation is selected then assign group id list for the combo box
				}else {
					for(String groups : groupArr) {
						comboBox_mSelectGroup.addItem(groups);
					}
				}
				
			}
		});
		
		//get all the subjects from the database
		ArrayList<String> subjectInfo = sessionDAO.getSubjectInfoList();
		
		//set the list of subjects by converting to an array
		comboBox_mSelectSub = new JComboBox(subjectInfo.toArray());
		comboBox_mSelectSub.setBounds(165, 56, 197, 20);
		panel_GroupStudent.add(comboBox_mSelectSub);
		
		textField_mNoOfStud = new JTextField();
		textField_mNoOfStud.setBounds(165, 90, 86, 20);
		panel_GroupStudent.add(textField_mNoOfStud);
		textField_mNoOfStud.setColumns(10);
		
		textField_mDuration = new JTextField();
		textField_mDuration.setColumns(10);
		textField_mDuration.setBounds(85, 128, 86, 20);
		panel_GroupStudent.add(textField_mDuration);
		
		JLabel lbl_mHours = new JLabel("hrs");
		lbl_mHours.setBounds(175, 131, 32, 14);
		panel_GroupStudent.add(lbl_mHours);
		
		String[] days = { "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		comboBox_mDay = new JComboBox(days);
		comboBox_mDay.setBounds(253, 128, 143, 20);
		panel_GroupStudent.add(comboBox_mDay);
		
		JLabel lblSelectLecturer = new JLabel("SELECT LECTURERS & TAGS");
		lblSelectLecturer.setBounds(20, 221, 166, 14);
		panel_ManageSession.add(lblSelectLecturer);
		
		//refresh button
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setBackground(SystemColor.activeCaption);
		btnRefresh.setBounds(549, 418, 89, 23);
		btnRefresh.addActionListener(this);
		panel_ManageSession.add(btnRefresh);
		
		//update button
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(SystemColor.activeCaption);
		btnUpdate.setBounds(672, 418, 89, 23);
		btnUpdate.addActionListener(this);
		panel_ManageSession.add(btnUpdate);
		
		JLabel lblSelectGroupSub = new JLabel("SELECT GROUP & SUBJECT");
		lblSelectGroupSub.setBounds(447, 221, 166, 14);
		panel_ManageSession.add(lblSelectGroupSub);
		
		frmManageSession.getContentPane().add(panel_ManageSession);
	}
	
	//the function to clear the fields and set the default values to the combo boxes
	public void resetField() {
		comboBoxLec1.setSelectedIndex(0);
		comboBoxLec2.setSelectedIndex(1);
		comboBoxSelectTag.setSelectedIndex(0);
		comboBox_mSelectGroup.setSelectedIndex(0);
		comboBox_mSelectSub.setSelectedIndex(0);
		comboBox_mDay.setSelectedIndex(0);
		textField_mNoOfStud.setText(null);
		textField_mDuration.setText(null);
	}
	
	//the method that retrieve all the session records to the table
	public void sessionGroupTable(){
		SessionDAOImpl sessionDAOImpl = new SessionDAOImpl();
		ArrayList<Session> session_list = sessionDAOImpl.getSessionList();
		
		DefaultTableModel modal = (DefaultTableModel) table_Session.getModel();
		
		Object[] row = new Object[9];
		
		for(int i=0; i< session_list.size(); i++) {
			
			row[0] = session_list.get(i).getId();
			row[1] = session_list.get(i).getFirstLecturer();
			row[2] = session_list.get(i).getSecLecturer();
			row[3] = session_list.get(i).getTag();
			row[4] = session_list.get(i).getGroupId();
			row[5] = session_list.get(i).getSubject();
			row[6] = session_list.get(i).getNoOfStudents();
			row[7] = session_list.get(i).getDay();
			row[8] = session_list.get(i).getDuration();
			
			modal.addRow(row);
		}
		
	}

	//filter session records in the table
	public void filteredListTable(){
		SessionDAOImpl sessionDAOImpl = new SessionDAOImpl();
		
		//get the selected values of the year and lecturer combo box and assign them to string
		String yearValue = comboBoxSearchYear.getSelectedItem().toString();
		String lecturer = comboBoxSearchLec.getSelectedItem().toString();
		
		//get the particular session records by  passing the academic year and the lecturer
		ArrayList<Session> session_list = sessionDAOImpl.getAcademicYearAndLecturer(yearValue, lecturer);
		
		System.out.println(session_list.toArray());
		DefaultTableModel modal = (DefaultTableModel) table_Session.getModel();
		
		//sorted table
		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modal);
		 table_Session.setRowSorter(sorter);
		 
		 List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		 //sort rows according to ascending order of session id
		 sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		 //sort row according to ascending order of lecturer name
		 sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		 sorter.setSortKeys(sortKeys);
		
        if(lecturer.length() == 0) {
            sorter.setRowFilter(null);
         } else {
            try {
            	//sort the rows to the searched lecturer name
               sorter.setRowFilter(RowFilter.regexFilter(lecturer));
            } catch(PatternSyntaxException pse) {
                  System.out.println("Invalid pattern");
            }
          }
        //get the filtered row count
        countRows = table_Session.getRowCount();
        System.out.println(countRows + "filter");
        //when a record in the sorted list is clicked the modal id and the view id are different
        table_Session.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					int viewRow = table_Session.getSelectedRow();
                    if (viewRow < 0) {
                        //Selection got filtered away.
                    	System.out.println("");
                    } else {
                        int modelRow = 
                            table_Session.convertRowIndexToModel(viewRow);
                        System.out.println(
                            String.format("Selected Row in view: %d. " +
                                "Selected Row in model: %d.", 
                                viewRow, modelRow));
                        
                        SessionDAOImpl sessionDao = new SessionDAOImpl();
                        //capture the modal id of the selected row of the sorted record list
        				idValue = Integer.parseInt(modal.getValueAt(modelRow, 0).toString());
        				
        				//get the particular record data by passing the id
        				Session session = sessionDao.getSessionById(idValue);
        				System.out.println(idValue);
        				
        				//set the data for the particular fields and combo boxes
        				comboBoxLec1.setSelectedItem(session.getFirstLecturer());
        				comboBoxLec2.setSelectedItem(session.getSecLecturer());
        				comboBoxSelectTag.setSelectedItem(session.getTag());
        				comboBox_mSelectGroup.setSelectedItem(session.getGroupId());
        				comboBox_mSelectSub.setSelectedItem(session.getSubject());
        				textField_mNoOfStud.setText(Integer.toString(session.getNoOfStudents()));
        				comboBox_mDay.setSelectedItem(session.getDay());
        				textField_mDuration.setText(Integer.toString(session.getDuration()));
                    }
					
				}
            }
        );
		
	}
	

	public void tableClickEvent() {
	      table_Session.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int viewRow = table_Session.getSelectedRow();
	                    if (viewRow < 0) {
	                        //Selection got filtered away.
	                    	System.out.println("");
	                    } else {
	                        int modelRow = 
	                            table_Session.convertRowIndexToModel(viewRow);
	                        System.out.println(
	                            String.format("Selected Row in view: %d. " +
	                                "Selected Row in model: %d.", 
	                                viewRow, modelRow));
	                        
	                        SessionDAOImpl sessionDao = new SessionDAOImpl();
	                        //capture the modal id of the selected row of the sorted record list
	        				idValue = Integer.parseInt(modal.getValueAt(modelRow, 0).toString());
	        				
	        				//get the particular record data by passing the id
	        				Session session = sessionDao.getSessionById(idValue);
	        				System.out.println(idValue);
	        				
	        				//set the data for the particular fields and combo boxes
	        				comboBoxLec1.setSelectedItem(session.getFirstLecturer());
	        				comboBoxLec2.setSelectedItem(session.getSecLecturer());
	        				comboBoxSelectTag.setSelectedItem(session.getTag());
	        				comboBox_mSelectGroup.setSelectedItem(session.getGroupId());
	        				comboBox_mSelectSub.setSelectedItem(session.getSubject());
	        				textField_mNoOfStud.setText(Integer.toString(session.getNoOfStudents()));
	        				comboBox_mDay.setSelectedItem(session.getDay());
	        				textField_mDuration.setText(Integer.toString(session.getDuration()));
	                    }
						
					}
	            }
	        );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//get the object which is clicked
		Object obj = e.getSource();
		
		//update button is clicked
		if(obj==btnUpdate) {
			//check whether a row is selected or not
			if (idValue == 0) {
				JOptionPane.showMessageDialog(null, "Please select a record to update");
			}else if (textField_mNoOfStud.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the number of students", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(textField_mDuration.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please provide the duration", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(comboBoxLec1.getSelectedItem().equals(comboBoxLec2.getSelectedItem().toString()) || comboBoxLec2.getSelectedItem().equals(comboBoxLec1.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(null, "Please select another lecturer", "Alert",JOptionPane.WARNING_MESSAGE);
			}else if(Integer.parseInt(textField_mDuration.getText().toString()) < 0 || Integer.parseInt(textField_mNoOfStud.getText().toString()) < 0) {
				JOptionPane.showMessageDialog(null, "Add a positive integer value", "Alert", JOptionPane.WARNING_MESSAGE);
			}else {

				Session sessions = new Session();
				System.out.println(idValue + " update");
				//set the values of the selected record to a session object
				sessions.setId(idValue);
				sessions.setFirstLecturer(comboBoxLec1.getSelectedItem().toString());
				sessions.setSecLecturer(comboBoxLec2.getSelectedItem().toString());
				sessions.setTag(comboBoxSelectTag.getSelectedItem().toString());
				sessions.setGroupId(comboBox_mSelectGroup.getSelectedItem().toString());
				sessions.setSubject(comboBox_mSelectSub.getSelectedItem().toString());
				sessions.setNoOfStudents(Integer.parseInt(textField_mNoOfStud.getText()));
				sessions.setDay(comboBox_mDay.getSelectedItem().toString());
				sessions.setDuration(Integer.parseInt(textField_mDuration.getText()));
				
				//display the confirm message to update
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update session " + sessions.getId() + " ?", "Confirm Update",
						JOptionPane.YES_NO_OPTION);
				
				//if YES option is selected update the session
				if(confirm == JOptionPane.YES_OPTION) {
					SessionDAOImpl sessionDao = new SessionDAOImpl();
					
					//check whether same values are there for two session id
					if (sessionDao.getDuplicates(sessions).size() > 0) {
						JOptionPane.showMessageDialog(null, "All ready uptodate!");
					//check whether same group, same subject has two sessions for the same tag
					} else {
						//pass the session object to update
						sessionDao.updateSession(sessions);
						
						//display the successfully saved message
						JOptionPane.showMessageDialog(null, "Updated successfully");
						
						//display the table with latest data records
						DefaultTableModel modal = (DefaultTableModel) table_Session.getModel();
						modal.setRowCount(0);
						sessionGroupTable();
					}
				}
				//clear the fields once updated
				resetField();
			}
		}
		
		//if delete button is clicked
		if(obj==btnDelSession) {
			//check whether a row is selected or not
			if (idValue == 0) {
				JOptionPane.showMessageDialog(null, "Please select a record to delete");
			}else {
				SessionDAOImpl sessionDao = new SessionDAOImpl();
				
				//display the confirm message to delete the selected record
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete session " + sessionDao.getSessionById(idValue).getId() + " ?", "Confirm Update",
						JOptionPane.YES_NO_OPTION);
				
				//if YES option is selected delete the session
				if(confirm == JOptionPane.YES_OPTION) {
					
					//pass the selected row id to delete the session
					sessionDao.deleteSession(idValue);
					
					//display the table with latest records
					DefaultTableModel modal = (DefaultTableModel) table_Session.getModel();
					modal.setRowCount(0);
					sessionGroupTable();
				}
			}
			
			//clear the fields
			resetField();
		}
		
		//view button is clicked
		if(obj == btnViewSession) {
			//check whether a row is selected or not
			if (idValue == 0) {
				JOptionPane.showMessageDialog(null, "Please select a record to view the session");
			}else {
				SessionDAOImpl sessionDao = new SessionDAOImpl();
				
				sessionDao.getCount();
				System.out.println("ahd");
				//show a message dialog box to display the data of the selected row
				JOptionPane.showMessageDialog(null, sessionDao.getSessionById(idValue).getFirstLecturer() +" - "+ sessionDao.getSessionById(idValue).getSubject()+" - "+ 
				sessionDao.getSessionById(idValue).getTag() + " - "+ sessionDao.getSessionById(idValue).getGroupId() + " - " + 
						sessionDao.getSessionById(idValue).getNoOfStudents() + " - " + sessionDao.getSessionById(idValue).getDuration()
						);
			}
		}
		
		//when the search button is clicked invoke the filterListTable()
		if(obj==btnSearch) {
			filteredListTable();
		}
		
		//refresh button
		if(obj == btnRefresh) {
			SessionDAOImpl sessionDao = new SessionDAOImpl();
			
//	        for (int row = 0; row < table_Session.getRowCount(); row++) {
//	        	modal.removeRow(row);
//	        }
	        
//			if(countRows != sessionDao.getCount()) {
//				modal.getDataVector().removeAllElements();
//				modal.fireTableDataChanged();
//				sessionGroupTable();
//				System.out.println("noo");
//			}
			//resetField();
		}
		
		//add new session button
		if(obj == btnAddNewSession) {
			Add_Session window = new Add_Session();
			window.frmAddSession.setVisible(true);
			
			//display the table with latest records
			DefaultTableModel modal = (DefaultTableModel) table_Session.getModel();
			modal.setRowCount(0);
			sessionGroupTable();
		}
	}
}