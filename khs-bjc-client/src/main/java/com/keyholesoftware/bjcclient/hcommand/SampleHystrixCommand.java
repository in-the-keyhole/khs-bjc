package com.keyholesoftware.bjcclient.hcommand;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.keyholesoftware.bjcclient.rest.ObjectMessageConverter;
import com.khs.hystrix.model.SampleObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SampleHystrixCommand extends HystrixCommand<SampleObject> {

    private SampleObject cachedSample = new SampleObject(999);
    private String serverUrl;

    private static final Logger LOG = Logger.getLogger(SampleHystrixCommand.class);

    public SampleHystrixCommand(String serverUrl) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Sample"))
                // defaulting to a fairly long timeout value
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)
                        .withCircuitBreakerErrorThresholdPercentage(50)));

        this.serverUrl = serverUrl;
    }

    @Override
    protected SampleObject run() throws Exception {
        RestTemplate template = new RestTemplate();

        template.getMessageConverters().add(new ObjectMessageConverter());
        ResponseEntity<SampleObject> response = template.getForEntity(serverUrl + "/sample", SampleObject.class);

        LOG.info(response.getBody());

        return response.getBody();
    }

    @Override
    protected SampleObject getFallback() {
        LOG.warn("In getFallback");
        return cachedSample;
    }
}
