package eu.waldonia.domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test basics of coordinate.
 * @author sid
 *
 */
public class CoordinateTest {
	
	private Coordinate coord;

	/**
	 * 
	 */
	@Test
	public void shouldSetCoordsAsYZXFromConstructor() {
		coord = new Coordinate(1,2,3);
		assertEquals(1,coord.y());
		assertEquals(2,coord.z());
		assertEquals(3,coord.x());
	}
	
	@Test
	public void shouldMatchAllCoordsToBeEqual() {
		coord = new Coordinate(1,2,3);
		Coordinate a = new Coordinate(1,2,0);
		Coordinate b = new Coordinate(0,2,3);
		Coordinate c = new Coordinate(1,0,3);
		Coordinate same = new Coordinate(1,2,3);
		
		assertFalse(a.equals(coord));
		assertFalse(b.equals(coord));
		assertFalse(c.equals(coord));
		assertEquals(same,coord);
	}
	

}
