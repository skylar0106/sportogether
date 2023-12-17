package controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.*;

public class UpdateTeamController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateTeamController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	int teamId = 1;
		//int teamId = Integer.parseInt(request.getParameter("teamId"));
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: 커뮤니티 수정 form 요청	
//   		UserManager manager = UserManager.getInstance();
    		TeamManager tmanager = TeamManager.getInstance();
    		Team team = tmanager.findTeam(teamId);
			request.setAttribute("team", team);			
			
//			List<User> members = tmanager.findTeamMembers(teamId); // 커뮤니티 회원 리스트 검색
//			request.setAttribute("members", members);
			
			// 우리가 이동할 주소로 변경해야함!
			return "/teamManage_modi.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
    	// POST request (커뮤니티 정보가 parameter로 전송됨)
		// 업데이트 관련 jsp 파일에선 요정도만 수정되게 해놓을거임
    	Team team = new Team(
    		teamId,
    		request.getParameter("name"),
    		request.getParameter("sport"),
    		request.getParameter("location")
    		);

    	
 
    	log.debug("Update Team : {}", team);

		TeamManager tmanager = TeamManager.getInstance();
		tmanager.updateTeam(team);
		//이것도 우리가 변경해줘야함
		// 정보가 수정된 상태로 updateForm에 redirect됨
        return "/teamManage_modi.jsp";			
    }
}
