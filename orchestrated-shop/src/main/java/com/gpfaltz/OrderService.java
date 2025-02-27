package com.gpfaltz;

import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderService {

	private Set<Long> orders = new HashSet<>();

	public void newOrder(Long id) {
		orders.add(id);
	}

	public void cancelOrder(Long id) {
		orders.remove(id);
	}
}
