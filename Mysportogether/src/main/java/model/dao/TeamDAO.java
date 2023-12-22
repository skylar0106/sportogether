package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.service.dto.*;

public class TeamDAO {
    private JDBCUtil jdbcUtil = null;
    private MemberDAO member = null; //MemberDAO추가
    
    public TeamDAO() {
        jdbcUtil = new JDBCUtil();
        member = new MemberDAO();
    }
     // 새로운 팀 생성
    public Team newTeam(Team team) {
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder();

            query.append("INSERT into TEAM ");
            query.append("(name, spoleader, \"LEVEL\", sport, location, membership, rival) ");
            query.append("values(?, ?, ?, ?, ?, ?, ?) ");
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{team.getName(), team.getSpoLeader(),
                    team.getLevel(), team.getSport(), team.getLocation(), team.getMembership(), team.getRival()});
            rs = jdbcUtil.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    // 팀 정보 변경
    public Team changeTeamInfo(Team team) {
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder();

            query.append("UPDATE TEAM ");
            query.append("SET name = ?, spoleader = ?, \"LEVEL\" = ?, sport = ?, location = ?, membership = ?, rival = ? ");
            query.append("WHERE teamId = ? ");
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{team.getName(), team.getSpoLeader(),
                    team.getLevel(), team.getSport(), team.getLocation(), team.getMembership(), team.getRival(),
                    team.getTeamId()});
            rs = jdbcUtil.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    // 팀 정보 삭제
    public boolean deleteTeam(String teamID) {
        boolean success = false;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder();

            // 팀에 속한 멤버를 먼저 삭제
            //member.removeMembersByTeam(teamID);

            query.append("DELETE FROM TEAM ");
            query.append("WHERE teamId = ? ");
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{teamID});
            rs = jdbcUtil.executeQuery();
            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return success;
    }

    // 모든 팀 정보 검색해서 리스트에 저장 후 반환
    public List<Team> getTeamList() {
        List<Team> teamList = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM TEAM ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{});
        try {
            teamList = new ArrayList<>();
            jdbcUtil.executeQuery();

            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getString("teamId")); // 수정: teamId를 INTEGER로 변경
                t.setName(rs.getString("name"));
                t.setSpoLeader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
                t.setLevel(rs.getInt("level"));
                t.setSport(rs.getString("sport"));
                t.setLocation(rs.getString("location"));
                t.setMembership(rs.getInt("membership"));
                t.setRival(rs.getString("rival"));
                teamList.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return teamList;
    }
    
    // 팀 이름으로 팀 검색
    public Team findTeamByName(String teamName) {
        Team team = new Team();
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM TEAM ");
        sql.append("WHERE name = ? ");
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{teamName});

        try {
            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                team.setTeamId(rs.getString("teamId")); // 수정: teamId를 INTEGER로 변경
                team.setName(rs.getString("name"));
                team.setSpoLeader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
                team.setLevel(rs.getInt("level"));
                team.setSport(rs.getString("sport"));
                team.setLocation(rs.getString("location"));
                team.setMembership(rs.getInt("membership"));
                team.setRival(rs.getString("rival"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return team;
    }

    // 랭킹 리스트 조회
    public List<Team> findRankingList() {
        List<Team> sortedTeamList = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT name, matches, win, lose, ranking, draw, rate ");
        sql.append("FROM TEAM JOIN TEAMSCORE USING(teamid) ");
        sql.append("ORDER BY ranking, name ");
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{});

        try {
            sortedTeamList = new ArrayList<>();
            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getString("teamId")); // 수정: teamId를 INTEGER로 변경
                t.setName(rs.getString("name"));
                t.setSpoLeader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
                t.setLevel(rs.getInt("level"));
                t.setSport(rs.getString("sport"));
                t.setLocation(rs.getString("location"));
                t.setMembership(rs.getInt("membership"));
                t.setRival(rs.getString("rival"));
                sortedTeamList.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return sortedTeamList;
    }
    
    // 특정 팀의 멤버 목록을 반환
    public List<User> getMemberListByTeamName(String teamName) {
        List<User> memberList = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userid, name, nickname, birth, position, team ");
        sql.append("FROM USER JOIN TEAM USING(teamId) ");
        sql.append("ORDER BY position, nickname, birth ");

        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{});

        try {
            memberList = new ArrayList<>();
            rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                User m = new User();
                m.setUserId(rs.getString("userid"));
                m.setName(rs.getString("name"));
              //  m.setBirth(rs.getString("birth"));
               // m.setPosition(rs.getString("birth"));
                m.setNickName(rs.getString("nickname"));
                memberList.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return memberList;
    }
    
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
                r.setDate(rs.getString("date"));
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
        Member m = new Member(userID, null, teamID);
        boolean success = member.addMember(m);

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
