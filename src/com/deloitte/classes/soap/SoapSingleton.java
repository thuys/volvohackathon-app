package com.deloitte.classes.soap;

import java.util.HashMap;
import java.util.Timer;

import com.deloitte.classes.datamodel.Fleet;

public class SoapSingleton {
	
	public static HashMap<Integer, Fleet> dataToVisualise;

	Timer t = new Timer();
    SoapDynafleet dynafleet = new SoapDynafleet(dataToVisualise);
    // This task is scheduled to run every 10 seconds
    
    private static SoapSingleton singleton = new SoapSingleton( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private SoapSingleton() { 
    	//t.scheduleAtFixedRate(dynafleet, 0, 30000);
    }

    /* Static 'instance' method */
    public static SoapSingleton getInstance( ) {
       return singleton;
    }

    /* Other methods protected by singleton-ness */
    protected static  HashMap<Integer, Fleet> getFleet() {
       return dataToVisualise;
    }
    
}
