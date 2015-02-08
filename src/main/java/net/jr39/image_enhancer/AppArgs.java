package net.jr39.image_enhancer;

import com.google.common.collect.ImmutableList;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformation;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformationArgs;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformationArgs;
import net.jr39.image_enhancer.graphics.transformations.TransformationType;
import net.jr39.image_enhancer.shapes.TransformationShape;
import net.jr39.image_enhancer.shapes.args.IShapeArgs;
import net.jr39.image_enhancer.shapes.args.RectangleArgs;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

/**
 *
 * @author james
 */
public class AppArgs {

	private static final Logger LOGGER = Logger.getLogger(AppArgs.class);

	@Option(name = "-transformation-shape-args", aliases = {"-tsa"}, usage = "transformation shape args", metaVar = "-tsa 0,10 200,300", handler = StringArrayOptionHandler.class, depends = "-transformation-shape")
	private List<String> transformationShapeArgs;

	@Option(name = "-transformation-shape", aliases = {"-ts"}, usage = "transformation shape")
	private String transformationShape;

	@Option(name = "-transformation-args", aliases = {"-ta"}, usage = "comma seperated list of transformation args", metaVar = "-ta 0 200", handler = StringArrayOptionHandler.class)
	private String[] transformationArgs;

	@Option(name = "-image", aliases = {"-i"}, usage = "absolute image filepaths", handler = StringArrayOptionHandler.class, required = true)
	private List<String> imagePaths;

	@Option(name = "-transformation-type", aliases = {"-tt"}, usage = "transformation type", required = true)
	public void setTransformationType(String transformationType) {
		if (transformationType.equals("brighten")) {
			this.transformationType = TransformationType.BRIGHTEN;
		} else {
			throw new IllegalArgumentException("The transformation type '" + transformationType + "' is not supported");
		}
	}

	private TransformationType transformationType;

	public TransformationShape getTransformationShape() {
		if (transformationShape == null) {
			return null;
		} else if (transformationShape.equals("rectangle")) {
			return TransformationShape.RECTANGLE;
		} else {
			throw new IllegalArgumentException("The shape '" + transformationShape + "' is not supported");
		}
	}

	public IShapeArgs getTransformationShapeArgs() {

		if (this.getTransformationShape() == null) {
			return null;
		}

		switch (this.getTransformationShape()) {
			case RECTANGLE:
				String[] upperLeftPointArg = transformationShapeArgs.get(0).split(",");
				String[] dimensionsArg = transformationShapeArgs.get(1).split(",");
				Point upperLeftPoint = new Point(Integer.parseInt(upperLeftPointArg[0]), Integer.parseInt(upperLeftPointArg[1]));
				Dimension dimensions = new Dimension(Integer.parseInt(dimensionsArg[0]), Integer.parseInt(dimensionsArg[1]));
				return new RectangleArgs(new Rectangle(upperLeftPoint, dimensions));
			default:
				throw new IllegalArgumentException("The shape '" + transformationShape + "' is not supported");
		}
	}

	public AbstractTransformationArgs getTransformationArgs() {
		switch (transformationType) {
			case BRIGHTEN:
				return new BrightenTransformationArgs(this.getTransformationShapeArgs(), Float.parseFloat(transformationArgs[0]));
			default:
				throw new IllegalArgumentException("The transformation type '" + transformationType + "' is not supported");
		}
	}

	public AbstractTransformation getTransformationType() {
		switch (transformationType) {
			case BRIGHTEN:
				return new BrightenTransformation((BrightenTransformationArgs) getTransformationArgs());
			default:
				throw new IllegalArgumentException("The transformation type '" + transformationType + "' is not supported");
		}
	}

	public List<Image> getImages() {
		List<Image> images = ImageEnhancerHelper.getImagesFromPaths(imagePaths);
		return ImmutableList.copyOf(images);
	}

}
