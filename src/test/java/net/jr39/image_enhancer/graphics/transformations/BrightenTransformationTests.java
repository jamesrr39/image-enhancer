package net.jr39.image_enhancer.graphics.transformations;

import java.awt.image.BufferedImage;
import java.awt.Color;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author james
 */
public class BrightenTransformationTests {

	@Test
	public void testGradualBrightnessTransformation() {
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_4BYTE_ABGR);
		int[] rgbArray = new int[image.getWidth() * image.getHeight()];
		Color startColor = new Color(100, 50, 10);
		for(int i = 0; i < rgbArray.length; i++){
			rgbArray[i] = startColor.getRGB();
		}
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), rgbArray, 0, image.getWidth());
		
		boolean isGradual = true;
		float scaleFactor = 3;
		BrightenTransformationArgs bta = new BrightenTransformationArgs(null, scaleFactor, isGradual);
		BrightenTransformation bt = new BrightenTransformation(bta);
		BufferedImage transformedImage = bt.transform(image);

		Assert.assertNotSame("image get transformed", transformedImage, image);
		Assert.assertEquals("edge pixel hasn't been changed", startColor.getRGB(), transformedImage.getRGB(0, 0));
		Assert.assertTrue("centre pixel got transformed more than the edge pixel", new Color(transformedImage.getRGB(0, 0)).getGreen() < new Color(transformedImage.getRGB(100, 50)).getGreen());
	}

}
