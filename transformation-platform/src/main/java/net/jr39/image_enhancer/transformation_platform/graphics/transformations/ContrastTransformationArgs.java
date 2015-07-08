package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class ContrastTransformationArgs extends GenericTransformationArgs {

	private final float factor;

	public ContrastTransformationArgs(IShapeArgs shapeArgs, float factor, boolean isGradual) {
		super(shapeArgs, isGradual);
		this.factor = factor;
	}
	
	public ContrastTransformationArgs(IShapeArgs shapeArgs, float factor) {
		this(shapeArgs, factor, false);
	}

	public float getFactor() {
		return factor;
	}

}
