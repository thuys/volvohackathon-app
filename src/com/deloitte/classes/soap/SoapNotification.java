package com.deloitte.classes.soap;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;

import org.w3c.dom.NodeList;

public class SoapNotification {
	public static void getBody(SOAPBody body, String login) throws Exception{
		SOAPElement soapBodyElem1 = body.addChildElement("checkNotificationFlags", "typ");
		
        	SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Api_SessionId_1");
        
		        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("id");
		        	soapBodyElem3.addTextNode(login);
	}
	
	public static Notification InterpreteBody(SOAPBody body)throws Exception{
		NodeList ele = body.getElementsByTagName("result");
		NodeList results = ele.item(0).getChildNodes();

		Notification notification = new Notification();
		for(int i = 0; i<results.getLength(); i++){
			notification.setSetting(results.item(i).getLocalName(), results.item(i).getTextContent());
		}
		return notification;
	}
}
