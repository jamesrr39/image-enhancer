package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import net.jr39.image_enhancer.transformation_platform.graphics.transformations.BrightenTransformation;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.BrightenTransformationArgs;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author james
 */
public class BrightenTransformationTests {

	/**
	 * generates an image of [200, 100] pixels with pixels all coloured the Color object passed in
	 *
	 * @return image
	 */
	private BufferedImage generateTestImage(final Color startColour) {

		final int IMAGE_WIDTH = 200;
		final int IMAGE_HEIGHT = 100;

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		int[] rgbArray = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < rgbArray.length; i++) {
			rgbArray[i] = startColour.getRGB();
		}
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), rgbArray, 0, image.getWidth());
		return image;
	}

	@Test
	public void testGradualBrightnessTransformation() {

		final Color startColour = new Color(100, 50, 10);
		final BufferedImage image = generateTestImage(startColour);
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
