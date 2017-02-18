package com.deloitte.classes.datamodel;

import java.util.concurrent.ThreadLocalRandom;

public class Truck {
	public String id = "ZER";
	public ColourCode status = getColor();
	public Position position = new Position();
	public TruckInformation information = new TruckInformation();

	private ColourCode getColor(){
		int rnd = ThreadLocalRandom.current().nextInt(0, 11);
		if(rnd >= 10){
		return ColourCode.RED;
		} else if(rnd <=7){
			return ColourCode.GREEN;
		} else {
			return ColourCode.YELLOW;
		}
	}
}
