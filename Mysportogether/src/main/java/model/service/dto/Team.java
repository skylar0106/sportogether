package model.service.dto;

public class Team {
    private String teamId;
	private String name;
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
    public String getName() {
        return name;
    }
    public void setName(String tname) {
        this.name = tname;
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
    public Team(String teamId, String name, String spoLeader, int level, String sport, String location, int membership,
            String rival) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.spoLeader = spoLeader;
        this.level = level;
        this.sport = sport;
        this.location = location;
        this.membership = membership;
        this.rival = rival;
    }
    
}