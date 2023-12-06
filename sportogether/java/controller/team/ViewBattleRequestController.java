package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.BattleRequestDAO;
import model.dao.TeamDAO;
import model.service.dto.BattleRequest;
import model.service.dto.Team;

public class ViewBattleRequestController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //대결 신청 리스트 조회하는 Controller
        
        //팀 이름을 요청 파라미터로 받아 팀 객체 검색, 검색한 팀 객체로 다시 대결 신청 리스트 검색해 얻는다
        BattleRequestDAO battleRequestDao = new BattleRequestDAO();
        TeamDAO teamDAO = new TeamDAO();
        String teamName = request.getParameter("teamName");
        Team team = teamDAO.findTeamByName(teamName);
        List<BattleRequest> battleRequestList = battleRequestDao.getBattleRequestList(team);
        
        request.setAttribute("battleRequestList", battleRequestList);
        
        return "/team/matchSearch.jsp";
    }
    
}
