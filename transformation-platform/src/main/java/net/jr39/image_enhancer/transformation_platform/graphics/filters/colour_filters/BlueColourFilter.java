
package net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters;

import java.awt.image.BufferedImage;

public class BlueColourFilter extends AbstractColourFilter{

	private int minBlue, maxBlue;
	
	public BlueColourFilter(BufferedImage image, int minBlue, int maxBlue, int imageWidth, int imageHeight){
		this.minBlue = minBlue;
		this.maxBlue = maxBlue;
		super.setFilteredPixels(image, imageWidth, imageHeight);
	}
	
	protected boolean isPixelFiltered(int pixelColour){
		int red = ColourUtils.getBlueFromRGB(pixelColour);
		return (red < maxBlue && red > minBlue);
	}
	
}
