package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import model.service.dto.Team;

public class LevelDAO {
private JDBCUtil jdbcUtil = null;
    
    public LevelDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    /*
     * 팀의 레벨 구하기
     * teamscore의 테이블에서 경기 횟수 가져옴
     * 경기 횟수에 따라 레벨을 반환
     * 0: 0번 1: 1~3번 /2: 4~7번 /3: 8~10번 /4: 11~15번 /5: 16~20번
     * 6: 21~30번 /7: 31~50번 /8: 51~80번 /9: 81~
     * */
    public int getMatchList(Team tm) throws SQLException{
        int level = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT matches ");
        sql.append("FROM teamscore ");
        sql.append("where teamid = ? ");
        
        jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] {tm.getTeamId()});
        try {
            int matches = jdbcUtil.executeUpdate();
            
            if (matches > 80)
                level = 9;
            else if (matches > 50)
                level = 8;
            else if (matches > 30)
                level = 7;
            else if (matches > 20)
                level = 6;
            else if (matches > 10)
                level = 5;
            else if (matches > 7)
                level = 4;
            else if (matches > 3)
                level = 3;
            else if (matches > 1)
                level = 2;
            else if (matches > 50)
                level = 2;
            else
                level = 0;
        }catch(SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.close();
        }

        return level;
    }
}
