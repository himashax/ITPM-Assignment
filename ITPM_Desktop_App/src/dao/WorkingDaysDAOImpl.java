package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.DaysndHours;

public class WorkingDaysDAOImpl {
	private DBConnection db = new DBConnection();

	public String insertWorkingDays(String workingDays, int workingTimePerDay,int minutes,int noOfWorkingDays) {
		String outcome = "";
		
		try {
		    Connection connection = db.connect();
		    String insertQuery = "insert into workingdays values (?,?,?,?,?)";
		
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, workingDays);
			ps.setInt(3, workingTimePerDay);
			ps.setInt(4, minutes);
			ps.setInt(5, noOfWorkingDays);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outcome;
		
		
	}
	
	public ArrayList<DaysndHours> workingDayList(){
		ArrayList<DaysndHours> s1 =new ArrayList<DaysndHours>();
		Connection con = db.connect();
		String list = "select*from workingdays";
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(list);
			while(rs.next()) {
				DaysndHours dh = new DaysndHours();
				dh.setId(rs.getInt(1));
				dh.setWorkingDays(rs.getString(2));
				dh.setWorkingTimePerDay(rs.getInt(3));
				dh.setMin(rs.getInt(4));
				dh.setNoOfWorkingDays(rs.getInt(5));
				s1.add(dh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s1;
		
	}
	
	public void updateWorkingDays(int id, String workingDays, int workingTimePerDay,int minutes,int noOfWorkingDays) {
		try {
		Connection connection = db.connect();
		String updateQuery = "update workingdays set workingDays = '"+workingDays+"', workingTimePerDay = '"+workingTimePerDay+"', minutes = '"+minutes+"', noOfWorkingDays ='"+noOfWorkingDays+"' where id = '"+id+"' ";

		PreparedStatement ps;
		ps = connection.prepareStatement(updateQuery);
		ps.execute();
		connection.close();

		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
	
	public void deleteWorkingDays(int id) {

		try {
		Connection connection = db.connect();

		String delQuery = "delete from workingdays where id = '"+id+"'";

		PreparedStatement ps = connection.prepareStatement(delQuery);
		ps.execute();
		connection.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		}
	
	
}

