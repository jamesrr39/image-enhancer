
package net.jr39.image_enhancer.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImageHelper {

	public static BufferedImage resizeImage(BufferedImage image, int width, int height){

		BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image,0,0,width,height,null);
		g.dispose();
		
		return resizedImage;

	}
	
	public static int getImageIntArrayIndex(Point point, Dimension imageDimensions){
		return (int) (point.getY() * imageDimensions.getWidth() + point.getX());
	}
	
}