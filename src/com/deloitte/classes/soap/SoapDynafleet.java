package com.deloitte.classes.soap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import com.deloitte.classes.datamodel.ColourCode;
import com.deloitte.classes.datamodel.Fleet;
import com.deloitte.classes.datamodel.Truck;

public class SoapDynafleet extends TimerTask {
	
	private String login= "";
	private Notification notification = new Notification();
	private Map<Integer, Fleet> fleetMap;
	private long lastRun = 0;
	private ArrayList<Truck> truckList;

	public SoapDynafleet(Map<Integer, Fleet> fleetMap){
		this.fleetMap = fleetMap;
	}
	
	
	//Parameters
	private static String soapURL = "https://api2.dynafleetonline.com/ports/";
	private static String serverURI="http://wirelesscar.com/dynafleet/api/types";    
    
    public String RunSoap(String type) {
 
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(type), soapURL + type);

            // Process the SOAP Response
            String result = printSOAPResponse(type, soapResponse);

            soapConnection.close();
            return result;
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            return null;
        }
    }

    private SOAPMessage createSOAPRequest(String type) throws Exception {
    	
    	MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("typ", serverURI);
        
        SOAPBody soapBody = envelope.getBody();
        
    	switch(type){
    	case "Login":
    		SoapLogin.getBody(soapBody);
    		break;
    	case "Notification":
    		SoapNotification.getBody(soapBody, login);
    		break;
    	case "Tracking":
    		SoapTracking.getBody(soapBody, login);
    		break;
    	}
        soapMessage.saveChanges();
        

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    private String printSOAPResponse(String type, SOAPMessage soapResponse) throws Exception {
    	
    	//Display message
    	/*TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.println("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        System.out.println("");*/
        
        String returnMessage = "";
        
        //Get info
        switch(type){
	        case "Login":
	        	returnMessage = SoapLogin.InterpreteBody(soapResponse.getSOAPBody());
	        	break;
	        case "Notification":
	        	notification = SoapNotification.InterpreteBody(soapResponse.getSOAPBody());
	        	break;
	        case "Tracking":
	        	truckList = SoapTracking.InterpreteBody(soapResponse.getSOAPBody());
	        	break;
        }
    	return returnMessage;       
    }

	@Override
	public void run() {
		try{
			login = RunSoap("Login");
			TimeUnit.SECONDS.sleep(2);				
			RunSoap("Tracking");
			lastRun = System.currentTimeMillis();
			Fleet fleet = new Fleet();
			fleet.trucks = truckList;
			fleetMap.put(fleetMap.size(), fleet);
			fleet.numberIfUnusualAreas = 4;
			fleet.numberInAttentionZone = 12;
			fleet.numberInCheckPointRange = 32;
			fleet.numberOfHarshBrakes = 30;
			fleet.numberOfHoursIdle = 12;
			fleet.numberOfLockBreaches = 1;
			fleet.numberOfShocks = 321;
			fleet.numberOfSpeedingBreaches = 3;
			fleet.colourInCheckPointRange = ColourCode.GREEN;
			fleet.colourofAttentionZone = ColourCode.RED;
			fleet.colourOfHarshBrakes = ColourCode.RED;
			fleet.colourOfHoursIdle = ColourCode.YELLOW;
			fleet.colourOfLockBreaches = ColourCode.YELLOW;
			fleet.colourOfShocks = ColourCode.RED;
			fleet.colourOfSpeedingBreaches = ColourCode.RED;
			fleet.colourOfUnsualAraes = ColourCode.GREEN;
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
