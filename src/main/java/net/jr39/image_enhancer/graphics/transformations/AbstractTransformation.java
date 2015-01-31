package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import net.jr39.image_enhancer.shapes.args.IShapeArgs;

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
//		
//		// mock
//		Point[] pixels = new Point[(image.getWidth() - 201) * image.getHeight()];
//		int c=0;
//		for(int y = 0; y < image.getHeight(); y++){
//			for(int x = 0; x < image.getWidth(); x++){
//				if(x > 200){
//					pixels[c] = new Point(x, y);
//					c++;
//				}
//			}
//		}
//		
//		final List<Point> pixelsToBeTransformed = ImmutableList.of(pixels);
		
		if (this.transformationArgs.getShapeArgs() == null) {
			// full image transformation
			return performTransformation(image);
		} else {
			return performTransformation(image, transformationArgs.getShapeArgs().getPixelsToBeTransformed());
			
//			final Graphics g = image.getGraphics();
//			final Point point = transformationArgs.getUpperLeftPoint();
//			final Rectangle rectangle = transformationArgs.getTransformationRectangle();
//			BufferedImage subImage = image.getSubimage((int) point.getX(), (int) point.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
//			subImage = performTransformation(subImage);
//			g.drawImage(subImage, (int) point.getX(), (int) point.getY(), null);
//			return image;
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
