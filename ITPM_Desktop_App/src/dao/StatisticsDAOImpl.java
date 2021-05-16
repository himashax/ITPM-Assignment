package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConnection.DBConnection;

public class StatisticsDAOImpl {
	DBConnection db = new DBConnection() ;


	public int registeredCount(String value) {

	Connection conn = db.connect();

	int lecCount = 0;

	String getStudents = "select count(*) as total from "+value+" ";

	try {
	Statement stat = conn.createStatement();
	ResultSet rs = stat.executeQuery(getStudents);
	rs.next();
	lecCount = rs.getInt("total");

	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

	return lecCount;
	}



	public String latestRecord(String value) {
	Connection conn = db.connect();

	String lastRecord = "";

	int column = 0;

	if(value.equals("lecturer")) {
	column = 2;
	}else if(value.equals("student_group")) {
	column = 7;
	}else if(value.equals("subject")) {
	column = 5;
	}



	String latestrecordQuery = "select * from "+value+" ";
	try {
	Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	ResultSet rs = st.executeQuery(latestrecordQuery);
	rs.last();
	lastRecord = rs.getString(column);
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

	return lastRecord;
	}
	
	public  int getRoom(String type) {
		Connection con = db.connect();
		String countRooms = "select count(location_id) as roomCount from location where room_type = '"+type+"' ";
		
		int countType = 0;
		
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(countRooms);
			rs.next();
			
			countType = rs.getInt("roomCount");
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countType;
	}
}
