package net.jr39.image_enhancer.graphics.transformations;

import java.awt.Point;
import java.awt.image.BufferedImage;
import net.jr39.image_enhancer.graphics.filters.colour_filters.ColourUtils;

/**
 *
 * @author james
 */
public class ContrastTransformation extends AbstractTransformation<ContrastTransformationArgs> {

	public ContrastTransformation(ContrastTransformationArgs transformationArgs) {
		super(transformationArgs);
	}

	@Override
	protected int transformPixel(Point point, int colour, BufferedImage image) {
		int[] colours = {
			ColourUtils.getRedFromRGB(colour),
			ColourUtils.getGreenFromRGB(colour),
			ColourUtils.getBlueFromRGB(colour)
		};
		int[] finalColours = new int[colours.length];
		for(int index = 0; index < colours.length; index++){
			int individualColour = colours[index];
			int midColour = individualColour - 128;
			int contrastedColour = (int) (midColour * transformationArgs.getFactor());
			int wholeColour = contrastedColour + 128;
			int boundedColour = (wholeColour > ColourUtils.RGB_MAX ? ColourUtils.RGB_MAX : (wholeColour < ColourUtils.RGB_MIN ? ColourUtils.RGB_MIN : wholeColour));
			finalColours[index] = boundedColour;
		}
		return ColourUtils.getRGBInt(finalColours[0], finalColours[1], finalColours[2]);
	}
}
