package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author james
 */
public abstract class AbstractTransformationArgs {

	private Point upperLeftPoint;
	private Rectangle transformationRectangle;

	public AbstractTransformationArgs() {
	}
	
	/**
	 * All transformation args must implement this constructor so that the transformation can be used from the command line
	 * @param transformationArgs
	 * @throws java.lang.NoSuchMethodException if this constructor is not implemented in the extended class
	 */
	public AbstractTransformationArgs(String[] transformationArgs) throws NoSuchMethodException{
		throw new NoSuchMethodException();
	};

	public AbstractTransformationArgs(Point upperLeftPoint, Rectangle transformationRectangle) {
		this.upperLeftPoint = upperLeftPoint;
		this.transformationRectangle = transformationRectangle;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public Rectangle getTransformationRectangle() {
		return transformationRectangle;
	}
}
