
package net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters;

import java.awt.Color;
import org.junit.Test;
import org.junit.Assert;

public class ColourUtilsTest {

	@Test
	public void testGetRedGreenBlueFromRGB(){
		
		// test creating a Color object and the getRed getGreen and getBlue methods are returning the same as the ColourUtils methods
		Color c1 = new Color(123,15,9);
		int c1rgb = c1.getRGB();
		Assert.assertEquals(c1.getRed(), ColourUtils.getRedFromRGB(c1rgb));
		Assert.assertEquals(c1.getBlue(), ColourUtils.getBlueFromRGB(c1rgb));
		Assert.assertEquals(c1.getGreen(), ColourUtils.getGreenFromRGB(c1rgb));
		
	}
	
	@Test
	public void testGetRGBInt(){
		int red = 200;
		int green = 124;
		int blue = 76;
		Assert.assertEquals(new Color(red, green, blue).getRGB(), ColourUtils.getRGBInt(red, green, blue));
		int alpha = 32;
		Assert.assertEquals(new Color(red, green, blue, alpha).getRGB(), ColourUtils.getRGBInt(red, green, blue, alpha));

	}

}
