package controller.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import model.dao.JDBCUtil;
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
        
        if(request.getMethod().equals("GET")) {
            HttpSession session = request.getSession();
            String userId = UserSessionUtils.getLoginUserId(session);
        
            UserManager manager = UserManager.getInstance();
            User user = manager.findUserForPortfolio(userId);   // 수정하려는 사용자 정보 검색
            request.setAttribute("user", user); 

            return "/user/portfolio.jsp";
        }
        
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        
            User user = new User(
            userId,
            request.getParameter("career"),
            request.getParameter("selectedInterests"),
            request.getParameter("tcomment"))
            ;
        
        UserManager manager = UserManager.getInstance();
        manager.updateMyPotpolio(user); 
        
        // 로그로 확인
        logger.debug("User: {}", user);
        logger.debug("User: {}", user.getComment());
        logger.debug("User: {}", user.getCareer());
        logger.debug("User: {}", user.getInterests());

        // 팀 포트폴리오 화면으로 이동(forwarding)
        return "redirect:/user/portfolio";
    }
}
