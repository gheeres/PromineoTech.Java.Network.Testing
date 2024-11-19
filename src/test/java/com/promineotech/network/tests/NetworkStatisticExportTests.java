package com.promineotech.network.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.promineotech.NetworkStatisticsExport;

public class NetworkStatisticExportTests {
  @Test
  public void export_Returns_Collection_Of_Values() {
    NetworkStatisticsExport exporter = new NetworkStatisticsExport();
    NetworkStatisticsExport spyExporter = spy(exporter);
    
    // TODO
  }
  
  @Test
  public void export_Returns_Expected_Collection_Of_Values() {
    TestStaticNetworkClient[] clients = List.of(
      new TestStaticNetworkClient(1000, 1000),
      new TestStaticNetworkClient(2000, 2000),
      new TestStaticNetworkClient(3000, 3000),
      new TestStaticNetworkClient(4000, 4000)
    ).toArray(new TestStaticNetworkClient[0]);
    NetworkStatisticsExport exporter = new NetworkStatisticsExport(clients);

    // TODO
  }  
}
