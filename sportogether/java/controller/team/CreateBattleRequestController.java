package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.BattleRequestDAO;
import model.service.dto.BattleRequest;

public class CreateBattleRequestController implements Controller{
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
       
       String teamId = request.getParameter("teamId");
       String rivalId = request.getParameter("rivalId");
       String sports= request.getParameter("sports");
       String message = request.getParameter("message");
       String date = request.getParameter("date");
       String approval = request.getParameter("approval");
       
       BattleRequest b = new BattleRequest(teamId, rivalId, sports, message, date, approval);
       
       battleRequestDAO.insetBattleRequest(b);
       List<BattleRequest> battleRequestList = battleRequestDAO.getBattleRequestList(teamId);
       request.setAttribute("battleRequestList",battleRequestList );
       return "/team/matchSearch.jsp";
    }
}
