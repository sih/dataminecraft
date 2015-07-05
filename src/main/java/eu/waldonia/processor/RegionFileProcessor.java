package eu.waldonia.processor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Processes one or more Minecraft (.mca) region files
 * @author sid
 *
 */
public class RegionFileProcessor {
	
	public static final String MCA = ".mca";
	public static final int CHUNK_BYTEMAP_SIZE = 4096;
	private static final int CHUNK_BYTEMAP_ITEM_SIZE = 4;
	private static final int CHUNK_SECTOR_SIZE = 4096;

	/**
	 * Make a map of the chunks held in the input file
	 * @param f The input region file. Will validate this is an .mca file
	 * @return A map of chunk locations by their byte offset or an empty map if the file is not valid
	 */
	public Map<Integer,Integer> mapChunks(final File f) {
		Map<Integer,Integer> map = new HashMap<>();
		// basic validity checks
		if (f != null && !f.isDirectory()) {
			// slightly more sophisticated validity checks
			if (f.getName().contains(".")) {
				String fileExtension = f.getName().substring(f.getName().lastIndexOf("."));
				if (fileExtension != null && MCA.equals(fileExtension)) {
					try {
						map.putAll(processMap(f));
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		
		return map;
	}
	
	
	/**
	 * @see http://minecraft.gamepedia.com/Region_file_format
	 * @param f The region file to process. Guaranteed not null
	 * @return A map of MC sector sizes keyed by the byte location of their chunk
	 * @throws Exception when the file cannot be found or there is an error processing it
	 */
	private Map<Integer,Integer> processMap(final File f) throws Exception {
		Map<Integer,Integer> map = new HashMap<>();
		
		try(FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis)) {
		
			byte[] chunkByteMap = new byte[CHUNK_BYTEMAP_SIZE];
			bis.read(chunkByteMap);
			// iterate through each logical item
			for (int offset = 0; offset < CHUNK_BYTEMAP_SIZE / CHUNK_BYTEMAP_ITEM_SIZE; offset+=CHUNK_BYTEMAP_ITEM_SIZE) {
				byte[] item = Arrays.copyOfRange(chunkByteMap, offset, offset+CHUNK_BYTEMAP_ITEM_SIZE);
			
				// first 3 bytes represent the address - shift and mask to turn them in to ints (they're big-endian)
				int mcChunkByteAddress = ((item[0] & 0xFF) << 16 | (item[1] & 0xFF) << 8 | (item[2] & 0xFF)) * CHUNK_SECTOR_SIZE;
				int mcSectorSize = item[3];
				
				map.put(mcChunkByteAddress,mcSectorSize);
			}
			
		}
		return map;
	}

}
