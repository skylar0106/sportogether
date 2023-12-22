package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.ExistingUserException;
import model.service.UserManager;
import model.service.dto.*;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
//    		// GET request: 회원정보 등록 form 요청	
//    		log.debug("RegisterForm Request");
//
//    		List<Team> TeamList = UserManager.getInstance().findTeamList();	// 커뮤니티 리스트 검색
//			request.setAttribute("teamList", teamList);	
//		
			return "/user/signUp3.jsp";   // 검색한 커뮤니티 리스트를 registerForm으로 전송     	
	    }	

    	// POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
			request.getParameter("userId"),
			request.getParameter("name"),
			request.getParameter("nickName"),
			request.getParameter("sex"),
			request.getParameter("password")
			);
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			
			//return "/home/mainPage.jsp";
			return "/user/signUp4.jsp";
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "redirect:/user/register";
		}
    }
}

