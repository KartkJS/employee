package com.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.rest.endpoint.SubscriptionEndpoint;
import com.rest.service.EmployeeService;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(EmployeeService.class);
        register(SubscriptionEndpoint.class);

    }

}