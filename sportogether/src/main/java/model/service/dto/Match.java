package model.service.dto;

import java.time.LocalDate;

public class Match {
	private String teamID;
	private String sport;
    private LocalDate date;
    private String winner;
    private String loser;
    private String matchID;
    private int winpoint;
    private int losepoint;
    
    public Match() {}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
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

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = matchID;
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

	public Match(String teamID, String sport, LocalDate date, String winner, String loser, String matchID, int winpoint,
			int losepoint) {
		super();
		this.teamID = teamID;
		this.sport = sport;
		this.date = date;
		this.winner = winner;
		this.loser = loser;
		this.matchID = matchID;
		this.winpoint = winpoint;
		this.losepoint = losepoint;
	}
    
     
    
    
}