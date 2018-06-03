package com.oath.snakewars.main;

import com.google.inject.servlet.GuiceFilter;
import com.oath.snakewars.guice.InitGuiceContextListener;
import com.sun.jersey.api.core.ResourceConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Map;
import java.util.Properties;

public class Main
{
  private final static Logger log = Logger.getLogger(Main.class);
  private static Server server;
  private static final String LOG4JPATH = "/home/atulmohan/";
  public static void main(String args[]) {
    new Main().startServer(Integer.parseInt(args[0]));
  }
  private void startServer(int port) {
    server = new Server(port);
    ServletContextHandler context = createRootContext();
    context.addEventListener(new InitGuiceContextListener());
    context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    // To prevent Guice from handling the request
    context.addServlet(DefaultServlet.class, "/");
    updateLog4jConfiguration(LOG4JPATH+"log_"+port+".log");
    try {
      server.start();
      log.info("Server started at port "+port);
    }
    catch (Exception e) {
      log.error("Exception while starting server", e);
    }
  }
  private ServletContextHandler createRootContext() {
    ServletContextHandler context = new ServletContextHandler();
    context.setContextPath("");
    server.setHandler(context);
    return context;
  }
  private void updateLog4jConfiguration(String logFile) {
    Properties props = new Properties();
    try {
      InputStream configStream = getClass().getResourceAsStream("/log4j.properties");
      props.load(configStream);
      configStream.close();
    } catch (IOException e) {
      System.out.println("Error: Cannot laod configuration file ");
    }
    props.setProperty("log4j.appender.file.File", logFile);
    PropertyConfigurator.configure(props);
  }
}
