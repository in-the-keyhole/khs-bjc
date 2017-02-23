package com.keyholesoftware.bjcclient.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.keyholesoftware.bjcclient.hcommand.SampleHystrixCommand;
import com.keyholesoftware.bjcclient.rest.ObjectMessageConverter;
import com.khs.hystrix.model.SampleObject;

@Service
public class SampleService {

	private boolean simulate = false;

	public SampleObject sample() {
		return new SampleHystrixCommand().execute();
	}

	public SampleObject sampleDirect() {
		RestTemplate template = new RestTemplate();

		template.getMessageConverters().add( new ObjectMessageConverter());
		ResponseEntity<SampleObject> object1 = template.getForEntity("http://server:8080/app/sample", SampleObject.class);

		System.out.println(object1.getBody());

		return object1.getBody();
	}

	private void startSimulation() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isSimulate()) {
					sample();
					try {
						Thread.sleep(100);
					}catch (Exception e) {
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
