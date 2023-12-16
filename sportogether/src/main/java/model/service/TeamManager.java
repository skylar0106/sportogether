package model.service;

import java.sql.SQLException;
import java.util.List;

import model.service.dto.*;
import model.dao.*;

public class TeamManager {
	private static TeamManager teamMan = new TeamManager();
	private UserDAO userDAO;
	private TeamDAO teamDAO;
	
	private TeamManager() {
		try {
			userDAO = new UserDAO();
			teamDAO = new TeamDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static TeamManager getInstance() {
		return teamMan;
	}
	
	
	public Team createTeam(Team team) throws SQLException {
		return teamDAO.create(team);		
	}

	public int updateTeam(Team team) throws SQLException {
		return teamDAO.update(team);				
	}
	
//	Id로 팀찾는 경우
	public Team findTeam(int teamId) throws SQLException {
		Team team = teamDAO.findTeam(teamId); 
		
//		List<User> memberList = userDAO.findUsersInTeam(teamId);
//		team.setMemberList(memberList);
		
//		int numOfMembers = userDAO.getNumberOfUsersInTeam(teamId);
//		team.setNumofMembers(numOfMembers);
		return team;
	}
	
	public Team findTeamByName(String teamName) {
		Team team = teamDAO.findTeamByName(teamName);
		
		return team; 
	}
	
	
	public List<Team> findTeamList() throws SQLException {
		return teamDAO.findTeamList();
	}
	
	public List<User> findTeamMembers(int teamId) throws SQLException {
		return userDAO.findUsersInTeam(teamId);
	}
}
