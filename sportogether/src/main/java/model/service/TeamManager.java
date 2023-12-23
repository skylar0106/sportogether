
package model.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.dao.LankingDAO;
import model.dao.MatchRivalDAO;
import model.dao.TeamDAO;
import model.dao.TeamPortfolioDAO;
import model.dao.UserDAO;
import model.service.dto.*;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class TeamManager {
    private static TeamManager teamMan = new TeamManager();
    private UserDAO userDAO;
    private TeamDAO teamDAO;
    private LankingDAO lankingDAO;
    private UserAnalysis userAanlysis;
    
    private MatchRivalDAO rivalDAO;

    private TeamManager() {
        try {
            userDAO = new UserDAO();
            teamDAO = new TeamDAO();
            userAanlysis = new UserAnalysis(userDAO);
            rivalDAO = new MatchRivalDAO();
            lankingDAO = new LankingDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static TeamManager getInstance() {
        return teamMan;
    }
    
    /*라이벌 찾기*/
    public Rival findRivalTeam(int teamId)
            throws SQLException, UserNotFoundException {
            Team tm = null;
            Rival rival = null;
            if (teamId == 0) {
                rival = new Rival ("로그인 해주세요", teamId, 0);
            }
            else {
                tm = rivalDAO.findByTeamName(teamId);
                if (tm == null) {
                    throw new UserNotFoundException(teamId + "는 존재하지 않는 아이디입니다.");
                }   
                
                rival = rivalDAO.getMatchList(tm);
                if (rival == null) {
                    throw new UserNotFoundException("라이벌이 존재하지 않습니다.");
                }         
            }
             
            return rival;
        }
    
    /*내 팀 랭킹 반환*/
    public Lanking findTeamLanking(int teamId)
            throws SQLException, UserNotFoundException {
            Lanking lanking = null;
            if (teamId == 0) {
                lanking = new Lanking(
                        teamId,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        "로그인 해주세요"
                        );
            }
            else {
                lanking = lankingDAO.findTeamLanking(teamId);
                if (lanking == null) {
                    throw new UserNotFoundException(teamId + "는 존재하지 않는 아이디입니다.");
                } 
            }
                         
            return lanking;
        }
    
    /*랭킹리스트 반환*/
    public List<Lanking> findLankingList()
            throws SQLException, UserNotFoundException { 
            List<Lanking> lankingList = lankingDAO.findTeamLankingList();
            if (lankingList == null) {
                throw new UserNotFoundException("랭킹이 존재하지 않습니다.");
            }          
            return lankingList;
        }
    
    public Team createTeam(Team team) throws SQLException {
        return teamDAO.create(team);        
    }

    public int updateTeam(Team team) throws SQLException {
        return teamDAO.update(team);                
    }
    
//  Id로 팀찾는 경우
    public Team findTeam(int teamId) throws SQLException {
        Team team = teamDAO.findTeam(teamId); 
        
//      List<User> memberList = userDAO.findUsersInTeam(teamId);
//      team.setMemberList(memberList);
        
//      int numOfMembers = userDAO.getNumberOfUsersInTeam(teamId);
//      team.setNumofMembers(numOfMembers);
        return team;
    }
    
    public Team findTeamByName(String teamName) {
        Team team = teamDAO.findTeamByName(teamName);
        
        return team; 
    }
    
    
    public List<Team> findTeamList() throws SQLException {
        return teamDAO.findTeamList();
    }
    
//    public List<User> findTeamMembers(int teamId) throws SQLException {
//        return userDAO.findUsersInTeam(teamId);
//    }
    
    
    
//    public int create(User user) throws SQLException, ExistingUserException {
//        if (userDAO.existingUser(user.getUserId()) == true) {
//            throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
//        }
//        return userDAO.create(user);
//    }

//    public int update(User user) throws SQLException, UserNotFoundException {
//        int oldCommId = findUser(user.getUserId()).getCommId();
//        if (user.getCommId() != oldCommId) {    // 소속 커뮤티니가 변경됨
//            Community comm = commDAO.findCommunity(oldCommId);  // 기존 소속 커뮤니티
//            if (comm != null && user.getUserId().equals(comm.getChairId())) {
//                // 사용자가 기존 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
//                comm.setChairId(null);
//                commDAO.updateChair(comm);
//            }
//        }
//        return userDAO.update(user);
//    }   
//
//    public int remove(String userId) throws SQLException, UserNotFoundException {
//        int commId = findUser(userId).getCommId();
//        Community comm = commDAO.findCommunity(commId);  // 소속 커뮤니티
//        if (comm != null && userId.equals(comm.getChairId())) {
//            // 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
//            comm.setChairId(null);
//            commDAO.updateChair(comm);
//        }
//        return userDAO.remove(userId);
//    }

//    public User findUser(String userId)
//        throws SQLException, UserNotFoundException {
//        User user = userDAO.findUser(userId);
//        
//        if (user == null) {
//            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
//        }       
//        return user;
//    }

//    public List<User> findUserList() throws SQLException {
//            return userDAO.findUserList();
//    }
//    
//    public List<User> findUserList(int currentPage, int countPerPage)
//        throws SQLException {
//        return userDAO.findUserList(currentPage, countPerPage);
//    }

//    public boolean login(String userId, String password)
//        throws SQLException, UserNotFoundException, PasswordMismatchException {
//        User user = findUser(userId);
//
//        if (!user.matchPassword(password)) {
//            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
//        }
//        return true;
//    }

//    public List<User> makeFriends(String userId) throws Exception {
//        return userAanlysis.recommendFriends(userId);
//    }
    

    
//    public Community findCommunity(int commId) throws SQLException {
//        Community comm = commDAO.findCommunity(commId); 
//        
//        List<User> memberList = userDAO.findUsersInCommunity(commId);
//        comm.setMemberList(memberList);
//        
//        int numOfMembers = userDAO.getNumberOfUsersInCommunity(commId);
//        comm.setNumOfMembers(numOfMembers);
//        return comm;
//    }
    
    
//    public List<User> findCommunityMembers(int commId) throws SQLException {
//        return userDAO.findUsersInCommunity(commId);
//    }

    public UserDAO getUserDAO() {
        return this.userDAO;
    }

	public TeamScore getTeamScore(int teamID) {
		TeamPortfolioDAO teamPortfolioDAO = new TeamPortfolioDAO();
	    return teamPortfolioDAO.getTeamScore(teamID);
	}
	
	public String getRecentMatchDate(int teamId) throws SQLException {
	    TeamPortfolioDAO teamPortfolioDAO = new TeamPortfolioDAO();
	    return teamPortfolioDAO.getRecentMatchDate(teamId);
	}

	public Team getTeamInfo(int teamID) throws SQLException {
	    TeamPortfolioDAO teamPortfolioDAO = new TeamPortfolioDAO();
	    return teamPortfolioDAO.getTeamInfo(teamID);
	}
	
	public int getTeamMemberCount(int teamID) throws SQLException {
	    TeamPortfolioDAO teamPortfolioDAO = new TeamPortfolioDAO();
	    return teamPortfolioDAO.getTeamMemberCount(teamID);
	}
	
	public String getTeamIntroduction(int teamID) throws SQLException {
	    TeamPortfolioDAO teamPortfolioDAO = new TeamPortfolioDAO();
	    return teamPortfolioDAO.getTeamIntroduction(teamID);
	}

}

