package com.deloitte.soap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.Text;


import com.deloitte.jdbc.Database;
import com.deloitte.jdbc.Row;

public class SoapReader {
	
	//Parameters
	private String soapURL = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
    private String serverURI = "http://ws.cdyne.com/";
	private Database db;
    private Map<String,String> resultMap;
    
    
    public SoapReader(Database source){
    	this.db = source;
    }
    
    public void RunSoap() {
    	resultMap = new HashMap<String, String>();
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), soapURL);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
            
            Row r = Row.CreateRow(resultMap);
            db.Write(r);
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", "example");
        soapBodyElem1.addTextNode("tuyttendaele@deloitte.com");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", "example");
        soapBodyElem2.addTextNode(""+Math.round((Math.random()*10000)));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

        soapMessage.saveChanges();

        /* Print the request message */
        //System.out.print("Request SOAP Message = ");
        //soapMessage.writeTo(System.out);
        //System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
    	SOAPBody body = soapResponse.getSOAPBody();
    	Iterator i = body.getChildElements();

    	while(i.hasNext()){
    		getChildNodes((SOAPElement)i.next());
    	}
       
    }
    
    private void getChildNodes(SOAPElement soapElement){
    	if(soapElement.hasChildNodes()){
    		Iterator it = soapElement.getChildElements();
    		while(it.hasNext()){
    			Object o = it.next();
    			if(o instanceof SOAPElement){
    				SOAPElement soapEle = (SOAPElement)o;
    				//System.out.println(soapEle.getLocalName());
    				getChildNodes(soapEle);
    			} else if(o instanceof Text){
    				Text text = (Text)o;
    				resultMap.put(soapElement.getLocalName(), text.getData());
    				//System.out.println(soapElement.getLocalName() + " - " + text.getData());
    			} else{
    				System.out.println("sth else");
    			}
    		}
    	}
    }
}
