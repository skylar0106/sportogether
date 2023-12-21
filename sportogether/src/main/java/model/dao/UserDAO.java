package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.service.dto.*;

public class UserDAO {
    private JDBCUtil jdbcUtil = null;

    public UserDAO() {
        jdbcUtil = new JDBCUtil();
    }


    
    // 회원가입(spouser에 user한명 추가)
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO spouser(userid, name, nickname, birth, sex, position, password) VALUES (?, ?, ?, ?, ?, ?, ?)";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getUserId(), user.getName(), 
				user.getNickName(), user.getBirth(), user.getSex(),
				user.getPosition(), user.getPassword()});	// JDBCUtil 에 insert문과 매개 변수 설정		
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
	
	
    // 존재하는 아이디인지 확인
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM spouser WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	
    /*
     * 유저 아이디로 유저 검색
     * User 객체 반환
     * 로그인, 사용자 정보 수정, 팀 멤버 조회 등에 사용?
     * 
     * */
    public User getUserById(String userId) {
        User user = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM spouser ");
        sql.append("WHERE userID = ? "); // 이 부분 변경하면 다른 필드로도 검색
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {userId});

        try {
            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("userID"),
                    rs.getString("name"),
                    rs.getString("nickName"),
                    rs.getString("sex"),
                    rs.getString("birth"),
                    rs.getString("picture"),
                    rs.getString("comment"), // 추가된 부분
                    getInterestsList(rs.getString("interests")), // 추가된 부분
                    rs.getString("career") // 추가된 부분
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return user;
    }

    // interests를 문자열로 변환
    private static List<String> getInterestsList(String interestsString) {
        List<String> interestsList = new ArrayList<>();

        if (interestsString != null && !interestsString.isEmpty()) {
            interestsList = Arrays.asList(interestsString.split(","));
        }

        return interestsList;
    }

    //User 정보 수정
    public int updateInfo(User user) {
    	StringBuilder query = new StringBuilder();

    	query.append("UPDATE SPOUSER ");
    	query.append("SET userid = ?, name = ?, nickname = ?, birth = ?, sex = ?, position = ?, password = ?");
    	query.append("WHERE userID = ? ");
    	jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{
    			user.getUserId(),
    			user.getName(),	
                user.getNickName(),
                user.getBirth(),
                user.getSex(),
                user.getPosition(),
                user.getPassword(),
                user.getUserId()
        });
    	try {
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
    
    
	 //주어진 사용자 ID에 해당하는 사용자 정보를 spouser 데이터베이스에서 찾아 User 도메인 클래스에 저장하여 반환.
	public User findUser(String userId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
        sql.append("SELECT name, teamid, nickName, sex, birth, position, password ");
        sql.append("FROM SPOUSER ");
        sql.append("WHERE userid=? ");
        
		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				User user = new User(		// User 객체를 생성하여 학생 정보를 저장
						//이것도 date형을 어케 변환할지 생각해봐야함
					userId,
					rs.getInt("teamid"),
					rs.getString("name"),
					rs.getString("nickName"),
					rs.getString("sex"),
					rs.getString("birth"),
					rs.getString("position"),
					rs.getString("password")
					);
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	
	 // 전체 사용자 정보를 검색하여 List에 저장 및 반환
	public List<User> findUserList() throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT userid, u.name, nickname, sex, birth, position, NVL(teamId,0) AS teamid, t.name ");
		sql.append("FROM spouser u LEFT JOIN team t ");
		sql.append("ON u.teamid = t.teamid ");
		sql.append("ORDER BY userid");
		
		jdbcUtil.setSqlAndParameters(sql.toString(), null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<User> userList = new ArrayList<User>();	// User들의 리스트 생성
			while (rs.next()) {
				User user = new User(			// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("userId"),
						rs.getInt("teamId"),
						rs.getString("u.name"),
						rs.getString("nickName"),
						rs.getString("sex"),
						rs.getString("birth"),
						rs.getString("position"),
						null
					);
				userList.add(user);				// List에 User 객체 저장
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	 // 사용자 ID에 해당하는 사용자를 삭제.
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM spouser WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	


	// 특정 커뮤니티에 속한 사용자들을 검색하여 List에 저장 및 반환
	public List<User> findUsersInTeam(int teamId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT userId, name, nickName, email, phone FROM spouser ");
		sql.append("WHERE teamId = ?");
                         
		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<User> userList = new ArrayList<User>();	// member들의 리스트 생성
			while (rs.next()) {
				User member = new User(		// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("userId"),
					rs.getString("name"),
					rs.getString("nickName"),
					rs.getString("sex"),
					rs.getString("birth"),
					rs.getString("position")
					);
				userList.add(member);			// List에 Community 객체 저장
			}		
			return userList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	 // 특정 팀에 속한 사용자들의 수를 count하여 반환
	public int getNumberOfUsersInTeam(int teamId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT COUNT(userId) FROM UserInfo ");
		sql.append("WHERE commId = ?");
		
		jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {teamId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}
}
