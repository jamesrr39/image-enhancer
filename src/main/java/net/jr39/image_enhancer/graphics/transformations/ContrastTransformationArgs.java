package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class ContrastTransformationArgs extends AbstractTransformationArgs {

	private final float factor;

	public ContrastTransformationArgs(IShapeArgs shapeArgs, float factor) {
		super(shapeArgs);
		this.factor = factor;
	}

	public float getFactor() {
		return factor;
	}

}
