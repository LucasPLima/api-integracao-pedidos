package com.hibrido.api.exceptionhandler;

import java.io.IOException;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		return (
		          response.getStatusCode().series() == Series.CLIENT_ERROR 
		          || response.getStatusCode().series() == Series.SERVER_ERROR);
		
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == Series.SERVER_ERROR) {
		    throw new HttpClientErrorException(response.getStatusCode(), 
		    		                           response.getStatusText(),
		    		                           response.getHeaders(),
		    		                           response.getBody().readAllBytes(),
		    		                           java.nio.charset.Charset.defaultCharset()
		    		                           );
		} else if (response.getStatusCode().series() == Series.CLIENT_ERROR) {
			throw new HttpClientErrorException(response.getStatusCode(), 
                    response.getStatusText(),
                    response.getHeaders(),
                    response.getBody().readAllBytes(),
                    java.nio.charset.Charset.defaultCharset()
                    );
        }
		
	}

}
