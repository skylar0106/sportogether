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
    
    //대결신청
    public boolean insertBattleRequest(BattleRequest b) {
        StringBuilder dml = new StringBuilder();
        dml.append("INSERT INTO BATTLE ");
        dml.append("VALUES (?, ?, ?, ?, ?, ?, battle_seq.nextval) ");
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getTeamId(), b.getRivalId(), b.getSports(), b.getDate(),b.getMessage(), b.getApproval()});
        
        //battleid속성을 시퀀스로
        try {
        	jdbcUtil.executeUpdate();
        	 
        }catch(Exception ex) {
            ex.printStackTrace();
            jdbcUtil.rollback();
        }finally {      
	         jdbcUtil.commit();
	         jdbcUtil.close();   // resource 반환
	      }   
        return true;
    }
    
  
    //우리팀이 받은 대결 신청 리스트 반환
    public List<BattleRequest> getReceivedBattleRequest(int teamId){

    	List<BattleRequest> battleRequestList = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	
    	try {
        	sql.append("SELECT * ");
        	sql.append("FROM battle ");
        	sql.append("where rivalid = ? ");
        	sql.append("order by battleid, 'date' ");
        	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] { teamId});
        	rs = jdbcUtil.executeQuery();
        	
        	battleRequestList = new ArrayList<>();
    		while(rs.next()) {
    			BattleRequest bq = new BattleRequest();
       		 	bq.setTeamId(rs.getInt("teamid"));
                bq.setRivalId(rs.getInt("rivalid"));
                bq.setMessage(rs.getString("message"));
                bq.setDate(rs.getDate("date").toLocalDate());
                bq.setSports(rs.getString("sport"));
                bq.setApproval(rs.getString("approval"));
                bq.setBattleId(rs.getInt("battleid"));
    			battleRequestList.add(bq);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	return battleRequestList;
    	
    }
    
  //우리팀이 보낸 대결 신청 리스트 반환
    public List<BattleRequest> getSentBattleRequest(int teamId){

    	List<BattleRequest> battleRequestList = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	
    	try {
        	sql.append("SELECT * ");
        	sql.append("FROM battle ");
        	sql.append("where teamid = ? ");
        	sql.append("order by battleid, 'date' ");
        	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] {teamId});
        	rs = jdbcUtil.executeQuery();
        	
        	battleRequestList = new ArrayList<>();
    		while(rs.next()) {
    			BattleRequest bq = new BattleRequest();
       		 	bq.setTeamId(rs.getInt("teamid"));
                bq.setRivalId(rs.getInt("rivalid"));
                bq.setMessage(rs.getString("message"));
                bq.setDate(rs.getDate("date").toLocalDate());
                bq.setSports(rs.getString("sport"));
                bq.setApproval(rs.getString("approval"));
                bq.setBattleId(rs.getInt("battleid"));
    			battleRequestList.add(bq);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	return battleRequestList;
    	
    }
    
    //우리팀에 신청한 라이벌 팀의 정보 반환
    public List<Team> getReceivedRequestTeam(int teamId){
    	
    	List<Team> receivedList = new ArrayList<>();
    	ResultSet rs = null;
    	/*
    	 * 
    	 * */
    	try {
    		StringBuilder sql = new StringBuilder();
        	sql.append("SELECT * ");
        	sql.append("FROM team t join battle b ");
        	sql.append("on t.teamid = b.teamid ");
        	sql.append("where b.rivalid = ? ");
        	sql.append("order by b.battleid ");
        	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] {teamId});
        	rs = jdbcUtil.executeQuery();
        	
    		while(rs.next()) {
    			Team t = new Team(rs.getInt("teamid"), rs.getString("name"), rs.getString("spoleader"),
    				rs.getInt("tlevel"), rs.getString("sport"), rs.getString("location"),
    				rs.getInt("memberscount"), rs.getString("rival"));
    			receivedList.add(t);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    		return receivedList;
		}
    	
    	return receivedList;
    }
    
    //우리팀이 보낸 대결 신청 팀 반환
    public List<Team> getSentRequestTeam(int teamId){
    	/*
    	 * */
    	List<Team> sentRequestList = null;
    	ResultSet rs = null;
    	
    	try {
    		StringBuilder sql = new StringBuilder();
        	sql.append("select * ");
        	sql.append("from team t join battle  b ");
        	sql.append("on t.teamid = b.teamid ");
        	sql.append("where b.teamid = ? ");
        	sql.append("order by b.battleid ");
    	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] {teamId});
    		rs = jdbcUtil.executeQuery();
    		
    		sentRequestList = new ArrayList<>();
    		while(rs.next()) {
    			Team t = new Team(rs.getInt("teamid"), rs.getString("name"), rs.getString("spoleader"),
        				rs.getInt("tlevel"), rs.getString("sport"), rs.getString("location"),
        				rs.getInt("memberscount"), rs.getString("rival"));
    			sentRequestList.add(t);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	return sentRequestList;
    }
    
    
    
    //팀 아이디와 라이벌 팀을 받아 대결 신청 정보 반환
    /*
    public BattleRequest getBattleRequest(int teamId, int rivalId) {
        BattleRequest b = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM BATTLE ");
        sql.append("WHERE teamid = ? AND rivalid = ? ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId, rivalId});
        try {
            rs =  jdbcUtil.executeQuery();
           if(rs.next()) {
               b = new BattleRequest();
                b.setTeamId(teamId);
                b.setRivalId(rs.getInt("rivalid"));
                b.setMessage(rs.getString("message"));
                b.setDate(rs.getDate("date").toLocalDate());
                b.setSports(rs.getString("sport"));
                b.setApproval(rs.getString("approval"));
                b.setBattleId(rs.getInt("battleid"));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return b;
    }
    */
    //battleId로 대결 신청 정보 반환
    public BattleRequest getBattleRequestById(int battleId) {
    	
    	BattleRequest bq = null;
    	 ResultSet rs = null;
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * ");
         sql.append("FROM BATTLE ");
         sql.append("WHERE battleid = ? ");
         
         jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {battleId});
         
         try {
        	 rs = jdbcUtil.executeQuery();
        	 
        	 if(rs.next()) {
        		 bq = new BattleRequest();
        		 bq.setTeamId(rs.getInt("teamid"));
                 bq.setRivalId(rs.getInt("rivalid"));
                 bq.setMessage(rs.getString("message"));
                 bq.setDate(rs.getDate("date").toLocalDate());
                 bq.setSports(rs.getString("sport"));
                 bq.setApproval(rs.getString("approval"));
                 bq.setBattleId(rs.getInt("battleid"));
        	 }
         }catch (Exception e) {
        	 e.printStackTrace();
		}
         return bq;
    }
    
    
    //대결 신청을 승인함
    public boolean approveBattleRequest(BattleRequest b)  {
        StringBuilder dml = new StringBuilder();
        dml.append("UPDATE BATTLE ");
        dml.append("SET approval = 'true' ");
        dml.append("WHERE battleid = ? ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getBattleId()});
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
        dml.append("WHERE battleid = ? ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getBattleId()});
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
    
    //대결 신청 취소(삭제)
    public int cancelBattleRequest(BattleRequest b) {
    	StringBuilder dml = new StringBuilder();
    	dml.append("DELETE FROM BATTLE ");
    	dml.append("WHERE battleid=? ");
    	jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getBattleId()});
    	
    	try {
    		return  jdbcUtil.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return 0;
    }
    
}
