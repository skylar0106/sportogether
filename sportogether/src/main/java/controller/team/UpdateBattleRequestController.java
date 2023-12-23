package controller.team;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.service.dto.BattleRequest;
import model.service.dto.Team;

public class UpdateBattleRequestController implements Controller{
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        /*
         * 대결 신청 승인, 거부, 취소 컨트롤러
         * */
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form?hasLogined=false";     // login form 요청으로 redirect
        }
        BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        
        String teamName =  request.getParameter("teamName");
        
        Team team = teamDAO.findTeamByName(teamName);
        int battleId = 1;
        BattleRequest bq =  battleRequestDAO.getBattleRequestById(battleId); 
        try {
        
        try {
	        if (request.getServletPath().equals("/team/request/approve")) {
	            battleRequestDAO.approveBattleRequest(bq);
	        }
	        else if(request.getServletPath().equals("/team/request/reject")){   
	            battleRequestDAO.rejectBattleRequest(bq);
	        }
	        else { //
	        	battleRequestDAO.cancelBattleRequest(bq);
	        }
        }catch (Exception e) {
        	e.printStackTrace();
        	request.setAttribute("BattleRequestException", true);
        	return "/team/request";
		}
        
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
        
        return "redirect:/team/requestList.jsp";
    }
    
}
