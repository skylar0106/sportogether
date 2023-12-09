package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.service.dto.*;

public class LankingDAO {
    private JDBCUtil jdbcUtil = null;
    
    public LankingDAO() {          
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }

    public Lanking findTeamLanking(int teamId) throws SQLException {
        String sql = "SELECT teamId, matches, win, lose, ranking, draw, rate, name "
                    + "FROM teamScore JOIN team USING (teamId) "
                    + "WHERE teamId=? ";              
        jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId});   // JDBCUtil에 query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery();     // query 실행
            if (rs.next()) {                        // 학생 정보 발견
                Lanking lanking = new Lanking(           // User 객체를 생성하여 현재 행의 정보를 저장
                        rs.getString("teamId"),
                        rs.getInt("matches"),
                        rs.getInt("win"),
                        rs.getInt("lose"),
                        rs.getInt("ranking"),
                        rs.getInt("draw"),
                        rs.getFloat("rate"),
                        rs.getString("name"));
                return lanking;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // resource 반환
        }
        return null;
    }

    /**
     * 랭킹 리스트 반환
     * 경기 수 많은 순
     * 승률 높은 순
     */
    public List<Lanking> findTeamLankingList() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT teamId, matches, win, lose, ranking, draw, rate, name ");
        sql.append("FROM teamScore JOIN team USING (teamId) ");
        sql.append("ORDER BY ranking ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {});       // JDBCUtil에 query문 설정
                    
        try {
            ResultSet rs = jdbcUtil.executeQuery();         // query 실행         
            List<Lanking> lankingList = new ArrayList<Lanking>();    // User들의 리스트 생성
            while (rs.next()) {
                Lanking lanking = new Lanking(           // User 객체를 생성하여 현재 행의 정보를 저장
                    rs.getString("teamId"),
                    rs.getInt("matches"),
                    rs.getInt("win"),
                    rs.getInt("lose"),
                    rs.getInt("ranking"),
                    rs.getInt("draw"),
                    rs.getFloat("rate"),
                    rs.getString("name"));
                lankingList.add(lanking);             // List에 User 객체 저장
            }       
            return lankingList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // resource 반환
        }
        return null;
    }
    
    /**
     * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
     * 해당하는 사용자 정보만을 List에 저장하여 반환.
     */
    public List<User> LankingList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT userId, name, email, NVL(commId, 0) AS commId, cName " 
                    + "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
                    + "ORDER BY userId";
        jdbcUtil.setSqlAndParameters(sql, null,                 // JDBCUtil에 query문 설정
                ResultSet.TYPE_SCROLL_INSENSITIVE,              // cursor scroll 가능
                ResultSet.CONCUR_READ_ONLY);                        
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();             // query 실행         
            int start = ((currentPage-1) * countPerPage) + 1;   // 출력을 시작할 행 번호 계산
            if ((start >= 0) && rs.absolute(start)) {           // 커서를 시작 행으로 이동
                List<User> userList = new ArrayList<User>();    // User들의 리스트 생성
                do {
                    User user = new User(           // User 객체를 생성하여 현재 행의 정보를 저장
                        rs.getString("userId"),
                        null,
                        rs.getString("name"),
                        rs.getString("email"),
                        null,
                        rs.getInt("commId"),
                        rs.getString("cName"));
                    userList.add(user);                         // 리스트에 User 객체 저장
                } while ((rs.next()) && (--countPerPage > 0));      
                return userList;                            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // resource 반환
        }
        return null;
    }

    

}
