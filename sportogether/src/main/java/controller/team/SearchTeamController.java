package controller.team;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.User;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.*;

public class SearchTeamController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
//        String teamName = request.getParameter("teamName");
        String teamName = "TeamA";
        // 로그인 여부 확인
    	
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
		TeamManager manager = TeamManager.getInstance();
		Rival rival  = manager.findRivalTeam(teamName);
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("rival", rival);				
//		request.setAttribute("curUserId", 
//				UserSessionUtils.getLoginUserId(request.getSession()));		

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/team/matchSearch.jsp";        
    }
}
