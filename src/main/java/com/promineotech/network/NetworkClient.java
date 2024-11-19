package com.promineotech.network;

public interface NetworkClient {
  /**
   * Retrieves the external address of the remote host.
   * @return The remote address.
   */
  public String getTargetAddress();
  
  /**
   * Retrieves the count of the number of interfaces.
   * @return The number of interfaces.
   */
  int getInterfaceCount(); 
  
  /**
   * Retrieves the details of the specified network interface.
   * @param ifIndex The unique index or id of the interface to retrieve.
   * @return The interface details or summary.
   */
  InterfaceDetail get(int ifIndex); 
}
