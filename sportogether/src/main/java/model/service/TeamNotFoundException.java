package model.service;

public class TeamNotFoundException extends Exception {
	public  TeamNotFoundException() {
		super();
	}
	public TeamNotFoundException(String m) {
		super(m);
	}
}
