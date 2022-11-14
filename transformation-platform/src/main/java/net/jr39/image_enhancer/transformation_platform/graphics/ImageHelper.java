
package net.jr39.image_enhancer.transformation_platform.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImageHelper {

	public static BufferedImage cloneImage(BufferedImage image){
		return new BufferedImage(image.getColorModel(), image.copyData(null), image.getColorModel().isAlphaPremultiplied(), null);
	}
	
	public static BufferedImage resizeImage(BufferedImage image, int width, int height){

		BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image,0,0,width,height,null);
		g.dispose();
		
		return resizedImage;

	}
	
	/**
	 * Gets a int array of the whole image
	 * @param image
	 * @return 
	 */
	public static int[] getImageRGB(BufferedImage image){
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	}
	
	public static int getImageIntArrayIndex(Point point, Dimension imageDimensions){
		return (int) (point.getY() * imageDimensions.getWidth() + point.getX());
	}
	
}
