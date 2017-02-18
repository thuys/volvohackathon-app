package com.deloitte.classes.datamodel;

public class CheckPoint {
	public Position position = new Position();
	public String doneStatus = "done";


	public CheckPoint(Position pos, String done) {
		this.position = pos;
		this.doneStatus = done;
	}

}
