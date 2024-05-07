1. The assignment

The task consists of writing an application with a rest service.





2. Tasks to develop RESTful web service

Tasks:


2.1.
Define the relative URI of the root resource class and its methods using the @Path annotation.
The LoanResource class is a ROOT resource with a relative URI path defined as "/loans".


2.2.
Map incoming HTTP requests to your Java methods using @GET, @POST, @PUT, or @DELETE, to get, create, update, or delete representations of the resource, respectively.

In LoanResource class there are:
The getLoans() method supports the HTTP GET method. 
The createLoan() method supports the HTTP POST method.
The getLoan() method supports the HTTP GET method.
The deleteLoan() method supports the HTTP DELETE method.
The updateLoan() method supports the HTTP PUT method.


2.3. 
Customize the request and response messages, as required, to specify the MIME media types of representations a resource can produce and consume.

In LoanResource class there are:
The getLoans() method produces content of the MIME media type "application/json".
The createLoan() method produces content of the MIME media type "application/json".
The getLoan() method produces content of the MIME media type "application/json".
The deleteLoan() method produces content of the MIME media type "application/json".
The updateLoan() method produces content of the MIME media type "application/json".

The createLoan() method consumes content of the MIME media type "application/json".
The updateLoan() method consumes content of the MIME media type "application/json".


2.4.
Extract information from the request.


2.5. 
Build custom response messages to customize response codes or include additional metadata.


2.6.
Access information about the application deployment context or the context of individual requests.


2.7.
Build new or extend existing resource URIs.


2.8.
Evaluate one or more preconditions before processing a GET request, potentially reducing bandwidth and improving server performance.


2.9.
Access the WADL.


2.10.
Secure your RESTful Web services.




3. Application files with functional description


3.1. RestapitaskApplication.class

package com.venergy.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapitaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapitaskApplication.class, args);
	}

}

File Functional Description

3.1.1. Created with in STS 4.0 (Spring Tool Suite).
3.1.2. @SpringBootApplication is a convenience annotation.
3.1.3. The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.


3.2. pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="https://maven.apache.org/POM/4.0.0" xmlns:xsi="https://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.venergy.restfulapi</groupId>
	<artifactId>restapitask</artifactId>
	<version>1.</version>
	<name>restapitask</name>
	<description>RESTful API backend</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jersey</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>


 
File Functional Description 

1. Created with in STS 4.0 (Spring Tool Suite).
2. Dependency "Spring Boot Starter Jersey" for a project:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jersey</artifactId>
		</dependency>


"Spring Boot Starter Jersey" is starter for building RESTful web applications using JAX-RS and Jersey.


Jersey (Eclipse Jersey)
https://eclipse-ee4j.github.io/jersey/ 
Developing RESTful Web services that seamlessly support exposing your data in a variety of representation media types and abstract away the low-level details of the client-server communication is not an easy task without a good toolkit. In order to simplify development of RESTful Web services and their clients in Java, a standard and portable JAX-RS API (https://jakarta.ee/specifications/restful-ws/ ) has been designed.
...
Jersey framework is more than the JAX-RS Reference Implementation. Jersey provides it’s own API (Jersey 2.41 API Documentation  https://eclipse-ee4j.github.io/jersey.github.io/apidocs/latest/jersey/index.html  ) that extend the JAX-RS toolkit with additional features and utilities to further simplify RESTful service and client development.
Goals of Jersey project can be summarized in the following points:
...
The latest stable release of Jersey is 2.41 
(
https://eclipse-ee4j.github.io/jersey/download.html  
Jakarta RESTful WebServices 3.1.0 / Jersey 3.1.5
Jersey 3.1.5, that implements Jakarta RESTful WebServices 3.1  (https://jakarta.ee/specifications/restful-ws/3.1/ => jakarta.jaxrs:jakarta.jaxrs-api:jar:3.1.0 => jakarta.ws.rs-api ( https://mvnrepository.com/artifact/jakarta.ws.rs/jakarta.ws.rs-api )) API is the future release of Jersey. Note that Jersey 2.x releases will continue along with Jersey 3.1.x releases.

)



3.3. JerseyConfig.class

package com.venergy.restfulapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	
	// Register endpoints in using constructor so Jersey is aware of this endpoint
	public JerseyConfig() {
		register(LoanResource.class);
	}

}


File Functional Description

JAX-RS and Jersey
https://docs.spring.io/spring-boot/docs/1.2.2.RELEASE/reference/html/boot-features-developing-web-applications.html
=>
https://docs.spring.io/spring-boot/docs/1.2.2.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-jersey
"
If you prefer the JAX-RS programming model for REST endpoints you can use one of the available implementations instead of Spring MVC. Jersey 1.x and Apache Celtix work quite well out of the box if you just register their Servlet or Filter as a @Bean in your application context. Jersey 2.x has some native Spring support so we also provide auto-configuration support for it in Spring Boot together with a starter.

To get started with Jersey 2.x just include the spring-boot-starter-jersey as a dependency and then you need one @Bean of type ResourceConfig in which you register all the endpoints:

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Endpoint.class);
    }

}

