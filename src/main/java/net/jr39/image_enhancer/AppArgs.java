package net.jr39.image_enhancer;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Option;

/**
 *
 * @author james
 */
public class AppArgs {
	
	@Option(name="-image", usage="image filepath")
	private List<String> imagePaths = new ArrayList<>();

	public List<String> getImagePaths() {
		return imagePaths;
	}
}
