package model.service;

import java.sql.SQLException;
import java.util.List;
import model.service.dto.*;
import model.dao.MyPortfolioDAO;
import model.dao.TeamDAO;
import model.dao.UserDAO;

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
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
	        teamDAO = new TeamDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public boolean login(String userId, String password)
	        throws SQLException, UserNotFoundException, PasswordMismatchException {
	        User user = findUser(userId);

	        if (!user.matchPassword(password)) {
	            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
	        }
	        return true;
	    }
	
	public User findUser(String userId)
	        throws SQLException, UserNotFoundException {
	        User user = userDAO.findUser(userId);
	        
	        if (user == null) {
	            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
	        }       
	        return user;
	    }

	

	public UserDAO getUserDAO() {
		return this.userDAO;
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

        return userDAO.updateInfo(user);
    } 
    
    //myportfolio
    public User getUserInfo(String userID) throws SQLException {
        MyPortfolioDAO myPortfolioDAO = new MyPortfolioDAO();
        return myPortfolioDAO.getUserInfo(userID);
    }
    
    public int updateMyPotpolio(User usr) throws SQLException{
       MyPortfolioDAO myPortfolioDAO = new MyPortfolioDAO();
       return myPortfolioDAO.updateMyPotpolio(usr);
    }
    
    
    //spouser에서 user를 삭제하는 것!
    // 삭제하려는 user가 특정 팀의 leader라면 그 팀의 leader를 null로 변경
    // 우리는 spoleader의 dto도 변경해줘야함!
//    public int remove(String userId) throws SQLException, UserNotFoundException {
//        int teamId = findUser(userId).getTeamId();
//        Team team = teamDAO.findTeam(teamId);  // 소속 커뮤니티
//        if (team != null && userId.equals(team.getSpoLeader())) {
//            // 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
//            team.setSpoLeader(null);
//            teamDAO.updateLeader(team);
//        }
//        return userDAO.remove(userId);
//    }
    
    
    
    
//  public int create(User user) throws SQLException, ExistingUserException {
//  if (userDAO.existingUser(user.getUserId()) == true) {
//      throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
//  }
//  return userDAO.create(user);
//}
//
//public int update(User user) throws SQLException, UserNotFoundException {
//  int oldCommId = findUser(user.getUserId()).getCommId();
//  if (user.getCommId() != oldCommId) {    // 소속 커뮤티니가 변경됨
//      Community comm = commDAO.findCommunity(oldCommId);  // 기존 소속 커뮤니티
//      if (comm != null && user.getUserId().equals(comm.getChairId())) {
//          // 사용자가 기존 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
//          comm.setChairId(null);
//          commDAO.updateChair(comm);
//      }
//  }
//  return userDAO.update(user);
//}   
//
//public int remove(String userId) throws SQLException, UserNotFoundException {
//  int commId = findUser(userId).getCommId();
//  Community comm = commDAO.findCommunity(commId);  // 소속 커뮤니티
//  if (comm != null && userId.equals(comm.getChairId())) {
//      // 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
//      comm.setChairId(null);
//      commDAO.updateChair(comm);
//  }
//  return userDAO.remove(userId);
//}

//public User findUser(String userId)
//  throws SQLException, UserNotFoundException {
//  User user = userDAO.findUser(userId);
//  
//  if (user == null) {
//      throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
//  }       
//  return user;
//}
//
//public List<User> findUserList() throws SQLException {
//      return userDAO.findUserList();
//}
//
//public boolean login(String userId, String password)
//  throws SQLException, UserNotFoundException, PasswordMismatchException {
//  User user = findUser(userId);
//
//  if (!user.matchPassword(password)) {
//      throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
//  }
//  return true;
//}
//
//public List<User> makeFriends(String userId) throws Exception {
//  return userAanlysis.recommendFriends(userId);
//}
//
//public Community createCommunity(Community comm) throws SQLException {
//  return commDAO.create(comm);        
//}
//
//public int updateCommunity(Community comm) throws SQLException {
//  return commDAO.update(comm);                
//}
//
//public Community findCommunity(int commId) throws SQLException {
//  Community comm = commDAO.findCommunity(commId); 
//  
//  List<User> memberList = userDAO.findUsersInCommunity(commId);
//  comm.setMemberList(memberList);
//  
//  int numOfMembers = userDAO.getNumberOfUsersInCommunity(commId);
//  comm.setNumOfMembers(numOfMembers);
//  return comm;
//}
//
//public List<Community> findCommunityList() throws SQLException {
//  return commDAO.findCommunityList();
//}
//
//public List<User> findCommunityMembers(int commId) throws SQLException {
//  return userDAO.findUsersInCommunity(commId);
//}
//
}
