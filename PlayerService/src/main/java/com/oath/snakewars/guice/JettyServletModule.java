package com.oath.snakewars.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class JettyServletModule extends ServletModule
{
  @Override
  protected void configureServlets()
  {
    serve("/*").with(GuiceContainer.class);
    PackagesResourceConfig resourceConfig = new PackagesResourceConfig("com.oath.snakewars.resource");
    for (Class<?> resourceClass : resourceConfig.getClasses()) {
      bind(resourceClass);
    }
  }
}
