package net.jr39.image_enhancer.graphics.filters;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import java.util.logging.Logger;
import net.jr39.image_enhancer.graphics.ColourRange;
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;

public class FilterManager {

		private final BufferedImage originalImage;
		private BufferedImage filterImage;
		private List<Integer> filterIndexes;
		private final int xStartInput, yStartInput, filterWidth, filterHeight;
		private final int minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue;
		private final boolean isRedFilterApplied, isGreenFilterApplied, isBlueFilterApplied;
		private static final Logger logger = Logger.getLogger(FilterManager.class.getName());

		public FilterManager(BufferedImage image, int xStartInput, int yStartInput, int filterWidth, int filterHeight, boolean redColourFilter, boolean greenColourFilter, boolean blueColourFilter) {
				this.originalImage = image;
				this.filterImage = new BufferedImage(filterWidth, filterHeight, BufferedImage.TYPE_INT_ARGB);
				this.xStartInput = xStartInput;
				this.yStartInput = yStartInput;
				this.filterWidth = filterWidth;
				this.filterHeight = filterHeight;

				this.isRedFilterApplied = redColourFilter;
				this.isGreenFilterApplied = greenColourFilter;
				this.isBlueFilterApplied = blueColourFilter;

				//TODO real values
				this.minRed = redColourFilter ? 30 : 0;
				this.maxRed = redColourFilter ? 60 : 255;
				this.minGreen = greenColourFilter ? 30 : 0;
				this.maxGreen = greenColourFilter ? 60 : 255;
				this.minBlue = blueColourFilter ? 30 : 0;
				this.maxBlue = blueColourFilter ? 60 : 255;

				this.applyColourFilters(minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue);
				this.setFilterMaskImage(Color.GREEN);

		}

		/**
		 * applies pixels on an AND/UNION Basis; ie if all pixels fall in the ranges then filter
		 *
		 * @param minRed minimum Red value (0-255)
		 * @param maxRed maximum Red value (0-255)
		 * @param minGreen minimum Green value (0-255)
		 * @param maxGreen maximum Green value (0-255)
		 * @param minBlue minimum Blue value (0-255)
		 * @param maxBlue maximum Blue value (0-255)
		 */
		private void applyColourFilters(int minRed, int maxRed, int minGreen, int maxGreen, int minBlue, int maxBlue) {

				Rectangle applyFilterArea = new Rectangle(this.xStartInput, this.yStartInput, this.filterWidth, this.filterHeight);

				int[] imageAsIntArray = ((DataBufferInt) this.originalImage.getData(applyFilterArea).getDataBuffer()).getData();
				logger.info("imageAsIntArrayLength: " + imageAsIntArray.length);
				this.filterIndexes = new ArrayList<>();

				logger.info("minRed: " + this.minRed + ", maxRed: " + this.maxRed);
				logger.info("minGreen: " + this.minGreen + ", maxGreen: " + this.maxGreen);
				logger.info("minBlue: " + this.minBlue + ", maxBlue: " + this.maxBlue);
				ColourRange colourRange = new ColourRange(minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue);
				AppliedFilters appliedFilters = new AppliedFilters(this.isRedFilterApplied, this.isGreenFilterApplied, this.isBlueFilterApplied);

				for (int pos = 0; pos < imageAsIntArray.length; pos++) {
						int pixelColour = imageAsIntArray[pos];
						if (isPixelFiltered(pixelColour, colourRange, appliedFilters)) {
								this.filterIndexes.add(pos);
						}
				}

				logger.info("minRed: " + this.minRed + ", maxRed: " + this.maxRed);
				logger.info("minGreen: " + this.minGreen + ", maxGreen: " + this.maxGreen);
				logger.info("minBlue: " + this.minBlue + ", maxBlue: " + this.maxBlue);
		}

		/**
		 * Decides whether a pixel should be matches, if any of the pixelColour RGB components are inside the colourRange
		 *
		 * @param pixelColour integer (Color) representation of the pixel colour
		 * @param colourRange ColourRange object representing
		 * @param appliedFilters object representing which filters will be applied
		 * @return true if any of the pixelColour RGB components are inside the colourRange, false if not
		 */
		@VisibleForTesting
		public static boolean isPixelFiltered(int pixelColour, ColourRange colourRange, AppliedFilters appliedFilters) {

				boolean isFilteredByRed = false;
				boolean isFilteredByGreen = false;
				boolean isFilteredByBlue = false;

				if (appliedFilters.isRedFilterApplied) {
						int redPixelValue = ColourUtils.getRedFromRGB(pixelColour);
						isFilteredByRed = (colourRange.getMinRed() <= redPixelValue && colourRange.getMaxRed() >= redPixelValue);
				}

				if (appliedFilters.isGreenFilterApplied) {
						int greenPixelValue = ColourUtils.getGreenFromRGB(pixelColour);
						isFilteredByGreen = (colourRange.getMinGreen() <= greenPixelValue && colourRange.getMaxGreen() >= greenPixelValue);
				}

				if (appliedFilters.isBlueFilterApplied) {
						int bluePixelValue = ColourUtils.getBlueFromRGB(pixelColour);
						isFilteredByBlue = (colourRange.getMinBlue() <= bluePixelValue && colourRange.getMaxBlue() >= bluePixelValue);
				}

				return (isFilteredByRed || isFilteredByGreen || isFilteredByBlue);

		}

		/**
		 * creates a BufferedImage of the filter mask
		 *
		 * @param filterColour desired filter mask colour
		 */
		private void setFilterMaskImage(Color filterColour) {

				int[] filterMaskDataBuffer = new int[this.filterImage.getWidth() * this.filterImage.getHeight()];
				Color transparent = new Color(0f, 0f, 0f, 0f);
				Arrays.fill(filterMaskDataBuffer, transparent.getRGB());

				int filterColourRGB = filterColour.getRGB();
				for (int pos : this.filterIndexes) {
						filterMaskDataBuffer[pos] = filterColourRGB;
				}

				DataBuffer databuffer = new DataBufferInt(filterMaskDataBuffer, filterMaskDataBuffer.length);

				ColorModel cm = ColorModel.getRGBdefault();
				SampleModel sm = cm.createCompatibleSampleModel(this.filterImage.getWidth(), this.filterImage.getHeight());
				WritableRaster wr = Raster.createWritableRaster(sm, databuffer, null);

				this.filterImage = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
		}

		public BufferedImage getFilterImage() {
				return this.filterImage;
		}

		public BufferedImage getImage() {
				return this.originalImage;
		}

		public BufferedImage getResizedUnfilteredImage() {
				return this.originalImage.getSubimage(this.xStartInput, this.yStartInput, this.filterWidth, this.filterHeight);
		}

		public BufferedImage getSuperImposedImage() {
				BufferedImage superImposedImage = this.getImage();
				Graphics g = superImposedImage.getGraphics();
				g.drawImage(this.filterImage, this.xStartInput, this.yStartInput, null);
				return superImposedImage;
		}

}