All the registered endpoints should be @Components with HTTP resource annotations (@GET etc.), e.g.



@Component
@Path("/hello")
public class Endpoint {

    @GET
    public String message() {
        return "Hello";
    }

}

Since the Endpoint is a Spring @Component its lifecycle is managed by Spring and you can @Autowired dependencies and inject external configuration with @Value. The Jersey servlet will be registered and mapped to /* by default. You can change the mapping by adding @ApplicationPath to your ResourceConfig.

By default Jersey will be set up as a Servlet in a @Bean of type ServletRegistrationBean named jerseyServletRegistration. You can disable or override that bean by creating one of your own with the same name. You can also use a Filter instead of a Servlet by setting spring.jersey.type=filter (in which case the @Bean to replace or override is jerseyFilterRegistration). The servlet has an @Order which you can set with spring.jersey.filter.order. Both the Servlet and the Filter registrations can be given init parameters using spring.jersey.init.* to specify a map of properties.

There is a Jersey sample (https://github.com/spring-projects/spring-boot/tree/v1.2.2.RELEASE/spring-boot-samples/spring-boot-sample-jersey  ) so you can see how to set things up. There is also a Jersey 1.x sample. Note that in the Jersey 1.x sample that the spring-boot maven plugin has been configured to unpack some Jersey jars so they can be scanned by the JAX-RS implementation (because the sample asks for them to be scanned in its Filter registration). You may need to do the same if any of your JAX-RS resources are packages as nested jars.
"


3.4. Loan.class

package com.venergy.restfulapi;

public class Loan {
	
	private int loanNumber;
	private String customer;
	private String description;
	private double amount;
	
	public int getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}


3.5. Loans.class

package com.venergy.restfulapi;

import java.util.ArrayList;

public class Loans {

		private ArrayList<Loan> loans;

		public ArrayList<Loan> getLoans() {
			return loans;
		}

		public void setLoans(ArrayList<Loan> loans) {
			this.loans = loans;
		}
}



3.6. LoanResources.class

// HashMap
package com.venergy.restfulapi;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/loans")
public class LoanResource {
	
	private static Map<Integer, Loan> HM = new HashMap<>();
	
	// RESTful endpoint method which return loans
	@GET
	@Produces("application/json")
	public Loans getLoans() {
		Loans loans = new Loans();
		loans.setLoans(new ArrayList<>(HM.values()));
		return loans;
	}
	
	// RESTful endpoint method which create new loan
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createLoan(Loan loan) {
		if(loan.getCustomer() == null || loan.getAmount() == 0) {
			return Response.status(400).entity("Missing data !").build();
		}
		loan.setLoanNumber(HM.values().size()+1);
		HM.put(loan.getLoanNumber(), loan);
		return Response.status(201).entity(loan).build();
	}
	
	// odavde
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getLoan(@PathParam("id") int loanNumber) {
		Loan loan = HM.get(loanNumber);
		if(loan==null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(loan).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response deleteLoan(@PathParam("id") int loanNumber) {
		Loan loan = HM.get(loanNumber);
		if(loan != null) {
			HM.remove(loan.getLoanNumber());
			return Response.status(200).build();
		}
		return Response.status(404).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateLoan(Loan loan) {
		if(loan.getCustomer() == null || loan.getAmount() == 0) {
			return Response.status(400)
					.entity("The loan does not exist.").build();
		}
		HM.put(loan.getLoanNumber(), loan);
		return Response.status(201).entity(loan).build();
	}
	
	
	
	static {
		
		Loan loan1 = new Loan();
		loan1.setLoanNumber(1);
		loan1.setCustomer("Customer 1");
		loan1.setDescription("Description 1");
		loan1.setAmount(1000);
		
		Loan loan2 = new Loan();
		loan2.setLoanNumber(2);
		loan2.setCustomer("Customer 2");
		loan2.setDescription("Description 2");
		loan2.setAmount(2000);
		
		Loan loan3 = new Loan();
		loan3.setLoanNumber(3);
		loan3.setCustomer("Customer 3");
		loan3.setDescription("Description 3");
		loan3.setAmount(3000);
		
		Loan loan4 = new Loan();
		loan4.setLoanNumber(4);
		loan4.setCustomer("Customer 4");
		loan4.setDescription("Description 4");
		loan4.setAmount(4000);
		
		Loan loan5 = new Loan();
		loan5.setLoanNumber(5);
		loan5.setCustomer("Customer 5");
		loan5.setDescription("Description 5");
		loan5.setAmount(5000);
		
		HM.put(loan1.getLoanNumber(), loan1);
		HM.put(loan2.getLoanNumber(), loan2);
		HM.put(loan3.getLoanNumber(), loan3);
		HM.put(loan4.getLoanNumber(), loan4);
		HM.put(loan5.getLoanNumber(), loan5);
	}
	

}


File Functional Description

The LoanResource class is a ROOT resource with a relative URI path defined as "/loans".

The getLoans() method RETURNS all loans.

The getLoans() method supports the HTTP GET method. 
The createLoan() method supports the HTTP POST method.
The getLoan() method supports the HTTP GET method.
The deleteLoan() method supports the HTTP DELETE method.
The updateLoan() method supports the HTTP PUT method.

The getLoans() method produces content of the MIME media type "application/json".
The createLoan() method produces content of the MIME media type "application/json".
The getLoan() method produces content of the MIME media type "application/json".
The deleteLoan() method produces content of the MIME media type "application/json".
The updateLoan() method produces content of the MIME media type "application/json".

The createLoan() method consumes content of the MIME media type "application/json".
The updateLoan() method consumes content of the MIME media type "application/json".




4. Source of knowledge

Source - Oracle: 
Fusion Middleware Developing RESTful Web Services for Oracle WebLogic Server
https://docs.oracle.com/cd/E24329_01/web.1211/e24983/develop.htm#RESTF113  


4.1. Learning resources - Oracle

https://docs.oracle.com/cd/E24329_01/web.1211/e24983/overview.htm#RESTF207

Jersey 1.9 User Guide - resource:
http://jersey.java.net/nonav/documentation/1.9/user-guide.html 

RESTful Web Services (JAX-RS) sample - resource:
https://docs.oracle.com/cd/E24329_01/web.1211/e24446/examples.htm#INTRO299 

The Java EE 6 Tutorial—Building RESTful Web Services With JAX-RS - resource:
http://download.oracle.com/javaee/6/tutorial/doc/giepu.html 


4.2. Other resources

Jersey site - resource:
http://jersey.java.net 

Community Wiki for Project Jersey - resource:
http://wikis.sun.com/display/Jersey/Main 

Jersey 1.9 API Javadoc - resource:
http://jersey.java.net/nonav/apidocs/1.9/jersey/overview-summary.html 

JSR-311 JAX-RS Specification - resource:
http://jcp.org/en/jsr/summary?id=311 

JSR-311 JAX-RS Project - resource:
http://jsr311.java.net/ 

JSR-311 JAX-RS API Javadoc - resource:
http://jsr311.java.net/nonav/javadoc/index.html 

"Representational State Transfer (REST)" in Architectural Styles and the Design of Network-based Software Architectures (Dissertation by Roy Fielding) - resource:
http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm 

