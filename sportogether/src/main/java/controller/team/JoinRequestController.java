package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Community;
import model.dao.JoinRequestDAO;
import model.dao.TeamDAO;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.Request;

public class JoinRequestController implements Controller{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
     //가입 요청, 거절, 승인 
        TeamDAO teamDAO = new TeamDAO();
        JoinRequestDAO joinRequestDAO = new JoinRequestDAO();
        int teamId = Integer.parseInt(request.getParameter("teamId"));

        List<Request> requestList=joinRequestDAO.getRequestList(teamId);
        String userId = request.getParameter("userId");
        
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLoginUser(userId, session) ||
				UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
								
				List<Community> commList = manager.findCommunityList();	// 커뮤니티 리스트 검색
				request.setAttribute("commList", commList);	
				
				return "/user/updateForm.jsp";   // 검색한 사용자 정보 및 커뮤니티 리스트를 updateForm으로 전송     
			}    
        
       try {
	        if (request.getServletPath().equals("/team/join/approve") && request.getParameter("u")) {
	            joinRequestDAO.approveJoinRequestById(teamId, userId);
	        }
	        else if(request.getServletPath().equals("/team/join/reject")){   
	            joinRequestDAO.rejectBattleRequest(bq);
	        }
	        else { //신청
	        	joinRequestDAO.cancelBattleRequest(bq);
	        }
       }catch (Exception e) {
       	e.printStackTrace();
       	request.setAttribute("BattleRequestException", e);
       	return "/team/request";
		}
        List<Request> requestList = teamDAO.getRequestList(teamId);
        
        request.setAttribute("requestList",requestList);
        
        return "redirect:";
    }
}
