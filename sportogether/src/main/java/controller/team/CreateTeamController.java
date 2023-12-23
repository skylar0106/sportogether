package controller.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.dto.*;
import model.service.TeamManager;
import model.service.UserManager;

public class CreateTeamController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateTeamController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
      //로그인한 사람의 id찾기 -> teamId찾기!!
       HttpSession session = request.getSession();
       String userId = UserSessionUtils.getLoginUserId(session);
        
        if(request.getMethod().equals("GET")) {
            return "/team/teamManage_regi.jsp";
        }
        
        //POST request( 팀 정보가 parameter로 전송됨)
        Team team = new Team(
                0,
                request.getParameter("name"),
                request.getParameter("location"),
                request.getParameter("sport"),
                userId
                );
        
        try {
            TeamManager tmanager = TeamManager.getInstance();
            tmanager.createTeam(team);
            
            int teamId = team.getTeamId();
            UserManager uManager = UserManager.getInstance();
            uManager.update2(userId, teamId);
            
            log.debug("Create Team : {}", team);
            // 우리가 변경
            return "redirect:/team/update"; // 성공 시 팀 생성 화면으로 redirect
            //          return "redirect:/community/list";  // 성공 시 커뮤니티 리스트 화면으로 redirect
            
        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("team", team);
            //우리가 변경
            //실패할 경우 team register Form으로 접속됨
            return "redirect:/team/register";
        }
    }
}