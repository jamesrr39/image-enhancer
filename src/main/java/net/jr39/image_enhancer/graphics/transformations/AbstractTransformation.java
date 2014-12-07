package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

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
		if (this.transformationArgs.getUpperLeftPoint() == null) {
			// full image transformation
			return transform(image);
		} else {
			final Graphics g = image.getGraphics();
			final Point point = transformationArgs.getUpperLeftPoint();
			final Rectangle rectangle = transformationArgs.getTransformationRectangle();
			BufferedImage subImage = image.getSubimage((int) point.getX(), (int) point.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
			subImage = performTransformation(subImage);
			g.drawImage(subImage, (int) point.getX(), (int) point.getY(), null);
			return image;
		}
	}

	/**
	 * Transforming to be performed by the extending classes
	 *
	 * @param image part of the image to be transformed
	 * @return transformed image
	 */
	protected abstract BufferedImage performTransformation(BufferedImage image);

}
