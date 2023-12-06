package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.service.dto.*;

public class MatchDAO {
    private JDBCUtil jdbcUtil = null;
    
    public MatchDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    /*
     * 경기 결과 조회(모든 경기 결과)
     * 경기(MATCH)테이블의 모든 행 검색, 
     * Match 리스트에 저장 후 반환
     * */
    public List<Match> getMatchList() {
        List<Match> matchList = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM MATCH ");
        sql.append("ORDER BY date ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {});
        try {
           matchList = new ArrayList<Match>();
            jdbcUtil.executeQuery();
            
            rs =  jdbcUtil.executeQuery();
            while(rs.next()) {
                Match m = new Match(rs.getString("winner"), rs.getString("loser"), rs.getString("sport"), rs.getDate("date").toLocalDate(), rs.getInt("winpoint"), rs.getInt("losepoint"));
                /*생성자 매개변수
                 * String winner, String loser, String sport, LocalDate date, int winpoint, int losepoint
                 */
                matchList.add(m);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }

        return matchList;
    }
}
