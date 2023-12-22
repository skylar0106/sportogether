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
                     rs.getString("comment"),
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
    public int updateMyPotpolio(User usr) {
        int result = 0;
        
        String query = "UPDATE SPOUSER SET COMMENT = ?, SET INTERESTS = ?, SET CAREER = ? WHERE USERID = ?";
        Object[] param = new Object[] {usr.getComment(), usr.getInterests(), usr.getCareer(), usr.getUserId()};
        
        jdbcUtil.setSqlAndParameters(query, param);
        try {
            result = jdbcUtil.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        
        return result;
    }
}