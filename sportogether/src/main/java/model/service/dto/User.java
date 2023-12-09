package model.service.dto;

import java.util.Date;
import java.util.List;

public class User {
	private int teamId; //추가한 정보
    private String userId;
    private String name;
    private String nickName;
    private String sex;
    private String birth;
    private String password;
    private String position;
    
    //추가
    private String comment;
    private List<String> interests;
    private String career;
    private String picture;

    
    //기본 생성자
    public User() {}
    
    //일반 생성자(개인정보 수정할 때 사용)
    public User(String name, String nickName, String sex, String birth, String position, String password) {
        super();
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.position = position;
        this.password = password;
    }
    
    
    public User(String userId, String name, String nickName, String sex, String birth, String position, String password) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.position = position;
        this.password = password;
    }
    
    
    //find하는 용도
    public User(String userId, int teamId, String name, String nickName, String sex, String birth, String position, String password) {
        super();
        this.userId = userId;
        this.teamId = teamId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.position = position;
        this.password = password;
    }
    

    //password없는 생성자(포트폴리오 수정시 사용)
    public User(String userId, String name, String nickName, String sex, String birth, 
            String picture, String comment, List<String> interests, String career) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.picture = picture;
        this.comment = comment;
        this.interests = interests;
        this.career = career;
    }
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPosition() {
    	return position;
    }
    public void setPosition(String position) {
    	this.position = position;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<String> getInterests() {
        return interests;
    }
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
    public String getCareer() {
        return career;
    }
    public void setCareer(String career) {
        this.career = career;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
//	public String getLeaderName() {
//		return leaderName;
//	}
//
//	public void setLeaderName(String leaderName) {
//		this.leaderName = leaderName;
//	}
//
//	public String getLeaderId() {
//		return leaderId;
//	}
//
//	public void setLeaderId(String leaderId) {
//		this.leaderId = leaderId;
//	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
    
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }




}

