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
    //db BATTLE(teamid, rivalid, sports, date, message, approval, battleid)
    public boolean insetBattleRequest(BattleRequest b) {
        StringBuilder dml = new StringBuilder();
        dml.append("INSERT INTO BATTLE ");
        //teamid, rivalid, sports, date, message, approve
        dml.append("VALUES (?, ?, ?, ?, ?, ?, ?) ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {b.getTeamId(), b.getRivalId(), b.getSports(), b.getDate(), b.getApproval(), b.getBattleId()});
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
                b.setApproval(rs.getString("approval"));
                b.setBattleId(rs.getString("battleid"));
                battleRequestList.add(b);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        
        return battleRequestList;
    }
    
    //우리팀이 받은 대결 신청 리스트 반환
    public List<BattleRequest> getReceivedBattleRequest(String teamId){

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
       		 	bq.setTeamId(rs.getString("teamid"));
                bq.setRivalId(rs.getString("rivalid"));
                bq.setMessage(rs.getString("message"));
                bq.setDate(rs.getString("date"));
                bq.setSports(rs.getString("sports"));
                bq.setApproval(rs.getString("approval"));
                bq.setBattleId(rs.getString("battleid"));
    			battleRequestList.add(bq);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	return battleRequestList;
    	
    }
    
  //우리팀이 보낸 대결 신청 리스트 반환
    public List<BattleRequest> getSentBattleRequest(String teamId){

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
       		 	bq.setTeamId(rs.getString("teamid"));
                bq.setRivalId(rs.getString("rivalid"));
                bq.setMessage(rs.getString("message"));
                bq.setDate(rs.getString("date"));
                bq.setSports(rs.getString("sports"));
                bq.setApproval(rs.getString("approval"));
                bq.setBattleId(rs.getString("battleid"));
    			battleRequestList.add(bq);
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	return battleRequestList;
    	
    }
    
    //우리팀이 받은 대결 신청 팀의 이름 리스트 반환
    //teamId, rivalId, sports, message, date, approval 모두 문자열
    public List<String> getReceivedBattleRequestTeamName(String teamId){
    	
    	List<String> battleRequestRivalTeamNameList = null;
    	ResultSet rs = null;
    	/*
    	 * 
    	 * */
    	try {
    		StringBuilder sql = new StringBuilder();
        	sql.append("SELECT name ");
        	sql.append("FROM team ");
        	sql.append("on team.teamid = battle.teamid ");
        	sql.append("where battle.rivalid = ? ");
        	sql.append("order by 'date' ");
        	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] { teamId});
        	rs = jdbcUtil.executeQuery();
        	
        	battleRequestRivalTeamNameList = new ArrayList<>();
    		while(rs.next()) {
    			battleRequestRivalTeamNameList.add(rs.getString("name"));
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	return battleRequestRivalTeamNameList;
    }
    
    //우리팀이 보낸 대결 신청 팀의 이름 리스트 반환
    public List<String> getSentBattleRequestTeamName(String teamId){
    	/*
    	 * */
    	List<String> battleRequestRivalTeamNameList = null;
    	ResultSet rs = null;
    	
    	try {
    		StringBuilder sql = new StringBuilder();
        	sql.append("select team.name ");
        	sql.append("from team on team.teamid = battle.rivalid ");
        	sql.append("where battle.teamid = ? ");
        	sql.append("order by 'date' ");
    	
        	jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] { teamId});
    		rs = jdbcUtil.executeQuery();
    		
    		battleRequestRivalTeamNameList = new ArrayList<String>();
    		while(rs.next()) {
    			battleRequestRivalTeamNameList.add(rs.getString("name"));
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	return battleRequestRivalTeamNameList;
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
                b.setApproval(rs.getString("approval"));
                b.setBattleId(rs.getString("battleid"));
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
                b.setApproval(rs.getString("approval"));
                b.setBattleId(rs.getString("battleid"));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return b;
    }
    
    //battleId로 대결 신청 정보 반환
    public BattleRequest getBattleRequestById(String battleId) {
    	
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
        		 bq.setTeamId(rs.getString("teamid"));
                 bq.setRivalId(rs.getString("rivalid"));
                 bq.setMessage(rs.getString("message"));
                 bq.setDate(rs.getString("date"));
                 bq.setSports(rs.getString("sports"));
                 bq.setApproval(rs.getString("approval"));
                 bq.setBattleId(rs.getString("battleid"));
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
    
    //대결 신청 취소
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
