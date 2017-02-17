package com.deloitte.classes.soap;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;

import org.w3c.dom.NodeList;

public class SoapLogin {

	public static void getBody(SOAPBody body) throws Exception{
		SOAPElement soapBodyElem1 = body.addChildElement("login", "typ");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Api_LoginLoginTO_1");
        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("gmtOffset");
        SOAPElement soapBodyElem4 = soapBodyElem2.addChildElement("password");
        SOAPElement soapBodyElem5 = soapBodyElem2.addChildElement("username");
        
        SOAPElement soapGmtoffsetElem = soapBodyElem3.addChildElement("value");
        soapGmtoffsetElem.addTextNode("1");
        
        soapBodyElem4.addTextNode("ApiVolvoHack013");
        soapBodyElem5.addTextNode("ApiVolvoHack13");
	}
	
	public static String InterpreteBody(SOAPBody body)throws Exception{
		NodeList ele = body.getElementsByTagName("id");
		return ele.item(0).getTextContent();
	}
	
}
