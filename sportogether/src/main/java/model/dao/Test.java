package model.dao;

import model.service.dto.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Test {
   
    private static MatchRivalDAO compDao = new MatchRivalDAO();
    private static LankingDAO lDao = new LankingDAO();
    
    public static void main(String[] args) throws SQLException {        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("팀명을 입력하시오: ");
        String teamName = scanner.next();
        System.out.println();
        
         Team tm = compDao.findByTeamName(teamName);        

         System.out.println("<팀정보>");
         System.out.println("팀ID: " + tm.getTeamId());
         System.out.println("팀명: " + teamName);
         System.out.println("리더: " + tm.getSpoLeader());
         System.out.println("레벨: " + tm.getLevel());
         System.out.println("종목: " + tm.getSport());
         System.out.println("라이벌: " + tm.getRival());
         System.out.println();
         
         Rival rival = compDao.getMatchList(tm);
         System.out.println("라이벌: " + rival.getTeamName());
         System.out.println("라이벌승률: " + rival.getRate());
         
   
         List<Lanking> lk = lDao.findTeamLankingList();
    
         Iterator<Lanking> iter = lk.iterator();
         while(iter.hasNext()) {
             Lanking emp = iter.next();
             int teamId = emp.getTeamID();
             int match = emp.getMatches();
             int win = emp.getWin();
             int lose = emp.getLose();
             int rank = emp.getRanking();
             int draw = emp.getDraw();
             float rate = emp.getRate();
             String name = emp.getTeamName();

             System.out.printf("%s %d %d %d %d %d %f %s\n",teamId, match, win, lose, rank, draw, rate, name);
         }
         System.out.println();
         
        
        scanner.close();
    }
}