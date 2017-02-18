package com.deloitte.classes.datamodel;

import java.util.ArrayList;

public class HeatmapInformation {
	public ArrayList<HeatPoint> heatPoints = new ArrayList<HeatPoint>();
	
	public HeatmapInformation() {
	
		this.heatPoints.add(new HeatPoint(new Position(51.218925, 4.403438), "FF8200", 5));
		this.heatPoints.add(new HeatPoint(new Position(50.700194, 5.119693), "FFE600", 1));
		this.heatPoints.add(new HeatPoint(new Position(55.761354, 37.594202), "FF9B00", 4));
		this.heatPoints.add(new HeatPoint(new Position(50.015424, 22.010245), "FF3700", 8));
		this.heatPoints.add(new HeatPoint(new Position(50.006089, 21.975576), "FF3700", 8));
		this.heatPoints.add(new HeatPoint(new Position(57.071497, 27.933450), "FF0500", 10));
		this.heatPoints.add(new HeatPoint(new Position(56.944252, 27.645947), "FFCD00", 2));
		this.heatPoints.add(new HeatPoint(new Position(52.324589, 14.502123), "FF6900", 6));
		this.heatPoints.add(new HeatPoint(new Position(48.653205, 13.246692), "FF8200", 5));
	}
}
