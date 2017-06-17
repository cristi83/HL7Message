/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl7message;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 *
 * @author Cristian
 */

public class HL7Message 
{
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    public static void main(String[] args) throws Exception
    {
      
         ADT_A01 adt = new ADT_A01();
        adt.initQuickstart("ADT", "A01", "P");
        //adt.initQuickstart("ADT", "A01", "P");    
        
        MSH mshSegment = adt.getMSH();
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        
        PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue(("Doe"));
        pid.getPatientName(0).getGivenName().setValue("Joe");
        pid.getPatientIdentifierList(0).getID().setValue("123456");
        
        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getPipeParser();
        String encodedMessage = parser.encode(adt);
        System.out.println("Printing ER7 Encoded Message");
        System.out.println(encodedMessage);
        
        parser =  context.getXMLParser();
        encodedMessage = parser.encode(adt);
        System.out.println("Printing XML Encoded Message");
        System.out.println(encodedMessage); 
        
    }
    
}
