package dao;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;
import models.ConsecutiveSessions;
import dao.ISessionService;
import models.SessionType;

public class ConsecSessionsDAOImpl implements ISessionService{
	
	private DBConnection dbconnect = new DBConnection();
	
	@Override
	public ArrayList<String> getSessionID(){
		ArrayList<String> arrayList = new ArrayList<>();
		
		String getSessionIDs = "select sessionCode from consecutive_sessions";
		
		Connection connection = dbconnect.connect();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSessionIDs);
			while(rs.next()) {
				arrayList.add(rs.getString(1));
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	@Override
	public String generateSessionCode(ArrayList<String> sessionIdList) {
		String sessionCode = "CS10";
		
		String sessionID;
		int count = sessionIdList.size();
		count++;
		sessionID = sessionCode + count;
		if (sessionIdList.contains(sessionID)) {
			count++;
			sessionID = sessionCode + count;
		}
		
		return sessionID;
	}
	
	public void createConsecSessions(int lecId, int tuteId) {
		
		Connection connection = dbconnect.connect();
		
		String insertQuery = "insert into consecutive_sessions values(?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			
			String sessionCode = generateSessionCode(getSessionID());
			
			ps.setInt(1, 0);
			ps.setString(2, sessionCode);
			ps.setInt(3, lecId);
			ps.setInt(4, tuteId);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public ArrayList<ConsecutiveSessions> getSessionList() {
		Connection connection = dbconnect.connect();
		
		String listQuery = "select * from consecutive_sessions";
		
		ArrayList<ConsecutiveSessions> listSessions = new ArrayList<>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listQuery);
			
			while(rs.next()) {
				ConsecutiveSessions consecObj = new ConsecutiveSessions();
				consecObj.setId(rs.getInt(1));
				consecObj.setSessionCode(rs.getString(2));
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

	@Override
	public void deleteSession(String sessionCode) {
		Connection connection = dbconnect.connect();
		
		String deleteQuery = "delete from consecutive_sessions where sessionCode = '"+sessionCode+"' ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean checkExistence(int sessionID) {
		boolean exist = false;
		try {
			Connection connection = dbconnect.connect();
			
			String sessionIDs = "select id from consecutive_sessions where session_lec = '"+sessionID+"' or session_tute='"+sessionID+"'";
			PreparedStatement ps = connection.prepareStatement(sessionIDs);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				exist = true;
			}else {
				exist = false;
			}
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	
	
}
