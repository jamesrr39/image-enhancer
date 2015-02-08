package net.jr39.image_enhancer.graphics.transformations;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.jr39.image_enhancer.graphics.ImageHelper;
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
	protected BufferedImage performTransformation(BufferedImage image, List<Point> pixelsToBeTransformed) {
		int[] imagePixels = ImageHelper.getImageRGB(image);
		final Dimension imageDimensions = new Dimension(image.getWidth(), image.getHeight());
		pixelsToBeTransformed.forEach((point) -> {
			int colour = imagePixels[ImageHelper.getImageIntArrayIndex(point, imageDimensions)];
			colour = contrastPixel(colour);
			image.setRGB((int) point.getX(), (int) point.getY(), colour);
		});
		return image;
	}

	private int contrastPixel(int colour) {
		final int MAX = 255;
		final int MIN = 0;
		int midRed = ColourUtils.getRedFromRGB(colour) - 128;
		int midGreen = ColourUtils.getGreenFromRGB(colour) - 128;
		int midBlue = ColourUtils.getBlueFromRGB(colour) - 128;
		int contrastedRed = (int) (midRed * transformationArgs.getFactor());
		int contrastedGreen = (int) (midGreen * transformationArgs.getFactor());
		int contrastedBlue = (int) (midBlue * transformationArgs.getFactor());
		int wholeRed = contrastedRed + 128;
		int wholeGreen = contrastedGreen + 128;
		int wholeBlue = contrastedBlue + 128;
		int maximisedRed = wholeRed > MAX ? MAX : wholeRed;
		int minimisedRed = maximisedRed < MIN ? MIN : maximisedRed;
		int maximisedGreen = wholeGreen > MAX ? MAX : wholeGreen;
		int minimisedGreen = maximisedGreen < MIN ? MIN : maximisedGreen;
		int maximisedBlue = wholeBlue > MAX ? MAX : wholeBlue;
		int minimisedBlue = maximisedBlue < MIN ? MIN : maximisedBlue;
		return new Color(minimisedRed, minimisedGreen, minimisedBlue).getRGB();
	}
}
