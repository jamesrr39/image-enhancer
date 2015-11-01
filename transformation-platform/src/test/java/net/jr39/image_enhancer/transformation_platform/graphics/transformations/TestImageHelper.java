
package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author james
 */
public class TestImageHelper {
	
	/**
	 * generates an image of [200, 100] pixels with pixels all coloured the Color object passed in
	 *
	 * @param startColour Colour of the image
	 * @return image
	 */
	public static BufferedImage generateTestImage(final Color startColour) {

		final int IMAGE_WIDTH = 200;
		final int IMAGE_HEIGHT = 100;
		final int startColourInt = startColour.getRGB();

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		int[] rgbArray = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < rgbArray.length; i++) {
			rgbArray[i] = startColourInt;
		}
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), rgbArray, 0, image.getWidth());
		return image;
	}
	
}
