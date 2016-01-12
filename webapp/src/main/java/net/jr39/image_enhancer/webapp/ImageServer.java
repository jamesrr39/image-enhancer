package net.jr39.image_enhancer.webapp;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author james
 */
public class ImageServer {

	public static void main(String[] args) throws Exception {
		Guice.createInjector(new ImageServerServletModule());

		Server server = new Server(8080);
		ServletContextHandler apiHandler = new ServletContextHandler(server, "/api/", ServletContextHandler.NO_SESSIONS);
		apiHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
		apiHandler.addServlet(DefaultServlet.class, "/");
		
		ResourceHandler webpageHandler = new ResourceHandler();
		webpageHandler.setResourceBase("src/main/resources/webapp");
		webpageHandler.setServer(server);
		
		HandlerList serverHandler =  new HandlerList();
		serverHandler.setHandlers(new Handler[]{webpageHandler, apiHandler});
		
		server.setHandler(serverHandler);
		
		server.start();
		server.join();
	}

}
