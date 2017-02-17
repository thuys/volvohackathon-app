package com.deloitte.classes.soap;
import java.util.HashMap;

import java.util.Map;

public class Notification {
	
	private static Map<String, String> map = new HashMap<String, String>(){{
		put("deletedAlarm", "");
		put("deletedCustomer", "");
		put("deletedDTMAlarm", "");
		put("deletedDriver", "");
		put("deletedFormMessage", "");
		put("deletedGeofencePlus", "");
		put("deletedMessage", "");
		put("deletedOrder", "");
		put("deletedPOI", "");
		put("deletedServicePlan", "");
		put("deletedUser", "");
		put("deletedVehicle", "");
		put("modifiedCustomer", "");
		put("modifiedDriver", "");
		put("modifiedGeofencePlus", "");
		put("modifiedOrder", "");
		put("modifiedOrderItem", "");
		put("modifiedPOI", "");
		put("modifiedServicePlan", "");
		put("modifiedUser", "");
		put("modifiedVehicle", "");
		put("newAlarm", "");
		put("newCDPDownloadSendstatus", "");
		put("newDTMAlarm", "");
		put("newDTMAlarmSetupSendstatus", "");
		put("newDriverData", "");
		put("newDtjData", "");
		put("newFormMessage", "");
		put("newFormMessageSendStatus", "");
		put("newGeofenceData", "");
		put("newGeofencePlusData", "");
		put("newMessage", "");
		put("newMessageSendStatus", "");
		put("newTrackingData", "");
		put("newTrackingSetUpSendstatus", "");
		put("newVehiclePositionData", "");
		put("newVehicleEventMessages", "");
		put("newVehicleReportData", "");
		put("orderMarkedAsRead", "");
	}};
	
	public void setSetting(String setting, String value){
		map.put(setting, value);
	}
	
	
	public String getSetting(String setting){
		return map.get(setting);
	}
}
