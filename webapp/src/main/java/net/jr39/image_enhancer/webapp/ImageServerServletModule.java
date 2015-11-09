package net.jr39.image_enhancer.webapp;

import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.util.HashMap;

/**
 *
 * @author james
 */
public class ImageServerServletModule extends ServletModule{

	@Override
	protected void configureServlets() {
		bind(ImageTransformationService.class).to(ImageTransformationServiceImpl.class);
		
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		serve("/*").with(GuiceContainer.class, options);
	}
	
	@Provides
	CORSFilter provideCORSFilter(){
		return new CORSFilter();
	}
	
}
