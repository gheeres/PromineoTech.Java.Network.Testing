package com.promineotech.network.tests;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import com.promineotech.network.InterfaceDetail;

class InterfaceDetailTests {

  @Test
  void constructor_With_Null_Interface_Returns_Null_For_getIfDescr() {
    
    InterfaceDetail statistics = new InterfaceDetail(2, "", 587781012L, 3052839794L);
    
    assertThat(statistics.getIfDescr()).isNull();
  }  

  @ParameterizedTest
  @ValueSource(strings = { "", " ", "  " })
  void constructor_With_Empty_Or_Blank_Interface_Returns_Null_getIfDescr(String ifDescr) {
    
    InterfaceDetail statistics = new InterfaceDetail(1, ifDescr, 0, 0);
    
    assertThat(statistics.getIfDescr()).isNull();
  }  

  @Test
  void toString_With_No_Interface_Name_Returns_Expected_Result() {
    String expected = "Interface[ 2]: In/Out:    587781012 /   3052839794 bytes";
    
    InterfaceDetail statistics = new InterfaceDetail(2, null, 587781012L, 3052839794L);
    String actual = statistics.toString();
    
    assertThat(actual).isEqualTo(expected);
  }

  private static Stream<Arguments> getSampleInterfaceStatistics() {
    return Stream.of(
      Arguments.of(new InterfaceDetail(1, "eth2", 0, 0), "Interface( 1): eth2  , In/Out:            0 /            0 bytes"),
      Arguments.of(new InterfaceDetail(2, "ens32", 1000, 2000), "Interface( 2): ens32 , In/Out:         1000 /         2000 bytes"),
      Arguments.of(new InterfaceDetail(3, " ", 1000, 2000), "Interface[ 3]: In/Out:         1000 /         2000 bytes"),
      Arguments.of(new InterfaceDetail(0, "", 4293262695L, 3996081156L), "Interface[ 0]: In/Out:   4293262695 /   3996081156 bytes")
    );
}
  
  @ParameterizedTest
  @MethodSource("getSampleInterfaceStatistics")
  void toString_With_Returns_Expected_Result(InterfaceDetail statistics, String expected) {
    String actual = statistics.toString();
    
    assertThat(actual).isEqualTo(expected);
  }
  
}
