package net.jr39.image_enhancer.transformation_platform.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated 
 */
public class EnhancedImage {
	
	private BufferedImage enhancedImage;
	
	public EnhancedImage(BufferedImage image){
		
		List filterBounds = new ArrayList(){{
			add(new int[] {1834,776});
			add(new int[] {1940,830});
		}};
		
		for(int yCoord = 0; yCoord < image.getHeight(); yCoord++){
			for(int xCoord = 0; xCoord < image.getWidth(); xCoord++){
				int pixelRGBValue = image.getRGB(xCoord, yCoord);
				Color c = new Color(pixelRGBValue);
				Color overlayColor = new Color(0,255,0);

				float[] hsbVals = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
				float hue = hsbVals[0] * 360;
				float saturation = hsbVals[1];
				float value = hsbVals[2];

				boolean isInRedRange = (c.getRed() > 30 && c.getRed() < 60);
				boolean isInGreenRange = (c.getGreen() > 30 && c.getGreen() < 60);
				boolean isInBlueRange = (c.getBlue() > 30 && c.getBlue() < 60);
				boolean isInHueRange = (200 < hue && 230 > hue);
//				boolean isInSaturationRange = (0.10 < saturation && 0.20 > saturation);
				boolean isInSaturationRange = true;
				
				boolean isPixelFiltered = (applyRedFilter(c.getRed()) && applyGreenFilter(c.getGreen()) && applyBlueFilter(c.getBlue()) && applyHueFilter(hue));

				if(isPixelFiltered){
					image.setRGB(xCoord, yCoord, overlayColor.getRGB());
					System.out.println("Hue: " + hue + ", Saturation: " + saturation + ", Value: " + value);
				}
			}
		}
		this.enhancedImage = image;
	}
	
	private boolean applyRedFilter(int red){
		return (red > 30 && red < 60);
	}
	
	private boolean applyGreenFilter(int green){
		return (green > 30 && green < 60);
	}
	
	private boolean applyBlueFilter(int blue){
		return (blue > 30 && blue < 60);
	}
	
	private boolean applyHueFilter(float hue){
		return (200 < hue && 230 > hue);
	}
	
	public BufferedImage getEnhancedImage(){
		return this.enhancedImage;
	}
	
	
}
