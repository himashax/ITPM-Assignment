package models;

import java.util.ArrayList;

public class ConsecutiveSessions extends SessionType{
	
	int lectureSessionId;
	
	int tuteSessionId;

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



	
}
