package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.service.dto.*;

public class LeaderDAO {
    private JDBCUtil jdbcUtil = null;

    public LeaderDAO() {
        jdbcUtil = new JDBCUtil();
    }

    // 새로운 리더를 spoleader 테이블에 추가
    public int createSpoLeader(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO SPOLEADER VALUES (?) ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{user.getUserId()});

		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;	
    }
    
    // Team dto에서 리더를 변경
	public int updateLeader(Team team) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Team ");
		sql.append("SET leaderid= ? ");
		sql.append("WHERE teamId=? ");
						
		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {team.getSpoLeader(), team.getTeamId()});	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

    // 특정 팀의 리더를 가져옴
    public Leader getLeaderByTeam(String teamID) {
        Leader leader = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userID FROM SPOLEADER ");
        sql.append("WHERE userID = (SELECT spoleader FROM TEAM WHERE teamID = ?)");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{teamID});

        try {
            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                leader = new Leader(rs.getString("userID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return leader;
    }
}
