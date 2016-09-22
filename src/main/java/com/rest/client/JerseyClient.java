package com.rest.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.ning.http.client.oauth.ConsumerKey;
import com.ning.http.client.oauth.OAuthSignatureCalculator;
import com.ning.http.client.oauth.RequestToken;
import com.rest.entity.Employee;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@SuppressWarnings("deprecation")
/**
 * This file is mainly to abstract the external dependencies and used for development testing
 * @author k.suba
 *
 */
public class JerseyClient {

	static String key = "8ff3d152-9362-405a-ba4b-e03ba3cf20d0";
	static String prefix =  "https%3A%2F%2Fwww.appdirect.com%2Fapi%2Fintegration%2Fv1%2Fevents%2F";
	
//    public static void main(final String[] args) {
//
//        try {
//
//            ignoreLocalHostFromSSL();
//
//            /* Create an Employee */
//            testCreateEmployeeResponseEmployeeEntity();
//            testCreateEmployeeResponseXML();
//            testReadEmployeeResponseEmployeeEntity();
//            testReadEmployeesResponseEmployeeEntity();
//
//            //Trial 1
//           testCreateSubscriptionEvent();
//           
//           //Trial 2
//            getEventDetails(prefix+key, 
//            		"OAuth oauth_consumer_key="+"customer-crud-137161"+", oauth_version="+"1.0"+", oauth_signature_method="+"HMAC-SHA1"+", oauth_timestamp="+"1474570091"+", oauth_nonce="+"8440743720406272615"+", oauth_signature="+"1Lx67ZvmFJMcARspYA8nERaDVsc%3D");
//            
//            //Trial 3
//            verifyAsyncHttpClient(prefix+key);
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void verifyAsyncHttpClient(String eventUrl)
    {
    	OAuthSignatureCalculator calc = new OAuthSignatureCalculator(new ConsumerKey("customer-crud-137161", "exDfdls5IZ0OBNoi"), new RequestToken(null, null));
    	Request arequest = null;
		try {
		arequest = new RequestBuilder().setUrl(URLDecoder.decode(eventUrl,"UTF-8")).build();
		AsyncHttpClient client = new AsyncHttpClient();
		   Response response = client.prepareGet((URLDecoder.decode(eventUrl,"UTF-8")))
		     .execute().get();

	    	System.out.println("Response : " + response.getStatusText());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     */
    private static void ignoreLocalHostFromSSL() {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

            @Override
            public boolean verify(final String hostname, final javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Client method to test the create employee service
     * 
     * Response is a Employee entity
     */
    public static void testCreateEmployeeResponseEmployeeEntity() {
        try {

            /* Create an Employee */
            Employee emp = new Employee();
            emp.setName("New User 1 ");
            emp.setPassword("1222");
            emp.setSalary(BigDecimal.valueOf(33322d));

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/ui/create/employee/");

            ClientResponse response = webResource.accept("application/xml")
                    .post(ClientResponse.class, emp);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            Employee createdEmployee = response.getEntity(Employee.class);
            System.out.println("Create Employee : " + createdEmployee.getName());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Client method to test the create employee service
     * 
     * Response is a XML String. Parsing logic can be used to parse through the event
     */
    public static void testCreateEmployeeResponseXML() {
        try {

            /* Create an Employee */
            Employee emp = new Employee();
            emp.setName("New User 2");
            emp.setPassword("1222133");
            emp.setSalary(BigDecimal.valueOf(343433d));

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/ui/create/employee/");

            ClientResponse response = webResource.accept("application/xml")
                    .post(ClientResponse.class, emp);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String createdXML = response.getEntity(String.class);
            Element rootElement = readFromXMLResponse(createdXML);
            System.out.println("Created Employee : " + getString("name", rootElement));

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * Client method to test the read employee
     * 
     * Response is a Employee entity
     */
    public static void testReadEmployeeResponseEmployeeEntity() {
        try {

            /* Create an Employee */
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/ui/employee/1");

            ClientResponse response = webResource.accept("application/xml")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            Employee readEmployee = response.getEntity(Employee.class);
            System.out.println("Read Employee : " + readEmployee.getName());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Client method to test the read employees
     * 
     * Response is a List of Employee entities
     */
    public static void testReadEmployeesResponseEmployeeEntity() {
        try {

            /* Create an Employee */
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/ui/employees/");

            ClientResponse response = webResource.accept("application/xml")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String createdXML = response.getEntity(String.class);
            Element rootElement = readFromXMLResponse(createdXML);
            System.out.println("Read Employees : " + getString("name", rootElement));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get the event details from the eventUrl sent by AppDirect
     * 
     * Not working since I am getting 401 Gone status.
     */
    private static void testCreateSubscriptionEvent() {

        try {

            OAuthConsumer consumer = new DefaultOAuthConsumer("customer-crud-137161", "exDfdls5IZ0OBNoi");
            URL url;
            url = new URL(
                    URLDecoder.decode(prefix+key, "UTF-8"));
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            System.out.println(request.getResponseCode());

        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        }
        catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        }
        catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
    }

    public static Element readFromXMLResponse(final String responsXML) {
        String xml = responsXML; // Populated XML String....

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xml)));
            Element rootElement = document.getDocumentElement();
            return rootElement;
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getString(final String tagName, final Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0)
                    .getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0)
                        .getNodeValue();
            }
        }

        return null;
    }

    /**
     * Method to get the event details from the eventUrl sent by AppDirect
     * 
     * Not working since I am getting 401 Gone status.
     */
    public static Integer getEventDetails(final String eventUrl, String authorization) {

        InputStream inputStream = null;
        String ul;

        Integer result = 0;
        try {
            ul = URLDecoder.decode(eventUrl, "UTF-8");
            OAuthConsumer consumer = new CommonsHttpOAuthConsumer("customer-crud-137161", "exDfdls5IZ0OBNoi");
            consumer.setTokenWithSecret(null, null);

            /* create Apache HttpClient */
            HttpClient httpclient = new DefaultHttpClient();

            /* Httppost Method */
            HttpGet httpget = new HttpGet(ul);
            
            httpget.addHeader("Authorization", authorization);

            // sign the request
            consumer.sign(httpget);

            HttpResponse httpResponse = httpclient.execute(httpget);

            int statusCode = httpResponse.getStatusLine()
                    .getStatusCode();

            System.out.println("response json: status code is:" + statusCode);
            System.out.println("response json:status message?:" + httpResponse.getStatusLine()
                    .toString());

            /* 200 represents HTTP OK */
            if (statusCode == 200) {
                /* receive response as inputStream */
                inputStream = httpResponse.getEntity()
                        .getContent();
                // response = convertInputStreamToString(inputStream);
                //
                // System.out.println("response json:json response?:" + response);
                //
                // System.out.println("response block ticket : status block key:" + response);

                result = 1; // Successful
            }
            else {
                result = 0; // "Failed to fetch data!";
            }
        }
        catch (Exception e) {
            System.out.println("response error" + e.getLocalizedMessage());
        }
        return result; // "Failed to fetch data!";

    }

}
