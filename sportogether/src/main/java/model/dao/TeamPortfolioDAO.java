package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import model.service.dto.Match;
import model.service.dto.Team;
import model.service.dto.TeamScore;

public class TeamPortfolioDAO {
	 private JDBCUtil jdbcUtil = null;

	 public TeamPortfolioDAO() {
		 jdbcUtil = new JDBCUtil();
	 }
	
	 public Team getTeamInfo(int teamID) {
		    Team team = null;
		    ResultSet rs = null;
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT * FROM Team WHERE teamID = ?");
		    jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});

		    try {
		        rs = jdbcUtil.executeQuery();
		        if (rs != null && rs.next()) {
		            team = new Team(
		                rs.getInt("teamID"),
		                rs.getString("name"),
		                rs.getString("spoleader"),
		                rs.getInt("tlevel"),
		                rs.getString("sport"),
		                rs.getString("location"),
		                rs.getInt("membership"),
		                rs.getString("rival")
		            );
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        jdbcUtil.close();
		    }

		    return team;
		}
	 
    // 팀 전적
    public TeamScore updateMatchResult(String teamID, String result) {
    	TeamScore teamscore = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT * FROM \"MATCH\" WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if (rs != null && rs.next()) {  // rs가 null이 아니고 다음 레코드가 있는 경우에만 처리
                teamscore = new TeamScore(
                        rs.getInt("teamID"),
                        rs.getInt("matches"),
                        rs.getInt("win"),
                        rs.getInt("lose"),
                        rs.getInt("ranking"),
                        rs.getInt("draw"),
                        rs.getDouble("rate")
                );
            }
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    	
    	return teamscore;
    }

    // 최근 매치 일자 가져오기
    public String getRecentMatchDate(int teamID) {
    	String date = null;
    	String recentDate = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT \"DATE\" FROM \"MATCH\" WHERE TEAMID = ?");
    	sql.append("ORDER BY \"DATE\" DESC FETCH FIRST 1 ROWS ONLY");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs.next()) {
    			date = rs.getString("DATE");

    	        // 띄어쓰기로 문자열 분할
    	        String[] tmpDate = date.split(" ");

    	        // 첫 번째 부분 가져오기
    	        if (tmpDate.length > 0) {
    	            recentDate = tmpDate[0];
    	            System.out.println(recentDate); // 결과 출력
    	        } else {
    	            System.out.println("분할된 부분이 없습니다.");
    	        }
    	        
    			return recentDate;
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    	
    	return recentDate;
    }

    // 팀 멤버 수 가져오기
    public int getTeamMemberCount(int teamID) {
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT COUNT(*) AS memberCount FROM spomember WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			return rs.getInt("memberCount");
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}

        return 0;
    }

    // 팀 소개 멘트 가져오기
    public String getTeamIntroduction(int teamID) {
    	String comment = "";
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT \"COMMENT\" FROM Team WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			comment = rs.getString("comment");
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    	
    	return comment;
    }




//    // 팀 레벨 업데이트
//    public void updateTeamLevel(int level, String teamID) {
//    	// LevelDAO를 사용하여 팀 레벨을 가져옴
//        LevelDAO levelDAO = new LevelDAO();
//        Team team = null;
//
//    	ResultSet rs = null;
//    	StringBuilder sql = new StringBuilder();
//    	sql.append("UPDATE Team SET level = ? WHERE teamID = ?");
//    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {level, teamID});
//    	   	
//    	try {
//    		rs = jdbcUtil.executeQuery();
//    		if(rs.next()) {
//    			team = new Team(
//    				rs.getString("teamID"),
//    				rs.getString("name"),
//    				rs.getString("spoleader"),
//    				rs.getInt("level"),
//    				rs.getString("sport"),
//    				rs.getString("location"),
//    				rs.getInt("membership"),
//    				rs.getString("rival")
//    			);
//    			level = levelDAO.getMatchList(team);
//    		}
//    	}
//    	catch(SQLException e){
//    		e.printStackTrace();
//    	}finally {
//    		jdbcUtil.close();
//    	}
//    }

    // 새로운 팀 추가
    public void addNewTeam(String teamID) {
    	ResultSet rs = null;
    	@SuppressWarnings("unused")
		Team team = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("INSERT INTO Team (teamID) VALUES (?)");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			team = new Team(
        				rs.getInt("teamID"),
        				rs.getString("name"),
        				rs.getString("spoleader"),
        				rs.getInt("level"),
        				rs.getString("sport"),
        				rs.getString("location"),
        				rs.getInt("membership"),
        				rs.getString("rival")
        			);
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    }

	public TeamScore getTeamScore(int teamID) {
		TeamScore teamScore = null;
	    ResultSet rs = null;
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT * FROM TeamScore WHERE teamID = ?");
	    jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] { teamID });

	    try {
	        rs = jdbcUtil.executeQuery();
	        if (rs != null && rs.next()) {
	            teamScore = new TeamScore(
	                rs.getInt("teamID"),
	                rs.getInt("matches"),
	                rs.getInt("win"),
	                rs.getInt("lose"),
	                rs.getInt("ranking"),
	                rs.getInt("draw"),
	                rs.getDouble("rate")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	    return teamScore;
	}
}
