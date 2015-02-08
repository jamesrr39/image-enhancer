package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jr39.image_enhancer.graphics.ImageHelper;

/**
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

	/**
	 *
	 * @param image image to be transformed
	 * @return transformed image
	 */
	@Override
	protected BufferedImage performTransformation(BufferedImage image) {
		LOGGER.log(Level.INFO, "starting brighten transformation");
		final long startTime = System.currentTimeMillis();
		RescaleOp op = new RescaleOp(transformationArgs.getScaleFactor(), transformationArgs.getOffset(), null);
		LOGGER.log(Level.INFO, "finished brighten transformation in {0}ms", (System.currentTimeMillis() - startTime));
		return op.filter(image, null);
	}

	@Override
	protected BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed) {
		int[] imageIntArray = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		final Dimension imageDimensions = new Dimension(image.getWidth(), image.getHeight());
		pixelsToBeTransformed
				.stream()
				.forEach((point) -> {
					int colour = imageIntArray[ImageHelper.getImageIntArrayIndex(point, imageDimensions)];
					colour = getBrightenedColour(colour);
					// todo - brighten
					image.setRGB((int)point.getX(), (int)point.getY(), colour);
						});
		return image;
	}
	
	private int getBrightenedColour(int intcolour){
		// optimise if needed
		// mock factor
		final float factor = 4f;
		final int MAX = 255; // white for that component
		final int MIN = 0; // black (no colour) for that component
		Color colour = new Color(intcolour);
		int red = (int) (colour.getRed() * factor);
		int green = (int) (colour.getGreen() * factor);
		int blue = (int) (colour.getBlue() * factor);
		red = red > MAX ? MAX : red;
		blue = blue > MAX ? MAX : blue;
		green = green > MAX ? MAX : green;
		return new Color(red, green, blue).getRGB();
	}
}