package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.TimeSlot;

public class TimeSlotDAOImpl {
	
	private DBConnection db = new DBConnection();

	public void createTimeSlot(String start_time, String end_time) {
		
		Connection conn = db.connect();
		
		String insertTimeSlot = "insert into timeslot values(?, ?, ?)";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(insertTimeSlot);
			st.setInt(1, 0);
			st.setString(2, start_time);
			st.setString(3, end_time);
			st.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


		public ArrayList<TimeSlot> getTimeSlots(){
			Connection conn = db.connect();
			ArrayList<TimeSlot> ts = new ArrayList<>();
			
			String getTSlots = "select * from timeslot";
			
			Statement st;
			try {
				st = conn.createStatement();
				ResultSet rs = st.executeQuery(getTSlots);
				while(rs.next()) {
					TimeSlot tslot = new TimeSlot();
					tslot.setId(rs.getInt(1));
					tslot.setStartTime(rs.getString(2));
					tslot.setEndTime(rs.getString(3));
					ts.add(tslot);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return ts;
		}
		
		public void deleteTimeSlot(int id) {
			Connection conn = db.connect();
			
			String deleteTSlot = "delete from timeslot where id = '"+id+"'";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(deleteTSlot);
				st.execute();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

