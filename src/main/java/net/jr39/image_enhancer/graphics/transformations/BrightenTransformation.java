package net.jr39.image_enhancer.graphics.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author james
 */
public class BrightenTransformation extends AbstractTransformation<BrightenTransformationArgs> {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

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
		logger.log(Level.INFO, "starting brighten transformation");
		final long startTime = System.currentTimeMillis();
		RescaleOp op = new RescaleOp(transformationArgs.getScaleFactor(), transformationArgs.getOffset(), null);
		logger.log(Level.INFO, "finished brighten transformation in {0}ms", (System.currentTimeMillis() - startTime));
		return op.filter(image, null);
	}
}
