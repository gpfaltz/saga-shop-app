package com.gpfaltz;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("orcherstrated-shop")
public class OrcherstratedShopResource {

	@Inject
	CreditService creditService;

	@Inject
	OrderService orderService;

	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response saga() {
		
		//credit = 100
		Long id = 0L;
		
		shop(++id, 20);
		shop(++id, 30);
		shop(++id, 30);
		shop(++id, 25);

		return Response.ok().build();
	}

	private void shop(Long id, int value) {
		
		orderService.newOrder(id);
		try {
			creditService.newOrderValue(id, value);
			System.out.println("Order " + id + " registred with a value of " + value + ". Available balance: " + creditService.getTotalCredit());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			orderService.cancelOrder(id);
			System.err.println("Order " + id + " reversed with a value of " + value + ". Available balance: " + creditService.getTotalCredit());
		}

	}
}
