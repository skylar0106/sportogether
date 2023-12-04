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
                        rs.getString("message")
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
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT spomember.userID, spomember.message, spomember.teamID ");
        sql.append("FROM spomember ");
        sql.append("JOIN team ON spomember.teamID = team.teamID ");
        sql.append("WHERE team.teamID = ? ");

	jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{teamID});

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
    public boolean addMember(Member member) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO spomember (userID, message) ");
        sql.append("VALUES (?, ?)");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{
                member.getUserID(),
                NULL
        });

        return jdbcUtil.executeUpdate() > 0;
    }

    // MEMBER 코멘트 수정
    public boolean updateMember(Member member) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE spomember ");
        sql.append("SET message = ? ");
        sql.append("WHERE userID = ? ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{
                member.getMessage(),
                member.getUserID()
        });

        return jdbcUtil.executeUpdate() > 0;
    }

    // MEMBER 정보 삭제
    public boolean deleteMember(String userID) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM spomember ");
        sql.append("WHERE userID = ? ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{userID});

        return jdbcUtil.executeUpdate() > 0;
    }
}
