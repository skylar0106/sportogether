package model.service.dto;

public class Leader {
    private String userID;

    public Leader() {
    }

    public Leader(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
