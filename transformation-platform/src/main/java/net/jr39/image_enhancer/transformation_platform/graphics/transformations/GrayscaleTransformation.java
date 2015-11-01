package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import java.awt.Point;
import java.awt.image.BufferedImage;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters.ColourUtils;

/**
 *
 * @author james
 */
public class GrayscaleTransformation extends AbstractTransformation<GrayscaleTransformationArgs> {

	public GrayscaleTransformation(GrayscaleTransformationArgs transformationArgs) {
		super(transformationArgs);
	}

	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		int averageColour = (ColourUtils.getRedFromRGB(colour) + ColourUtils.getGreenFromRGB(colour) + ColourUtils.getBlueFromRGB(colour)) / 3;
		return ColourUtils.getRGBInt(averageColour, averageColour, averageColour);
	}

}
