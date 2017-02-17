package com.deloitte.classes.soap;

import java.util.HashMap;
import java.util.Timer;

import com.deloitte.classes.datamodel.Fleet;

public class SoapSingleton {
	
	public static HashMap<Integer, Fleet> dataToVisualise;

	Timer t = new Timer();
    private static SoapDynafleet dynafleet;
    
    private static SoapSingleton singleton = new SoapSingleton( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    
    private SoapSingleton() { 
    	dataToVisualise = new HashMap<Integer, Fleet>();
    	dynafleet = new SoapDynafleet(dataToVisualise);
    	t.scheduleAtFixedRate(dynafleet, 0, 300000);
    }

    /* Static 'instance' method */
    public static SoapSingleton getInstance( ) {
       return singleton;
    }

    /* Other methods protected by singleton-ness */
    public HashMap<Integer, Fleet> getFleet() {
       return dataToVisualise;
    }
    
}
