package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;

public class Active_Days {

	DBConnection db = new DBConnection();
	
	private int EmpID;
	private String Days;
	private String hours;
	private int id;
	
	
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	public String getDays() {
		return Days;
	}
	public void setDays(String days) {
		Days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String insertActiveDays(int empid, String day, String hours ) {
		
		String result = "";
		
		try {
			
			Connection connection = db.connect();
			
			String insertDays = "insert into lecturer_activedays values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertDays);
			
			ps.setInt(1, empid);
			ps.setString(2, day);
			ps.setString(3, hours);

			ps.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Active_Days> listDays(){
		ArrayList<Active_Days> activeDay_group = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String listDayGroup = "select * from lecturer_activedays";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listDayGroup);
			
			while(rs.next()) {
				Active_Days ac_days = new Active_Days();
				
				ac_days.setEmpID(rs.getInt(1));
				ac_days.setDays(rs.getString(2));
				ac_days.setHours(rs.getString(3));

				
				activeDay_group.add(ac_days);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return activeDay_group;
	} 
	

	public void updateActiveDays(int id,String day,String hours) {
		
		try {
			Connection con = db.connect();
			
			String updateQuery = "Update lecturer_activedays set days = '"+day+"', hours='"+hours+"' where emp_id = '"+id+"' ";
		
			
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.execute();
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteActiveDays(int id) {
		try {
			Connection connection = db.connect();
			String delDayQuery = "delete from lecturer_activedays where emp_id = '"+id+"'";
			PreparedStatement ps = connection.prepareStatement(delDayQuery);
			ps.execute();
			
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
