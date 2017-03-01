package com.khs.turbine;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.netflix.turbine.init.TurbineInit;

public class TurbineContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        TurbineInit.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        TurbineInit.stop();
    }

}
