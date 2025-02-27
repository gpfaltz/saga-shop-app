package com.gpfaltz;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("choreographed-shop")
public class ChoreographedShopResource {

	@Inject
	OrderService orderService;

	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response saga() {
		
		Long id = 0L;
		
		orderService.newOrder(++id, 20);
		orderService.newOrder(++id, 30);
		orderService.newOrder(++id, 40);
		orderService.newOrder(++id, 15);
		
		return Response.ok().build();
	}
}
