package net.jr39.image_enhancer.graphics.filters.colour_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractColourFilter {
	
	private boolean[] filterMask;
	private List<Integer> filterIndexes;
	private int imageWidth, imageHeight;

	/**
	 * sets filtered pixels in filterMask and filterIndexes
	 * @param image
	 * @param imageWidth
	 * @param imageHeight 
	 */
	protected void setFilteredPixels(BufferedImage image, int imageWidth, int imageHeight){
		
		int[] imageAsIntArray = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		this.filterMask = new boolean[imageAsIntArray.length];
		this.filterIndexes = new ArrayList<>();
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		
		for(int pos = 0; pos < imageAsIntArray.length; pos++){
			int pixelColour = imageAsIntArray[pos];
			
			if(isPixelFiltered(pixelColour)){
				this.filterMask[pos] = true;
				this.filterIndexes.add(pos);
			}
		}
	}
	
	public BufferedImage getFilterMaskImage(Color filterColour){
		
		int[] filterMaskDataBuffer = new int[this.filterMask.length];
		Color transparent = new Color(0f,0f,0f,0f);
		Arrays.fill(filterMaskDataBuffer, transparent.getRGB());
		
		int filterColourRGB = filterColour.getRGB();
		this.filterIndexes.stream().forEach((pos) -> {
					filterMaskDataBuffer[pos] = filterColourRGB;
			});
		
		DataBuffer databuffer = new DataBufferInt(filterMaskDataBuffer,filterMaskDataBuffer.length);
		
		ColorModel cm = ColorModel.getRGBdefault();
		SampleModel sm = cm.createCompatibleSampleModel(this.imageWidth, this.imageHeight);
		WritableRaster wr = Raster.createWritableRaster(sm, databuffer, null);
		
		return new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
	}
	
	protected abstract boolean isPixelFiltered(int pixelColour);
	
	/**
	 * convenience method
	 * @return List indexes in areaColourMap of pixels that are filtered
	 */
	public List getFilterIndexes(){
		return this.filterIndexes;
	}
	
	/**
	 * convenience method
	 * @return boolean[] filterMask
	 * @deprecated
	 */
	public boolean[] getFilterMask(){
		return this.filterMask;
	}
	
}
