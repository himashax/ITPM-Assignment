package models;

import java.sql.*;

import java.util.ArrayList;

import dbConnection.DBConnection;


public class Student_Group {
	
	private DBConnection dbConnection;
	
	private String id;
	private String yearAndSemester;
	private String programme;
	private int groupNO;
	private int subGroupNo;
	private String groupID;
	private String subGroupID;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getYearAndSemester() {
		return yearAndSemester;
	}


	public void setYearAndSemester(String yearAndSemester) {
		this.yearAndSemester = yearAndSemester;
	}


	public String getProgramme() {
		return programme;
	}


	public void setProgramme(String programme) {
		this.programme = programme;
	}


	public int getGroupNO() {
		return groupNO;
	}


	public void setGroupNO(int groupNO) {
		this.groupNO = groupNO;
	}


	public int getSubGroupNo() {
		return subGroupNo;
	}


	public void setSubGroupNo(int subGroupNo) {
		this.subGroupNo = subGroupNo;
	}


	public String getGroupID() {
		return groupID;
	}


	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}


	public String getSubGroupID() {
		return subGroupID;
	}


	public void setSubGroupID(String subGroupID) {
		this.subGroupID = subGroupID;
	}

	
	public void insertStudentGroup(String yearAndSem, String programme, int groupNo, int subGroupNo, String groupID, String subGroupID) {
		
		try {
			Connection connection = dbConnection.connect();
			
//			String insertQuery = "insert into testing values ('11','"+yearAndSem+"', '"+programme+"', '"+groupNo+"', '"+subGroupNo+"'"
//					+ ", '"+groupID+"', '"+subGroupID+"') ";
			
			String insertQuery = "insert into student_group values (?,?,?,?,?,?,?) ";
			
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, yearAndSem);
			ps.setString(3, programme);
			ps.setInt(4, groupNo);
			ps.setInt(5, subGroupNo);
			ps.setString(6, groupID);
			ps.setString(7, subGroupID);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Student_Group> listStudentGroups(){
		
		ArrayList<Student_Group> group_list = new ArrayList<Student_Group>();
		
		try {
			Connection connection = dbConnection.connect();
			
			String listGroupsQuery = "select * from student_group";
			
			Statement statement = connection.createStatement();
			ResultSet rs =  statement.executeQuery(listGroupsQuery);
			while(rs.next()) {
				Student_Group sg = new Student_Group();
				sg.setId(rs.getString(1));
				sg.setYearAndSemester(rs.getString(2));
				sg.setProgramme(rs.getString(3));
				sg.setGroupNO(rs.getInt(4));
				sg.setSubGroupNo(rs.getInt(5));
				sg.setGroupID(rs.getString(6));
				sg.setSubGroupID(rs.getString(7));
				group_list.add(sg);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return group_list;
	}
	
	public void updateStudentGroup(int id, String yearAndSem, String programme, int groupNo, int subGroupNo, String GroupID, String subGroupID) {
		try {
			Connection connection = dbConnection.connect();
			String updateQuery = "update student_group set year_and_semester = '"+yearAndSem+"', programme = '"+programme+"', group_no = '"+groupNo+"',"
					+ " sub_group_no = '"+subGroupNo+"', group_id = '"+GroupID+"', sub_group_id = '"+subGroupID+"' where student_group_id = '"+id+"' ";
			
			PreparedStatement ps;
			ps = connection.prepareStatement(updateQuery);
			ps.execute();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudentGroup(int id) {
		
		try {
			Connection connection = dbConnection.connect();
			
			String deleteQuery = "delete from student_group where student_group_id = '"+id+"'";
			
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean manageGroups(String subGroupID) {
		boolean exist = false;
		try {
			Connection connection = dbConnection.connect();
			
			String getIDs = "select student_group_id from student_group where sub_group_id = '"+subGroupID+"'";
			PreparedStatement ps = connection.prepareStatement(getIDs);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				exist = true;
			}else {
				exist = false;
			}
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	
}
