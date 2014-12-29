
package net.jr39.image_enhancer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformation;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformationArgs;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformationArgs;

/**
 *
 * @author james
 */
public class ImageEnhancerHelper {

	public static List<Image> getImagesFromPaths(List<String> paths){
		List<Image> images = new ArrayList<>();
		paths.stream().forEach((String path) -> {
			try {
				images.add(new Image(path));
			} catch (IOException ex) {
				Logger.getLogger(ImageEnhancerHelper.class.getName()).log(Level.SEVERE, "Couldn't load image: '{0}'", path);
			}
		});
		return images;
	}
	
	public static AbstractTransformation getTransformationType(String transformationType, String[] args){
		switch(transformationType){
			case "brightnessTransformation":
				return new BrightenTransformation(new BrightenTransformationArgs(Float.parseFloat(args[0])));
			default:
				throw new IllegalArgumentException("Couldn't recognise the transformation type '" + transformationType + "'");
		}
	}
	
}
