
package net.jr39.image_enhancer.transformation_platform.graphics.filters;

import net.jr39.image_enhancer.transformation_platform.graphics.filters.FilterManager;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.AppliedFilters;
import java.awt.Color;
import net.jr39.image_enhancer.transformation_platform.graphics.ColourRange;
import org.junit.Test;
import org.junit.Assert;

public class FilterManagerTest {
	
	@Test
	public void testIsPixelFiltered(){
		
		ColourRange cr = new ColourRange(30,60,30,60,30,60);
		ColourRange cr2 = new ColourRange(0,50,2,5,90,120);
		Color redPixel = new Color(255,0,0);
		Color filteredPixel = new Color(40,0,100);
		Color cr2filtered = new Color(20,5,100);
		Color cr2unfiltered = new Color(51,6,130);
		AppliedFilters af1 = new AppliedFilters(true,true,true);
		
		Assert.assertFalse("Test red pixel filters, shouldn't be filtered",FilterManager.isPixelFiltered(redPixel.getRGB(),cr,af1));
		Assert.assertTrue("Test for a filtered pixel is filtered", FilterManager.isPixelFiltered(filteredPixel.getRGB(), cr,af1));
		Assert.assertTrue("Test second colour range; should be filtered",FilterManager.isPixelFiltered(cr2filtered.getRGB(), cr2,af1));
		Assert.assertFalse("Test second colour range; should be unfiltered",FilterManager.isPixelFiltered(cr2unfiltered.getRGB(), cr2,af1));
	}

}
