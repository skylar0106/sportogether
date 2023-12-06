package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.service.dto.BattleRequest;
import model.service.dto.Request;
import model.service.dto.Team;

public class BattleRequestController implements Controller{
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        /*
         * 대결 신청 승인, 거부 컨트롤러
         * 
         * 
         * */
        BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        
        String teamName =  request.getParameter("teamName");
        String rivalId =  request.getParameter("rivalId");
        Team team = teamDAO.findTeamByName(teamName);
        BattleRequest bq =  battleRequestDAO.getBattleRequest(team, rivalId );
        
        if (request.getServletPath().equals("/team/battleRequest/approve")) {
            battleRequestDAO.approveBattleRequest(bq);
        }
        else {   
            battleRequestDAO.rejectBattleRequest(bq);
        }
        
        List<BattleRequest> bqList = battleRequestDAO.getBattleRequestList(team);
        
        request.setAttribute("battleRequest", bq);
        request.setAttribute("battleRequestList", bqList);
        
        return "/team/matchSearch.jsp";
    }
    /*
     * 
     * 
     *     BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        
        String teamName =  request.getParameter("teamName");
        String rivalId =  request.getParameter("rivalId");
        Team team = teamDAO.findTeamByName(teamName);
        BattleRequest bq =  battleRequestDAO.getBattleRequest(team, rivalId );
        battleRequestDAO.approveBattleRequest(bq);
        List<BattleRequest> bqList = battleRequestDAO.getBattleRequestList(team);
        
        request.setAttribute("battleRequest", bq);
        request.setAttribute("battleRequestList", bqList);
        
        //포워딩
        return "/team/matchSearch.jsp";
        
        ====================================
        
        BattleRequestDAO battleRequestDAO = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        
        String teamName =  request.getParameter("teamName");
        String rivalId =  request.getParameter("rivalId");
        Team team = teamDAO.findTeamByName(teamName);
        BattleRequest bq =  battleRequestDAO.getBattleRequest(team, rivalId );
        battleRequestDAO.rejectBattleRequest(bq);
        List<BattleRequest> bqList = battleRequestDAO.getBattleRequestList(team);
        
        request.setAttribute("battleRequest", bq);
        request.setAttribute("battleRequestList", bqList);
        
        //포워딩
        return "/team/matchSearch.jsp";
     * */
}
