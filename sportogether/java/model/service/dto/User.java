package model.service.dto;

import java.sql.Date;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String nickName;
    private String sex;
    private Date birth;
    private String password;
    private String picture;
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
    public User(String userId, String name, String nickName, String sex, Date birth,
            String picture) {
    	this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.picture = picture;
    }
    
    public User(String userId, String name, String nickName, String sex, Date birth, String password,
            String picture) {
        super();
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.birth = birth;
        this.password = password;
        this.picture = picture;
    }

    public User(String userId, String name, String nickName, String sex, Date birth, 
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
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
}
