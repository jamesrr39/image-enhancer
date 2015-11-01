package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class GrayscaleTransformationArgs extends GenericTransformationArgs{

	public GrayscaleTransformationArgs(IShapeArgs shapeArgs) {
		super(shapeArgs, false);
	}

}
