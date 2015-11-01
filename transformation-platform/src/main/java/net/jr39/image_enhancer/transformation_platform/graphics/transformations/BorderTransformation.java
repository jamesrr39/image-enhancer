
package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * adds a border to the image
 * @author james
 */
public class BorderTransformation extends AbstractTransformation<BorderTransformationArgs>{

	private static final Logger logger = Logger.getLogger(BorderTransformation.class.getName());
	
	public BorderTransformation(BorderTransformationArgs transformationArgs) {
		super(transformationArgs);
	}
	
	/**
	 * Helper to generate an int array of a given Color
	 * @param colour
	 * @param arraySize width * height of area
	 * @return int array filled with the RGB representation of that colour.
	 */
	@VisibleForTesting
	static int[] generateBorder(Color colour, int arraySize){
		int[] array = new int[arraySize];
		Arrays.fill(array, colour.getRGB());
		return array;
	};
	
	/**
	 * adds a border to an image
	 * @param image
	 * @return 
	 */
	@Override
	protected BufferedImage performTransformation(BufferedImage image) {
		final int newImageWidth = transformationArgs.getLeftBorder()+ image.getWidth() + transformationArgs.getRightBorder();
		final int newImageHeight = transformationArgs.getTopBorder() + image.getHeight() + transformationArgs.getBottomBorder();
		
		final BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, image.getType());
		final Color borderColour = transformationArgs.getBorderColour();
		
		// top border
		newImage.setRGB(
				0,
				0,
				newImageWidth,
				transformationArgs.getTopBorder(),
				generateBorder(borderColour, newImageWidth * transformationArgs.getTopBorder()),
				0,
				newImageWidth
		);
		// bottom border
		newImage.setRGB(
				0,
				(newImageHeight -1) - transformationArgs.getBottomBorder(),
				newImageWidth,
				transformationArgs.getTopBorder(),
				generateBorder(borderColour, newImageWidth * transformationArgs.getTopBorder()),
				0,
				newImageWidth
		);
		// left border
		newImage.setRGB(
				0,
				transformationArgs.getTopBorder(),
				transformationArgs.getLeftBorder(),
				image.getHeight(),
				generateBorder(borderColour, transformationArgs.getLeftBorder() * image.getHeight()),
				0,
				transformationArgs.getLeftBorder()
		);
		// right border
		newImage.setRGB(
				newImageWidth - transformationArgs.getRightBorder(),
				transformationArgs.getTopBorder(),
				transformationArgs.getRightBorder(),
				image.getHeight(),
				generateBorder(borderColour, transformationArgs.getRightBorder()* image.getHeight()),
				0,
				transformationArgs.getRightBorder()
		);
		
		// write old image into new image
		newImage.setRGB(
				transformationArgs.getLeftBorder(), 
				transformationArgs.getTopBorder(), 
				image.getWidth(), 
				image.getHeight(), 
				image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()),
				0,
				image.getWidth()
		);
		
		logger.log(Level.INFO, "Image new width: {0}, height: {1}", new Object[]{
			newImage.getWidth(),
			newImage.getHeight()
		});
		return newImage;
		
	}
	
	

	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		return colour;
	}
	
}
