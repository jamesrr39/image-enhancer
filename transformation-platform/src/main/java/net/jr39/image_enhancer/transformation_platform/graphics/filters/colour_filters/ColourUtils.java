package net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters;

/**
 * needed static utility functions otherwise we are creating a new Color object for each pixel.
 */
public class ColourUtils {
	
	public static final int RGB_MAX = 255;
	public static final int RGB_MIN = 0;

	public static int getRedFromRGB(int binaryColour) {
		return (binaryColour & 0x00ff0000) >> 16;
	}

	public static int getGreenFromRGB(int binaryColour) {
		return (binaryColour >> 8) & 0xFF;
	}

	public static int getBlueFromRGB(int binaryColour) {
		return (binaryColour) & 0xFF;
	}

	/**
	 * untested
	 */
	public static int getAlphaFromRGB(int binaryColour) {
		return (binaryColour >> 24) & 0xFF;
	}

	public static int getRGBInt(int red, int green, int blue, int alpha) {
		return ((alpha & 0xFF) << 24)
				| ((red & 0xFF) << 16)
				| ((green & 0xFF) << 8)
				| (blue & 0xFF);
	}

	public static int getRGBInt(int red, int green, int blue) {
		final int alpha = 255;
		return ColourUtils.getRGBInt(red, green, blue, alpha);

	}

}
