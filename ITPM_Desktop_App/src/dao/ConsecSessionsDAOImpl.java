package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.Session;

import dbConnection.DBConnection;
import models.ConsecutiveSessions;

public class ConsecSessionsDAOImpl {
	
	private static DBConnection dbconnect = new DBConnection();

	public void createConsecSessions(String sessionId, int lecId, int tuteId) {
		
		Connection connection = dbconnect.connect();
		
		String insertQuery = "insert into consecutive_sessions values(?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, sessionId);
			ps.setInt(3, lecId);
			ps.setInt(2, tuteId);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public List<ConsecutiveSessions> listConsecSessions(){
		Connection connection = dbconnect.connect();
		
		String listQuery = "select * from consecutive_sessions";
		
		List<ConsecutiveSessions> listSessions = new ArrayList<>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listQuery);
			
			while(rs.next()) {
				ConsecutiveSessions consecObj = new ConsecutiveSessions();
				consecObj.setId(rs.getInt(1));
				consecObj.setConsecSessionId(rs.getString(2));
				consecObj.setLectureSessionId(rs.getInt(3));
				consecObj.setTuteSessionId(rs.getInt(4));
				listSessions.add(consecObj);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSessions;
	}
	
	
	public static void deleteConsecSessions(String consecSessionId) {
		
		Connection connection = dbconnect.connect();
		
		String deleteQuery = "delete from consecutive_sessions where consecSessionId = '"+consecSessionId+"' ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		deleteConsecSessions("hi");
	}
	
}
