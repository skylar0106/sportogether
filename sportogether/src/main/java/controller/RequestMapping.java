package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.team.*;
import controller.comm.*;
import controller.team.UpdateTeamController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
//      
        mappings.put("/home/main", new ForwardController("/home/mainPage_member.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/login.jsp"));
        mappings.put("/user/login", new LoginController());
        
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());
//       
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
//      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
//      mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/register", new RegisterUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
//      mappings.put("/user/update/form", new UpdateUserFormController());
//      mappings.put("/user/update", new UpdateUserController());        
        mappings.put("/user/update", new UpdateUserController());
        
//       mappings.put("/user/delete", new DeleteUserController());
        
        // 커뮤니티 관련 request URI 추가
//        mappings.put("/community/list", new ListCommunityController());
//        mappings.put("/community/view", new ViewCommunityController());
//        mappings.put("/community/create/form", new ForwardController("/community/creationForm.jsp"));
//        mappings.put("/community/create", new CreateCommunityController());
//        mappings.put("/community/update", new UpdateCommunityController());
                
        //메인 페이지
        mappings.put("/mainPage", new ForwardController("/mainPage.jsp"));
        mappings.put("/mainPageLogin", new MainPageLoginController());
        
        // 검색, 라이벌 관련 controller
        mappings.put("/team/test", new ForwardController("/team/test.jsp"));
        mappings.put("/team/search", new SearchTeamController());
        //팀 검색하고 결과 Controller
        mappings.put("/team/search/result", new SearchTeamListController());
        
       //대결 신청 관련 Controller
       // mappings.put("/team/request",new ForwardController("/team/requestList.jsp") ); 
        mappings.put("/team/request",new ViewBattleRequestController() ); //받거나 보낸 대결신청 조회
        mappings.put("/team/request/create", new CreateBattleRequestController()); //대결 신청함
        mappings.put("/team/request/approve",new UpdateBattleRequestController() ); // 받은 대결신청 승인
        mappings.put("/team/request/reject",new UpdateBattleRequestController() ); //받은 대결신청 승인
        mappings.put("/team/request/cancel",new UpdateBattleRequestController() ); //보낸대결신청 취소
        
        // team controller
        mappings.put("/team/portfolio", new TeamPortfolioController());
        
     // 나의 포트폴리오 controller
        mappings.put("/user/portfolio", new MyPortfolioController());
        
        // 랭킹 관련 controller
        mappings.put("/team/lankingList", new LankingController());
        
        //마이페이지 관련 controller
        mappings.put("/user/myTeamLanking", new MyTeamLankingController());
        mappings.put("/user/update", new UpdateUserController());	// 팀 정보 업데이트
        
      
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}