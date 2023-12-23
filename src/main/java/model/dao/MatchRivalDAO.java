package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.team.TeamPortfolioController;
import model.*;
import model.service.dto.*;

public class MatchRivalDAO {
    
private JDBCUtil jdbcUtil = null;
private static final Logger logger = (Logger) LoggerFactory.getLogger(TeamPortfolioController.class);
    
    public MatchRivalDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    
    /*
     * 라이벌 팀을 추천하기
     * 같은 레벨인 모든 팀 검색
     * 승률이 가장 비슷한 팀 이름 반환
     * */
    public Team findByTeamName(int teamId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("FROM team ");
        query.append("WHERE teamId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{teamId}); // JDBCUtil에 질의문과 파라미터 설정   
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {        // 검색 결과 존재
                Team tm = new Team();          
                 tm.setTeamId(teamId);
                 tm.setName(rs.getString("name"));
                 tm.setSpoLeader(rs.getString("spoleader"));
                 tm.setLevel(rs.getInt("LEVEL"));
                 tm.setSport(rs.getString("sport"));
                 tm.setLocation(rs.getString("location"));
                 tm.setMembership(rs.getInt("membersCount"));
                 tm.setRival(rs.getString("rival"));
                return tm;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
        return null;
    }
    
    public Rival getMatchList(Team tm) {
        float gap = 0;
        Rival rival = new Rival();
        float rivalRate = 0;
        
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

            sql2.append("SELECT name, team.teamId, rate ");
            sql2.append("FROM teamScore JOIN team ON teamScore.teamID = team.teamID ");
            sql2.append("WHERE tlevel = ? ");

            
            jdbcUtil.setSqlAndParameters(sql2.toString(), new Object[] {tm.getLevel()});
            
            teamList = new ArrayList<Rival>();
            rs =  jdbcUtil.executeQuery();
            logger.debug("TEAMlIST: {}", rs);
            
            while(rs.next()) {
                Rival r = new Rival(rs.getString("name"), 
                            rs.getInt("teamId"), 
                            rs.getFloat("rate"));
                if(r.getTeamId()!= tm.getTeamId())
                    teamList.add(r);
                logger.debug("TEAMlIST: {}", teamList);
                logger.debug("TEAMlIST: {}", tm.getTeamId());
                logger.debug("TEAMlIST: {}", r.getTeamId());
            }
            /*라이벌 찾기*/
            gap = 1000;
//            rival = teamList.get(0).getTeamName();
            rival = teamList.get(0);
            for(int i=0; i < teamList.size(); i++) {
                if(Math.abs(myRate -teamList.get(i).getRate()) < gap) {
                    gap = Math.abs(myRate - teamList.get(i).getRate());
//                    rival = teamList.get(i).getTeamName();
//                    rivalRate = teamList.get(i).getRate();
                    rival = teamList.get(i);
                }
            }
            /*db 라이벌 업데이트*/
            String sql3 = "UPDATE team "
                    + "SET rival= ? "
                    + "WHERE teamId=?";
            Object[] param = new Object[] {rival.getTeamName(), tm.getTeamId()};                
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