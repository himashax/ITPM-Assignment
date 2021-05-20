package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Member3_Location;
import models.NotAvailable;

public class Mem03_LocationDAOImpl {
	private  DBConnection db = new DBConnection();
	
	public  boolean checkTime(String room, String day, String sTime, String eTime) {
		boolean value = false;
		try {
		    Connection connection = db.connect();
		    String chkTime = "select id from notavailable_location "
		    		+ "where lRoom = '"+room+"' and lDay = '"+day+"' and startTime ='"+sTime+"' and endTime = '"+eTime+"'";
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(chkTime);
			if(rs.next()){
				value = true;
			}else {
				value = false;
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	

	public String insertNotAvailableLocation(int session_ID, String lRoom, String lDay,String startTime, String endTime) {
		String outcome2 = "";
		
		try {
		    Connection connection = db.connect();
		    String insertQuery2 = "insert into notavailable_location values (?,?,?,?,?,?)";
		
			PreparedStatement ps = connection.prepareStatement(insertQuery2);
			ps.setInt(1, 0);
			ps.setInt(2, session_ID);
			ps.setString(3, lRoom);
			ps.setString(4, lDay);
			ps.setString(5, startTime);
			ps.setString(6, endTime);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outcome2;
		
		
	}
	
	public ArrayList<Member3_Location> notAvailableLocationList(){
		ArrayList<Member3_Location> s3 =new ArrayList<Member3_Location>();
		Connection con = db.connect();
		String list1 = "select*from notAvailableLocation";
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(list1);
			while(rs.next()) {
				Member3_Location ml = new Member3_Location();
				ml.setId(rs.getInt(1));
				ml.setSession(rs.getInt(2));
				ml.setRoom(rs.getString(3));
				ml.setDay(rs.getString(4));
				ml.setStartTime(rs.getString(5));
				ml.setEndTime(rs.getString(6));
				s3.add(ml);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s3;
		
	}
	
	public void updateNotAvailableLocation(int id, int session_ID, String lRoom, String lDay,String startTime, String endTime) {
		try {
		Connection connection = db.connect();
		String updateQuery2 = "update notAvailableLocation set session_ID = '"+session_ID+"',lRoom = '"+lRoom+"', lDay = '"+lDay+"', startTime = '"+startTime+"', endTime ='"+endTime+"' where id = '"+id+"' ";

		PreparedStatement ps;
		ps = connection.prepareStatement(updateQuery2);
		ps.execute();
		connection.close();

		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
	
	public void deleteNotAvailableLocation(int id) {

		try {
		Connection connection = db.connect();

		String delQuery2 = "delete from notAvailableLocation where id = '"+id+"'";

		PreparedStatement ps = connection.prepareStatement(delQuery2);
		ps.execute();
		connection.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		}
	
	
	public ArrayList<String> retrieveTimeSeperate1(){
		ArrayList<String> ob = new ArrayList<String>();
		Connection conn = db.connect();
		String retrieveTSlotSeperate1 = "select * from timeslot";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(retrieveTSlotSeperate1);
			while(rs.next()) {
				ob.add(rs.getString(2));
			}
			System.out.println(ob);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return ob;
			
	}
	
	
	public ArrayList<String> retrieveTimeSeperate2(){
		ArrayList<String> ob = new ArrayList<String>();
		Connection conn = db.connect();
		String retrieveTSlotSeperate2 = "select * from timeslot";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(retrieveTSlotSeperate2);
			while(rs.next()) {
				ob.add(rs.getString(3));
			}
			System.out.println(ob);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return ob;
			
	}
}
