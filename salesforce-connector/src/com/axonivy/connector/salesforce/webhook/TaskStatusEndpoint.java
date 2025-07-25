package com.axonivy.connector.salesforce.webhook;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import ch.ivyteam.ivy.environment.Ivy;

@Path("webhooks")
public class TaskStatusEndpoint {
	@POST
	@Path("/notifyTaskId")
	@Consumes(value= {MediaType.APPLICATION_XML})
	@Produces(value = {MediaType.APPLICATION_XML})
	public String getJsonResponse(
			String task) {
		
		int start = task.indexOf("<sf:Id>");
		int end = task.indexOf("</sf:Id>");
		String taskid = start>=0&& end>=0 && start<end ?task.substring(start+7,end):"N/a";
		Ivy.log().info("Task with id {0} was changed in salesforce",taskid);

		Ivy.wf().signals().create().data(taskid).send("webhook:notifyTaskId");

		try {
			SOAPMessage msg = createSOAPResponse(null);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			msg.writeTo(out);
			String strMsg = new String(out.toByteArray());
			return strMsg;
		} catch (Exception e) {
			Ivy.log().error("could not create SOAP message response",e);
		}
		return "true";//fallback 
	}
	
   
    private static SOAPMessage createSOAPResponse(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
      //add the soap declaration
        envelope.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        //remove the default SOAP-ENV
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        //Set the prefix to soap instead of SOAP-ENV
        envelope.setPrefix("soapenv");
        soapMessage.getSOAPHeader().detachNode(); //no header 


        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
      //add the soap declaration
        soapBody.setPrefix("soapenv");
        SOAPElement soapBodyElem = soapBody.addChildElement("notificationsReponse","","http://soap.sforce.com/2005/09/outbound");
        
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ack");
        soapBodyElem1.addTextNode("true");
    

        //MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }

}
