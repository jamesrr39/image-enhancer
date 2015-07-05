package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author james
 */
public class GradualTransformationTests {
	
	@Test
	public void testGetPixelScaleFactor() {

		final Point transformationCentre = new Point(200, 100);
		
		final Point topLeftPoint = new Point(0, 0);
		final Point topRightPoint = new Point((int) (transformationCentre.getX() *2), 0);
		final Point bottomLeftPoint = new Point(0, (int) (transformationCentre.getY() * 2));
		final Point bottomRightPoint = new Point((int) (transformationCentre.getX() *2), (int) (transformationCentre.getY() * 2));
		
		final Point halfwayPoint = new Point((int) (transformationCentre.getX() /2), (int) (transformationCentre.getY() /2));
		// expect 1 at the middle
		// expect 0.5 halfway
		// expect 0 at the edges
		Assert.assertEquals("Is 1 in the middle", (double) 1, GradualTransformationHelper.getDecimalDistanceFromCentre(transformationCentre, transformationCentre));

		Assert.assertEquals("Is 0 at the edges", (double) 0, GradualTransformationHelper.getDecimalDistanceFromCentre(topLeftPoint, transformationCentre));
		Assert.assertEquals("Is 0 at the edges", (double) 0, GradualTransformationHelper.getDecimalDistanceFromCentre(topRightPoint, transformationCentre));
		Assert.assertEquals("Is 0 at the edges", (double) 0, GradualTransformationHelper.getDecimalDistanceFromCentre(bottomLeftPoint, transformationCentre));
		Assert.assertEquals("Is 0 at the edges", (double) 0, GradualTransformationHelper.getDecimalDistanceFromCentre(bottomRightPoint, transformationCentre));
		
		Assert.assertEquals("Is 0.5 at halfway", 0.5, GradualTransformationHelper.getDecimalDistanceFromCentre(halfwayPoint, transformationCentre));

	}

}
