package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.JoinRequestDAO;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.Request;

public class ViewJoinRequestConroller implements Controller{
    /*가입신청 리스트 조회 컨트롤러*/
   
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
       JoinRequestDAO joinRequestDao = new JoinRequestDAO();
       
       String teamId = request.getParameter("teamId");
       
       List<Request> requestList = joinRequestDao.getRequestList(teamId);
       request.setAttribute("requestList", requestList);
       
       return "/user/leader/joinTeam/form.jsp";
    }
}
