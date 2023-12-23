package model.dao;

import java.security.spec.ECFieldF2m;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.service.dto.Request;

public class JoinRequestDAO {
    
    private JDBCUtil jdbcUtil = null;
    private MemberDAO member = null;
   // private final String FIND_JOIN_REQUEST = "";
    
    public JoinRequestDAO() {
        jdbcUtil = new JDBCUtil();
        member = new MemberDAO();
    }
    
    /*
     * 
     * REQUEST테이블
     * 가입신청을 신청(생성), 삭제, 수정, 조회
     * 
     * */
    
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
     * REQUEST 테이블 추가 : 가입 신청 정보, (teamid, teamname, userid, username, massage, date)로 구성
     * 팀에 들어온 가입 신청 목록을 검색하고 
     * 각 신청을 Request객체에 저장해서 리스트로 반환함
     * 
     * */
    
    // 팀에 들어온 가입 신청 목록 반환
    public List<Request> getRequestList(int teamId) {
        List<Request> requestList = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT teamid, teamname, userid, username, massage, date ");
        sql.append("FROM REQUEST ");
        sql.append("WHERE teamid = ? ");
        sql.append("ORDER BY date ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{teamId});

        try {
            requestList = new ArrayList<>();
            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setTeamId(rs.getString("teamid"));
                r.setTeamName(rs.getString("teamname"));
                r.setUserId(rs.getString("userid"));
                r.setUserName(rs.getString("username"));
                r.setMassage(rs.getString("massage"));
                r.setDate(rs.getDate("date"));
                requestList.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return requestList;
    }

    public int updateJoinRequest(Request r) {
        int rs = 0;
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE request ");
         sql.append("SET teamid = ?, teamname = ?, userid = ?, username = ?, message = ?, date = ? ");
         sql.append("WHERE teamid = ? AND userid = ? ");
         jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {r.getTeamId(), r.getTeamName(), r.getUserId(), r.getTeamName(), r.getMassage(), r.getDate(), r.getTeamId(), r.getUserId()});
        try {
            rs = jdbcUtil.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    // 요청 승인 시 팀 멤버로 추가 - memberdao나 dto사용시
    /*
    public int approveRequest(String teamID, String userID) {
        // 요청을 승인하면 SPOMEMBER 테이블에 추가
        boolean success = member.addMember(userID, null, teamID);

        // 승인한 경우 요청 목록에서 해당 요청 삭제
        if (success) {
            removeRequest(teamID, userID);
        }

        return success;
    }
   */
    
    //요청 승인, spouser에 teamid추가함 - id로
    public int approveJoinRequestById(String teamId, String userId){
        StringBuilder sql =  new StringBuilder();
        sql.append("UPDATE spouser ");
        sql.append("SET teamid = ? ");
        sql.append("WHERE userid = ? ");
        jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] {teamId, userId});
        try {
            return jdbcUtil.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //요청 승인, spouser teamid추가함 - Request객체로
    public int approveJoinRequest(Request r) {
        StringBuilder sql =  new StringBuilder();
        int rs = 0;
        sql.append("UPDATE spouser ");
        sql.append("SET teamid = ? ");
        sql.append("WHERE userid = ? ");
        jdbcUtil.setSqlAndParameters(sql.toString(),new Object[] {r.getTeamId(), r.getUserId()});
        
        try {
            rs =  jdbcUtil.executeUpdate();
            if(rs > 0) {
                rejectRequest(r.getTeamId(), r.getUserId());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    // 요청 거절 시 요청 목록에서 제거
    public boolean rejectRequest(String teamID, String userID) {
        return removeRequest(teamID, userID);
    }
    
    // 요청 목록에서 제거 id로
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