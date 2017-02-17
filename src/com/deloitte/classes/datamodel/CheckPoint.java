package com.deloitte.classes.datamodel;

public class CheckPoint {
	public Position position = new Position();
	public boolean doneStatus = true;


	public CheckPoint(Position pos, boolean done) {
		this.position = pos;
		this.doneStatus = done;
	}

}
