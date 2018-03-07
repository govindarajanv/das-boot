package com.boot;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import com.boot.controller.ShipwreckController;
public class ShipwreckControllerTest {
	
	@InjectMocks
    private ShipwreckController sc;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGet() {
        Shipwreck sw = new Shipwreck();
        sw.setId(1l);
        //when(ShipwreckRepository.findOne(1l)).thenReturn(sw);
        when(shipwreckRepository.getOne(1l)).thenReturn(sw);
        
        Shipwreck wreck = sc.get(1L);
        
        verify(shipwreckRepository).getOne(1l);
        assertEquals(1l, wreck.getId().longValue());
        
     // junit assertion
//      assertEquals(123L, result.getId().longValue());

      // hamcrest assertion
      assertThat(wreck.getId(), is(1l));

        
    }
}
