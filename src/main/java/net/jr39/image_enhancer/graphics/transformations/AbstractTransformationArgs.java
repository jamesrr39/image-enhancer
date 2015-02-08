package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public abstract class AbstractTransformationArgs {

	private final IShapeArgs shapeArgs;

	public AbstractTransformationArgs(IShapeArgs shapeArgs) {
		this.shapeArgs = shapeArgs;
	}

	public IShapeArgs getShapeArgs() {
		return shapeArgs;
	}
	
}
