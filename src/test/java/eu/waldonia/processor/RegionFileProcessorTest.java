package eu.waldonia.processor;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RegionFileProcessorTest {

	private RegionFileProcessor rfp;
	private File f;
	private static final String VALID_REGION_FILE_NAME = "./src/test/resources/r.0.-1.mca";
	
	@Before
	public void setUp() {
		rfp = new RegionFileProcessor();
	}
	
	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNullFileSupplied() {
		f = null;
		Map<Integer,Integer> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}

	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNoFileExtension() {
		f = new File("../test/resources/boohoo");
		Map<Integer,Integer> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}
	
	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNonMCAFileExtension() {
		f = new File("./test/resources/boohoomca");
		Map<Integer,Integer> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}
	
	@Test
	public void mapChunksShouldReturnDataForValidFile() throws Exception {
		f = new File(VALID_REGION_FILE_NAME);
		Map<Integer,Integer> chunkMap = rfp.mapChunks(f);
		assertFalse(chunkMap.isEmpty());
	}
	
	@Test
	public void mapChunksShouldReturnNonNegativeAddresses() throws Exception {
		f = new File(VALID_REGION_FILE_NAME);
		Map<Integer,Integer> chunkMap = rfp.mapChunks(f);
		assertFalse(chunkMap.isEmpty());
		Set<Integer> addresses = chunkMap.keySet();
		for (Integer addr : addresses) {
			if (addr <= 0) {
				fail("Address was "+addr+" but should be non-negative");
			}
		}
		
	}
	
}
