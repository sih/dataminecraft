package eu.waldonia.processor;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class RegionFileProcessorTest {

	private RegionFileProcessor rfp;
	private File f;
	
	@Before
	public void setUp() {
		rfp = new RegionFileProcessor();
	}
	
	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNullFileSupplied() {
		f = null;
		Map<Integer,String> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}

	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNoFileExtension() {
		f = new File("../test/resources/boohoo");
		Map<Integer,String> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}
	
	@Test
	public void mapChunksShouldReturnAnEmptyMapIfNonMCAFileExtension() {
		f = new File("../test/resources/boohoomca");
		Map<Integer,String> chunkMap = rfp.mapChunks(f);
		assertTrue(chunkMap.isEmpty());
	}
	
	@Test
	public void mapChunksShouldReturnDataForValidFile() {
		f = new File("../test/resources/boohoo.mca");
		Map<Integer,String> chunkMap = rfp.mapChunks(f);
		assertFalse(chunkMap.isEmpty());
	}
	
}
