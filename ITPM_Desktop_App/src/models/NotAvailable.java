package models;

public class NotAvailable {
	private int id;
	private int dur;
	private int sessionID;
	private String fistLecturer;
	private String secondLecturer;
	private String groupID;
	private String day; 
	private String time;
	
	
	public int getId() {
		return id;
	}
	public String getFistLecturer() {
		return fistLecturer;
	}
	public void setFistLecturer(String fistLecturer) {
		this.fistLecturer = fistLecturer;
	}
	public String getSecondLecturer() {
		return secondLecturer;
	}
	public void setSecondLecturer(String secondLecturer) {
		this.secondLecturer = secondLecturer;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getDur() {
		return dur;
	}
	public void setDur(int dur) {
		this.dur = dur;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
