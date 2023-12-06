package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.service.dto.Request;

public class JoinRequestDAO {
    
    private JDBCUtil jdbcUtil = null;
    private MemberDAO member = null;
    
    public JoinRequestDAO() {
        jdbcUtil = new JDBCUtil();
        member = new MemberDAO();
    }
    
    //가입신청함 1
    public int createJoinRequest(Request r) {
        StringBuilder dml = new StringBuilder();
        dml.append("INSERT INTO REQUEST ");
        //teamid, teamName, userId, userName, message, date 기본키 팀아이디+유저아이디
        dml.append("VALUES (?, ?, ?, ?, ?, ?) ");
        
        jdbcUtil.setSqlAndParameters(dml.toString(), new Object[] {r.getTeamId(), r.getTeamName(), r.getUserId(), r.getUserName(), r.getMassage(), r.getDate() });
        try {
           int result = jdbcUtil.executeUpdate();
           return result;
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return 0;
    }
    
    //가입 신청 승인
    //가입 신청 거절
    /*
     * REQUEST 테이블 추가 : 가입 신청 정보, (teamid, userid,  massage, date)로 구성
     * 팀에 들어온 가입 신청 목록을 검색하고 
     * 각 신청을 Request객체에 저장해서 리스트로 반환함
     * 
     * */
    
    // 가입 신청 목록 반환
    public List<Request> getRequestList(String teamId) {
        List<Request> requestList = null;

        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT teamid, userid, massage, date ");
        sql.append("FROM REQUEST JOIN TEAM USING(teamId) ");
        sql.append("WHERE teamid = ? ");
        sql.append("ORDER BY date ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{teamId});

        try {
            requestList = new ArrayList<>();
            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setTeamId(rs.getString("teamid"));
                r.setUserId(rs.getString("userid"));
                r.setDate(rs.getDate("date"));
                r.setMassage(rs.getString("massage"));
                requestList.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return requestList;
    }

    // 요청 승인 시 팀 멤버로 추가
    public boolean approveRequest(String teamID, String userID) {
        // 요청을 승인하면 SPOMEMBER 테이블에 추가
        boolean success = member.addMember(userID, null, teamID);

        // 승인한 경우 요청 목록에서 해당 요청 삭제
        if (success) {
            removeRequest(teamID, userID);
        }

        return success;
    }

    // 요청 거절 시 요청 목록에서 제거
    public boolean rejectRequest(String teamID, String userID) {
        return removeRequest(teamID, userID);
    }

    // 요청 목록에서 제거
    private boolean removeRequest(String teamID, String userID) {
        boolean success = false;

        try {
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM REQUEST ");
            query.append("WHERE teamId = ? AND userId = ? ");
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{teamID, userID});
            jdbcUtil.executeQuery();
            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return success;
    }
}
