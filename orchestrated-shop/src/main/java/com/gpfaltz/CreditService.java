package com.gpfaltz;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditService {

	private int totalCredit;
	private Map<Long, Integer> order_value = new HashMap<>();

	public CreditService() {
		this.totalCredit = 100;
	}

	public void newOrderValue(Long orderId, int value) {

		if (value > totalCredit) {
			throw new IllegalStateException("Insufficient Balance.");
		}

		totalCredit = totalCredit - value;
		order_value.put(orderId, value);
	}

	public void cancelOrderValue(Long id) {

		totalCredit = totalCredit + order_value.get(id);
		order_value.remove(id);

	}
	
	public int getTotalCredit() {
		return totalCredit;
	}
}
