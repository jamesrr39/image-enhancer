package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Point;
import java.awt.image.BufferedImage;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters.ColourUtils;

/**
 *
 * @author james
 */
public class SepiaTransformation extends AbstractTransformation<GenericTransformationArgs> {

	public SepiaTransformation(GenericTransformationArgs transformationArgs) {
		super(transformationArgs);
	}

	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		return getSepiaColour(colour);
	}
	
	@VisibleForTesting
	static int getSepiaColour(int colour) {
		final float MAX_RED_FRACTION = 112f/255;
		final float MAX_GREEN_FRACTION = 66f/255;
		final float MAX_BLUE_FRACTION = 20f/255;
		int red = (int) (ColourUtils.getRedFromRGB(colour) * MAX_RED_FRACTION);
		int green = (int) (ColourUtils.getGreenFromRGB(colour) * MAX_GREEN_FRACTION);
		int blue = (int) (ColourUtils.getGreenFromRGB(colour) * MAX_BLUE_FRACTION);
		return ColourUtils.getRGBInt(red, green, blue);
	}

}
