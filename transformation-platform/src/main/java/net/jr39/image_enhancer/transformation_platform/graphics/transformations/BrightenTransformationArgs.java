package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class BrightenTransformationArgs extends GenericTransformationArgs {

	private final float scaleFactor;

	
	/**
	 *
	 * @param scaleFactor brightness multiplier. Amount to multiply the pixel RGB value by when transforming.
	 * @param shapeArgs
	 * @param isGradual
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, float scaleFactor, boolean isGradual) {
		super(shapeArgs, isGradual);
		this.scaleFactor = scaleFactor;
	}
	
	/**
	 *
	 * @param scaleFactor brightness multiplier. Amount to multiply the pixel RGB value by when transforming.
	 * @param shapeArgs
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, float scaleFactor) {
		this(shapeArgs, scaleFactor, false);
	}

	public float getScaleFactor() {
		return scaleFactor;
	}

}
