package controller.team;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.BattleRequestDAO;
import model.service.dto.BattleRequest;

public class CreateBattleRequestController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 대결신청함(임의의 form에서 신청 정보 입력받는다고 가정)

		BattleRequestDAO battleRequestDAO = new BattleRequestDAO();

		try {
			int teamId = Integer.parseInt(request.getParameter("teamId"));
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
				//request.setAttribute("comm", comm);
				return "/team/matchSearch.jsp";
		}

	}
}
