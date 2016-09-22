package com.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/api/integration/v1/login/")
public class SingleSignOnEndpoint {

    String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";

    @GET
    /**
     * The endpoints SSO will be
     * http://127.0.0.1:8080/api/integration/v1/login?openid={openid}&accountId={accountIdentifier}
     * 
     * @param authorization
     * @param openId
     * @return
     */
    public Response get(@HeaderParam(HttpHeaders.AUTHORIZATION) final String authorization, @QueryParam("openId") final String accessToken) {

        // do OpenID authentication

        return null;
    }
}