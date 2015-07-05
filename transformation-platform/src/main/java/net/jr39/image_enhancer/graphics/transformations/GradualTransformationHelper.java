package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;

/**
 *
 * @author james
 */
public class GradualTransformationHelper {
	
	/**
	 * distance factor to multiply by; as it gets closer to the centre this should become closer to 1; as we move to the edge of the transformation area it should move towards 0
	 * @param point co-ords of the current pixel
	 * @param transformationCentre co-ords of the centre of the transformation
	 * @return 
	 */
	public static double getDecimalDistanceFromCentre(Point point, Point transformationCentre) {

		double xDistanceFromCentre = Math.abs((Math.abs(point.getX() - transformationCentre.getX()) / transformationCentre.getX()) - 1);
		double yDistanceFromCentre = Math.abs((Math.abs(point.getY() - transformationCentre.getY()) / transformationCentre.getY()) - 1);
		double decimalDistanceFromCentre = Math.sqrt(Math.pow(xDistanceFromCentre, 2) + Math.pow(yDistanceFromCentre, 2)) / Math.sqrt(2);

		return Math.abs(decimalDistanceFromCentre);
	}
}
