package models;

public class Session {
	private int id;
	private String firstLecturer;
	private String secLecturer;
	private String tag;
	private String groupId;
	private String subject;
	private int noOfStudents;
	private String day;
	private int duration;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstLecturer() {
		return firstLecturer;
	}
	public void setFirstLecturer(String firstLecturer) {
		this.firstLecturer = firstLecturer;
	}
	public String getSecLecturer() {
		return secLecturer;
	}
	public void setSecLecturer(String secLecturer) {
		this.secLecturer = secLecturer;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}
