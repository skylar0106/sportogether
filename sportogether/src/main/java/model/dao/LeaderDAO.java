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
    public boolean addLeader(String userID) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO SPOLEADER (userID) VALUES (?)");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{userID});

        return jdbcUtil.executeUpdate() > 0;
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
                leader = new SPOLeader(rs.getString("userID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return leader;
    }
}
