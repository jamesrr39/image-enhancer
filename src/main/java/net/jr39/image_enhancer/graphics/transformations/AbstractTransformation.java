package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author james
 * @param <T>
 */
public abstract class AbstractTransformation<T extends AbstractTransformationArgs> {
	
	protected final T transformationArgs;

	public AbstractTransformation(T transformationArgs) {
		this.transformationArgs = transformationArgs;
	}

	/**
	 * External 'API' to be called by Image
	 *
	 * @param image part of the image to be transformed
	 * @return
	 */
	public BufferedImage transform(BufferedImage image) {
		
		if (this.transformationArgs.getShapeArgs() == null) {
			// full image transformation
			return performTransformation(image);
		} else {
			return performTransformation(image, transformationArgs.getShapeArgs().getPixelsToBeTransformed());
		}
	}

	/**
	 * Transforming to be performed by the extending classes
	 *
	 * @param image part of the image to be transformed
	 * @return transformed image
	 */
	protected abstract BufferedImage performTransformation(BufferedImage image);
	
	protected abstract BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed);

}
