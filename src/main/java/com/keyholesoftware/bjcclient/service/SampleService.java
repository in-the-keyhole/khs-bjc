package com.keyholesoftware.bjcclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.keyholesoftware.bjcclient.hcommand.SampleHystrixCommand;
import com.keyholesoftware.bjcclient.rest.ObjectMessageConverter;
import com.khs.hystrix.model.SampleObject;

@Service
public class SampleService {

    private boolean simulate = false;

    @Value("${server.url:http://server:8080/app}")
    private String serverUrl;

    private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);

    public SampleService() {
        LOG.info(serverUrl);
    }

    public SampleObject sample() {
        return new SampleHystrixCommand(serverUrl).execute();
    }

    public SampleObject sampleDirect() {
        RestTemplate template = new RestTemplate();

        template.getMessageConverters().add(new ObjectMessageConverter());
        ResponseEntity<SampleObject> response = template.getForEntity(serverUrl + "/sample", SampleObject.class);

        LOG.info(response.getBody().toString());

        return response.getBody();
    }

    private void startSimulation() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isSimulate()) {
                    sample();
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public boolean isSimulate() {
        return simulate;
    }

    public void setSimulate(boolean simulate) {
        this.simulate = simulate;
        if (simulate) {
            startSimulation();
        }
    }

}
