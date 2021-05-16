
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Subject;

public class SubjectDAOImpl {
	DBConnection db = new DBConnection();
	
	public String insertSubQuery(Subject subject) throws Exception{
		
		String result = "";
		
		
			Connection connection = db.connect();
			
			String insertSub = "insert into subject values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(insertSub);
			
			ps.setInt(1, 0);
			ps.setInt(2, subject.getYear());
			ps.setString(3, subject.getSemester());
			ps.setString(4, subject.getSubjectCode());
			ps.setString(5, subject.getSubjectName());
			ps.setInt(6, subject.getNoOfLecHrs());
			ps.setInt(7, subject.getNoOfTuteHrs());
			ps.setInt(8, subject.getNoOfLabHrs());
			ps.setInt(9, subject.getNoOfEvalHRs());
			
			ps.execute();
			
			connection.close();

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
	
	public Subject getSubjectById(int id){
		Subject sub = new Subject();
		
		try {
			Connection connection = db.connect();
			String subjectList = "select * from subject where sub_id = '"+id+"'";
			System.out.println(id +"fkjhg");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(subjectList);
			
			while(rs.next()) {
				
				sub.setId(rs.getInt(1));
				sub.setYear(rs.getInt(2));
				sub.setSemester(rs.getString(3));
				sub.setSubjectCode(rs.getString(4));
				sub.setSubjectName(rs.getString(5));
				sub.setNoOfLecHrs(rs.getInt(6));
				sub.setNoOfTuteHrs(rs.getInt(7));
				sub.setNoOfLabHrs(rs.getInt(8));
				sub.setNoOfEvalHRs(rs.getInt(9));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sub;
	}
	
	
	
	
	public void updateSubjects(Subject subject) {
		
		try {
			Connection connection = db.connect();
			
			String updateSubject = "update subject set year = '"+subject.getYear()+"', semester ='"+subject.getSemester()+"', subject_code = '"+subject.getSubjectCode()+"',"
					+ " subject_name = '"+subject.getSubjectName()+"', lecture_hours = '"+subject.getNoOfLecHrs()+"', tute_hours = '"+subject.getNoOfTuteHrs()+"',"
							+ " lab_hours = '"+subject.getNoOfLabHrs()+"', evaluation_hours ='"+subject.getNoOfEvalHRs()+"' where sub_id = '"+subject.getId()+"'";
			
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
