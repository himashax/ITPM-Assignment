package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Location;

public class LocationDAOImpl {

	DBConnection dbconnect = new DBConnection();
	
	public void insertLocation(String bname , String rname , String type , int capacity) {
			
			
			Connection con = dbconnect.connect();
			
			
			String insertquery = "insert into location values (? , ? , ? , ? , ?)";
			try {
				PreparedStatement statement = con.prepareStatement(insertquery);
				
				statement.setInt(1, 0 );
				statement.setString(2, bname);
				statement.setString(3, rname);
				statement.setString(4, type);
				statement.setInt(5, capacity);
				statement.execute();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		}

			public ArrayList<Location> locations () {
				ArrayList<Location> locationlist = new ArrayList<Location> ();
				Connection con = dbconnect.connect();
				
				String retrieve = "select* from location ";
				
				try {
					Statement s = con.createStatement();
					
					ResultSet res = s.executeQuery(retrieve) ;
					
					while(res.next()) {
						
						Location loc = new Location();
						loc.setId(res.getInt(1));
						loc.setBuildingname(res.getString(2));
						loc.setRoomname(res.getString(3));
						loc.setType(res.getString(4));
						loc.setCapacity(res.getInt(5));
						
						locationlist.add(loc);
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return locationlist;
				
			}
			
	public void updatelocation (int id , String bname , String rname , String type , int capacity) {
				
				Connection con = dbconnect.connect();
				
				String update = " update location set building_name =  '"+bname+"', room_name = '"+rname+"', room_type =  '"+type+"' , capacity = '"+capacity+"' where location_id = '"+id+"' " ;
				try {
					PreparedStatement stat = con.prepareStatement(update);
					
					stat.execute();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	public void deleteLocation(int id) {
				try {
				Connection connection = dbconnect.connect();
				String delQuery = "delete from Location where location_id = '"+id+"'";
				PreparedStatement ps = connection.prepareStatement(delQuery);
				ps.execute();
				
				connection.close();
				}catch(SQLException e) {
				e.printStackTrace();
				}
	}
}
