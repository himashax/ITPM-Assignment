package models;

import java.sql.*;

import java.util.ArrayList;

import dbConnection.DBConnection;

public class Tags {

	private DBConnection dbConnection;
	
	private int id;
	private String tagName;
	private String tagCode;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public String getTagCode() {
		return tagCode;
	}


	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}


	public void insertTags(String tagName, String tagCode) {
		
		try {
			Connection connection = dbConnection.connect();
			
			String insertQuery = "insert into tag values (?,?,?)";
					
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, tagName);
			ps.setString(3, tagCode);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Tags> listTags(){
			
			ArrayList<Tags> tags_list = new ArrayList<Tags>();
			
			try {
				Connection connection = dbConnection.connect();
				
				String listTagsQuery = "select * from tag";
				
				Statement statement = connection.createStatement();
				ResultSet rs =  statement.executeQuery(listTagsQuery);
				while(rs.next()) {
					Tags tag = new Tags();
					tag.setId(rs.getInt(1));
					tag.setTagName(rs.getString(2));
					tag.setTagCode(rs.getString(3));
					tags_list.add(tag);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return tags_list;
		}
	
	
	public void updateTags(int id , String tagName, String tagCode) {
		
		try {
			
			Connection connection = dbConnection.connect();
			
			String updateQuery = "update tag set tag_name = '"+tagName+"', tag_code = '"+tagCode+"' where tag_id = '"+id+"' ";
			PreparedStatement ps = connection.prepareStatement(updateQuery);
			ps.execute();
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteTags(int id) {
		
		try {
			Connection connection = dbConnection.connect();
			String deleteQuery = "delete from tag where tag_id = '"+id+"' ";
			
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			ps.execute();
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
