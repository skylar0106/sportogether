package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import model.service.dto.User;

public class MyPortfolioDAO {
     private JDBCUtil jdbcUtil = null;

     public MyPortfolioDAO() {
         jdbcUtil = new JDBCUtil();
     }
    
     public User getUserInfo(String userId) {
         User user = null;
         ResultSet rs = null;
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * FROM SPOUSER WHERE USERID = ?");
         jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {userId});

         try {
             rs = jdbcUtil.executeQuery();
             if (rs != null && rs.next()) {
                 user = new User(
                     rs.getString("userID"),
                     rs.getString("name"),
                     rs.getString("nickName"),
                     rs.getString("sex"),
                     rs.getString("tcomment"),
                     rs.getString("interests"),
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

    // 나의 포트폴리오 업데이트
     public int updateMyPortfolio(User user) {
         StringBuilder query = new StringBuilder();

         query.append("UPDATE SPOUSER ");
         query.append("SET career = ?, interests = ?, tcomment = ?");
         query.append("WHERE userID = ? ");
         jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{
                 user.getCareer(),
                 user.getInterests(), 
                 user.getComment(),
                 user.getUserId()
         });
         try {
             int result = jdbcUtil.executeUpdate();
             return result;
         } catch (Exception ex) {
             jdbcUtil.rollback();
             ex.printStackTrace();
         } finally {
             jdbcUtil.commit();
             jdbcUtil.close();
         }
         return 0;
     }
     
     public User findUser(String userId) throws SQLException {
         String sql = "SELECT name, nickname, sex, career, interests, tcomment  "
                     + "FROM spouser "
                     + "WHERE userid=? ";              
         jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

         try {
             ResultSet rs = jdbcUtil.executeQuery();     // query 실행
             if (rs.next()) {                        // 학생 정보 발견
                 User user = new User(       // User 객체를 생성하여 학생 정보를 저장
                     userId,
                     rs.getString("name"),
                     rs.getString("nickname"),
                     rs.getString("sex"),
                     rs.getString("career"),
                     rs.getString("interests"),                    
                     rs.getString("tcomment"));
                 return user;
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         } finally {
             jdbcUtil.close();       // resource 반환
         }
         return null;
     }
}