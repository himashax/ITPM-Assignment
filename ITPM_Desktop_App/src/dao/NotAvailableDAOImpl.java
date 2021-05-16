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

	public String insertNotAvailableTime(int duration,String session_ID, String lecturer,String group_ID,String day,String time) {
		String outcome1 = "";
		
		try {
		    Connection connection = db.connect();
		    String insertQuery1 = "insert into notAvailableTime values (?,?,?,?,?,?,?)";
		
			PreparedStatement ps = connection.prepareStatement(insertQuery1);
			ps.setInt(1, 0);
			ps.setInt(2, duration);
			ps.setString(3, session_ID);
			ps.setString(4, lecturer);
			ps.setString(5, group_ID);
			ps.setString(6, day);
			ps.setString(67, time);
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
				na.setSessionID(rs.getString(3));
				na.setLecturer(rs.getString(4));
				na.setGroupID(rs.getString(5));
				na.setDay(rs.getString(6));
				na.setTime(rs.getString(7));
				s2.add(na);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s2;
		
	}
	
	
	public void updateNotAvailableTime(int id,int duration, String session_ID, String lecturer,String group_ID,String day,String time) {
		try {
		Connection connection = db.connect();
		String updateQuery1 = "update notAvailableTime set duration = '"+duration+"',session_ID = '"+session_ID+"', lecturer = '"+lecturer+"', group_ID = '"+group_ID+"', day ='"+day+"', time = '"+time+"' where id = '"+id+"' ";

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
}
