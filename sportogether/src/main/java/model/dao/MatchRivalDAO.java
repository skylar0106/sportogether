package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.service.dto.*;

public class MatchRivalDAO {
private JDBCUtil jdbcUtil = null;
    
    public MatchRivalDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    /*
     * 라이벌 팀을 추천하기
     * 같은 레벨인 모든 팀 검색
     * 승률이 가장 비슷한 팀 이름 반환
     * */
    public String getMatchList(Team tm) {
        float gap = 0;
        String rival = null;
        
        float myRate = 0;
        List<Rival> teamList = null;
        ResultSet rs = null;
        
        try {
            /*내 승률 구하기*/
            StringBuilder sql1 = new StringBuilder();
            sql1.append("SELECT rate ");
            sql1.append("FROM teamScore JOIN team USING (teamId) ");
            sql1.append("Where teamId = ? ");
            
            jdbcUtil.setSqlAndParameters(sql1.toString(), new Object[] {tm.getTeamId()});  
            rs =  jdbcUtil.executeQuery();
            if(rs.next()) {
                myRate = rs.getFloat("rate");
            }
            /*상대팀 정보 구하기*/
            StringBuilder sql2 = new StringBuilder();
            sql2.append("SELECT name, teamId, rate ");
            sql2.append("FROM teamScore JOIN team USING (teamId) ");
            sql2.append("Where level = ? ");
            
            jdbcUtil.setSqlAndParameters(sql2.toString(), new Object[] {tm.getLevel()});
            
            teamList = new ArrayList<Rival>();
            rs =  jdbcUtil.executeQuery();
            
            while(rs.next()) {
                Rival r = new Rival(rs.getString("teamName"), 
                            rs.getInt("teamId"), 
                            rs.getFloat("rate"));
                teamList.add(r);
            }
            /*라이벌 찾기*/
            gap = Math.abs(myRate - teamList.get(0).getRate());
            rival = teamList.get(0).getTeamName();
            for(int i=1; i < teamList.size(); i++) {
                if(Math.abs(myRate -teamList.get(i).getRate()) < gap)
                    gap = Math.abs(myRate - teamList.get(i).getRate());
                rival = teamList.get(i).getTeamName();
            }
            /*db 라이벌 업데이트*/
            String sql3 = "UPDATE team "
                    + "SET rival= ? "
                    + "WHERE teamId=?";
            Object[] param = new Object[] {rival, tm.getTeamId()};                
            jdbcUtil.setSqlAndParameters(sql3, param);   // JDBCUtil에 update문과 매개 변수 설정
        
            int result = jdbcUtil.executeUpdate();  // update 문 실행

        }catch(SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } catch (Exception e) {
  
            e.printStackTrace();
        }finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }

        return rival;
    }
}
