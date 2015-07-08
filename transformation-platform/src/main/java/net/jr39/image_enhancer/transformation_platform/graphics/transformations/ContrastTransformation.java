package net.jr39.image_enhancer.transformation_platform.graphics.transformations;

import com.google.common.annotations.VisibleForTesting;
import java.awt.Point;
import java.awt.image.BufferedImage;
import net.jr39.image_enhancer.transformation_platform.graphics.filters.colour_filters.ColourUtils;

/**
 *
 * @author james
 */
public class ContrastTransformation extends AbstractTransformation<ContrastTransformationArgs> {

	public ContrastTransformation(ContrastTransformationArgs transformationArgs) {
		super(transformationArgs);
	}

	@Override
	protected int graduallyTransformPixel(Point point, int colour, BufferedImage image, Point transformationCentre) {
		float appliedTransformationFactor = (float) ((transformationArgs.getFactor() - 1) * GradualTransformationHelper.getDecimalDistanceFromCentre(point, transformationCentre) + 1);
		return getTransformedColour(colour, appliedTransformationFactor);
	}
	
	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		return getTransformedColour(colour, transformationArgs.getFactor());
	}
	
	@VisibleForTesting
	static int getTransformedColour(int colour, float scaleFactor){
		int[] colours = {
			ColourUtils.getRedFromRGB(colour),
			ColourUtils.getGreenFromRGB(colour),
			ColourUtils.getBlueFromRGB(colour)
		};
		int[] finalColours = new int[colours.length];
		for(int index = 0; index < colours.length; index++){
			int individualColour = colours[index];
			int midColour = individualColour - 128;
			int contrastedColour = (int) (midColour * scaleFactor);
			int wholeColour = contrastedColour + 128;
			int boundedColour = (wholeColour > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : (wholeColour < ColourUtils.RGB_MIN ? ColourUtils.RGB_MIN : wholeColour));
			finalColours[index] = boundedColour;
		}
		return ColourUtils.getRGBInt(finalColours[0], finalColours[1], finalColours[2]);
	}
}
