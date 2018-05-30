package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClinet;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String url;
	RestClinet restclinet;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod

	public void setUp() throws ClientProtocolException, IOException {

		TestBase testBase = new TestBase();

		serviceurl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceUrl");

		// https://reqres.in/api/users

		url = serviceurl + apiUrl;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {

		restclinet = new RestClinet();
		closeableHttpResponse=restclinet.get(url);
		
		//a. Status Code
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("statusCode------->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		
		//b. json String
		String resposeString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson=new JSONObject(resposeString);
		System.out.println("Response JSON from API----->"+ responseJson);
		
		
		//single assertion
		
		
		String perPageValue= TestUtil.getValueByJpath("/per_page", responseJson);
		System.out.println("value of per page is----->"+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		String totalValue= TestUtil.getValueByJpath("/total", responseJson);
		System.out.println("value of Total is----->"+totalValue);
		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		
		// get array value
		
		
		String lastname=TestUtil.getValueByJpath("/data[0]/last_name", responseJson);
		String id=TestUtil.getValueByJpath("/data[0]/id", responseJson);
		String avatar=TestUtil.getValueByJpath("/data[0]/avatar", responseJson);
		String firstName=TestUtil.getValueByJpath("/data[0]/first_name", responseJson);
		
		System.out.println(lastname);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//c. All header
		
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeader=new HashMap<String, String>();
		
		for(Header header:headerArray){
			allHeader.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array----->"+allHeader );

	}
}