package model.service.dto;

import java.sql.Date;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String nickName;
    private String sex;
    private String password;
    private String picture;
    private int leader;
    private int teamId;
    //추가
    private String comment;
    private List<String> interests;
    private String career;

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    //기본 생성자
    public User() {}
    
    //password 없는 생성자
    public User(String userId, String name, String nickName, String sex,
            String picture) {
    	this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;

        this.picture = picture;
    }
    
    public User(String userId, String name, String nickName, String sex, String password,
            String picture, int leader, int teamId) {
        super();
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
        this.picture = picture;
        this.leader = leader;
        this.teamId = teamId;
    }
    public User (int teamId) {
        this.teamId = teamId;
    }

    public User(String userId, String name, String nickName, String sex,
            String picture, String comment, List<String> interests, String career) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
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
