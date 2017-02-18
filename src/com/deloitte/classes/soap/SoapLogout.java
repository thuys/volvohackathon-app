package com.deloitte.classes.soap;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;

import org.w3c.dom.NodeList;

public class SoapLogout {
	
	public static void getBody(SOAPBody body, String login) throws Exception{
		SOAPElement soapBodyElem1 = body.addChildElement("getNewTrackingDataV2", "typ");
		
        	SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Api_SessionId_1");
        
		        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("id");
		        	soapBodyElem3.addTextNode(login);
	}
	
	public static String InterpreteBody(SOAPBody body)throws Exception{
		NodeList ele = body.getElementsByTagName("id");
		return ele.item(0).getTextContent();
	}
}
