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
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;

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
		LOGGER.info("scale: " + transformationArgs.getScaleFactor());
		int[] imageIntArray = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
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
		final float factor = transformationArgs.getScaleFactor();
		final int MAX = 255; // white for that component
		final int MIN = 0; // black (no colour) for that component
		int red = (int) (ColourUtils.getRedFromRGB(intcolour) * factor);
		int green = (int) (ColourUtils.getGreenFromRGB(intcolour) * factor);
		int blue = (int) (ColourUtils.getBlueFromRGB(intcolour)* factor);
		red = red > MAX ? MAX : red;
		blue = blue > MAX ? MAX : blue;
		green = green > MAX ? MAX : green;
		return new Color(red, green, blue).getRGB();
	}
}