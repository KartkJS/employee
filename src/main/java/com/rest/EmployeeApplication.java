package com.rest;

import javax.servlet.MultipartConfigElement;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@ComponentScan
// @Import(ServiceConfiguration.class)
@EnableAutoConfiguration
@SpringBootApplication
public class EmployeeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    // you can run this with SSL/TLS. For example, build the application (`mvn clean install`) in the `oauth` directory, then run:
    // java -Dspring.profiles.active=production -Dkeystore.file=file:///`pwd`/src/main/resources/keystore.p12 -jar target/oauth-1.0.0.BUILD-SNAPSHOT.jar
    @Bean
    @Profile("production")
    EmbeddedServletContainerCustomizer containerCustomizer(@Value("${keystore.file}") final Resource keystoreFile,
            @Value("${keystore.pass}") final String keystorePass) throws Exception {

        String absoluteKeystoreFile = keystoreFile.getFile()
                .getAbsolutePath();

        return (final ConfigurableEmbeddedServletContainer container) -> {
            TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
            tomcat.addConnectorCustomizers((connector) -> {
                connector.setPort(8443);
                connector.setSecure(true);
                connector.setScheme("https");

                Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
                proto.setSSLEnabled(true);
                proto.setKeystoreFile(absoluteKeystoreFile);
                proto.setKeystorePass(keystorePass);
                proto.setKeystoreType("PKCS12");
                proto.setKeyAlias("tomcat");
            });

        };
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    /*
     * @Configuration static class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
     * @Autowired private CrmService crmService;
     * @Override public void init(final AuthenticationManagerBuilder auth) throws Exception { auth.userDetailsService(userDetailsService()); } protected
     * UserDetailsService userDetailsService() { return (username) -> { User u = this.crmService.findUserByUsername(username); return new
     * org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), u.isEnabled(), u.isEnabled(), u.isEnabled(), u.isEnabled(),
     * AuthorityUtils.createAuthorityList("USER", "write")); }; } }
     */
}

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    private final String applicationName = "employee";

    /**
     * This is required for password grants, which we specify below as one of the {@literal authorizedGrantTypes()}.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android-crm")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("ROLE_USER")
                .scopes("write")
                .resourceIds(this.applicationName)
                .secret("123456");
    }
}
