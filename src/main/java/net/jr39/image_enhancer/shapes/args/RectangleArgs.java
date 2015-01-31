
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
	
	/**
	 * upper left point of the rectangle.
	 * format: <code>[X,Y]</code>
	 */
	private final Point upperLeftPoint;
	
	private final Rectangle rectangle;
	
	/**
	 * 
	 * @param upperLeftPoint
	 * @param rectangle 
	 */
	public RectangleArgs(Point upperLeftPoint, Rectangle rectangle){
		this.upperLeftPoint = upperLeftPoint;
		this.rectangle = rectangle;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
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
				pixels[index] = new Point(x + (int)upperLeftPoint.getX(), y + (int)upperLeftPoint.getY());
				index++;
			}
		}
		return ImmutableList.of(pixels);
	}

}
