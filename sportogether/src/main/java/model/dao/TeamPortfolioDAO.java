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
	
	 public Team getTeamInfo(String teamID) {
		    Team team = null;
		    ResultSet rs = null;
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT * FROM Team WHERE teamID = ?");
		    jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});

		    try {
		        rs = jdbcUtil.executeQuery();
		        if (rs != null && rs.next()) {
		            team = new Team(
		                rs.getString("teamID"),
		                rs.getString("name"),
		                rs.getString("spoleader"),
		                rs.getInt("level"),
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
	 
    // 팀 전적 업데이트
    public TeamScore updateMatchResult(String teamID, String result) {
    	TeamScore teamscore = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE TeamScore SET matches = matches + 1");
    	sql.append((result.equals("win") ? "win" : result.equals("lose") ? "lose" : "draw") + " = ");
    	sql.append((result.equals("win") ? "win + 1" : result.equals("lose") ? "lose + 1" : "draw + 1"));
    	sql.append(" WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if (rs != null && rs.next()) {  // rs가 null이 아니고 다음 레코드가 있는 경우에만 처리
                teamscore = new TeamScore(
                        rs.getString("teamID"),
                        rs.getInt("matches"),
                        rs.getInt("win"),
                        rs.getInt("lose"),
                        rs.getInt("ranking"),
                        rs.getInt("draw"),
                        rs.getDouble("rate"),
                        rs.getString("comment")
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

    // 승률 계산
    public double calculateWinRatio(String teamID) { 
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT win, lose, draw FROM TeamScore WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			int win = rs.getInt("win");
                int lose = rs.getInt("lose");
                int draw = rs.getInt("draw");

                int totalMatches = win + lose + draw;

                if (totalMatches > 0) {
                    return (double) win / totalMatches * 100.0;
                }
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}

        return 0.0;
    }

    // 최근 매치 일자 가져오기
    public LocalDate getRecentMatchDate(String teamID) {
    	Match match = null;
    	LocalDate date = null;
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT date FROM Match WHERE teamID = ?");
    	sql.append("ORDER BY date DESC FETCH FIRST 1 ROWS ONLY");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamID});
    	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			match = new Match();
    			date = match.getDate();
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    	
    	return date;
    }

    // 팀 멤버 수 가져오기
    public int getTeamMemberCount(String teamID) {
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
    public String getTeamIntroduction(String teamID) {
    	String comment = "";
    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT comment FROM Team WHERE teamID = ?");
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

    // 팀 레벨 업데이트
    public void updateTeamLevel(int level, String teamID) {
    	// LevelDAO를 사용하여 팀 레벨을 가져옴
        LevelDAO levelDAO = new LevelDAO();
        Team team = null;

    	ResultSet rs = null;
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE Team SET level = ? WHERE teamID = ?");
    	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {level, teamID});
    	   	
    	try {
    		rs = jdbcUtil.executeQuery();
    		if(rs != null && rs.next()) {
    			team = new Team(
    				rs.getString("teamID"),
    				rs.getString("name"),
    				rs.getString("spoleader"),
    				rs.getInt("level"),
    				rs.getString("sport"),
    				rs.getString("location"),
    				rs.getInt("membership"),
    				rs.getString("rival")
    			);
    			level = levelDAO.getMatchList(team);
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		jdbcUtil.close();
    	}
    }

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
        				rs.getString("teamID"),
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

	public TeamScore getTeamScore(String teamID) {
		TeamScore teamScore = null;
	    ResultSet rs = null;
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT matches, win, lose, draw, rate, comment FROM TeamScore WHERE teamID = ?");
	    jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] { teamID });

	    try {
	        rs = jdbcUtil.executeQuery();
	        if (rs != null && rs.next()) {
	            teamScore = new TeamScore(
	                rs.getString("teamID"),
	                rs.getInt("matches"),
	                rs.getInt("win"),
	                rs.getInt("lose"),
	                rs.getInt("draw"),
	                rs.getInt("ranking"),
	                rs.getDouble("rate"),
	                rs.getString("comment")
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