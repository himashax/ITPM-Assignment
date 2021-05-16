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
	private DBConnection db = new DBConnection();

	public String insertNotAvailableLocation(String lRoom, String lDay,String startTime, String endTime) {
		String outcome2 = "";
		
		try {
		    Connection connection = db.connect();
		    String insertQuery2 = "insert into notavailable_location values (?,?,?,?,?)";
		
			PreparedStatement ps = connection.prepareStatement(insertQuery2);
			ps.setInt(1, 0);
			ps.setString(2, lRoom);
			ps.setString(3, lDay);
			ps.setString(4, startTime);
			ps.setString(5, endTime);
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
				ml.setRoom(rs.getString(2));
				ml.setDay(rs.getString(3));
				ml.setStartTime(rs.getString(4));
				ml.setEndTime(rs.getString(5));
				s3.add(ml);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s3;
		
	}
	
	public void updateNotAvailableLocation(int id, String lRoom, String lDay,String startTime, String endTime) {
		try {
		Connection connection = db.connect();
		String updateQuery2 = "update notAvailableLocation set lRoom = '"+lRoom+"', lDay = '"+lDay+"', startTime = '"+startTime+"', endTime ='"+endTime+"' where id = '"+id+"' ";

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
}
