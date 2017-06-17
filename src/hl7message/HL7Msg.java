/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl7message;



import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.VersionLogger;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_PROCEDURE;
import ca.uhn.hl7v2.model.v24.message.ACK;
import ca.uhn.hl7v2.model.v24.segment.ABS;
import ca.uhn.hl7v2.model.v24.segment.AL1;
import ca.uhn.hl7v2.model.v24.segment.DG1;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.OBR;
import ca.uhn.hl7v2.model.v24.segment.OBX;
import ca.uhn.hl7v2.model.v24.segment.PR1;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import org.hl7x.HL7X;

/**
 *
 * @author Cristian
 */

public class HL7Msg 
{
    public static void main (String args[]) throws Exception
    {
         ADT_A01 adt = new ADT_A01();
         /* adt.initQuickstart(messageCode, messageTriggerEvent, processingId); */
         /* */ /*ADT_A01 is the trigger event.MSH is part of the trigger event */
         /* ADT_A01:ADT A01: Admit/visit notification */
         //adt.getINSURANCE();
         //adt.initQuickstart("ADT");
         
         adt.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_pid = new ADT_A01();
         adt_pid.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_evn = new ADT_A01();
         adt_evn.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_obx = new ADT_A01();
         adt_obx.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_PV1 = new ADT_A01();
         adt_PV1.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_al1 = new ADT_A01();
         adt_al1.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_dg1 = new ADT_A01();
         adt_dg1.initQuickstart("ADT", "A01", "P");
         ADT_A01 adt_pr1 = new ADT_A01();
         adt_pr1.initQuickstart("ADT", "A01", "P");
         
         //ADT_A01 al1_adt = new ADT_A01();
         //al1_adt.initQuickstart("ADT", "A01", "P");
         
         
        //MSH segment - message header segment
         MSH sgmt = adt.getMSH();
        sgmt.getSendingApplication().getNamespaceID().setValue("Test");
        
        //sgmt.getDateTimeOfMessage().getTimeOfAnEvent().getValueAsDate();
        
        sgmt.getSequenceNumber().setValue("123");
        //sgmt.getCountryCode().setValue("+40"); /* country code phone number */
     
        
        //PID segment PID: Patient identification segment
        PID pid = adt_pid.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Popa");
        pid.getPatientName(0).getGivenName().setValue("Ion");
        pid.getPatientIdentifierList(0).getID().setValue("PATID2837"); /* patient identify number */
        /* new */
         pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue("Pantelimon");
         pid.getPatientAddress(0).getCountry().setValue("IF");
         pid.getPatientAddress(0).getStreetAddress().getSad3_DwellingNumber().setValue("15");
         pid.getCitizenship(0).getText().setValue("Romanian");
        /* new */
        pid.getPatientIdentifierList(0).getCheckDigit().setValue("5"); /* here we have a field separator ^ 5 */
        pid.getPatientIdentifierList(0).getCx3_CodeIdentifyingTheCheckDigitSchemeEmployed().setValue("M11"); /* here we have a field separator ^ M11 */
        
        
        //pid.getPatientIdentifierList(1).getID().setValue("789");
        // ^  Name - Component separator (hat)  Purpose - Separates components in a field
        // |  Name - Field separator (pipe)     Purpose - Separates fields in a message
        //
        pid.getAdministrativeSex().setValue("M-");
        
        pid.getPid7_DateTimeOfBirth().getTimeOfAnEvent().setDatePrecision(1980, 10, 18);
       // pid.getPid7_DateTimeOfBirth().getTimeOfAnEvent().setValue("1980"); /* get the birth date */
        
        //pid.getPhoneNumberHome(0).getXtn7_PhoneNumber().setValue("0724111222");
        //pid.getNationality().getCe2_Text().setValue("Romanian");/* CE.2	0	ST	O		Text */
        
        
        /* last value is the last getStreetAddress() method */
        //pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue("Colentina"); // Character - &	Name - Sub-component separator	
                                                                                           // Purpose - Separates components within components (see Data Types)
        //pid.getPatientAddress(0).getXad1_StreetAddress().getStreetName().setValue("Militari");
        
       // pid.getMaritalStatus().getCe2_Text().setValue("Unmarried");
       // pid.getBirthPlace().setValue("Bucharest");
        
        /* AL1: Patient allergy information segment */
        AL1 al1 = adt_al1.getAL1();
        
      
        
        
       adt_al1.getAL1().getAl11_SetIDAL1().getCe2_Text().setValue("Penicilin");
        adt_al1.getAL1().getAl14_AllergySeverityCode().getCe2_Text().setValue("Allergy in tratment"); 
        //adt_al1.getAL1().getAllergyReactionCodeReps(
        
        //Event type segment
        EVN evn = adt_evn.getEVN();
        //evn.getEvn4_EventReasonCode().setValue("A01");
        //evn.getEvn3_DateTimePlannedEvent().getTimeOfAnEvent().setValue("01012017");
        //evn.getEventReasonCode().setValue("A01");
        
        evn.getEvn1_EventTypeCode().setValue("A01"); /* .getEVn1 is with one | field separator */
        evn.getEvn2_RecordedDateTime().getTimeOfAnEvent().setValue("01012017");
        
        // evn.getEventOccurred().getTimeOfAnEvent().setDateMinutePrecision(2015, 10, 3, 22, 1);
        //evn.getDateTimePlannedEvent().getTimeOfAnEvent().setValue("2017"); /* date time planned event TS.1 */
        //evn.getEvn5_OperatorID(0).getGivenName().setValue("Operator");/* evn5 is the operator ID */
        //evn.getEvn6_EventOccurred().getTs1_TimeOfAnEvent().setValue("Good"); /* evn6 is the event occured */

        //ABS Abstract-used to communicate patient abstract information
        //ABS abs = adt.
        
        //OBR - Observation Request
        /* comment in 05-24-2017 */
        /* OBX obx = adt_obx.getOBX();
        obx.getDateTimeOfTheAnalysis().getTimeOfAnEvent().setValue("2017"); */
        
        //PV1 segment:Patient visit
        PV1 pv1 = adt_PV1.getPV1();
        pv1.getAdmittingDoctor(0).getFamilyName().getFn1_Surname().setValue("Rosca Ion"); /* doctor who admitted the patient */
        pv1.getPatientType().setValue("M");
        //pv1.getAttendingDoctor(0).getFamilyName().getFn1_Surname().setValue("Family doctor");
        pv1.getAdmitDateTime().getTimeOfAnEvent().setValue("2017"); 
        pv1.getDischargeDateTime(0).getTimeOfAnEvent().setValue("201701"); 
        // pv1.getAdmitDateTime()
        pv1.getAmbulatoryStatus(0).setValue("No functional limitations"); 
        pv1.getHospitalService().setValue("Tratment inside hospital"); 
        //pv1.getOtherHealthcareProvider(0).getIDNumber().setValue("1");
        //pv1.clear();
        //pv1.getAccountStatus().setValue("1");
        //AL1 - Patient allergy information
        //AL1 al1 = al1_adt.getAL1();
        //al1.getAl12_AllergenTypeCode().getCe5_AlternateText();
        
        /* Diagnosis - DG1 */
        DG1 dg1 = adt_dg1.getDG1();
        dg1.getDg11_SetIDDG1().setValue("001");
        dg1.getDg12_DiagnosisCodingMethod().setValue("I9");
        dg1.getDg13_DiagnosisCodeDG1().getCe1_Identifier().setValue("1550");
        dg1.getDg14_DiagnosisDescription().setValue("Diagnosis description");
        dg1.getDg119_AttestationDateTime().getTimeOfAnEvent().setValue("20151010");
        
        
        /* PR1:Procedures */
       ADT_A01_PROCEDURE pr1 = adt_pr1.getPROCEDURE();
       pr1.getPR1().getPr11_SetIDPR1().setValue("1");
       pr1.getPR1().getProcedureCodingMethod().setValue("MI1");
       pr1.getPR1().getProcedureCode().getText().setValue("111");
       //pr1.getPR1().getProcedureDRGType().setValue("111");
       pr1.getPR1().getProcedureDescription().setValue("Common procedures");
       pr1.getPR1().getProcedureDateTime().getTimeOfAnEvent().setValue("20150810");
       pr1.getPR1().getProcedureFunctionalType().setValue("Normal");
       pr1.getPR1().getProcedureMinutes().setValue("150");
        /* more segements */
        //encode the message and see the output
        HapiContext context = new DefaultHapiContext();
        HapiContext context_pid = new DefaultHapiContext();
        HapiContext context_evn = new DefaultHapiContext();
        HapiContext context_obx = new DefaultHapiContext();
        HapiContext context_pv1 = new DefaultHapiContext();
        HapiContext context_al1 = new DefaultHapiContext();
        HapiContext context_dg1 = new DefaultHapiContext();
        HapiContext context_pr1 = new DefaultHapiContext();
        
        Parser parser     = context.getPipeParser();
        Parser parser_pid = context_pid.getPipeParser();
        Parser parser_evn = context_evn.getPipeParser();
        Parser parser_obx = context_obx.getPipeParser();
        Parser parser_pv1 = context_pv1.getPipeParser();
        Parser parser_al1 = context_al1.getPipeParser();
        Parser parser_dg1 = context_dg1.getPipeParser();
        Parser parser_pr1 = context_pr1.getGenericParser();
        
        String encodedMessage = parser.encode(adt);
        String encodedMessagePID = parser_pid.encode(adt_pid);
        String encodedMessageEVN = parser_evn.encode(adt_evn);
        String encodedMessageOBX = parser_obx.encode(adt_obx);
        String encodedMessagePV1 = parser_pv1.encode(adt_PV1);
        String encodedMessageAL1 = parser_al1.encode(adt_al1);
        String encodedMessageDG1 = parser_dg1.encode(adt_dg1);
        String EncodedMessagePR1 = parser_pr1.encode(adt_pr1);
        
        System.out.println("HL7 Message");
        System.out.println(encodedMessage);
        System.out.println(encodedMessageEVN);
        System.out.println(encodedMessagePID);
        System.out.println(encodedMessagePV1);
        System.out.println(encodedMessageAL1);
        System.out.println(encodedMessageDG1);
        System.out.println(EncodedMessagePR1);
        //System.out.println(encodedMessageOBX);
        
        String encodedResults = encodedMessage + encodedMessagePID + encodedMessagePV1 + encodedMessageAL1;
        
        String ackMessageString = encodedMessage;
        PipeParser pipeParser = new PipeParser();
        
        //String ackMessageString_a = encodedMessagePID;
        String ackMessageString_a = encodedResults;
        PipeParser pipeParser_a = new PipeParser();
        
        String ackMessageString_b = encodedMessagePV1;
        PipeParser pipeParser_b = new PipeParser();
        
        /* try
        {
            Message message = pipeParser.parse(ackMessageString);
            if (message instanceof ACK) 
            {
                ACK ack = (ACK) message;
                ack.getMSH().getProcessingID().getProcessingMode().setValue("P");
            }
            XMLParser xmlParser = new DefaultXMLParser();
            String ackMessageInXML = xmlParser.encode(message);
            System.out.println(ackMessageInXML);
        }
        
        catch (Exception e)
        {
          e.printStackTrace();
        } */
        
        try
        {
            Message message_a = pipeParser_a.parse(ackMessageString_a);
            //Message message_b = pipeParser_b.parse(ackMessageString_b);
            if (message_a instanceof ACK)
            {
                ACK ack_a = (ACK) message_a;
                //ack_a.getMSH().getProcessingID().getProcessingMode().setValue("P");
                //ack_a.getMSA().getMsa1_AcknowledgementCode().setValue("P");
            }
             
            /* if(message_b instanceof ACK)
            {
                ACK ack_b = (ACK) message_b;
            } */
            
                XMLParser xmlParser_a = new DefaultXMLParser();
                String ackMessageInXML_a = xmlParser_a.encode(message_a);
                XMLParser xmlParser_b = new DefaultXMLParser();
                //String ackMessageInXML_b = xmlParser_b.encode(message_b);
               
                //HL7X hl7x = new HL7X();
                //String xml = hl7x.toXML(ackMessageInXML_a);
                //System.out.println(ackMessageInXML_a);
                //PrintStream console = new PrintStream(new File("output.xml"));
                //System.setOut(console);
                
                //store inside the file
                PrintWriter writer = new PrintWriter("initialOutput.xml", "UTF-8");
                writer.println(ackMessageInXML_a);
   
                writer.close();
                //refine the XML file
                System.out.println("Read from file");
                BufferedReader br_xml = new BufferedReader (new FileReader("output.xml")); 
                try
                {
                    StringBuilder sb_xml = new StringBuilder();
                    String line = br_xml.readLine();
                    Boolean foundMSH = false;
                    while (line != null)
                    {
                        if (line.contains("<MSH>") && foundMSH == false)
                        {
                            System.out.println("Entered");
                            while (!line.contains("</MSH>"))
                            {
                                sb_xml.append(line);
                                sb_xml.append(System.lineSeparator());
                                line = br_xml.readLine();
                            }
                            foundMSH = true;
                        }
                        else if(line.contains("<MSH>") && foundMSH == true)
                        {
                            System.out.println("Found again");
                            while (!line.contains("</MSH>"))
                            {
                                line = br_xml.readLine();
                            }
                            line = br_xml.readLine();
                        }
                        else
                        {
                            sb_xml.append(line);
                            sb_xml.append(System.lineSeparator());
                            line = br_xml.readLine();
                        }
                    }
                    String evry = sb_xml.toString();
                    System.out.println(evry);
                    //write output to file
                    try (Writer writefile = new BufferedWriter(new OutputStreamWriter
                    (
                        new FileOutputStream("finalOutput.xml"), "utf-8"))) {
                        writefile.write(evry);
                    }
                }
                
                finally
                {
                    br_xml.close();
                }
                
                
                
                /* HL7X hl7x = new HL7X();
                String xml = hl7x.toXML(ackMessageInXML_a);
                System.out.println(ackMessageInXML_a); */ 
                
                /* PrintStream xml_res = System.out;
                File file_xml = new File("output.txt");
                FileOutputStream fos_xml = new FileOutputStream(file_xml);
                PrintStream ps_xml = new PrintStream(fos_xml);
                System.setOut(ps_xml);
                System.setOut(xml_res); */
                
                /* if (ackMessageInXML_a != null)
                {
                    System.out.println("Values!");
                } */
        }
        catch(Exception e)
        {
              e.printStackTrace();
        } 
        
        /* CREATE XML PART */
        //use XML parser to encode as XML
        
        /* */
        //parser = context.getXMLParser();
        //parser_evn = context_evn.getXMLParser();
        /* */
        
        //parser_pid = context.getXMLParser();
        //parser_pv1 = context.getXMLParser();
        
        /* */
       // encodedMessage = parser.encode(adt);
       // encodedMessageEVN = parser_evn.encode(adt_evn);
        /* */
        
        //encodedMessagePV1 = parser_pv1.encode(adt_PV1);
        //System.out.println("XML HL7 Encoded Message");
       // System.out.println(encodedMessage);
        //System.out.println(encodedMessageEVN);
        
        
        /* EVN - event type - XML */
        //encodedMessageEVN = parser.encode(adt_evn);
    
        //System.out.println(encodedMessage + " " + encodedMessageEVN);
        
        /* PID XML */
        //encodedMessagePID = parser.encode(adt_pid);
       // System.out.println(encodedMessagePID);
        
        /* AL1 - event type - XML */
        //encodedMessageAL1 = parser.encode(adt_al1); /* return CE.2 which is the coded element */
       // System.out.println(encodedMessageAL1);
    }
    
   
    
}
