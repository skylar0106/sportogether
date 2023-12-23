package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import model.dao.MyPortfolioDAO;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.service.dto.User;
import model.service.dto.TeamScore;

public class MyPortfolioController implements Controller {
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyPortfolioController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
     // 로그인 여부 확인
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form 요청으로 redirect
        }
        
        UserManager uManager = UserManager.getInstance();
        String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
        
        User user = null;
        try {
            user = uManager.findUser(userId);    // 사용자 정보 검색    
        } catch (UserNotFoundException e) {             
            return "/mainPage.jsp";
        }   
        

        // 로그로 확인
        logger.debug("User: {}", user);
        
        // 팀 정보, 전적, 최근 경기 일자, 팀 멤버 수, 팀 소개 멘트를 request에 저장하여 전달
        request.setAttribute("user", user);
        System.out.println(user.getUserId());

        // 팀 포트폴리오 화면으로 이동(forwarding)
        return "/user/portfolio.jsp";
    }
}