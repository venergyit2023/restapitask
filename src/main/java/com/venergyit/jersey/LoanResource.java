// HashMap
package com.venergyit.jersey;

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
