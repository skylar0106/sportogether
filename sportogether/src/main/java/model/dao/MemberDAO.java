package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.service.dto.*;

public class MemberDAO {
    private JDBCUtil jdbcUtil = null;

    public MemberDAO() {
        jdbcUtil = new JDBCUtil();
    }

    // 특정 사용자의 정보 조회
    public Member getMemberByUserID(String userID) {
        Member member = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM spomember ");
        sql.append("WHERE userID = ? ");
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{userID});

        try {
            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                member = new Member(
                        rs.getString("userID"),
                        rs.getString("message"),
                        rs.getString("teamId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return member;
    }

    // 모든 MEMBER 정보 조회
    public List<Member> getAllMembers(Team team) {
        List<Member> members = new ArrayList<>();
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT spomember.userID, spomember.message, spomember.teamID ");
        sql.append("FROM spomember ");
        sql.append("JOIN team ON spomember.teamID = team.teamID ");
        sql.append("WHERE team.teamID = ? ");

	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{team.getTeamId()});

        try {
            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Member member = new Member(
                        rs.getString("userID"),
                        rs.getString("message"),
                        rs.getString("teamID")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return members;
    }

    // MEMBER 정보 추가
    public int addMember(Member member) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO spomember (userID, message) ");
        sql.append("VALUES (?, ?)");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{
                member.getUserID(),
                null
        });

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

    // MEMBER 코멘트 수정
    public int updateMember(Member member) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE spomember ");
        sql.append("SET message = ? ");
        sql.append("WHERE userID = ? ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{
                member.getMessage(),
                member.getUserID()
        });

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

    // MEMBER 정보 삭제
    public int deleteMember(String userID) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM spomember ");
        sql.append("WHERE userID = ? ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{userID});

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
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
    
    //Member의 팀 정보 삭제
    public Member removeMembersByTeam(Team teamId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE member ");
		sql.append("SET teamID = null ");
		sql.append("WHERE teamId = ?");
    	
    	return null;
    	
    }
}