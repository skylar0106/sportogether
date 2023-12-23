package model.service;
import model.dao.TeamDAO;
import model.dao.UserDAO;
import model.service.dto.User;


public class MemberAnalysis {
    /*
     * 리더인지
     * 이미 가입한 팀이 있는지
     * */
    private UserDAO userDAO = null;
    private TeamDAO teamDAO = null;
    
    public MemberAnalysis() {
        userDAO = new UserDAO();
        teamDAO = new TeamDAO();
    }
    
    
    //이미 가입한 팀이 있는지, 있으면 t, 없으면 f
    public boolean hasTeam(User user) {
        
        if(user.getTeamId() <= 0) { //없음
            return false;
        }
        else {
            return true;
        }
    }
    
    //리더이면 t, 아니면 f
    public boolean isLeader(User user) {
        if(hasTeam(user)) {
            if(user.getLeader() == 1) {
                return true;
            }
        }
        return false;
    }
    
    
    
}