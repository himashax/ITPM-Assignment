package models;

import java.sql.*;
import java.util.ArrayList;

import dbConnection.DBConnection;

public class Subject {

	DBConnection db = new DBConnection();
	
	private int year;
	private String semester;
	private String subjectCode;
	private String subjectName;
	private int noOfLecHrs;
	private int noOfTuteHrs;
	private int noOfLabHrs;
	private int noOfEvalHRs;
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getNoOfLecHrs() {
		return noOfLecHrs;
	}

	public void setNoOfLecHrs(int noOfLecHrs) {
		this.noOfLecHrs = noOfLecHrs;
	}

	public int getNoOfTuteHrs() {
		return noOfTuteHrs;
	}

	public void setNoOfTuteHrs(int noOfTuteHrs) {
		this.noOfTuteHrs = noOfTuteHrs;
	}

	public int getNoOfLabHrs() {
		return noOfLabHrs;
	}

	public void setNoOfLabHrs(int noOfLabHrs) {
		this.noOfLabHrs = noOfLabHrs;
	}

	public int getNoOfEvalHRs() {
		return noOfEvalHRs;
	}

	public void setNoOfEvalHRs(int noOfEvalHRs) {
		this.noOfEvalHRs = noOfEvalHRs;
	}

	public String insertSubQuery(int year, String sem, String subCode, String subName, int lecHrs, int tuteHrs, int labHrs, int evHrs) {
		
		String result = "";
		
		try {
			Connection connection = db.connect();
			
			String insertSub = "insert into subject values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(insertSub);
			
			ps.setInt(1, 0);
			ps.setInt(2, year);
			ps.setString(3, sem);
			ps.setString(4, subCode);
			ps.setString(5, subName);
			ps.setInt(6, lecHrs);
			ps.setInt(7, tuteHrs);
			ps.setInt(8, labHrs);
			ps.setInt(9, evHrs);
			
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ArrayList<Subject> listSubjects(){
		ArrayList<Subject> subject_group = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String subjectList = "select * from subject";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(subjectList);
			
			while(rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt(1));
				sub.setYear(rs.getInt(2));
				sub.setSemester(rs.getString(3));
				sub.setSubjectCode(rs.getString(4));
				sub.setSubjectName(rs.getString(5));
				sub.setNoOfLecHrs(rs.getInt(6));
				sub.setNoOfTuteHrs(rs.getInt(7));
				sub.setNoOfLabHrs(rs.getInt(8));
				sub.setNoOfEvalHRs(rs.getInt(9));
				
				subject_group.add(sub);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subject_group;
	}
	
	
	public void updateSubjects(int id, int year, String sem, String subCode,String subName,int lecHrs, int tuteHrs, int labHrs, int evlHrs) {
		
		try {
			Connection connection = db.connect();
			
			String updateSubject = "update subject set year = '"+year+"', semester ='"+sem+"', subject_code = '"+subCode+"',"
					+ " subject_name = '"+subName+"', lecture_hours = '"+lecHrs+"', tute_hours = '"+tuteHrs+"',"
							+ " lab_hours = '"+labHrs+"', evaluation_hours ='"+evlHrs+"' where sub_id = '"+id+"'";
			
			PreparedStatement ps = connection.prepareStatement(updateSubject);
			ps.execute();
			
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSubject(int id) {

		try {
			Connection connection = db.connect();
			
			String deleteSubjectQuery = "delete from subject where sub_id = '"+id+"'";
			PreparedStatement ps = connection.prepareStatement(deleteSubjectQuery);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
