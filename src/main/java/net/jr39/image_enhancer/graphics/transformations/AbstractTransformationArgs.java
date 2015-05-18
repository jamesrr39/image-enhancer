package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public abstract class AbstractTransformationArgs {

	private final IShapeArgs shapeArgs;
	private final boolean isGradual;

	public boolean getIsGradual() {
		return isGradual;
	}
	
	public AbstractTransformationArgs(IShapeArgs shapeArgs) {
		this(shapeArgs, false);
	}
	
	public AbstractTransformationArgs(IShapeArgs shapeArgs, boolean isGradual) {
		this.shapeArgs = shapeArgs;
		this.isGradual = isGradual;
	}

	public IShapeArgs getShapeArgs() {
		return shapeArgs;
	}
	
}
