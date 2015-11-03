package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;

/**
 *
 * @author james
 * @see https://github.com/jamesrr39/image-enhancer/wiki/simple-transformation
 * @see https://github.com/jamesrr39/image-enhancer/wiki/transformation-with-args
 */
public class GenericTransformationArgs {

	private final IShapeArgs shapeArgs;
	private final boolean isGradual;

	public boolean getIsGradual() {
		return isGradual;
	}
	
	public GenericTransformationArgs(IShapeArgs shapeArgs) {
		this(shapeArgs, false);
	}
	
	public GenericTransformationArgs(IShapeArgs shapeArgs, boolean isGradual) {
		this.shapeArgs = shapeArgs;
		this.isGradual = isGradual;
	}

	public IShapeArgs getShapeArgs() {
		return shapeArgs;
	}
	
}
