package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.service.dto.BattleRequest;
import model.service.dto.Team;

public class BattleRequestDAO {
    private JDBCUtil jdbcUtil = null;
    
    public BattleRequestDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    /*팀 대결 신청은 한 팀에 하나만 가능하다고 가정*/
    
    //대결신청
    
    public boolean insetBattleRequest(BattleRequest b) {
        StringBuilder dml = new StringBuilder();
        dml.append("INSERT INTO BATTLE ");
        //teamid, rivalid, sports, date, message, approve
        dml.append("VALUES (?, ?, ?, ?, ?, ?) ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getTeamId(), b.getRivalId(), b.getSports(), b.getDate(), b.getApproval()});
        try {
           jdbcUtil.executeUpdate();
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return true;
    }
    
    //대결신청 리스트 반환
    public List<BattleRequest> getBattleRequestList(Team t){
        List<BattleRequest> battleRequestList = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM BATTLE ");
        sql.append("WHERE teamid = ? ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {t.getTeamId()});
        try {
            battleRequestList = new ArrayList<>();
            rs =  jdbcUtil.executeQuery();
            while(rs.next()) {
                BattleRequest b = new BattleRequest();
                b.setTeamId(t.getTeamId());
                b.setRivalId(rs.getString("rivalid"));
                b.setMessage(rs.getString("message"));
                b.setDate(rs.getString("date"));
                b.setSports(rs.getString("sports"));
                battleRequestList.add(b);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        
        return battleRequestList;
    }
    //팀아이디로
    public List<BattleRequest> getBattleRequestList(String teamId){
        List<BattleRequest> battleRequestList = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM BATTLE ");
        sql.append("WHERE teamid = ? ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId});
        try {
            battleRequestList = new ArrayList<>();
            rs =  jdbcUtil.executeQuery();
            while(rs.next()) {
                BattleRequest b = new BattleRequest();
                b.setTeamId(teamId);
                b.setRivalId(rs.getString("rivalid"));
                b.setMessage(rs.getString("message"));
                b.setDate(rs.getString("date"));
                b.setSports(rs.getString("sports"));
                battleRequestList.add(b);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        
        return battleRequestList;
    }
    
    //팀 아이디와 라이벌 팀을 받아 대결 신청 정보 반환
    public BattleRequest getBattleRequest(Team t, String rivalId) {
        BattleRequest b = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM BATTLE ");
        sql.append("WHERE teamid = ? AND rivalid = ? ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {t.getTeamId(), rivalId});
        try {
            rs =  jdbcUtil.executeQuery();
           if(rs.next()) {
               b = new BattleRequest();
                b.setTeamId(t.getTeamId());
                b.setRivalId(rs.getString("rivalid"));
                b.setMessage(rs.getString("message"));
                b.setDate(rs.getString("date"));
                b.setSports(rs.getString("sports"));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return b;
    }
    
    //대결 신청을 승인함
    public boolean approveBattleRequest(BattleRequest b)  {
        StringBuilder dml = new StringBuilder();
        dml.append("UPDATE BATTLE ");
        dml.append("SET approval = 'true' ");
        dml.append("WHERE teamid = ? AND rivalId = ? ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getTeamId(), b.getRivalId()});
        try {
            jdbcUtil.executeUpdate();
        }catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }finally {
            jdbcUtil.close();
        }
        return true;
    }
    
    //대결 신청 거절함
    public boolean rejectBattleRequest(BattleRequest b)  {
        StringBuilder dml = new StringBuilder();
        dml.append("UPDATE BATTLE ");
        dml.append("SET approval = 'false' ");
        dml.append("WHERE teamid = ? AND rivalId = ? ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getTeamId(), b.getRivalId()});
        try {
            jdbcUtil.executeUpdate();
        }catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            jdbcUtil.close();
        }
        return true;
    }
    
}
