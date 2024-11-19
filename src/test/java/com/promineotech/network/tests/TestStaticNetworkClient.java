package com.promineotech.network.tests;

import com.promineotech.network.InterfaceDetail;
import com.promineotech.network.NetworkClient;

public class TestStaticNetworkClient implements NetworkClient {
  private String targetAddress;
  private long ifInOctets;
  private long ifOutOctets;
  
  public TestStaticNetworkClient(String targetAddress) {
    this.targetAddress = targetAddress;
  }

  public TestStaticNetworkClient(long ifInOctets, long ifOutOctets) {
    this("0.0.0.0");
    this.ifInOctets = ifInOctets;
    this.ifOutOctets = ifOutOctets;
  }
  
  @Override
  public String getTargetAddress() {
    return targetAddress;
  }

  @Override
  public int getInterfaceCount() {
    return 1;
  }

  @Override
  public InterfaceDetail get(int ifIndex) {
    return new InterfaceDetail(ifIndex, ifInOctets, ifOutOctets);
  }
  
  public String toJSON(int ifIndex) {
    return String.format("{ address: \"%s\", ifIndex: %d, ifDescr: %s, ifInOctets: %d, ifOutOctets: %d }",
                         getTargetAddress().toString(), 
                         ifIndex, "null", ifInOctets, ifOutOctets);  
  }
}
