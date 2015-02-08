package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class BrightenTransformationArgs extends AbstractTransformationArgs {

	private final float scaleFactor;

	/**
	 *
	 * @param shapeArgs
	 * @param transformationArgs 1st index brightness multiplier. Amount to multiply the pixel RGB value by when transforming.
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, String[] transformationArgs){
		this(shapeArgs, Float.parseFloat(transformationArgs[0]));
	}
	
	
	/**
	 *
	 * @param scaleFactor brightness multiplier. Amount to multiply the pixel RGB value by when transforming.
	 * @param shapeArgs
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, float scaleFactor) {
		super(shapeArgs);
		this.scaleFactor = scaleFactor;
	}

	public float getScaleFactor() {
		return scaleFactor;
	}

}
