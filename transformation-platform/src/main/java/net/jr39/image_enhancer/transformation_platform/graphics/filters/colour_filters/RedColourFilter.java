
package net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters;

import java.awt.image.BufferedImage;

/**
 *
 */
public class RedColourFilter extends AbstractColourFilter {
	
	private int minRed, maxRed;
	
	public RedColourFilter(BufferedImage image, int minRed, int maxRed, int imageWidth, int imageHeight){
		this.minRed = minRed;
		this.maxRed = maxRed;
		super.setFilteredPixels(image, imageWidth, imageHeight);
	}
	
	protected boolean isPixelFiltered(int pixelColour){
		int red = ColourUtils.getRedFromRGB(pixelColour);
		return (red < maxRed && red > minRed);
	}
	
}
