package model.service.dto;

public class Member {
	private String teamID;	
    private String userID;
    private String message;

    public Member() {
    	
    }

    public Member(String teamID,  String userID, String message) {
    	super();
    	this.teamID = teamID;
        this.userID = userID;
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userID='" + userID + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
