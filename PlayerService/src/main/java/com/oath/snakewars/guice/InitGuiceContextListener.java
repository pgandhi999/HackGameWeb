package com.oath.snakewars.guice;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

public class InitGuiceContextListener extends GuiceServletContextListener
{
  protected Injector getInjector()
  {
    return Guice.createInjector(ImmutableList.<Module>of(new SnakeWarsModule(), new JettyServletModule()));
  }
}
