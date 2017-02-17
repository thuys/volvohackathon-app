package com.deloitte.classes.datamodel;

import java.util.ArrayList;

public class TruckInformation {
	public ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();
	public boolean isInCheckPoint = true;
	public int driverScore = 0;
	public int assetScore = 0;
	public int geoScore = 0;
	public double propabilityOfDamage = 0.01;
//	public int numberOfSpeedingBreaches = 0;
//	public int numberOfTrucksIdle = 0;
//	public int numberOfShocks = 0;
//	public int numberInAttentionZone= 0;
//	public int numberOfLockBreaches = 0;
//	public int numberofUnusualAreas = 0;
//	public int numberofHarshBraks = 0;
	
}
