
package net.jr39.image_enhancer.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static BufferedImage resizeImage(BufferedImage image, int width, int height){

		BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image,0,0,width,height,null);
		g.dispose();
		
		return resizedImage;

	}
	
}
