package com.promineotech.network;

public class InterfaceDetail {
  private int ifIndex;
  private String ifDescr;
  private long ifInOctets;
  private long ifOutOctets;
  
  public InterfaceDetail(int ifIndex, long ifInOctets, long ifOutOctets) {
    this(ifIndex, null, ifInOctets, ifOutOctets);
  }

  public InterfaceDetail(int ifIndex, String ifDescr, long ifInOctets, long ifOutOctets) {
    if ((ifDescr != null) && (! ifDescr.isBlank())) {
      this.ifDescr = ifDescr;
    }
    this.ifIndex = ifIndex;
    this.ifInOctets = ifInOctets;
    this.ifOutOctets = ifOutOctets;
  }
  
  public int getIfIndex() {
    return ifIndex;
  }

  public String getIfDescr() {
    return ifDescr;
  }

  public long getIfInOctets() {
    return ifInOctets;
  }

  public long getIfOutOctets() {
    return ifOutOctets;
  }  
  
  @Override
  public String toString() {
    if ((getIfDescr() != null) && (! getIfDescr().isBlank())) {
      return String.format("Interface(%2d): %-6s, In/Out: %12d / %12d bytes",
                           getIfIndex(), getIfDescr(), getIfInOctets(), getIfOutOctets());
    }
    return String.format("Interface[%2d]: In/Out: %12d / %12d bytes",
                         getIfIndex(), getIfInOctets(), getIfOutOctets());
  }
}
