package net.jr39.image_enhancer.graphics.transformations;

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
			Dimension transformationDimensions = new Dimension(image.getWidth(), image.getHeight());
			// distance factor to multiply by; as it gets closer to the centre this should become closer to 1; as we move to the edge of the transformation area it should move towards 0
			double xDistanceFromCentre = Math.abs(point.getX() - transformationCentre.getX()) / transformationCentre.getX();
			double yDistanceFromCentre = Math.abs(point.getY() - transformationCentre.getY()) / transformationCentre.getY();
			double decimalDistanceFromCentre = Math.sqrt(Math.pow(xDistanceFromCentre, 2) + Math.pow(yDistanceFromCentre, 2));

			double thisPixelFactor = decimalDistanceFromCentre * transformationArgs.getScaleFactor();
			return brightenPixel(colour, (float) thisPixelFactor);
		} else {
			return brightenPixel(colour, transformationArgs.getScaleFactor());
		}
	}

	public static int brightenPixel(int colour, float scaleFactor) {
		int red = (int) (ColourUtils.getRedFromRGB(colour) * scaleFactor);
		int green = (int) (ColourUtils.getGreenFromRGB(colour) * scaleFactor);
		int blue = (int) (ColourUtils.getBlueFromRGB(colour) * scaleFactor);
		red = red > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : red;
		blue = blue > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : blue;
		green = green > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : green;
		return ColourUtils.getRGBInt(red, green, blue);
	}
}
