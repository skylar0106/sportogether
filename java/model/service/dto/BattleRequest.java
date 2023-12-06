package model.service.dto;


public class BattleRequest {
    private String teamId;
    private String rivalId;
    private String sports;
    private String message;
    private String date ;
    private String approval;
    
    public BattleRequest() {}
    
    public BattleRequest(String teamId, String rivalId, String sports, String message, String date,String approval) {
        super();
        this.teamId = teamId;
        this.rivalId = rivalId;
        this.sports = sports;
        this.message = message;
        this.date = date;
        this.approval = approval;
    }
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public String getRivalId() {
        return rivalId;
    }
    public void setRivalId(String rivalId) {
        this.rivalId = rivalId;
    }
    public String getSports() {
        return sports;
    }
    public void setSports(String sports) {
        this.sports = sports;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getApproval() {
        return approval;
    }
    public void setApproval(String approval) {
        this.approval = approval;
    }
    
    
}
