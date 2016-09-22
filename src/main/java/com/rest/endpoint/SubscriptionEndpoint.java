package com.rest.endpoint;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.springframework.stereotype.Component;

import com.rest.client.JerseyClient;

@Component
@Path("/api/integration/v1/")
public class SubscriptionEndpoint {

    @GET
    @Produces({ "application/xml" })
    /**
     * The endpoints Subscription Create/Change/Cancel/Status Notification URL will be
     * http://127.0.0.1:8080/api/integration/v1?eventUrl={eventUrl}
     * 
     * @param authorization
     * @param eventUrl
     * @return
     */
    public Response get(@HeaderParam(HttpHeaders.AUTHORIZATION) final String authorization, @QueryParam("eventUrl") final String eventUrl) {

        /* Verifying OAuth signature to ensure call from AppDirect */

        // if not valid
        // return Response.status(Status.UNAUTHORIZED).build();

        /* Fetching the event URL from the query param */
        try {
            /* Making a signed get request with the event URL to know details of the Event */

            /* Trial 1 No Success achieved. Getting 401 Unauthorized :( */
            ResponseBuilder builder = Response.seeOther(new URI(eventUrl));
            Response r = builder.build();
            String output = (String) r.getEntity();
            System.out.println("output : " + output);
            System.out.println("status : " + r.getStatus());

            /* Trial 2 and 3 done in Jersey Client class. No Success achieved. Getting 401 Unauthorized :( */
            JerseyClient.getEventDetails(eventUrl);

        }
        catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // If not found
        // return Response.status(Status.NOT_FOUND).build();
        // Else
        // Element rootElement = readFromXMLResponse(String responseXML);
        // System.out.println("Event Type : " + getString("type", rootElement));

        /* Send a status message as an acknowledgement */
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer("customer-crud-137161", "exDfdls5IZ0OBNoi");
        URL url;
        try {
            consumer.setSigningStrategy(new QueryStringSigningStrategy());
            url = new URL("https://www.appdirect.com/AppDirect/finishorder?success=true&accountIdentifer=Kartik");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            request.connect();
            System.out.println("Reponse Message: " + request.getResponseMessage());
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (OAuthMessageSignerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (OAuthExpectationFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (OAuthCommunicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Response.status(Status.OK)
                .build();
    }
}