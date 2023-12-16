package model.service.dto;

import java.util.List;

public class Team {
    private int teamId; // 수정: teamId를 INTEGER로 변경
    private String name;
    private String spoleader;// 수정: spoleader를 VARCHAR2로 변경
    private int level;
    private String sport;
    private String location;
    private int membership;
    private String rival;
	private List<User> memberList; // 추가한 정보(이건 db에는 추가 X)
	private int numofMembers; // 추가한 정보 (이건 db에는 추가 X)

    public Team() {
    }

    public Team(int teamId, String name, String spoleader) {
    	super();
    	this.teamId = teamId;
    	this.name = name;
    	this.spoleader = spoleader;

    }
    
    public Team(int teamId, String name, String spoleader, int level, String sport, String location, int membership,
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

    // 팀의 정보 수정 & 생성용
	public Team(int teamId, String name, String sport, String location) {
		super();
		this.teamId = teamId;
		this.name = name;
		this.sport = sport;
		this.location = location;
	}
    
    

	public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
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


	public List<User> getMemberList() {
		return memberList;
	}


	public void setMemberList(List<User> memberList) {
		this.memberList = memberList;
	}


	public int getNumofMembers() {
		return numofMembers;
	}


	public void setNumofMembers(int numofMembers) {
		this.numofMembers = numofMembers;
	}
}
