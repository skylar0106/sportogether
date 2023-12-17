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
    
     // Team 테이블에 새로운 팀 생성
    public Team create(Team team) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT into TEAM ");
        query.append("values(teamId_seq.nextval,?, ?, ?, ?) ");
        //이거하려면 db에 sequence 설정해줘야함 기억하기*******************
        // Team의 teamid은 PK => sequence로 자동 생성해주기!!
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{team.getName(), 
        			team.getSpoleader(), team.getLocation(), team.getSport()});
        
        String key[] = {"teamId"};	//pk 컬럼의이름
        try {
        	jdbcUtil.executeUpdate(key);	//insert문 실행
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {
            	int generatedKey = rs.getInt(1);	// 생성된 pk값
            	team.setTeamId(generatedKey);	//teamId 필드에 저장
            }
            return team;
            //return result;
        } catch (Exception ex) {
        	jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
        	jdbcUtil.commit();
            jdbcUtil.close();
        }
        return null;
    }


    
    // 팀 정보 수정
    public int update(Team team) throws SQLException{

        try {
            StringBuilder query = new StringBuilder();

            query.append("UPDATE TEAM ");
            query.append("SET name = ?, sport = ?, location = ? ");
            query.append("WHERE teamId = ? ");
            
          //  String leaderId = team.getSpoleader();
            
          //  if(leaderId.equals("")) leaderId = null;
            
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{team.getName(), team.getSport(), team.getLocation(), team.getTeamId()});
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
        	jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
        	jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }
    
    
    //team테이블에서 해당팀의 리더를 변경(spoleader만 update해주면 될듯...?) 근데 이걸 어디다..?  
    public int updateLeader(Team team) {
    	String sql = "UPDATE team "
    				+ "SET spoleader = ? "
					+ "WHERE teamId=?";
    	Object[] param = new Object[] {team.getSpoleader(), team.getTeamId()};				
    	jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
		
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

    
    // 팀 정보 삭제
    public boolean deleteTeam(Team teamID) {
        boolean success = false;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder();

            // 팀에 속한 멤버를 먼저 삭제
            member.removeMembersByTeam(teamID);

            query.append("DELETE FROM TEAM ");
            query.append("WHERE teamId = ? ");
            jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{teamID});
            rs = jdbcUtil.executeQuery();
            success = true;
        } catch (Exception ex) {
        	jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
        	jdbcUtil.commit();
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
                t.setTeamId(rs.getInt("teamId")); // 수정: teamId를 INTEGER로 변경
                t.setName(rs.getString("name"));
                t.setSpoleader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
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
                team.setTeamId(rs.getInt("teamId")); // 수정: teamId를 INTEGER로 변경
                team.setName(rs.getString("name"));
                team.setSpoleader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
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

    
	// 주어진  ID에 해당하는 팀 정보를 데이터베이스에서 찾아 Team 도메인 클래스에 저장하여 반환.
//	public Team findTeam(int teamId) throws SQLException {
//		Team team = null;
//		ResultSet rs = null;
//		
//		StringBuilder sql = new StringBuilder();
////		sql.append("SELECT t.name, teamid, spoleader ");
////		sql.append("FROM Team t LEFT OUTER JOIN spouser u ON t.spoleader = u.userId ");
////		sql.append("WHERE teamId=? ");
//
//		
//		sql.append("SELECT * ");
//		sql.append("FROM team ");
//		sql.append("WHERE teamid=? ");
//		
//		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId});	// JDBCUtil에 query문과 매개 변수 설정
//
//		try {
//			rs = jdbcUtil.executeQuery();		// query 실행
//			if (rs.next()) {						// 학생 정보 발견
//				team = new Team(		// Team 객체를 생성하여 커뮤니티 정보를 저장
//					teamId,
//					rs.getString("name"),
//					rs.getString("spoleader"),
//					rs.getString("location"),
//					rs.getString("sport")
//					);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return team;
//	}
	
	public Team findTeam(int teamId) throws SQLException {
		Team team = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name, sport, location ");
		sql.append("FROM Team t ");
		sql.append("WHERE teamId=? ");
		
		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				team = new Team(		// Team 객체를 생성하여 커뮤니티 정보를 저장
					teamId,
					rs.getString("name"),
					rs.getString("sport"),
					rs.getString("location")
					);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return team;
	}
    
	// TeamList 찾기
	public List<Team> findTeamList() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT teamId, name, spoleader ");
		sql.append("FROM Team ");
		sql.append("GROUP BY teamId, name, spoleader ");
		sql.append("ORDER BY teamId");
        
		jdbcUtil.setSqlAndParameters(sql.toString(), null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Team> teamList = new ArrayList<Team>();	// Team들의 리스트 생성
			while (rs.next()) {
				Team team = new Team(			// Team 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("teamId"),
						rs.getString("name"),
						rs.getString("spoleader")
						);
				teamList.add(team);				// List에 Team 객체 저장
			}		
			return teamList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
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
                t.setTeamId(rs.getInt("teamId")); // 수정: teamId를 INTEGER로 변경
                t.setName(rs.getString("name"));
                t.setSpoleader(rs.getString("spoleader")); // 수정: spoleader를 VARCHAR2로 변경
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
                m.setBirth(rs.getString("birth"));
                m.setPosition(rs.getString("birth"));
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
//    public boolean approveRequest(String teamID, String userID) {
//        // 요청을 승인하면 SPOMEMBER 테이블에 추가
//        boolean success = member.addMember(userID, null, teamID);
//
//        // 승인한 경우 요청 목록에서 해당 요청 삭제
//        if (success) {
//            removeRequest(teamID, userID);
//        }
//
//        return success;
//    }

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


