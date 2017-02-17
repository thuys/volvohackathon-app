package com.deloitte.classes.datamodel;

import java.util.ArrayList;

public class Fleet {
	public ArrayList<Truck> trucks = new ArrayList<Truck>();
	public int numberInCheckPointRange = 0;
	public ColourCode colourInCheckPointRange = ColourCode.GREEN;
	
	public int numberOfSpeedingBreaches = 0;
	public ColourCode colourOfSpeedingBreaches = ColourCode.GREEN;

	public int numberOfHoursIdle = 0;
	public ColourCode colourOfHoursIdle = ColourCode.GREEN;

	public int numberOfShocks = 0;
	public ColourCode colourOfShocks = ColourCode.GREEN;

	public int numberInAttentionZone= 0;
	public ColourCode colourofAttentionZone = ColourCode.GREEN;

	public int numberOfLockBreaches = 0;
	public ColourCode colourOfLockBreaches = ColourCode.GREEN;

	public int numberIfUnusualAreas = 0;
	public ColourCode colourOfUnsualAraes = ColourCode.GREEN;

	public int numberOfHarshBrakes = 0;
	public ColourCode colourOfHarshBrakes = ColourCode.GREEN;

	//+colourcode
}
