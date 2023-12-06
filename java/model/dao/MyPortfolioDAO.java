package model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.service.dto.User;
public class MyPortfolioDAO {
    private JDBCUtil jdbcUtil = null;

    public MyPortfolioDAO() {
        jdbcUtil = new JDBCUtil();
    }

    // 유저 아이디로 포트폴리오 검색
    public User getMyPortfolioById(String userId) {
        User user = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM spouser ");
        sql.append("WHERE userID = ? ");
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] { userId });
//String userId, String name, String nickName, String sex, Date birth, 
       // String picture, String comment, List<String> interests, String career
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
                    rs.getString("comment"),
                    getInterestsList(rs.getString("interests")),
                    rs.getString("career")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return user;
    }

    // 포트폴리오 정보 수정 및 저장
    public void updateMyPortfolio(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE spouser ");
        sql.append("SET name = ?, nickName = ?, position = ?, sex = ?, birth = ?, ");
        sql.append("password = ?, picture = ?, team = ?, comment = ?, interests = ?, career = ? ");
        sql.append("WHERE userid = ? ");
        
        Object param[] = {user.getName(),user.getNickName(),user.getPosition(),user.getSex(), user.getBirth(), user.getPassword()
                ,user.getPicture(), user.getTeam(),user.getComment(),getInterestsString(user.getInterests()),user.getCareer(),user.getUserId()};
        jdbcUtil.setSqlAndParameters(sql.toString(), param);
        
        try {
                jdbcUtil.executeUpdate();
                /*
                 * PreparedStatement preparedStatement = jdbcUtil.getConnection().prepareStatement(
            "UPDATE spouser SET name = ?, nickName = ?, position = ?, sex = ?, birth = ?, " +
            "password = ?, picture = ?, team = ?, comment = ?, interests = ?, career = ? " +
            "WHERE userID = ?")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickName());
            preparedStatement.setString(3, user.getPosition());
            preparedStatement.setString(4, user.getSex());
            preparedStatement.setDate(5, user.getBirth());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getPicture());
            preparedStatement.setString(8, user.getTeam());
            preparedStatement.setString(9, user.getComment());
            preparedStatement.setString(10, getInterestsString(user.getInterests()));
            preparedStatement.setString(11, user.getCareer());

            preparedStatement.setString(12, user.getUserId());

            preparedStatement.executeUpdate();
            */

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    // interests를 문자열로 변환
    private static String getInterestsString(List<String> interestsList) {
    StringBuilder interestsString = new StringBuilder();
    for (String interest : interestsList) {
        interestsString.append(interest).append(",");
    }
    // 마지막 쉼표 제거
    if (interestsString.length() > 0) {
        interestsString.deleteCharAt(interestsString.length() - 1);
    }
    return interestsString.toString();
}

// 문자열을 리스트로 변환
private static List<String> getInterestsList(String interestsString) {
    List<String> interestsList = new ArrayList<>();
    if (interestsString != null && !interestsString.isEmpty()) {
        String[] interestsArray = interestsString.split(",");
        for (String interest : interestsArray) {
            interestsList.add(interest);
        }
    }
    return interestsList;
}
}
