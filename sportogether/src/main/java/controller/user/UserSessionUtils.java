package controller.user;

import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.service.dto.User;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    static UserDAO userDao = new UserDAO();
    
    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginUserId(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
    public static boolean isLoginUser(String userId, HttpSession session) {
        String loginUser = getLoginUserId(session);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(userId);
    }
    
	public static boolean hasTeam(HttpSession session) {
		String userId = getLoginUserId(session);
		try {
		User user = userDao.findUser(userId);
		if(user.getTeamId()>0) { //없음
			return false;
		}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
