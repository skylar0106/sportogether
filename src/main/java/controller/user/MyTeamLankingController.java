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
       
        
        UserManager uManager = UserManager.getInstance();
        String userId = UserSessionUtils.getLoginUserId(request.getSession());
//        int teamId = Integer.parseInt(request.getParameter("teamId"));
             
        User user = null;
        try {
            user = uManager.findUser(userId);    // 사용자 정보 검색    
        } catch (UserNotFoundException e) {             
            return "/mainPage.jsp";
        }  
        
        TeamManager manager = TeamManager.getInstance();
        Lanking lanking = manager.findTeamLanking(user.getTeamId());

        request.setAttribute("lanking", lanking);                  

        // 사용자 리스트 화면으로 이동(forwarding)
        return "/user/myTeamLanking.jsp";          
    }
}

