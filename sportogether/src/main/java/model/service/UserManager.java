package model.service;

import java.sql.SQLException;
import java.util.List;

import model.service.dto.*;
import model.dao.*;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private TeamDAO teamDAO;


	private UserManager() {
		try {
			userDAO = new UserDAO();
			teamDAO = new TeamDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	
	// 회원가입
	// 1. existingUser로 존재하는 아이디인지 확인
	// 2. 존재하지 않으면 UserDao의 create실행
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	
	// spouser를 수정하는것(수정 화면에서 수정 가능한거 : 이름, 닉네임, 생년월일, 성별, 포지션, 비번)
	// 정보 수정(해당 사람이 팀 리더인데 팀을 변경할 경우 팀 리더를 null로 변경)
	// 우리는 spoleader dto도 변경해줘야함!
	public int update(User user) throws SQLException, UserNotFoundException {
		int oldTeamId = findUser(user.getUserId()).getTeamId(); // 현재 소속된 팀
		if (user.getTeamId() != oldTeamId) { 	// 소속 팀이 변경됨
			Team team = teamDAO.findTeam(oldTeamId);  // 기존 소속 팀
			if (team != null && user.getUserId().equals(team.getSpoleader())) {
				// 사용자가 기존 소속 팀의 리더인 경우 -> 그 팀의 리더를 null로 변경 및 저장
				// 우리는 position이라는 data가 있으니... "position이 leader이면~"의 조건문으로 변경도 가능
				team.setSpoleader(null);
				teamDAO.updateLeader(team);
			}
		}
		return userDAO.updateInfo(user);
	}	
	
	
	//spouser에서 user를 삭제하는 것!
	// 삭제하려는 user가 특정 팀의 leader라면 그 팀의 leader를 null로 변경
	// 우리는 spoleader의 dto도 변경해줘야함!
	public int remove(String userId) throws SQLException, UserNotFoundException {
		int teamId = findUser(userId).getTeamId();
		Team team = teamDAO.findTeam(teamId);  // 소속 커뮤니티
		if (team != null && userId.equals(team.getSpoleader())) {
			// 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
			team.setSpoleader(null);
			teamDAO.updateLeader(team);
		}
		return userDAO.remove(userId);
	}
	
	
	public User findUser(String userId)
			throws SQLException, UserNotFoundException {
			User user = userDAO.findUser(userId);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}		
			return user;
		}

	public List<User> findUserList() throws SQLException {
		return userDAO.findUserList();
	}

//	public List<User> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findUserList(currentPage, countPerPage);
//	}
	
	
	//로그인
	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
			User user = findUser(userId);
			// user.java파일에서의 메서드로 비번 확인
			if (!user.matchPassword(password)) {
				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
			}
			return true;
		}
	

	
	public Team createTeam(Team team) throws SQLException {
		return teamDAO.create(team);		
	}

	public int updateTeam(Team team) throws SQLException {
		return teamDAO.update(team);				
	}
	
	public Team findTeam(int teamId) throws SQLException {
		Team team = teamDAO.findTeam(teamId); 
		
		List<User> memberList = userDAO.findUsersInTeam(teamId);
		team.setMemberList(memberList);
		
		int numOfMembers = userDAO.getNumberOfUsersInTeam(teamId);
		team.setNumofMembers(numOfMembers);
		return team;
	}
	
	public List<Team> findTeamList() throws SQLException {
		return teamDAO.findTeamList();
	}
	
	public List<User> findTeamMembers(int teamId) throws SQLException {
		return userDAO.findUsersInTeam(teamId);
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
