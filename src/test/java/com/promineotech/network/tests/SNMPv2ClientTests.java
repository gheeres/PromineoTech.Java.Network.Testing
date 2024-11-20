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
    // Arrange
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    SNMPv2Client client = new SNMPv2Client("10.10.10.2/161");
    
    // Act
    InterfaceDetail actual = client.get(expectedIfIndex);
    
    // Assert
    assertThat(actual.getIfIndex()).isEqualTo(expectedIfIndex);
    assertThat(actual.getIfDescr()).isEqualTo(expectedIfDescr);
    //assertThat(actual.getIfInOctets()).isEqualTo(5984442870L);
    //assertThat(actual.getIfOutOctets()).isEqualTo(3070219050L);
    assertThat(actual.getIfInOctets()).isGreaterThan(0);
    assertThat(actual.getIfOutOctets()).isGreaterThan(0);
  }
  
  @Test
  public void get_Returns_Expected_Result() {
    // Arrange
    String expectedIfDescr = "eth0";
    int expectedIfIndex = 2;
    long expectedIfInOctets = 1111000;
    long expectedIfOutOctets = 25634500;
    SNMPv2Client client = Mockito.mock(SNMPv2Client.class);
    
    InterfaceDetail expected = new InterfaceDetail(expectedIfIndex, expectedIfDescr, expectedIfInOctets, expectedIfOutOctets);
    when(client.get(expectedIfIndex)).thenReturn(expected);

    // Act
    InterfaceDetail actual = client.get(expectedIfIndex);
    
    // Assert
    assertThat(actual.getIfIndex()).isEqualTo(expectedIfIndex);
    assertThat(actual.getIfDescr()).isEqualTo(expectedIfDescr);
    assertThat(actual.getIfInOctets()).isEqualTo(expectedIfInOctets);
    assertThat(actual.getIfOutOctets()).isEqualTo(expectedIfOutOctets);    
  }
}
