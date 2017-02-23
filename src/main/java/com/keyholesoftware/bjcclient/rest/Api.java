package com.keyholesoftware.bjcclient.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.khs.hystrix.model.SampleObject;

@RestController
public class Api {

	@RequestMapping(method = RequestMethod.GET, value = "/api/sample", produces = MediaType.APPLICATION_JSON_VALUE)
	SampleObject mock() {
		RestTemplate template = new RestTemplate();

		template.getMessageConverters().add( new ObjectMessageConverter());
		ResponseEntity<SampleObject> object1 = template.getForEntity("http://localhost:8080/app/sample", SampleObject.class);

		System.out.println(object1.getBody());

		return object1.getBody();
	}
}
