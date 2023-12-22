package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.service.dto.*;

public class MyTeamLankingController implements Controller {
    // private static final int countPerPage = 100; // 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 로그인 여부 확인
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form 요청으로 redirect
        }
        
        /*
        String currentPageStr = request.getParameter("currentPage");    
        int currentPage = 1;
        if (currentPageStr != null && !currentPageStr.equals("")) {
            currentPage = Integer.parseInt(currentPageStr);
        }       
        */
        
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form 요청으로 redirect
        }
        
        UserManager uManager = UserManager.getInstance();
        int teamId = Integer.parseInt(request.getParameter("teamId"));
                     
        
        TeamManager manager = TeamManager.getInstance();
        Lanking lanking = manager.findTeamLanking(teamId);

        request.setAttribute("lanking", lanking);                  

        // 사용자 리스트 화면으로 이동(forwarding)
        return "/user/myTeamLanking.jsp";          
    }
}

