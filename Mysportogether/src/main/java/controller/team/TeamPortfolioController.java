package controller.team;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import model.dao.TeamPortfolioDAO;
import model.service.TeamManager;
import model.service.dto.Rival;
import model.service.dto.Team;
import model.service.dto.TeamScore;

public class TeamPortfolioController implements Controller {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TeamPortfolioController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String teamID = request.getParameter("teamID");
        TeamManager manager = TeamManager.getInstance();
        
        // TeamManager를 이용하여 팀 정보 가져오기
        Team team = manager.getTeamInfo(teamID);

        // TeamManager에서 가져온 Team을 사용하여 전적 정보를 가져오기
        TeamScore teamScore = manager.getTeamScore(teamID);

        // TeamManager에서 가져온 Team을 사용하여 최근 경기 일자 가져오기
        String recentMatchDate = manager.getRecentMatchDate(teamID);
        
        // TeamManager에서 가져온 Team을 사용하여 팀 멤버 수 가져오기
        int teamMemberCount = manager.getTeamMemberCount(teamID);
        
        // TeamManager에서 가져온 Team을 사용하여 팀 소개 멘트 가져오기
        String teamIntroduction = manager.getTeamIntroduction(teamID);

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
