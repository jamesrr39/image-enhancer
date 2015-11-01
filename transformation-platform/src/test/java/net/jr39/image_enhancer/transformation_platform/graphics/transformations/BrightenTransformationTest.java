package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author james
 */
public class BrightenTransformationTest {

	@Test
	public void testGradualBrightnessTransformation() {

		final Color startColour = new Color(100, 50, 10);
		final BufferedImage image = TestImageHelper.generateTestImage(startColour);
		final boolean isGradual = true;
		final float scaleFactor = 1.2f;
		final float halfwayScaleFactor = (((scaleFactor -1)/2) + 1); // 1.1f
		BrightenTransformationArgs bta = new BrightenTransformationArgs(null, scaleFactor, isGradual);
		BrightenTransformation bt = new BrightenTransformation(bta);
		BufferedImage transformedImage = bt.transform(image);
		Color transformedCentrePixelColour = new Color(transformedImage.getRGB(image.getWidth() / 2, image.getHeight() / 2));
		Color transformedHalfwayFromTopRightPixelColour = new Color(transformedImage.getRGB((int) (image.getWidth() * 0.75), image.getHeight() / 4));

		Assert.assertNotSame("image get transformed", transformedImage, image);
		Assert.assertEquals("edge pixel hasn't been changed", image.getRGB(0, 0), transformedImage.getRGB(0, 0));
		Assert.assertEquals("halfway pixel has been half changed", halfwayScaleFactor * startColour.getGreen(), (float) transformedHalfwayFromTopRightPixelColour.getGreen());
		Assert.assertTrue("centre pixel got transformed more than the edge pixel", new Color(transformedImage.getRGB(0, 0)).getGreen() < transformedCentrePixelColour.getGreen());
		Assert.assertEquals("centre pixel blue component has been multiplied by the scale factor", scaleFactor * startColour.getBlue(), (float) transformedCentrePixelColour.getBlue());
	}

}
