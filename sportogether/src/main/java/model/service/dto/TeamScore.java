package model.service.dto;

public class TeamScore {
    private String teamID;
    private int matches;
    private int win;
    private int lose;
    private int ranking;
    private int draw;
    private double rate;
    
    public TeamScore() {}
    
    public TeamScore(String teamID, int matches, int win, int lose, int ranking, int draw, double rate) {
		this.teamID = teamID;
		this.matches = matches;
		this.win = win;
		this.lose = lose;
		this.ranking = ranking;
		this.draw = draw;
		this.rate = rate;
	}

    public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	// toString method
    @Override
    public String toString() {
        return "TeamScore{" +
                "teamID='" + teamID + '\'' +
                ", matches=" + matches +
                ", win=" + win +
                ", lose=" + lose +
                ", ranking=" + ranking +
                ", draw=" + draw +
                ", rate=" + rate +
                '}';
    }
}
