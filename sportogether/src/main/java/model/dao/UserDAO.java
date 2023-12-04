package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.service.dto.*;

public class UserDAO {
    private JDBCUtil jdbcUtil = null;

    public UserDAO() {
        jdbcUtil = new JDBCUtil();
    }

    /*
     * 유저 아이디로 유저 검색
     * 로그인, 사용자 정보 수정,next()){
     *  팀 멤버 조회 등에 사용?
     * 
     * */
    public User getUserById(String userId) {
        User user = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM spouser ");
        sql.append("WHERE userID = ? "); // 이 부분 변경하면 다른 필드로도 검색
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {userId});

        try {
            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("userID"),
                    rs.getString("name"),
                    rs.getString("nickName"),
                    rs.getString("sex"),
                    rs.getDate("birth"),
                    rs.getString("picture"),
                    rs.getString("comment"), // 추가된 부분
                    getInterestsList(rs.getString("interests")), // 추가된 부분
                    rs.getString("career") // 추가된 부분
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return user;
    }

    // interests를 문자열로 변환
    private static List<String> getInterestsList(String interestsString) {
        List<String> interestsList = new ArrayList<>();

        if (interestsString != null && !interestsString.isEmpty()) {
            interestsList = Arrays.asList(interestsString.split(","));
        }

        return interestsList;
    }

    public Member updateInfo(Member member) {
    	ResultSet rs = null;

    	try {
        	StringBuilder query = new StringBuilder();

        	query.append("UPDATE SPOUSER ");
        	query.append("SET name = ?, nickname = ?, sex = ?, birth = ?, password = ?, picture = ?, comment = ?, interests = ?, career = ? ");
        	query.append("WHERE userID = ? ");
        	jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{
                	member.getName(),
	                member.getNickname(),
	                member.getSex(),
	                member.getBirth(),
	                member.getPassword(),
	                member.getPicture(),
	                member.getComment(),
	                member.getInterests(),
	                member.getCareer(),
	                member.getUserID()
	        });
        	rs = jdbcUtil.executeQuery();
    	} catch (Exception ex) {
        	ex.printStackTrace();
    	} finally {
        	jdbcUtil.close();
    	}
    	return null;
	}


    }
}
