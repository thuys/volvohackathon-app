package com.deloitte.classes.soap;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.deloitte.classes.datamodel.Position;
import com.deloitte.classes.datamodel.Truck;

public class SoapTracking {
	
	public static void getBody(SOAPBody body, String login) throws Exception{
		SOAPElement soapBodyElem1 = body.addChildElement("getNewTrackingDataV2", "typ");
		
        	SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Api_SessionId_1");
        
		        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("id");
		        	soapBodyElem3.addTextNode(login);
	}
	
	public static ArrayList<Truck> InterpreteBody(SOAPBody body)throws Exception{
		ArrayList<Truck> truckList = new ArrayList<Truck>();
		NodeList result = body.getElementsByTagName("result").item(0).getChildNodes();
		for(int i = 0; i<result.getLength();i++){
			
			Truck t = new Truck();	
			t.information = null;
			t.status = null; //TODO
			
			Node truckArray = result.item(i);
			NodeList truckArrayNodes = truckArray.getChildNodes();
			for(int j=0; j<truckArrayNodes.getLength();j++){
				Node setting = truckArrayNodes.item(j);
				switch(setting.getLocalName()){
				case "vehicleId":
					t.id = setting.getChildNodes().item(0).getTextContent();
					//System.out.println(setting.getLocalName() + " - " + setting.getChildNodes().item(0).getTextContent());
					break;
				case "momentaneousVehicleSpeed":
					String value = setting.getTextContent();
					//System.out.println(setting.getLocalName() + " - " + value);
					break;
				case "timestamp":
					String value2 = setting.getTextContent();
					//System.out.println(setting.getLocalName() + " - " + value2);
					break;
				case "position":
					NodeList positionNodes = setting.getChildNodes();
					for(int k = 0; k<positionNodes.getLength(); k++){
						if(positionNodes.item(k).getLocalName().equals("longitude")){
							t.position.lng = positionNodes.item(k).getChildNodes().item(0).getTextContent();
						} else if(positionNodes.item(k).getLocalName().equals("latitude")){
							t.position.lat = positionNodes.item(k).getChildNodes().item(0).getTextContent();
						}
						//System.out.println(positionNodes.item(k).getLocalName() + " - " + positionNodes.item(k).getChildNodes().item(0).getTextContent());
					}
					break;
				case "triggerType":
					String stop = "FALSE";
					if(setting.getTextContent().equals("STOP")){
						stop = "TRUE";
					}
					//System.out.println(setting.getLocalName() + " - " + stop);
					break;
				}
			}
			truckList.add(t);
		}
		return truckList;
	}

}
