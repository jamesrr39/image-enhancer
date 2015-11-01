
package net.jr39.image_enhancer.transformation_platform.graphics.transformations;


import java.awt.Color;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters.ColourUtils;
import net.jr39.image_enhancer.transformation_platform.shapes.args.IShapeArgs;

/**
 *
 * @author james
 */
public class BorderTransformationArgs extends GenericTransformationArgs{

	private final int leftBorder;
	private final int topBorder;
	private final int rightBorder;
	private final int bottomBorder;
	private final Color borderColour;

	public BorderTransformationArgs(IShapeArgs shapeArgs) {
		this(shapeArgs, ColourUtils.getWhiteSmoke(), 100);
	}
	
	public BorderTransformationArgs(IShapeArgs shapeArgs, Color borderColour, int borderWidth) {
		super(shapeArgs);
		this.borderColour = borderColour;
		this.leftBorder = borderWidth;
		this.topBorder = borderWidth;
		this.rightBorder = borderWidth;
		this.bottomBorder = borderWidth;
	}

	public int getLeftBorder() {
		return leftBorder;
	}

	public int getTopBorder() {
		return topBorder;
	}

	public int getRightBorder() {
		return rightBorder;
	}

	public int getBottomBorder() {
		return bottomBorder;
	}
	
	public Color getBorderColour() {
		return borderColour;
	}
	
}
