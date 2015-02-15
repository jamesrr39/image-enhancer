package net.jr39.image_enhancer.graphics.transformations;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;

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
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		if (transformationArgs.getIsGradual()) {
			// to start, assume centre of the transformation shape is the middle of the image
			Point transformationCentre = new Point(image.getWidth() / 2, image.getHeight() / 2);
			float appliedTransformationFactor = (float) ((transformationArgs.getScaleFactor() - 1) * getDecimalDistanceFromCentre(point, transformationCentre) + 1);
			return brightenPixel(colour, appliedTransformationFactor);
		} else {
			return brightenPixel(colour, transformationArgs.getScaleFactor());
		}
	}

	/**
	 * distance factor to multiply by; as it gets closer to the centre this should become closer to 1; as we move to the edge of the transformation area it should move towards 0
	 * @param point co-ords of the current pixel
	 * @param transformationCentre co-ords of the centre of the transformation
	 * @return 
	 */
	@VisibleForTesting
	static double getDecimalDistanceFromCentre(Point point, Point transformationCentre) {

		double xDistanceFromCentre = Math.abs((Math.abs(point.getX() - transformationCentre.getX()) / transformationCentre.getX()) - 1);
		double yDistanceFromCentre = Math.abs((Math.abs(point.getY() - transformationCentre.getY()) / transformationCentre.getY()) - 1);
		double decimalDistanceFromCentre = Math.sqrt(Math.pow(xDistanceFromCentre, 2) + Math.pow(yDistanceFromCentre, 2)) / Math.sqrt(2);

		return Math.abs(decimalDistanceFromCentre);
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
