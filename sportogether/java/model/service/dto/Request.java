package model.service.dto;

import java.sql.Date;

public class Request {
    private String teamId;
    private String teamName;
    private String userName;
    private String userId;
    private String massage;
    private Date date;
    
   public Request() {}
    
    public Request(String teamId, String userId, String massage, Date date) {
        super();
        this.teamId = teamId;
        this.userId = userId;
        this.massage = massage;
        this.date = date;
    }
    
    public Request(String teamId, String teamName, String userName, String userId, String massage, Date date) {
        super();
        this.teamId = teamId;
        this.teamName = teamName;
        this.userName = userName;
        this.userId = userId;
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
    
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
