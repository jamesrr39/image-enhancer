package net.jr39.image_enhancer.transformation_platform.shapes.args;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author james
 */
public class PolygonArgs implements IShapeArgs {
	private final List<Point> points;
	private final Rectangle surroundingRectangle;

	/**
	 * 
	 * @param points list of points to join together. The last point will be connected to the first shape to complete the polygon
	 */
	public PolygonArgs(List<Point> points){
		this.points = points;
		this.surroundingRectangle = calculateSurroundingRectangle(points);
	}
	
	@VisibleForTesting
	static Rectangle calculateSurroundingRectangle(List<Point> points){
		Point firstPoint = points.get(0);
		int furthestLeft = (int) firstPoint.getX();
		int furthestRight = (int) firstPoint.getX();
		int highest = (int) firstPoint.getY();
		int lowest = (int) firstPoint.getY();
		for(Point point : points){
			if(point.getX() < furthestLeft){
				furthestLeft = (int) point.getX();
			}
			if(point.getX() > furthestRight){
				furthestRight = (int) point.getX();
			}
			if(point.getY() < highest){
				highest = (int) point.getY();
			}
			if(point.getY() > lowest){
				lowest = (int) point.getY();
			}
		}
		int height = lowest - highest;
		int width = furthestRight - furthestLeft;
		return new Rectangle(furthestLeft, highest, width, height);
	}

	@Override
	public List<Point> getPixelsToBeTransformed() {
		// get rectangle surrounding
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Point getTransformationCentre() {
		return new Point((int) surroundingRectangle.getCenterX(), (int) surroundingRectangle.getCenterY());
	}
}
