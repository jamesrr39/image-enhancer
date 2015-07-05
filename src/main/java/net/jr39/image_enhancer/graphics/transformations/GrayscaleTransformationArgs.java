package net.jr39.image_enhancer.graphics.transformations;

import net.jr39.image_enhancer.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class GrayscaleTransformationArgs extends GenericTransformationArgs{

	public GrayscaleTransformationArgs(IShapeArgs shapeArgs) {
		super(shapeArgs, false);
	}

}
