package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.dao.UserDAO;
import model.service.dto.BattleRequest;
import model.service.dto.Team;
import model.service.dto.User;

public class ViewBattleRequestController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
       
        //대결 신청 리스트 조회하는 Controller
        //로그인 안되어있으면
        HttpSession session = request.getSession();
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "redirect:/user/login/form";     // login form 요청으로 redirect
         }
         
         
        String userId = UserSessionUtils.getLoginUserId(session);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(userId);
        
        BattleRequestDAO battleRequestDao = new BattleRequestDAO();
        
        if(user.getTeamId() > 0) {
        
            int teamId =user.getTeamId();
        try {
            List<BattleRequest> sentBattleRequestList = battleRequestDao.getSentBattleRequest(teamId);
            List<BattleRequest> receivedBattleRequestList = battleRequestDao.getReceivedBattleRequest(teamId);
            List<Team> sentRequestTeamList = battleRequestDao.getSentRequestTeam(teamId);
            List<Team> receivedRequestTeamList = battleRequestDao.getReceivedRequestTeam(teamId);
            
            request.setAttribute("sentBattleRequestList", sentBattleRequestList);
            request.setAttribute("receivedBattleRequestList", receivedBattleRequestList);
            request.setAttribute("sentRequestTeamList",  sentRequestTeamList);
            request.setAttribute("receivedRequestTeamList", receivedRequestTeamList);
            
            return "/team/requestList.jsp";
        }
        catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            return "/user/login.jsp";
        }
        }
        else {
            request.setAttribute("TeamNotFound", true);
            return "/mainPageLogin.jsp";
        }
        
        
     
    }
    
}