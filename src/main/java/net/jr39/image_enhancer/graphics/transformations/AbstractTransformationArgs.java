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
