package controller.team;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.User;
import model.service.TeamManager;
import model.service.UserManager;
import model.service.dto.*;

public class LankingListController implements Controller {
    // private static final int countPerPage = 100; // 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 로그인 여부 확인
        
        
        /*
        String currentPageStr = request.getParameter("currentPage");    
        int currentPage = 1;
        if (currentPageStr != null && !currentPageStr.equals("")) {
            currentPage = Integer.parseInt(currentPageStr);
        }       
        */
        
        TeamManager manager = TeamManager.getInstance();
        List<Lanking> lankingList  = manager.findLankingList();
        // List<User> userList = manager.findUserList(currentPage, countPerPage);

        // userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
        request.setAttribute("lankingList", lankingList);               
//      request.setAttribute("curUserId", 
//              UserSessionUtils.getLoginUserId(request.getSession()));     

        // 사용자 리스트 화면으로 이동(forwarding)
        return "/team/totalLanking.jsp";        
    }
}
