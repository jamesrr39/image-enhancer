package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import net.jr39.image_enhancer.graphics.ImageHelper;
import net.jr39.image_enhancer.shapes.args.RectangleArgs;
import java.util.logging.Logger;

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
		LOGGER.log(Level.INFO, "Transformation performed in {0}ms", (System.currentTimeMillis() - startTime));
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

	protected BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed){
		int[] imagePixels = ImageHelper.getImageRGB(image);
		final Dimension imageDimensions = new Dimension(image.getWidth(), image.getHeight());
		pixelsToBeTransformed.forEach((point) -> {
			if(point.getX() < 0 || point.getX() > imageDimensions.getWidth() || point.getY() < 0 || point.getY() > imageDimensions.getHeight()){
				LOGGER.log(Level.WARNING, "Point [{0}, {1}] is out of the bounds of the image", new Object[]{
					point.getX(),
					point.getY()
				});
				return;
			}
			int colour = imagePixels[ImageHelper.getImageIntArrayIndex(point, imageDimensions)];
			image.setRGB((int) point.getX(), (int) point.getY(), transformPixel(point, colour, image));
		});
		return image;
	}
	
	protected abstract int transformPixel(Point point, int colour, BufferedImage image);

}
