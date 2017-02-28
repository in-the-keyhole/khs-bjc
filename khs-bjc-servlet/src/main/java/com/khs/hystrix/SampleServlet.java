package com.khs.hystrix;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bjc.clindesk.server.Dispatchable;

import com.khs.hystrix.hcommand.SampleHystrixServletCommand;

public class SampleServlet extends HttpServlet {

    private static final long serialVersionUID = -8427314587180836765L;

    private static final Logger LOG = Logger.getLogger(SampleServlet.class);

    private long counter = 1;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LOG.debug("get: " + counter);

        Dispatchable output = new SampleHystrixServletCommand(counter++).execute();
        output.dispatch(res);
    }

}
