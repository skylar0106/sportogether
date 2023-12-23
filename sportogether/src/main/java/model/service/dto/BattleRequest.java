package model.service.dto;

import java.time.LocalDate;

//db상 battleid 순서가 제일 마지막
public class BattleRequest {
    private int teamId;
    private int rivalId;
    private String sports;
    private String message;
    private LocalDate date ;
    private String approval;
    private int battleId;
    
    public BattleRequest() {}
    
    public BattleRequest(int teamId,int rivalId, String sports, String message, LocalDate date,String approval, int battleId) {
        super();
        this.teamId = teamId;
        this.rivalId = rivalId;
        this.sports = sports;
        this.message = message;
        this.date = date;
        this.approval = approval;
        this.battleId = battleId;
    }
    
    
    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public int getRivalId() {
        return rivalId;
    }
    public void setRivalId(int rivalId) {
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getApproval() {
        return approval;
    }
    public void setApproval(String approval) {
        this.approval = approval;
    }
    
    
}