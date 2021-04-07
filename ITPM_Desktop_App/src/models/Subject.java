package models;

import java.sql.*;
import java.util.ArrayList;

import dbConnection.DBConnection;

public class Subject {

	
	
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


}
