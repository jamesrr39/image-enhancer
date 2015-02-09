package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jr39.image_enhancer.graphics.ImageHelper;
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;

/**
 * Transformation to Brighten (or darken) and image.
 * To brighten, pass scale factor &gt; 1, to darken pass a scale factor &gt; 0 and &lt; 1
 * @author james
 */
public class BrightenTransformation extends AbstractTransformation<BrightenTransformationArgs> {

	private static final Logger LOGGER = Logger.getLogger(BrightenTransformation.class.getName());

	/**
	 *
	 * @param transformationArgs arguments to be passed for the transformation
	 */
	public BrightenTransformation(BrightenTransformationArgs transformationArgs) {
		super(transformationArgs);
	}

	@Override
	protected BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed) {
		LOGGER.log(Level.INFO, "scale: {0}", transformationArgs.getScaleFactor());
		int[] imageIntArray = ImageHelper.getImageRGB(image);
		final Dimension imageDimensions = new Dimension(image.getWidth(), image.getHeight());
		pixelsToBeTransformed
				.stream()
				.forEach((point) -> {
					int colour = imageIntArray[ImageHelper.getImageIntArrayIndex(point, imageDimensions)];
					colour = getBrightenedColour(colour);
					image.setRGB((int)point.getX(), (int)point.getY(), colour);
						});
		return image;
	}
	
	private int getBrightenedColour(int intcolour){
		int red = (int) (ColourUtils.getRedFromRGB(intcolour) * transformationArgs.getScaleFactor());
		int green = (int) (ColourUtils.getGreenFromRGB(intcolour) * transformationArgs.getScaleFactor());
		int blue = (int) (ColourUtils.getBlueFromRGB(intcolour)* transformationArgs.getScaleFactor());
		red = red > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : red;
		blue = blue > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : blue;
		green = green > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : green;
		return ColourUtils.getRGBInt(red, green, blue);
	}
}