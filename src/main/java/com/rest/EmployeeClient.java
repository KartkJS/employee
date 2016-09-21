/**
 * 
 */
package com.rest;


/**
 * @author k.suba
 *
 */
public class EmployeeClient {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        /*
         * initParams = {
         * @WebInitParam(name = "clientId", value = "3MVG9lKcPoNINVBJSoQsNCD.HHDdbugPsNXwwyFbgb47KWa_PTv"),
         * @WebInitParam(name = "clientSecret", value = "5678471853609579508"),
         * @WebInitParam(name = "redirectUri", value = "https://localhost:8443/RestTest/oauth/_callback"),
         * @WebInitParam(name = "environment", value = "https://yourInstance.salesforce.com/services/oauth2/token") } HttpClient httpclient = new HttpClient();
         * PostMethod post = new PostMethod(environment); post.addParameter("code",code); post.addParameter("grant_type","authorization_code");
         *//** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
        /*
         * post.addParameter("client_id",clientId); post.addParameter("client_secret",clientSecret); post.addParameter("redirect_uri",redirectUri); }
         */
        // OAuth2Request request = new OAuth2Request(requestParameters, clientId, authorities, approved, scope, resourceIds, redirectUri, responseTypes,
        // extensionProperties);
    }
}
