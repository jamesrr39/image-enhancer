
package net.jr39.image_enhancer.graphics.filters.colour_filters;

import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;
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

}
