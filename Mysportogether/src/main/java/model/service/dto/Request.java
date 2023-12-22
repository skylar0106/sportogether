package model.service.dto;


public class Request {
	
	//db순서 teamid, teamname, userid, username, message, date
    private String teamId;
    private String teamName;
    private String userId;
    private String userName;
    private String massage;
    private String date;
    
   public Request() {}
 
    public Request(String teamId, String teamName, String userId, String userName, String massage, String date) {
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
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
}
