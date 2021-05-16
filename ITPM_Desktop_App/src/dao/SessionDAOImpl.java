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
			
			System.out.println(getLecturers);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getLecturers;
	}
	
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
			System.out.println(groupIdList);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupIdList;
		
	}
	
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
			System.out.println(subGroupIdList);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subGroupIdList;
		
	}
	
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
			System.out.println(subjectInfoList);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjectInfoList;
		
	}
	
	public ArrayList<String> getAcademicYear(){
		ArrayList<String> academicYearList = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String getAcademicYear = "select * from student_group";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getAcademicYear);
			
			while(rs.next()) {
				academicYearList.add(rs.getString(2));
			}
			System.out.println(academicYearList);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return academicYearList;
		
	}
	
	
}
