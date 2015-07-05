package net.jr39.image_enhancer.shapes.args;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.jr39.image_enhancer.shapes.Circle;

/**
 *
 * @author james
 */
public class CircleArgs implements IShapeArgs{
	private final Circle circle;

	public CircleArgs(Circle circle){
		this.circle = circle;
	}

	@Override
	public List<Point> getPixelsToBeTransformed() {
		List<Point> pixelsToBeTransformed = new ArrayList<>();
		Point upperLeftSurroundingRectanglePoint = new Point((int)circle.getCentre().getX() - circle.getRadius(), (int)circle.getCentre().getY() - circle.getRadius());
		Rectangle surroundingRectangle = new Rectangle(upperLeftSurroundingRectanglePoint, new Dimension(circle.getRadius() * 2, circle.getRadius() * 2));
		for(int x = (int) surroundingRectangle.getX() ; x < surroundingRectangle.getWidth() + surroundingRectangle.getX(); x++){
			for(int y = (int) surroundingRectangle.getY(); y < surroundingRectangle.getHeight() + surroundingRectangle.getY(); y++){
				int xDistanceFromCentre = (int)circle.getCentre().getX() - x;
				int yDistanceFromCentre = (int)circle.getCentre().getY() - y;
				int distanceFromCentre = (int) Math.sqrt((Math.pow(xDistanceFromCentre, 2) + Math.pow(yDistanceFromCentre, 2)));
				if(distanceFromCentre < circle.getRadius()){
					// distance from centre is less than radius, it's in the circle
					pixelsToBeTransformed.add(new Point(x, y));
				}
			}
		}
		return pixelsToBeTransformed;
	}

	@Override
	public Point getTransformationCentre() {
		return circle.getCentre();
	}

}
