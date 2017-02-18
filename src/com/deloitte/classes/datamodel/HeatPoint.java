package com.deloitte.classes.datamodel;

public class HeatPoint {
	public Position position = new Position();
	public String color = "FFFF00";
	public int incidents = 0;


	public HeatPoint(Position pos, String color, int incidents) {
		this.position = pos;
		this.color = color;
		this.incidents = incidents;
	}
}
