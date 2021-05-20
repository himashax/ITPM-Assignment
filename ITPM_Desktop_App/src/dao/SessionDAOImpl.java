package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Lecturer;
import models.Session;

public class SessionDAOImpl {
	
	DBConnection db = new DBConnection();
	
<<<<<<< HEAD
=======
	//insert session method
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public String insertSession(Session s) {
		
		String result = "";
		Connection connection = db.connect();
		
		String insertSession = "insert into session values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(insertSession);
				ps.setInt(1, 0);
				ps.setString(2, s.getFirstLecturer());
				ps.setString(3, s.getSecLecturer());
				ps.setString(4, s.getTag());
				ps.setString(5, s.getGroupId());
				ps.setString(6, s.getSubject());
				ps.setInt(7, s.getNoOfStudents());
				ps.setString(8, s.getDay());
				ps.setInt(9, s.getDuration());
				
				ps.execute();
				
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;
		
	}
	
<<<<<<< HEAD
=======
	//retrieve all sessions
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public ArrayList<Session> getSessionList(){
		
		ArrayList<Session> session = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String getSessionList = "select * from session";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSessionList);
			
			while(rs.next()) {
				Session sessionObj = new Session();
				
				sessionObj.setId(rs.getInt(1));
				sessionObj.setFirstLecturer(rs.getString(2));
				sessionObj.setSecLecturer(rs.getString(3));
				sessionObj.setTag(rs.getString(4));
				sessionObj.setGroupId(rs.getString(5));
				sessionObj.setSubject(rs.getString(6));
				sessionObj.setNoOfStudents(rs.getInt(7));
				sessionObj.setDay(rs.getString(8));
				sessionObj.setDuration(rs.getInt(9));
				
				session.add(sessionObj);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return session;		
	}
	
<<<<<<< HEAD
	
=======
	//retrieve a particular session by  id
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public Session getSessionById(int id) {
		
		Session sess = new Session();
		Connection con = db.connect();
		
		String getSessionById = "select * from session where id = '"+id+"'";
		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(getSessionById);
			
			while(rs.next()) {
				sess.setId(rs.getInt(1));
				sess.setFirstLecturer(rs.getString(2));
				sess.setSecLecturer(rs.getString(3));
				sess.setTag(rs.getString(4));
				sess.setGroupId(rs.getString(5));
				sess.setSubject(rs.getString(6));
				sess.setNoOfStudents(rs.getInt(7));
				sess.setDay(rs.getString(8));
				sess.setDuration(rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sess;
	}
	
<<<<<<< HEAD
	
=======
	//update a session
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public void updateSession(Session s) {
				
		try {
			Connection connection = db.connect();
		
			String updateSession = "update session set first_lecturer = '"+s.getFirstLecturer()+"', sec_lecturer = '"+s.getSecLecturer()+"',"
					+ " tag = '"+s.getTag()+"', group_id = '"+s.getGroupId()+"', subject = '"+s.getSubject()+"', "
							+ "no_of_students = '"+s.getNoOfStudents()+"', day = '"+s.getDay()+"',"
									+ " duration = '"+s.getDuration()+"' where id = '"+s.getId()+"' ";
			
			PreparedStatement ps = connection.prepareStatement(updateSession);
			ps.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
<<<<<<< HEAD
=======
	//delete a session
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public void deleteSession(int id) {
		
		Connection con = db.connect();

		try {
			
			String delSession = "delete from session where id = '"+id+"'";

			PreparedStatement ps = con.prepareStatement(delSession);
			ps.execute();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

<<<<<<< HEAD
=======
	//get the list of lecturers
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public ArrayList<String> getLecturers() {
		
		ArrayList<String> getLecturers = new ArrayList<>();

		try {
			Connection connection = db.connect();

			String getAllecturers = "select * from lecturer";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getAllecturers);
			
			while(rs.next()) {
				getLecturers.add(rs.getString(2));
			}
<<<<<<< HEAD
			
			System.out.println(getLecturers);
=======
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getLecturers;
	}
	
<<<<<<< HEAD
=======
	//get all the group id as a String array list
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public ArrayList<String> getGroupIdList(){
		ArrayList<String> groupIdList = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String getGroups = "select * from student_group";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getGroups);
			
			while(rs.next()) {
				groupIdList.add(rs.getString(6));
			}
<<<<<<< HEAD
			System.out.println(groupIdList);
=======
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupIdList;
		
	}
	
<<<<<<< HEAD
=======
	//get all the sub group id as a String array list 
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public ArrayList<String> getSubGroupIdList(){
		ArrayList<String> subGroupIdList = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String getSubGroups = "select * from student_group";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSubGroups);
			
			while(rs.next()) {
				subGroupIdList.add(rs.getString(7));
			}
<<<<<<< HEAD
			System.out.println(subGroupIdList);
=======
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subGroupIdList;
		
	}
	
<<<<<<< HEAD
=======
	//get all the subject code and related subject name as a string array list
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	public ArrayList<String> getSubjectInfoList(){
		ArrayList<String> subjectInfoList = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String getSubjectInfo = "select * from subject";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSubjectInfo);
			
			while(rs.next()) {
				subjectInfoList.add(rs.getString(4) + " - " + rs.getString(5));
			}
<<<<<<< HEAD
			System.out.println(subjectInfoList);
=======
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjectInfoList;
		
	}
	
<<<<<<< HEAD
	public ArrayList<Session> getAcademicYearAndLecturer(String year, String lecturer){
		ArrayList<Session> filteredList = new ArrayList<>();
		

		Character c = year.charAt(2);
		System.out.println(c);
		
		
		
=======
	//get the sessions for a particular year and a lecturer
	public ArrayList<Session> getAcademicYearAndLecturer(String year, String lecturer){
		ArrayList<Session> filteredList = new ArrayList<>();
		
		Character c = year.charAt(2);
		System.out.println(c);
				
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
		try {
			Connection connection = db.connect();
			String getFilteredResult = "select * from session where group_id LIKE CONCAT ('(Y','" + c +"','%') and first_lecturer = '"+ lecturer +"' or sec_lecturer = '"+ lecturer+"'";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getFilteredResult);
			
			while(rs.next()) {
				
				Session sessionObj = new Session();
				
				sessionObj.setId(rs.getInt(1));
				sessionObj.setFirstLecturer(rs.getString(2));
				sessionObj.setSecLecturer(rs.getString(3));
				sessionObj.setTag(rs.getString(4));
				sessionObj.setGroupId(rs.getString(5));
				sessionObj.setSubject(rs.getString(6));
				sessionObj.setNoOfStudents(rs.getInt(7));
				sessionObj.setDay(rs.getString(8));
				sessionObj.setDuration(rs.getInt(9));
				
				filteredList.add(sessionObj);
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(filteredList);
		return filteredList;
		
	}
	
<<<<<<< HEAD
=======
	//get duplicate sessions
	public ArrayList<Session> getDuplicates(Session session){
		ArrayList<Session> filteredList = new ArrayList<>();
			
		try {
			Connection connection = db.connect();
			String getFilteredResult = "select * from session where first_lecturer = '"+session.getFirstLecturer()+"' AND sec_lecturer = '"+session.getSecLecturer()+"' AND"
					+ " tag = '"+session.getTag()+"' AND group_id = '"+session.getGroupId()+"' AND subject = '"+session.getSubject()+"' AND "
							+ "no_of_students = '"+session.getNoOfStudents()+"' AND day = '"+session.getDay()+"' AND"
									+ " duration = '"+session.getDuration()+"'";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getFilteredResult);
			
			while(rs.next()) {
				
				Session sessionObj = new Session();
				
				sessionObj.setId(rs.getInt(1));
				sessionObj.setFirstLecturer(rs.getString(2));
				sessionObj.setSecLecturer(rs.getString(3));
				sessionObj.setTag(rs.getString(4));
				sessionObj.setGroupId(rs.getString(5));
				sessionObj.setSubject(rs.getString(6));
				sessionObj.setNoOfStudents(rs.getInt(7));
				sessionObj.setDay(rs.getString(8));
				sessionObj.setDuration(rs.getInt(9));
				
				filteredList.add(sessionObj);
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(filteredList);
		return filteredList;
		
	}
	
	//get duplicate sessions with same tag,same group and same subject
	public ArrayList<Session> getDuplicateSessions(Session session){
		ArrayList<Session> filteredList = new ArrayList<>();
			
		try {
			Connection connection = db.connect();
			String getFilteredResult = "select * from session where tag = '"+session.getTag()+"' AND group_id = '"+session.getGroupId()+"' AND subject = '"+session.getSubject()+"'";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getFilteredResult);
			
			while(rs.next()) {
				
				Session sessionObj = new Session();
				
				sessionObj.setId(rs.getInt(1));
				sessionObj.setFirstLecturer(rs.getString(2));
				sessionObj.setSecLecturer(rs.getString(3));
				sessionObj.setTag(rs.getString(4));
				sessionObj.setGroupId(rs.getString(5));
				sessionObj.setSubject(rs.getString(6));
				sessionObj.setNoOfStudents(rs.getInt(7));
				sessionObj.setDay(rs.getString(8));
				sessionObj.setDuration(rs.getInt(9));
				
				filteredList.add(sessionObj);
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(filteredList);
		return filteredList;
		
	}
>>>>>>> c56367de363c415135f7979fa06b948ed15136cf
	
}
