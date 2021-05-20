package dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;
import models.ParallelSessions;
import models.SessionType;

public class ParallelSessionDAOImpl implements ISessionService{

	private DBConnection dbconnect = new DBConnection();

	

	@Override
	public ArrayList<String> getSessionID() {
		ArrayList<String> arrayList = new ArrayList<>();
		
		String getSessionIDs = "select sessionCode from parallel_sessions";
		
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
		String sessionCode = "PS10";
		
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
	
	public void createParallelSession(String sessionCode, int sessionID) {
		Connection connection = dbconnect.connect();
		
		String insertQuery = "insert into parallel_sessions values (?,?,?)";
		
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
	public ArrayList<ParallelSessions> getSessionList() {
		Connection connection = dbconnect.connect();
		
		String listQuery = "select * from parallel_sessions";
		
		ArrayList<ParallelSessions> list = new ArrayList<ParallelSessions>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listQuery);
			
			while(rs.next()) {
				ParallelSessions obj = new ParallelSessions();
				obj.setId(rs.getInt(1));
				obj.setSessionCode(rs.getString(2));
				obj.setParallelSessionID(rs.getInt(3));
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
		
		String deleteQuery = "delete from parallel_sessions where sessionCode = '"+sessionCode+"' ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
}
