package com.oath.snakewars.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;
import java.util.Map;

public class JettyServletModule extends ServletModule
{
  @Override
  protected void configureServlets()
  {
    PackagesResourceConfig resourceConfig = new PackagesResourceConfig("com.oath.snakewars.resource");
    final Map<String, Object> config = new HashMap<String, Object>();
    config.put("com.sun.jersey.api.json.POJOMappingFeature", true);
    resourceConfig.setPropertiesAndFeatures(config);
    for (Class<?> resourceClass : resourceConfig.getClasses()) {
      bind(resourceClass);
    }

    bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
    bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
    Map<String, String> initParams = new HashMap<String, String>();
    initParams.put("com.sun.jersey.config.feature.Trace",
                   "true");
    initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
    serve("/*").with(GuiceContainer.class,initParams);
  }

}
