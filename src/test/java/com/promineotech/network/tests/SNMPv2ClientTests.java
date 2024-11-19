package com.promineotech.network.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock; 
import com.promineotech.network.InterfaceDetail;
import com.promineotech.network.SNMPv2Client;

public class SNMPv2ClientTests {
  //@Test
  public void get_With_Valid_Address_Returns_Expected_Result() {
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    
    // TODO
  }
  
  @Test
  public void get_Returns_Expected_Result() {
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    long expectedIfInOctets = 1000;
    long expectedIfOutOctets = 2000;

    // TODO
  }
}
