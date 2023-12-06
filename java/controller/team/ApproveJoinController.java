package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TeamDAO;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.Request;

public class ApproveJoinController {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
     
        TeamDAO teamDAO = new TeamDAO();
        
        String teamId = request.getParameter("teamId");
        String userId = request.getParameter("userId");
        
        //모델, DAO직접 호출
        // 사입 요청 승인, 팀 멤버 추가 
       teamDAO.approveRequest(teamId,userId);
           
        List<Request> requestList = teamDAO.getRequestList(teamId);
        
        request.setAttribute("requestList",requestList);
        
        return "/team/TeamManage/member";
    }
}
