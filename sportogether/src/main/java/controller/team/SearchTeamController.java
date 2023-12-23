package controller.team;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.TeamDAO;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.service.dto.*;

public class SearchTeamController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String teamNam = request.getParameter("teamName");
        // 로그인 여부 확인
    	
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
        UserManager uManager = UserManager.getInstance();
        String userId = null;
        if (UserSessionUtils.hasLogined(request.getSession())) {
            userId = UserSessionUtils.getLoginUserId(request.getSession());
        }
        
        TeamManager manager = TeamManager.getInstance();
        User user = null;
        try {
            user = uManager.findUser(userId);    // 사용자 정보 검색    
        } catch (UserNotFoundException e) {             
            user = new User(0);
        }   
        
        request.setAttribute("user", user);     // 사용자 정보 저장   
    	
		Rival rival  = manager.findRivalTeam(user.getTeamId());
		// List<User> userList = manager.findUserList(currentPage, countPerPage);
		
		List<Team> teamList = null;
		TeamDAO teamDAO = new TeamDAO();
		try
		{
			teamList = teamDAO.getTeamList();
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("hasNoTeam", true);
			return  "/team/matchSearch.jsp"; 
		}
		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("rival", rival);
		request.setAttribute("teamList", teamList);
//		request.setAttribute("curUserId", 
//				UserSessionUtils.getLoginUserId(request.getSession()));		

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/team/matchSearch.jsp";        
    }
}
