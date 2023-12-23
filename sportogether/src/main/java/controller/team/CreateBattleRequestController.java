package controller.team;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.BattleRequestDAO;
import model.dao.UserDAO;
import model.service.dto.BattleRequest;
import model.service.dto.User;

public class CreateBattleRequestController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 대결신청함(임의의 form에서 신청 정보 입력받는다고 가정)

		HttpSession session = request.getSession();
   	 if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form 요청으로 redirect
        }
		BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
		String userId = UserSessionUtils.getLoginUserId(session);
    	UserDAO userDAO = new UserDAO();
    	User user = userDAO.findUser(userId);
		
		try {
			int teamId = user.getTeamId();
			int rivalId = Integer.parseInt(request.getParameter("rivalId"));
			String sports = request.getParameter("sports");
			String message = request.getParameter("message");
			LocalDate date = LocalDate.parse(request.getParameter("date"));
			String approval = request.getParameter("approval");
			int battleId = 0;
			
			BattleRequest b = new BattleRequest(teamId, rivalId, sports, message, date, approval, battleId);
			battleRequestDAO.insertBattleRequest(b);
			
			return "redirect:/team/matchSearch.jsp";
			
		} catch (Exception e) {
			 request.setAttribute("creationFailed", true);
				request.setAttribute("exception", e);
				return "/team/matchSearch.jsp";
		}

	}
}
