package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Community;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.service.dto.BattleRequest;
import model.service.dto.Team;

public class ViewBattleRequestController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
       
    	//대결 신청 리스트 조회하는 Controller
        //팀 이름을 요청 파라미터로 받아 팀 객체 검색, 검색한 팀 객체로 다시 대결 신청 리스트 검색해 얻는다
    	 if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "redirect:/user/login/form";     // login form 요청으로 redirect
         }
        BattleRequestDAO battleRequestDao = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        String teamName = request.getParameter("teamName");
        Team team = teamDAO.findTeamByName(teamName);
        
        try {
        List<BattleRequest> battleRequestList = battleRequestDao.getBattleRequestList(team);
        List<BattleRequest> battleSentBattleRequestList = battleRequestDao.getSentBattleRequest(team.getTeamId());
        List<BattleRequest> battleReceivedBattleRequestList = battleRequestDao.getReceivedBattleRequest(team.getTeamId());
        List<String> battleSentRequestTeamNameList = battleRequestDao.getSentBattleRequestTeamName(team.getTeamId());
        List<String> battleReceivedRequestTeamNameList = battleRequestDao.getReceivedBattleRequestTeamName(team.getTeamId());
        
        request.setAttribute("battleRequestList", battleRequestList);
        request.setAttribute("battleSentRequestList", battleSentBattleRequestList);
        request.setAttribute("battleReceivedRequestList", battleReceivedBattleRequestList);
        request.setAttribute("battleSentRequestTeamNameList", battleSentRequestTeamNameList);
        request.setAttribute("battleReceivedRequestTeamNameList", battleReceivedRequestTeamNameList);
        }catch(Exception e) {
        	e.printStackTrace();
        	request.setAttribute("exception", e);
        	return "/team/requestList.jsp";
        }
        
        
        return "/team/requestList.jsp";
    }
    
}
