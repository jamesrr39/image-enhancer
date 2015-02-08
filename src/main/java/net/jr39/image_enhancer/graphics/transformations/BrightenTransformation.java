package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
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
					// todo - brighten
					image.setRGB((int)point.getX(), (int)point.getY(), colour);
						});
		return image;
	}	
}