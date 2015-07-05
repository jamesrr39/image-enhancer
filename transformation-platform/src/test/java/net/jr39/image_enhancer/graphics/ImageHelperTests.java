package net.jr39.image_enhancer.graphics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author james
 */
public class ImageHelperTests {
	
	@Test
	public void testGetImageIntArrayAtIndex(){
		final BufferedImage image = new BufferedImage(5, 8, BufferedImage.TYPE_INT_ARGB);
		final Point testPixel = new Point(3,2);
		final int testColour = 200; // mauvish colour
		
		image.setRGB((int) testPixel.getX(), (int) testPixel.getY(), testColour);
		
		int[] imagePixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		int imageIntArrayIndex = ImageHelper.getImageIntArrayIndex(testPixel, new Dimension(image.getWidth(), image.getHeight()));
		
		assertEquals("test it corresponds to the correct pixel", testColour, imagePixels[imageIntArrayIndex]);
		
	}

}
