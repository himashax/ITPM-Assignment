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
	private int Mon,Tue,Wed,Thur,Fri,Sat,Sun;
	private int id;
	
	
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	
	public int getMon() {
		return Mon;
	}
	public void setMon(int mon) {
		Mon = mon;
	}
	public int getTue() {
		return Tue;
	}
	public void setTue(int tue) {
		Tue = tue;
	}
	public int getWed() {
		return Wed;
	}
	public void setWed(int wed) {
		Wed = wed;
	}
	public int getThur() {
		return Thur;
	}
	public void setThur(int thur) {
		Thur = thur;
	}
	public int getFri() {
		return Fri;
	}
	public void setFri(int fri) {
		Fri = fri;
	}
	public int getSat() {
		return Sat;
	}
	public void setSat(int sat) {
		Sat = sat;
	}
	public int getSun() {
		return Sun;
	}
	public void setSun(int sun) {
		Sun = sun;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
