package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class BrightenTransformationArgs extends AbstractTransformationArgs {

	private final float scaleFactor;
	private final boolean isGradual;

	public boolean getIsGradual() {
		return isGradual;
	}

	
	/**
	 *
	 * @param scaleFactor brightness multiplier. Amount to multiply the pixel RGB value by when transforming.
	 * @param shapeArgs
	 * @param isGradual
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, float scaleFactor, boolean isGradual) {
		super(shapeArgs);
		this.scaleFactor = scaleFactor;
		this.isGradual = isGradual;
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
