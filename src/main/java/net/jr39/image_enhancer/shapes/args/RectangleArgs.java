
package net.jr39.image_enhancer.shapes.args;

import com.google.common.collect.ImmutableList;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author james
 */
public class RectangleArgs implements IShapeArgs {
	
	private final Rectangle rectangle;
	
	/**
	 * 
	 * @param rectangle 
	 */
	public RectangleArgs(Rectangle rectangle){
		this.rectangle = rectangle;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	@Override
	public List<Point> getPixelsToBeTransformed() {
		Point[] pixels = new Point[rectangle.width * rectangle.height];
		int index = 0;
		for(int x = 0; x < rectangle.width; x++){
			for(int y = 0; y < rectangle.height; y++){
				pixels[index] = new Point(x + (int)rectangle.getX(), y + (int)rectangle.getY());
				index++;
			}
		}
		return ImmutableList.of(pixels);
	}

}
