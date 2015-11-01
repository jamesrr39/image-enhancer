package net.jr39.image_enhancer.transformation_platform.shapes.args;

import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;
import net.jr39.image_enhancer.transformation_platform.shapes.args.CircleArgs;
import java.awt.Point;
import java.util.List;
import junit.framework.Assert;
import net.jr39.image_enhancer.transformation_platform.shapes.Circle;
import org.junit.Test;

/**
 *
 * @author james
 */
public class CircleArgsTests {

	@Test
	public void testGetPixelsToBeTransformed(){
		final int radius = 100;
		final Point centrePoint = new Point(200, 250);
		final IShapeArgs circleArgs = new CircleArgs(new Circle(centrePoint, radius)); 
		final int roundedCircleArea = (int) (Math.PI * Math.pow(radius, 2));
		final List<Point> pixelsToBeTransformed = circleArgs.getPixelsToBeTransformed();
		final int differenceInArea = Math.abs(roundedCircleArea - pixelsToBeTransformed.size());
		
		Assert.assertEquals("number of pixels should be similar (very small difference, allowing for rounding)", true, differenceInArea < (roundedCircleArea * 0.001));
		Assert.assertEquals("middle pixel is the middle of the list", centrePoint, pixelsToBeTransformed.get(pixelsToBeTransformed.size() / 2));
	}
	
}
