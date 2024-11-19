package com.promineotech;

import java.util.ArrayList;
import java.util.List;
import com.promineotech.network.InterfaceDetail;
import com.promineotech.network.NetworkClient;
import com.promineotech.network.SNMPv2Client;

public class NetworkStatisticsExport {
  private NetworkClient[] clients;
  
  public NetworkStatisticsExport() {
    this(List.of(
      new SNMPv2Client("10.10.10.2/161"),
      new SNMPv2Client("10.10.10.3/161"),
      new SNMPv2Client("10.10.10.4/161"),
      new SNMPv2Client("10.10.10.6/161")
      //new SNMPv2Client("10.10.10.17/161")
    ).toArray(new NetworkClient[0]));  
  }

  public NetworkStatisticsExport(NetworkClient[] clients) {
    this.clients = clients;  
  }
  
  public List<String> export() {
    List<String> result = new ArrayList<>();
    for(NetworkClient client : clients) {
      int interfaceCount = client.getInterfaceCount();
      for(int ifIndex = 1; ifIndex <= interfaceCount; ifIndex++) {
        InterfaceDetail statistics = client.get(ifIndex);
        if (statistics != null) {
          result.add(String.format("{ address: \"%s\", ifIndex: %d, ifDescr: %s, ifInOctets: %d, ifOutOctets: %d }",
                                   client.getTargetAddress().toString(), 
                                   statistics.getIfIndex(), 
                                   statistics.getIfDescr() != null ? String.format("\"%s\"", statistics.getIfDescr()) : "null",
                                   statistics.getIfInOctets(), statistics.getIfOutOctets()));
        }
      }
    }
    return result;
  }
}
