package pl.spring.demo.web.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class EmbeddedJetty {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJetty.class);

    private static final int DEFAULT_PORT = 9721;
    private static final String CONTEXT_PATH = "/workshop/";
    private static final String CONFIG_LOCATION = "classpath*:spring/*.xml";
    private static final String MAPPING_URL = "/rest/*";
    private static final String DEFAULT_PROFILE = "dev";

    public static void main(String[] args) throws Exception {
        new EmbeddedJetty().startJetty(DEFAULT_PORT);
    }

    private void startJetty(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(getHandlers());
        server.start();
        logger.info("Server started at port {}", port);
        server.join();
    }

    private static HandlerList getHandlers() throws IOException {
        HandlerList handlers = new HandlerList();
        ServletContextHandler servletContextHandler = getServletContextHandler(getContext());
        handlers.setHandlers(new Handler[]{servletContextHandler});
        return handlers;
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setWelcomeFiles(new String[] {"index.html"});
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addServlet(new ServletHolder("default", new DefaultServlet()), "/*");
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(new ClassPathResource("static").getURI().toString());
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }

}
