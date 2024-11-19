package com.promineotech.network;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPv2Client implements NetworkClient {
  private Address targetAddress;
  
  public SNMPv2Client(String targetAddress) {
    this.targetAddress = new UdpAddress(targetAddress);
  }
  
  public String getTargetAddress() {
    return targetAddress.toString();
  }

  public int getInterfaceCount() {
    try (Snmp snmp = new Snmp(new DefaultUdpTransportMapping())) {
      snmp.listen();

      PDU pdu = new PDU();
      pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.1.0"))); // ifNumber
      pdu.setType(PDU.GET);
      
      CommunityTarget<Address> target = new CommunityTarget<Address>();
      target.setCommunity(new OctetString("public"));
      target.setAddress(targetAddress);
      target.setVersion(SnmpConstants.version2c);
      
      ResponseEvent<Address> response = snmp.send(pdu, target);
      if (response.getResponse() != null) {
        return response.getResponse().get(0).getVariable().toInt();
      }
      System.out.printf("Failed to receive response from: %s%n", targetAddress);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return 0;
  }
  
  public InterfaceDetail get(int ifIndex) {
    try (Snmp snmp = new Snmp(new DefaultUdpTransportMapping())) {
      snmp.listen();
      
      PDU pdu = new PDU();
      pdu.add(new VariableBinding(new OID(String.format("1.3.6.1.2.1.2.2.1.2.%d", ifIndex)))); // ifNumber
      pdu.add(new VariableBinding(new OID(String.format("1.3.6.1.2.1.2.2.1.10.%d", ifIndex)))); // ifInOctets
      pdu.add(new VariableBinding(new OID(String.format("1.3.6.1.2.1.2.2.1.16.%d", ifIndex)))); // ifOutOctets
      pdu.setNonRepeaters(1);
      pdu.setType(PDU.GET);
      
      CommunityTarget<Address> target = new CommunityTarget<Address>();
      target.setCommunity(new OctetString("public"));
      target.setAddress(targetAddress);
      target.setVersion(SnmpConstants.version2c);
      
      ResponseEvent<Address> response = snmp.send(pdu, target);
      if (response.getResponse() != null) {
        Map<String,Variable> values = response.getResponse().getAll()
                                              .stream()
                                              .collect(Collectors.toMap(value -> (String) value.getOid().toString(), 
                                                                        value -> (Variable) value.getVariable()));
        //for(Entry<String, Variable> value : values.entrySet()) {
        //  System.out.printf("%-32s => %s%n", value.getKey(), value.getValue().toString());
        //}
        return new InterfaceDetail(ifIndex,
                                       values.get(String.format("1.3.6.1.2.1.2.2.1.2.%d", ifIndex)).toString(),
                                       values.get(String.format("1.3.6.1.2.1.2.2.1.10.%d", ifIndex)).toLong(),
                                       values.get(String.format("1.3.6.1.2.1.2.2.1.16.%d", ifIndex)).toLong());
      }
      System.out.printf("Failed to receive response from: %s%n", targetAddress);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
