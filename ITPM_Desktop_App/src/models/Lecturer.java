package models;

import java.sql.*;
import java.util.ArrayList;

import dbConnection.DBConnection;

public class Lecturer {

	DBConnection db = new DBConnection();
	
	private String lecName;
	private String faculty;
	private String depart;
	private int level;
	private String rank;
	private String campus;
	private String building;
	private int eid;
	private int employeeId;
	
	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLecName() {
		return lecName;
	}

	public void setLecName(String lecName) {
		this.lecName = lecName;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	
	public String insertLecturers(String name,String faculty,String dept,int empid, int level,String rank,String campus,String build){
		
		String result = "";
		
		try {
			
			Connection connection = db.connect();
			
			String insertLec = "insert into lecturer values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertLec);
			
			ps.setInt(1, 0);
			ps.setString(2, name);
			ps.setString(3, faculty);
			ps.setString(4, dept);
			ps.setInt(5, empid);
			ps.setInt(6, level);
			ps.setString(7, rank);
			ps.setString(8, campus);
			ps.setString(9, build);
			
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	} 
	

	
	public ArrayList<Lecturer> listLecturer(){
		ArrayList<Lecturer> lec_group = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String listGroup = "select * from lecturer";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listGroup);
			
			while(rs.next()) {
				Lecturer lec = new Lecturer();
				
				lec.setEid(rs.getInt(1));
				lec.setLecName(rs.getString(2));
				lec.setFaculty(rs.getString(3));
				lec.setDepart(rs.getString(4));
				lec.setEmployeeId(rs.getInt(5));
				lec.setLevel(rs.getInt(6));
				lec.setRank(rs.getString(7));
				lec.setCampus(rs.getString(8));
				lec.setBuilding(rs.getString(9));
				
				lec_group.add(lec);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lec_group;
	} 
	
	
	public void updateLecturers(int id,String name,String faculty,String dept,int empID,int level,String rank,String campus,String build) {
		
		try {
			Connection con = db.connect();
			
			String updateQuery = "Update lecturer set name = '"+name+"', faculty = '"+faculty+"', department = '"+dept+"',emp_id = '"+empID+"', level ='"+level+"',"
					+ " rank = '"+rank+"', campus = '"+campus+"', building='"+build+"' where lec_id = '"+id+"' ";
		
			
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.execute();
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**********empid eka wenas krnna in update and del****/
	public void deleteLec(int id) {
		try {
			Connection connection = db.connect();
			String delQuery = "delete from lecturer where lec_id = '"+id+"'";
			PreparedStatement ps = connection.prepareStatement(delQuery);
			ps.execute();
			
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
