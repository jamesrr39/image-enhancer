package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import net.jr39.image_enhancer.shapes.args.RectangleArgs;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author james
 * @param <T>
 */
public abstract class AbstractTransformation<T extends AbstractTransformationArgs> {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractTransformation.class.getName());

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
	public final BufferedImage transform(BufferedImage image) {
		long startTime = System.currentTimeMillis();
		BufferedImage transformedImage;
		if (this.transformationArgs.getShapeArgs() == null) {
			// full image transformation
			transformedImage = performTransformation(image);
		} else {
			transformedImage = performTransformation(image, transformationArgs.getShapeArgs().getPixelsToBeTransformed());
		}
		LOGGER.info("Transformation performed in " + (System.currentTimeMillis() - startTime) + "ms");
		return transformedImage;
	}

	/**
	 * Performs a transformation on the whole image by treating it as a rectangle
	 * Can be overriden by extending classes if there is a more efficient approach
	 *
	 * @param image part of the image to be transformed
	 * @return transformed image
	 */
	protected BufferedImage performTransformation(BufferedImage image) {
		RectangleArgs r = new RectangleArgs(new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		List<Point> pixelsToBeTransformed = r.getPixelsToBeTransformed();
		return performTransformation(image, pixelsToBeTransformed);
	}

	protected abstract BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed);

}
