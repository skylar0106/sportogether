package model.service.dto;

public class Rival {
    /* 라이벌 팀을 추천하기위한 dto*/
    private String teamName;
    private String teamId;
    private float rate;
    
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    
    public Rival() {
    }
    
    public Rival(String teamName, String teamId, float rate) {
        super();
        this.teamName = teamName;
        this.teamId = teamId;
        this.rate = rate;
    }
    
    
}
