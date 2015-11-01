package net.jr39.image_enhancer.transformation_platform.shapes.args;

import net.jr39.image_enhancer.transformation_platform.shapes.args.PolygonArgs;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author james
 */
public class PolygonArgsTests {

	private PolygonArgs generatePolygon(){
		List<Point> polygonPoints = new LinkedList<>();
		polygonPoints.add(new Point(3,3));
		polygonPoints.add(new Point(5,5));
		polygonPoints.add(new Point(1,7));
		return new PolygonArgs(polygonPoints);
	}
	
	@Test
	public void testGetPixelsToBeTransformed(){
		Assert.assertTrue("pixels to be transformed is correct", false);
	}
	
	@Test
	public void testGetTransformationCentre(){
		Assert.assertEquals(new Point(3, 5), generatePolygon().getTransformationCentre());
	}
	
}
