package model.service.dto;

public class Team {
    private String teamId;
    private String tname;
    private String spoLeader;
    private int level;
    private String sport;
    private String location;
    private int membership;
    private String rival;
    
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public String getTname() {
        return tname;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }
    public String getSpoLeader() {
        return spoLeader;
    }
    public void setSpoLeader(String spoLeader) {
        this.spoLeader = spoLeader;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getMembership() {
        return membership;
    }
    public void setMembership(int membership) {
        this.membership = membership;
    }
    public String getRival() {
        return rival;
    }
    public void setRival(String rival) {
        this.rival = rival;
    }
    
    public Team() {

    }
    public Team(String teamId, String tname, String spoLeader, int level, String sport, String location, int membership,
            String rival) {
        super();
        this.teamId = teamId;
        this.tname = tname;
        this.spoLeader = spoLeader;
        this.level = level;
        this.sport = sport;
        this.location = location;
        this.membership = membership;
        this.rival = rival;
    }
    
}
