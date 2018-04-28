package com.oath.snakewars.main;

import com.google.inject.servlet.GuiceFilter;
import com.oath.snakewars.guice.InitGuiceContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main
{
  private static Server server;
  public static void main(String args[]) {
    new Main().startServer();
  }
  private void startServer() {
    server = new Server(8080);
    ServletContextHandler context = createRootContext();
    context.addEventListener(new InitGuiceContextListener());
    context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    // To prevent Guice from handling the request
    context.addServlet(DefaultServlet.class, "/");

    try {
      server.start();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  private ServletContextHandler createRootContext() {
    ServletContextHandler context = new ServletContextHandler();
    context.setContextPath("");
    server.setHandler(context);
    return context;
  }
}
