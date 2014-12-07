package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author james
 */
public class BrightenTransformationArgs extends AbstractTransformationArgs {

	private final float scaleFactor;
	/**
	 * for contrast
	 */
	private final float offset = 1f;

	/**
	 *
	 * @param scaleFactor brightness multiplier. Between 1f and 1.5f
	 */
	public BrightenTransformationArgs(float scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	/**
	 *
	 * @param scaleFactor brightness multiplier. Between 1f and 1.5f
	 * @param upperLeftPoint upper left point of the transform
	 * @param transformationRectangle dimensions of the rectangle to be transformed
	 */
	public BrightenTransformationArgs(float scaleFactor, Point upperLeftPoint, Rectangle transformationRectangle) {
		super(upperLeftPoint, transformationRectangle);
		this.scaleFactor = scaleFactor;
	}

	public float getOffset() {
		return offset;
	}

	public float getScaleFactor() {
		return scaleFactor;
	}

}
