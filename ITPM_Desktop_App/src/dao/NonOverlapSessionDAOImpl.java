package dao;

import java.sql.*;


import java.util.ArrayList;

import dbConnection.DBConnection;
import models.NonOverlapSession;
import models.SessionType;

public class NonOverlapSessionDAOImpl implements ISessionService{
	
	private static DBConnection dbconnect = new DBConnection();
	

	@Override
	public ArrayList<String> getSessionID() {
		ArrayList<String> arrayList = new ArrayList<>();
		
		String getSessionIDs = "select sessionCode from nonOverlap_sessions";
		
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
		String sessionCode = "NOS10";
		
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
	
	public void createNonOverlapSession(String sessionCode, int sessionID) {
		Connection connection = dbconnect.connect();
		
		String insertQuery = "insert into nonOverlap_sessions values(?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, sessionCode);
			ps.setInt(3, sessionID);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<NonOverlapSession> getSessionList() {
		Connection connection = dbconnect.connect();
		
		String listQuery = "select * from nonOverlap_sessions";
		
		ArrayList<NonOverlapSession> list = new ArrayList<NonOverlapSession>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listQuery);
			
			while(rs.next()) {
				NonOverlapSession obj = new NonOverlapSession();
				obj.setId(rs.getInt(1));
				obj.setSessionCode(rs.getString(2));
				obj.setNonOverlapSessionID(rs.getInt(3));
				list.add(obj);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteSession(String sessionCode) {
		Connection connection = dbconnect.connect();
		
		String deleteQuery = "delete from nonOverlap_sessions where sessionCode = '"+sessionCode+"' ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	public boolean checkExistence(int sessionID) {
		boolean exist = false;
		try {
			Connection connection = dbconnect.connect();
			
			String checkSessions = "select id from nonOverlap_sessions where nonoverlapSessionID = '"+sessionID+"' ";
			PreparedStatement ps = connection.prepareStatement(checkSessions);
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
