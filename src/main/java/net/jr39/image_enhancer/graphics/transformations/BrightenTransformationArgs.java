package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

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
	
	public BrightenTransformationArgs(IShapeArgs shapeArgs, String[] transformationArgs){
		this(shapeArgs, Float.parseFloat(transformationArgs[0]));
	}
	
	
	/**
	 *
	 * @param scaleFactor brightness multiplier. Between 1f and 1.5f
	 * @param shapeArgs
	 */
	public BrightenTransformationArgs(IShapeArgs shapeArgs, float scaleFactor) {
		super(shapeArgs);
		this.scaleFactor = scaleFactor;
	}

	public float getOffset() {
		return offset;
	}

	public float getScaleFactor() {
		return scaleFactor;
	}

}
