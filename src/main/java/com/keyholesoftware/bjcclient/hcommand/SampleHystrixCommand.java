package com.keyholesoftware.bjcclient.hcommand;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.keyholesoftware.bjcclient.rest.ObjectMessageConverter;
import com.khs.hystrix.model.SampleObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SampleHystrixCommand extends HystrixCommand<SampleObject> {

	public SampleHystrixCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Sample"))
				// defaulting to a fairly long timeout value
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)));
	}

	@Override
	protected SampleObject run() throws Exception {
		RestTemplate template = new RestTemplate();

		template.getMessageConverters().add( new ObjectMessageConverter());
		ResponseEntity<SampleObject> object1 = template.getForEntity("http://server:8080/app/sample", SampleObject.class);

		System.out.println(object1.getBody());

		return object1.getBody();
	}
}
