
package net.jr39.image_enhancer.graphics.filters.colour_filters;

import java.awt.image.BufferedImage;

public class GreenColourFilter extends AbstractColourFilter{

	private int minGreen, maxGreen;
	
	public GreenColourFilter(BufferedImage image, int minGreen, int maxGreen, int imageWidth, int imageHeight){
		this.minGreen = minGreen;
		this.maxGreen = maxGreen;
		super.setFilteredPixels(image, imageWidth, imageHeight);
	}
	
	protected boolean isPixelFiltered(int pixelColour){
		int red = ColourUtils.getGreenFromRGB(pixelColour);
		return (red < maxGreen && red > minGreen);
	}
}