package net.jr39.image_enhancer.webapp;

import com.google.inject.Guice;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import com.google.inject.servlet.GuiceFilter;

/**
 *
 * @author james
 */
public class ImageServer {
	
	public static void main(String[] args) throws Exception{
		Guice.createInjector(new ImageServerServletModule());
		
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
		context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

		context.addServlet(DefaultServlet.class, "/");
		server.start();
		server.join();
	}
	
}
