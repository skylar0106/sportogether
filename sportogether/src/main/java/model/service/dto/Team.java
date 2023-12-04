package model.service.dto;

public class Team {
    private String teamId;
    private String name;
    private String spoleader; // 수정: spoleader를 VARCHAR2로 변경
    private int level;
    private String sport;
    private String location;
    private int membership;
    private String rival;

    public Team() {
    }
    
    public Team(String teamId, String name, String spoleader, int level, String sport, String location, int membership,
			String rival) {
		super();
		this.teamId = teamId;
		this.name = name;
		this.spoleader = spoleader;
		this.level = level;
		this.sport = sport;
		this.location = location;
		this.membership = membership;
		this.rival = rival;
	}

	public Team(String name, String sport, String location) {
		super();
		this.name = name;
		this.sport = sport;
		this.location = location;
	}

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpoleader() {
        return spoleader;
    }

    public void setSpoleader(String spoleader) {
        this.spoleader = spoleader;
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
}
