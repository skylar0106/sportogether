package model.service.dto;

import java.util.Date;
import java.util.List;

public class User {
	private int teamId; //추가한 정보
    private String userId;
    private String name;
    private String nickName;
    private String sex;
    private int leader;
    private String password;
    private String position;
    private String comment;
    private String interests;
    private String career;


    
    //기본 생성자
    public User() {}
    
    public User (int teamId) {
        this.teamId = teamId;
    }
    
    public User(String userId, String name, String nickName, String sex, String password) {
        super();
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
    }
    
    public User(String userId, int teamId,  String name, String nickName, String sex, String password) {
        super();
        this.userId = userId;
        this.teamId = teamId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
    }
    
    public User(String name, String nickName, String sex, int leader, String password,
            String position, String comment, String interests, String career) {
        super();
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
    }
    
    public User( String userId, String name, String nickName, String sex, String password, int leader, int teamId) {
        super();
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
        this.leader = leader;
        this.teamId = teamId;
    }
    
    public User(String userId, String name, String nickName, String sex,
            String comment, String interests, String career) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.comment = comment;
        this.interests = interests;
        this.career = career;
    }
    
    public User(int teamId, String userId, String name, String nickName, String sex, int leader, String password,
            String position, String comment, String interests, String career) {
        super();
        this.teamId = teamId;
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.leader = leader;
        this.password = password;
        this.position = position;
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
    public String getInterests() {
        return interests;
    }
    public void setInterests(String interests) {
        this.interests = interests;
    }
    public String getCareer() {
        return career;
    }
    public void setCareer(String career) {
        this.career = career;
    }
    
    
    public int getLeader() {
        return leader;
    }
    public void setLeader(int leader) {
        this.leader = leader;
    }
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

