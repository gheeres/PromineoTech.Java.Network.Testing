package com.promineotech;

import com.promineotech.network.SNMPv2Client;

public class Application {
  private static volatile boolean running = true;
  
  public static void main(String[] args) {
    new Application().run(args);
  }
  
  public void run(String[] args) {
    System.out.println("Starting network statistics gathering...");
    
    SNMPv2Client client = new SNMPv2Client("10.10.10.2/161");
    //NetworkStatisticsExport exporter = new NetworkStatisticsExport();
    while(running) {
      try {
        System.out.println(client.get(2).toString());
        //exporter.export().forEach((export -> System.out.println(export) ));
        //System.out.println("------------------[ MARK ]------------------");

        Thread.sleep(2500); // Sleep for 2.5 seconds (milliseconds)
      } catch (InterruptedException e) {
        System.out.println("[INTERRUPT] Application execution interrupted. Terminating.");
        running = false;
      } 
    }
  }
}
