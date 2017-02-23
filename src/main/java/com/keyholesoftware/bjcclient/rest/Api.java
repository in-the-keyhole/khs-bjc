package com.keyholesoftware.bjcclient.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyholesoftware.bjcclient.service.SampleService;
import com.khs.hystrix.model.SampleObject;

@Controller
@RequestMapping("sample")
public class Api {

	@Autowired
	SampleService sampleService;

	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody SampleObject sample() {
		return sampleService.sample();
	}
}
