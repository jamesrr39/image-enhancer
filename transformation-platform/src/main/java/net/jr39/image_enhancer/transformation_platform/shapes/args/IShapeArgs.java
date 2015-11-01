
package net.jr39.image_enhancer.transformation_platform.shapes.args;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author james
 */
public interface IShapeArgs {

	public abstract List<Point> getPixelsToBeTransformed();
	
	public abstract Point getTransformationCentre();
	
}