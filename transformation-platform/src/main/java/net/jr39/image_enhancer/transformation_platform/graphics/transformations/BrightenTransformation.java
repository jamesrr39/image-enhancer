package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters.ColourUtils;

/**
 * Transformation to Brighten (or darken) and image. To brighten, pass scale factor &gt; 1, to darken pass a scale factor &gt; 0 and &lt; 1
 *
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
	protected int graduallyTransformPixel(Point point, int colour, BufferedImage image, Point transformationCentre) {
		float appliedTransformationFactor = (float) ((transformationArgs.getScaleFactor() - 1) * GradualTransformationHelper.getDecimalDistanceFromCentre(point, transformationCentre) + 1);
		return brightenPixel(colour, appliedTransformationFactor);
	}
	
	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		return brightenPixel(colour, transformationArgs.getScaleFactor());
	}

	@VisibleForTesting
	static int brightenPixel(int colour, float scaleFactor) {
		int red = (int) (ColourUtils.getRedFromRGB(colour) * scaleFactor);
		int green = (int) (ColourUtils.getGreenFromRGB(colour) * scaleFactor);
		int blue = (int) (ColourUtils.getBlueFromRGB(colour) * scaleFactor);
		red = red > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : red;
		blue = blue > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : blue;
		green = green > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : green;
		return ColourUtils.getRGBInt(red, green, blue);
	}
}
