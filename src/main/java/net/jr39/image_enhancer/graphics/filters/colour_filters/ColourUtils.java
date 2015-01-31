package net.jr39.image_enhancer.graphics.filters.colour_filters;

/**
 * needed static utility functions otherwise we are creating a new Color object for each pixel.
 */
public class ColourUtils {
	
	public static int getRedFromRGB(int binaryColour){
		return (binaryColour & 0x00ff0000) >> 16;
	}
	
	public static int getGreenFromRGB(int binaryColour){
		return (binaryColour >> 8) & 0xFF;
	}
	
	public static int getBlueFromRGB(int binaryColour){
		return (binaryColour) & 0xFF;
	}
	
	/**
	 * untested
	 */
	public static int getAlphaFromRGB(int binaryColour){
		return (binaryColour >> 24) & 0xFF;
	}
	
}
