package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Manage_Session_Room;

public class Manage_Session_RoomDAOImpl {

	DBConnection dbconnection = new DBConnection();
	
	public String insertSessionRoom(Manage_Session_Room msr) {
		
		String result = "";
		

		
		try {
			Connection con = dbconnection.connect();
			
			String insertSessionRooms = "insert into managesessionroom values(?,?,?)";
			
			PreparedStatement ps  = con.prepareStatement(insertSessionRooms);
			ps.setInt(1,0);
			ps.setInt(2, msr.getSessionID());
			ps.setString(3, msr.getRoomName());
			
			ps.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Manage_Session_Room> getSessionRoom(){
		
		ArrayList<Manage_Session_Room> listRoom = new ArrayList<>();
		
		try {
			Connection connection = dbconnection.connect();
			String getSessionRoom = "select * from managesessionroom";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSessionRoom);

			while(rs.next()) {
			Manage_Session_Room room = new Manage_Session_Room();

			room.setId(rs.getInt(1));
			room.setSessionID(rs.getInt(2));
			room.setRoomName(rs.getString(3));
			
			listRoom.add(room);
			}


			} catch (SQLException e) {
			e.printStackTrace();
			}

			return listRoom;
	} 
	
	public ArrayList<String> getSessionRoomList(){
		
		ArrayList<String> listRooms = new ArrayList<>();
		
		try {
			Connection connection = dbconnection.connect();
			String getSessionRoomList = "select * from location";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(getSessionRoomList);

			while(rs.next()) {
				listRooms.add(rs.getString(3));

			}
			System.out.println(listRooms);
			connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}

			return listRooms;
	} 
	
//	public ArrayList<String> getSessionRoomListById(String roomType){
//		
//		ArrayList<String> listRoomByIds = new ArrayList<String>();
//		
//		try {
//				Connection connection = dbconnection.connect();
//				String getSessionRoomListById = "select * from location where room_type = '"+ roomType +"'";
//	
//				Statement st = connection.createStatement();
//				ResultSet rs = st.executeQuery(getSessionRoomListById);
//	
//				while(rs.next()) {
//					listRoomByIds.add(rs.getString(3)+"kk");
//				}
//				System.out.println(listRoomByIds);
//				
//		} catch (SQLException e) {
//			e.printStackTrace();
//			}
//
//			return listRoomByIds;
//	} 
	
	public Manage_Session_Room getSessionRoomListById(int id){

		Manage_Session_Room listRoomByIds = new Manage_Session_Room();

		try {
		Connection connection = dbconnection.connect();
		String getSessionRoomListById = "select * from managesessionroom where session_id = '"+ id +"'";

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(getSessionRoomListById);

		while(rs.next()) {
		listRoomByIds.setId(rs.getInt(1));
		listRoomByIds.setSessionID(rs.getInt(2));
		listRoomByIds.setRoomName(rs.getString(3));
		}
		System.out.println(listRoomByIds);

		} catch (SQLException e) {
		e.printStackTrace();
		}



		return listRoomByIds;
		}
}
