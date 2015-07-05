package eu.waldonia.processor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Processes one or more Minecraft (.mca) region files
 * @author sid
 *
 */
public class RegionFileProcessor {
	
	public static final String MCA = "mca";
	public static final int CHUNK_BYTEMAP_SIZE = 4096;

	/**
	 * Make a map of the chunks held in the input file
	 * @param f The input region file. Will validate this is an .mca file
	 * @return A map of chunk locations by their byte offset or an empty map if the file is not valid
	 */
	public Map<Integer,String> mapChunks(final File f) {
		Map<Integer,String> map = new HashMap<>();
		// basic validity checks
		if (f != null && !f.isDirectory()) {
			// slightly more sophisticated validity checks
			String[] fileParts = f.getName().split("\\.");
			if (fileParts != null && fileParts.length == 2) {
				if (MCA.equals(fileParts[1])) {
					// TODO remove this stub
					map.put(1, "Yay");
				}
				
			}
			
		}
		
		
		return map;
	}
	
	
	private Map<Integer,String> processMap(final File f) throws Exception {
		Map<Integer,String> map = new HashMap<>();
		
		try(FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis)) {
		
			byte[] chunkByteMap = new byte[CHUNK_BYTEMAP_SIZE];
			bis.read(chunkByteMap);
			
		}
		
		
		
		
		
		return map;
	}

}
