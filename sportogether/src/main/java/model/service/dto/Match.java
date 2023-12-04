package model.service.dto;

import java.time.LocalDate;

public class Match {
    private String winner;
    private String loser;
    private String sport;
    private LocalDate date;
    private int winpoint;
    private int losepoint;
    
    public Match() {}
    
    public Match(String winner, String loser, String sport, LocalDate date, int winpoint, int losepoint) {
        super();
        this.winner = winner;
        this.loser = loser;
        this.sport = sport;
        this.date = date;
        this.winpoint = winpoint;
        this.losepoint = losepoint;
    }
    
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getLoser() {
        return loser;
    }
    public void setLoser(String loser) {
        this.loser = loser;
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getWinpoint() {
        return winpoint;
    }
    public void setWinpoint(int winpoint) {
        this.winpoint = winpoint;
    }
    public int getLosepoint() {
        return losepoint;
    }
    public void setLosepoint(int losepoint) {
        this.losepoint = losepoint;
    }
  
    
    
}
