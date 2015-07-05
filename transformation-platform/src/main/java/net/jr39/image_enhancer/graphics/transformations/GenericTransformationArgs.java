package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
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