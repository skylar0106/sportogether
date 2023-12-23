//package controller.team;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import controller.Controller;
//import controller.user.UserSessionUtils;
//import model.dao.JoinRequestDAO;
//import model.dao.TeamDAO;
//import model.service.TeamManager;
//import model.service.UserManager;
//import model.service.dto.Request;
//
//public class JoinRequestController implements Controller{
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
//     //가입 요청, 거절, 승인 
//        TeamDAO teamDAO = new TeamDAO();
//        JoinRequestDAO joinRequestDAO = new JoinRequestDAO();
//        int teamId = Integer.parseInt(request.getParameter("teamId"));
//
//        HttpSession session = request.getSession();
//        List<Request> requestList=joinRequestDAO.getRequestList(teamId);
//        String userId = UserSessionUtils.getLoginUserId(request.getSession());
//        if (!UserSessionUtils.isLoginUser(userId, session)) {
//                return "/user/login/form";
//            }    
//        
//       try {
//            if (request.getServletPath().equals("/team/join/approve") && request.getParameter("u")) {
//                joinRequestDAO.approveJoinRequestById(teamId, userId);
//            }
//            else if(request.getServletPath().equals("/team/join/reject")){   
//                joinRequestDAO.rejectBattleRequest(bq);
//            }
//            else { //신청
//                joinRequestDAO.createJoinRequest(bq);
//            }
//       }catch (Exception e) {
//        e.printStackTrace();
//        request.setAttribute("BattleRequestException", e);
//        return "/team/request";
//        }
//        requestList = teamDAO.getRequestList(teamId);
//        
//        request.setAttribute("requestList",requestList);
//        
//        return "redirect:";
//    }
//}