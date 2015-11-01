
package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author james
 */
public class BorderTransformationTest {
	
	@Test
	public void testGenerateBorder(){
		
		final int[] border = BorderTransformation.generateBorder(Color.blue, 10);
		final int blueRGB = Color.blue.getRGB();
		assertThat(border.length, is(10));
		for (int borderColour : border){
			assertThat(borderColour, is(blueRGB));
		}
		
	}
	
	@Test
	public void testImageSizeIncrease(){
		
		final Color testImageColour = Color.darkGray;
		BufferedImage image = TestImageHelper.generateTestImage(testImageColour);
		Assert.assertEquals(100, image.getHeight());
		Assert.assertEquals(200, image.getWidth());
		
		BorderTransformationArgs transformationArgs = new BorderTransformationArgs(null, Color.WHITE, 100);
		BorderTransformation borderTransformation = new BorderTransformation(transformationArgs);
		
		BufferedImage transformedImage = borderTransformation.transform(image);
		Assert.assertEquals("New image is 200 px taller", 300, transformedImage.getHeight());
		Assert.assertEquals("New image is 200 px wider", 400, transformedImage.getWidth());
		Assert.assertEquals("Top left pixel is white", Color.WHITE.getRGB(), transformedImage.getRGB(0, 0));
		Assert.assertEquals("Top border ends at 10, 10", Color.WHITE.getRGB(), transformedImage.getRGB(10, 10));
		Assert.assertEquals("Pixel 111,111 (first pixel of original image) is dark gray", testImageColour.getRGB(), transformedImage.getRGB(111,111));
		Assert.assertEquals("Bottom right pixel is white", 0, transformedImage.getRGB(transformedImage.getWidth() -1, transformedImage.getHeight() -1));
		final int originalImageBottomRightPixelColour = image.getRGB(image.getWidth() -1, image.getHeight() -1);
		final int transformedImageBottomRightPixelColour = transformedImage.getRGB(
						transformedImage.getWidth() - (1 + transformationArgs.getBottomBorder()), 
						transformedImage.getHeight() - (1 + transformationArgs.getRightBorder()));
		
		Assert.assertEquals(
				"Bottom pixel is the same in both",
				originalImageBottomRightPixelColour,
				transformedImageBottomRightPixelColour);
				
	}
	
}
