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
public abstract class AbstractTransformation<T extends GenericTransformationArgs> {
	
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
		final long startTime = System.currentTimeMillis();
		image = ImageHelper.cloneImage(image);
		BufferedImage transformedImage;
		final int numberOfPixelsTransformed;
		if (this.transformationArgs.getShapeArgs() == null) {
			// full image transformation
			transformedImage = performTransformation(image);
			numberOfPixelsTransformed = image.getWidth() * image.getHeight();
		} else {
			final Point transformationCentre = transformationArgs.getShapeArgs().getTransformationCentre();
			transformedImage = performTransformation(image, transformationArgs.getShapeArgs().getPixelsToBeTransformed(), transformationCentre);
			numberOfPixelsTransformed = transformationArgs.getShapeArgs().getPixelsToBeTransformed().size();
		}
		LOGGER.log(Level.INFO, "{0} pixels transformed in {1}ms", new Object[]{
			numberOfPixelsTransformed,
			System.currentTimeMillis() - startTime
		});
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
		Point transformationCentre = new Point(image.getWidth() / 2, image.getHeight() / 2);
		return performTransformation(image, pixelsToBeTransformed, transformationCentre);
	}

	protected BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed, Point transformationCentre){
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
			
			// todo shape to implement transformationCentre
			int newColour = transformationArgs.getIsGradual() ? graduallyTransformPixel(point, colour, image, transformationCentre) : transformPixel(point, colour, image);
			
			image.setRGB((int) point.getX(), (int) point.getY(), newColour);
		});
		return image;
	}
	
	protected int graduallyTransformPixel(Point point, int colour, BufferedImage image, Point transformationCentre){
		LOGGER.log(Level.WARNING, "The selected transformation ''{0}'' doesn''t offer a gradual transformation, proceeding with a normal transformation", transformationArgs.getClass().getSimpleName());
		return transformPixel(point, colour, image);
	}
	
	protected abstract int transformPixel(Point point, int colour, BufferedImage image);

}
