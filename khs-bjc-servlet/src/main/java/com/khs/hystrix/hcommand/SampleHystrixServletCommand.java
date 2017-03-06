package com.khs.hystrix.hcommand;

import java.net.URI;

import org.apache.log4j.Logger;
import org.bjc.clindesk.server.Dispatchable;
import org.bjc.clindesk.server.ObjectDispatchable;

import com.khs.hystrix.model.SampleObject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SampleHystrixServletCommand extends AbstractHystrixCommand  {

    private static final Logger LOG = Logger.getLogger(SampleHystrixServletCommand.class);

    private long counter;

    private static final URI SAMPLE_URI = URI.create("http://localhost:8080/app/sample");

    public SampleHystrixServletCommand(long counter) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GET"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(SAMPLE_URI.getPath()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(60000)
                        .withCircuitBreakerErrorThresholdPercentage(50)));
        this.counter = counter;
    }


    @Override
    protected Dispatchable run() throws Exception {
        if (counter % 10 == 0) {
            causeTimeout();
        } else if (counter % 5 == 0) {
            causeFailure();
        }

        return new ObjectDispatchable(createResponseData());

    }

    private void causeFailure() {
        throw new RuntimeException("Failure");
    }

    private void causeTimeout() {
        try {
            Thread.sleep(60000);
        } catch (Exception e) {
            //
        }
    }

    private SampleObject createResponseData() {
        SampleObject response = new SampleObject();
        response.setOne(1);
        response.setTwo("2");
        response.setThree("three");
        response.setFour(40);
        response.setFive("fifty-five");

        return response;
    }


}
