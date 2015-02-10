package net.jr39.image_enhancer.shapes;

import java.awt.Point;

/**
 * 
 * java doesn't already have this?
 * @author james
 */
public class Circle {
	private final Point centre;
	private final int radius;
	
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public int getRadius() {
		return radius;
	}

}
