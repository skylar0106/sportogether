package model.service.dto;

import java.time.LocalDate;

public class Request {
	
	//db순서 teamid, teamname, userid, username, message, date
    private int teamId;
    private String teamName;
    private String userId;
    private String userName;
    private String massage;
    private LocalDate date;
    
   public Request() {}
 
    public Request(int teamId, String teamName, String userId, String userName, String massage, LocalDate date) {
        super();
        this.teamId = teamId;
        this.teamName = teamName;
        this.userId = userId;
        this.userName = userName;
        this.massage = massage;
        this.date = date;
    }
 
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getMassage() {
        return massage;
    }
    public void setMassage(String massage) {
        this.massage = massage;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
