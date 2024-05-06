package com.venergyit.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	// Register endpoints in using constructor so Jersey is aware of this endpoint
		public JerseyConfig() {
			register(LoanResource.class);
		}
		
}
