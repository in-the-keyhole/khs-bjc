package com.keyholesoftware.bjcclient.rest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.khs.hystrix.model.SampleObject;

public class ObjectMessageConverter implements HttpMessageConverter<Object> {

	@Override
	public boolean canRead(Class<?> arg0, MediaType arg1) {
		if ("application/x-java-serialized-object".equals(arg1) || arg0.equals(SampleObject.class)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canWrite(Class<?> arg0, MediaType arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Collections.singletonList(MediaType.valueOf("application/x-java-serialized-object"));
	}

	@Override
	public Object read(Class<? extends Object> arg0, HttpInputMessage arg1) throws IOException, HttpMessageNotReadableException {
		Object response = null;

		ObjectInputStream objectInputStream = new ObjectInputStream(arg1.getBody());
		try {
			response = objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			objectInputStream.close();
		}

		return response;
	}

	@Override
	public void write(Object arg0, MediaType arg1, HttpOutputMessage arg2)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub

	}

}
