package models;

public class ConsecutiveSessions {

	int id;
	
	String consecSessionId;
	
	int lectureSessionId;
	
	int tuteSessionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLectureSessionId() {
		return lectureSessionId;
	}

	public void setLectureSessionId(int lectureSessionId) {
		this.lectureSessionId = lectureSessionId;
	}

	public int getTuteSessionId() {
		return tuteSessionId;
	}

	public void setTuteSessionId(int tuteSessionId) {
		this.tuteSessionId = tuteSessionId;
	}

	public String getConsecSessionId() {
		return consecSessionId;
	}

	public void setConsecSessionId(String consecSessionId) {
		this.consecSessionId = consecSessionId;
	}
	
}
