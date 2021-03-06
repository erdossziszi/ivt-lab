package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimaryTorpedoStore;
  private TorpedoStore mockSecondaryTorpedoStore;

  @Before
  public void init(){
	  this.mockPrimaryTorpedoStore = mock(TorpedoStore.class);
	  this.mockSecondaryTorpedoStore = mock(TorpedoStore.class);
	  
      this.ship = new GT4500(mockPrimaryTorpedoStore, mockSecondaryTorpedoStore);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimaryTorpedoStore, times(1)).fire(1);
    verify(mockSecondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
	when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPrimaryTorpedoStore, times(1)).fire(1);
    verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }

}
