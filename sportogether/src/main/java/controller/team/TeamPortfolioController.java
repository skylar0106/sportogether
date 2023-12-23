package controller.team;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.TeamPortfolioDAO;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.service.dto.Rival;
import model.service.dto.Team;
import model.service.dto.TeamScore;
import model.service.dto.User;

public class TeamPortfolioController implements Controller {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TeamPortfolioController.class);
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
        
        request.setAttribute("user", user);     // 사용자 정보 저장                

        
        int teamId =user.getTeamId();
        TeamManager manager = TeamManager.getInstance();
        
        // TeamManager를 이용하여 팀 정보 가져오기
        Team team = manager.getTeamInfo(teamId);

        // TeamManager에서 가져온 Team을 사용하여 전적 정보를 가져오기
        TeamScore teamScore = manager.getTeamScore(teamId);

        // TeamManager에서 가져온 Team을 사용하여 최근 경기 일자 가져오기
        String recentMatchDate = manager.getRecentMatchDate(teamId);
        
        // TeamManager에서 가져온 Team을 사용하여 팀 멤버 수 가져오기
        int teamMemberCount = manager.getTeamMemberCount(teamId);
        
        // TeamManager에서 가져온 Team을 사용하여 팀 소개 멘트 가져오기
        String teamIntroduction = manager.getTeamIntroduction(teamId);

        // 로그로 확인
        logger.debug("Team: {}", team);
        logger.debug("TeamScore: {}", teamScore);
        logger.debug("Recent Match Date: {}", recentMatchDate);
        logger.debug("Team Member Count: {}", teamMemberCount);
        logger.debug("Team Introduction: {}", teamIntroduction);
        
        // 팀 정보, 전적, 최근 경기 일자, 팀 멤버 수, 팀 소개 멘트를 request에 저장하여 전달
        request.setAttribute("team", team);
        request.setAttribute("teamScore", teamScore);
        request.setAttribute("recentMatchDate", recentMatchDate);
        request.setAttribute("teamMemberCount", teamMemberCount);
        request.setAttribute("teamIntroduction", teamIntroduction);

        // 팀 포트폴리오 화면으로 이동(forwarding)
        return "/team/portfolio.jsp";
    }
}