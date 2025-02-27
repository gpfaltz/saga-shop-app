package com.gpfaltz;

import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

	private Set<Long> orders = new HashSet<>();

	@Inject
	CreditService creditService;

	public void newOrder(Long id, int value) {
		orders.add(id);

		try {
			creditService.newOrderValue(id, value);
			System.out.println("Order " + id + " registred with a value of " + value + ". Available balance: " + creditService.getTotalCredit());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			cancelOrder(id);
			System.err.println("Order " + id + " reversed with a value of " + value + ". Available balance: " + creditService.getTotalCredit());
		}
	}

	public void cancelOrder(Long id) {
		orders.remove(id);
	}
}
