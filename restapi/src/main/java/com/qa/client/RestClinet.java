package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClinet {

	// 1.Get Method

	public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClinet = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);// http get request
		CloseableHttpResponse closeableHttpResponse=httpClinet.execute(httpget);// hit the get URL
		
		return closeableHttpResponse;


	}

}
