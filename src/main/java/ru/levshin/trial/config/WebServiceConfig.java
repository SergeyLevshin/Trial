package ru.levshin.trial.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.levshin.trial.webservice.customer.CustomerWebServiceImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint customerEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new CustomerWebServiceImpl());
        endpoint.publish("/Customers");
        return endpoint;
    }

}
