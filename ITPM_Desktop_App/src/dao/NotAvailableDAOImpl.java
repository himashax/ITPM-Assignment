package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.DaysndHours;
import models.NotAvailable;

public class NotAvailableDAOImpl {
	private DBConnection db = new DBConnection();

	public String insertNotAvailableTime(int duration,int session_ID, String first_lecturer,String second_lecturer,String group_ID,String day,String time, String endTime) {
		String outcome1 = "";
		
		try {
		    Connection connection = db.connect();
		    String insertQuery1 = "insert into notAvailableTime values (?,?,?,?,?,?,?,?,?)";
		
			PreparedStatement ps = connection.prepareStatement(insertQuery1);
			ps.setInt(1, 0);
			ps.setInt(2, duration);
			ps.setInt(3, session_ID);
			ps.setString(4, first_lecturer);
			ps.setString(5, second_lecturer);
			ps.setString(6, group_ID);
			ps.setString(7, day);
			ps.setString(8, time);
			ps.setString(9, endTime);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outcome1;
		
		
	}
	
	public ArrayList<NotAvailable> notAvailableTimeList(){
		ArrayList<NotAvailable> s2 =new ArrayList<NotAvailable>();
		Connection con = db.connect();
		String list1 = "select*from notAvailableTime";
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(list1);
			while(rs.next()) {
				NotAvailable na = new NotAvailable();
				na.setId(rs.getInt(1));
				na.setDur(rs.getInt(2));
				na.setSessionID(rs.getInt(3));
				na.setFistLecturer(rs.getString(4));
				na.setSecondLecturer(rs.getString(5));
				na.setGroupID(rs.getString(6));
				na.setDay(rs.getString(7));
				na.setTime(rs.getString(8));
				na.setEndTm(rs.getString(9));
				s2.add(na);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s2;
		
	}
	
	
	public void updateNotAvailableTime(int id,int duration, int session_ID, String first_lecturer,String second_lecturer, String group_ID,String day,String time, String endTime) {
		try {
		Connection connection = db.connect();
		String updateQuery1 = "update notAvailableTime set duration = '"+duration+"',session_ID = '"+session_ID+"', first_lecturer = '"+first_lecturer+"',second_lecturer = '"+second_lecturer+"', group_ID = '"+group_ID+"', day ='"+day+"', time = '"+time+"',endTime = '"+endTime+"' where id = '"+id+"' ";

		PreparedStatement ps;
		ps = connection.prepareStatement(updateQuery1);
		ps.execute();
		connection.close();

		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
	
	
	public void deleteNotAvailableTime(int id) {

		try {
		Connection connection = db.connect();

		String delQuery1 = "delete from notAvailableTime where id = '"+id+"'";

		PreparedStatement ps = connection.prepareStatement(delQuery1);
		ps.execute();
		connection.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		}
	
	public ArrayList<String> retrieveTime1(){
		ArrayList<String> ob = new ArrayList<String>();
		Connection conn = db.connect();
		String retrieveTSlot1 = "select * from timeslot";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(retrieveTSlot1);
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
	
	
	public ArrayList<String> retrieveTime2(){
		ArrayList<String> ob1 = new ArrayList<String>();
		Connection conn = db.connect();
		String retrieveTSlot2 = "select * from timeslot";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(retrieveTSlot2);
			while(rs.next()) {
				ob1.add(rs.getString(3));
			}
			System.out.println(ob1);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return ob1;
			
	}
	
	public NotAvailable getByNotAvailableId(int id) {

		NotAvailable nta = new NotAvailable();
		Connection con = db.connect();

		String getByNotAvailableId = "select * from notavailabletime where id = '"+id+"'";

		Statement st;
		try {
		st = con.createStatement();
		ResultSet rs = st.executeQuery(getByNotAvailableId);

		while(rs.next()) {
		nta.setId(rs.getInt(1));
		nta.setDur(rs.getInt(2));
		nta.setSessionID(rs.getInt(3));
		nta.setFistLecturer(rs.getString(4));
		nta.setSecondLecturer(rs.getString(5));
		nta.setGroupID(rs.getString(6));
		nta.setDay(rs.getString(7));
		nta.setTime(rs.getString(8));
		nta.setEndTm(rs.getString(9));
		
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}

		return nta;
		}
	
	
	
	
}
