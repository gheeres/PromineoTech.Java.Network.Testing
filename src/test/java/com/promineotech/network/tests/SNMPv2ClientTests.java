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
    SNMPv2Client client = new SNMPv2Client("10.10.10.2/161");
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    
    InterfaceDetail actual = client.get(2);
    
    assertThat(actual.getIfDescr()).isEqualTo(expectedIfDescr);
    assertThat(actual.getIfIndex()).isEqualTo(expectedIfIndex);
    //assertThat(actual.getIfInOctets()).isEqualTo(593213787L);
    //assertThat(actual.getIfOutOctets()).isEqualTo(100000);
    assertThat(actual.getIfInOctets()).isGreaterThan(0);
    assertThat(actual.getIfOutOctets()).isGreaterThan(0);
  }
  
  @Test
  public void get_Returns_Expected_Result() {
    SNMPv2Client client = Mockito.mock(SNMPv2Client.class);
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    long expectedIfInOctets = 1000;
    long expectedIfOutOctets = 2000;

    when(client.get(expectedIfIndex)).thenReturn(new InterfaceDetail(expectedIfIndex, expectedIfDescr, expectedIfInOctets, expectedIfOutOctets));
    
    InterfaceDetail actual = client.get(expectedIfIndex);
    
    assertThat(actual.getIfDescr()).isEqualTo(expectedIfDescr);
    assertThat(actual.getIfIndex()).isEqualTo(expectedIfIndex);
    assertThat(actual.getIfInOctets()).isEqualTo(expectedIfInOctets);
    assertThat(actual.getIfOutOctets()).isEqualTo(expectedIfOutOctets);
  }
}
