package controller.team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.dao.UserDAO;
import model.service.dto.BattleRequest;
import model.service.dto.User;

public class CancelBattleRequestController implements Controller{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form?hasLogined=false";     // login form 요청으로 redirect
        }
		
        BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        String teamName =  request.getParameter("teamName");
        
        String battleid = request.getParameter("battleid");
        BattleRequest bq= new BattleRequest();
        battleRequestDAO.cancel()
        try{
        	
        }catch (Exception e) {
        	return "/team/request.jsp";
		}
        
        battleRequestDAO.cancelBattleRequest(bq);
        return "/team/request.jsp";
		
	}
}
