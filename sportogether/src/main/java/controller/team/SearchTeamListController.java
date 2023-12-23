package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.TeamDAO;
import model.service.dto.Team;

public class SearchTeamListController implements Controller{
     public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
         TeamDAO teamDAO = new TeamDAO();
         
         //textarea이른 teamName, submit이름 searchBtn
         List<Team> list = null;
         String searchTeamName = request.getParameter("teamName");
         request.setAttribute("searchTeamName", searchTeamName);
         try {
            list= teamDAO.getSearchTeamList(searchTeamName);
            request.setAttribute("searchTeamList", list);
            return "/team/teamSearch.jsp";
         }catch (Exception e) {
             return "/team/matchList.jsp";
        }
     }
}