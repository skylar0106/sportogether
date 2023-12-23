package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.JoinRequestDAO;
import model.service.dto.Request;

public class ViewJoinRequestController implements Controller{
    /*가입신청 리스트 조회 컨트롤러*/
   
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
       JoinRequestDAO joinRequestDao = new JoinRequestDAO();
       int teamId = Integer.parseInt(request.getParameter("teamId"));
       
       try {
       List<Request> requestList= joinRequestDao.getRequestList(teamId);
       request.setAttribute("requestList", requestList);
       }catch(Exception e) {
           e.printStackTrace();
           request.setAttribute("joinRequestListException", e);
       }
       
       return "/user/leader/joinTeam/form.jsp";
    }
}