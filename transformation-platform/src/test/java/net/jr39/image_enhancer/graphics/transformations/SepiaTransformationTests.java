
package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Color;
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author james
 */
public class SepiaTransformationTests {
	
	@Test
	public void testGetSepiaColour(){
		final int sepiaFullColour = SepiaTransformation.getSepiaColour((new Color(255, 255, 255)).getRGB());
		
		assertEquals(112, ColourUtils.getRedFromRGB(sepiaFullColour));
		assertEquals(66, ColourUtils.getGreenFromRGB(sepiaFullColour));
		assertEquals(20, ColourUtils.getBlueFromRGB(sepiaFullColour));

		final int sepiaHalfColour = SepiaTransformation.getSepiaColour((new Color(128, 128, 128)).getRGB());
		
		assertEquals(56, ColourUtils.getRedFromRGB(sepiaHalfColour));
		assertEquals(33, ColourUtils.getGreenFromRGB(sepiaHalfColour));
		assertEquals(10, ColourUtils.getBlueFromRGB(sepiaHalfColour));
		
		final int sepiaNoColour = SepiaTransformation.getSepiaColour((new Color(0, 0, 0)).getRGB());
		
		assertEquals(0, ColourUtils.getRedFromRGB(sepiaNoColour));
		assertEquals(0, ColourUtils.getGreenFromRGB(sepiaNoColour));
		assertEquals(0, ColourUtils.getBlueFromRGB(sepiaNoColour));
		
	}
	
}
