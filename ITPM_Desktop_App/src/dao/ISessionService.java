package dao;

import java.util.*;

import models.SessionType;

public interface ISessionService {
	
	public ArrayList<String> getSessionID();
	
	public String generateSessionCode(ArrayList<String> sessionIdList);
	
	public ArrayList<? extends SessionType> getSessionList();
	
	public void deleteSession(String sessionCode);
	
}
