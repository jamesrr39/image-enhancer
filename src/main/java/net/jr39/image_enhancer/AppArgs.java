package net.jr39.image_enhancer;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Option;

/**
 *
 * @author james
 */
public class AppArgs {
	
	@Option(name="-image", aliases={"-i"}, usage="image filepath")
	private List<String> imagePaths = new ArrayList<>();

	@Option(name="-transformation-type", aliases={"-tt"}, usage="transformation type")
	private String transformationType;
	
	@Option(name="-transformation-args", aliases={"-ta"}, usage="comma seperated list of transformation args")
	private String transformationArgs;

	public String[] getTransformationArgs() {
		return transformationArgs.split(",");
	}

	public String getTransformationType() {
		return transformationType;
	}
	
	public List<String> getImagePaths() {
		return imagePaths;
	}
	
}
