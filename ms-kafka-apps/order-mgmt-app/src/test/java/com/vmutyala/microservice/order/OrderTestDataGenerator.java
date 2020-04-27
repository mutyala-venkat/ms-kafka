package com.vmutyala.microservice.order;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.vmutyala.microservice.order.customer.CustomerRepository;
import com.vmutyala.microservice.order.item.ItemRepository;
import com.vmutyala.microservice.order.logic.Address;
import com.vmutyala.microservice.order.logic.Order;
import com.vmutyala.microservice.order.logic.OrderRepository;

@Component
@Profile("test")
@DependsOn({ "itemTestDataGenerator", "customerTestDataGenerator" })
public class OrderTestDataGenerator {

	private final OrderRepository orderRepository;
	private ItemRepository itemRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public OrderTestDataGenerator(OrderRepository orderRepository, ItemRepository itemRepository,
			CustomerRepository customerRepository) {
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
		this.customerRepository = customerRepository;
	}

	@PostConstruct
	public void generateTestData() {
		Order order = createOrder();
		orderRepository.save(order);
	}

	public Order createOrder() {
		Order order = new Order(customerRepository.findAll().iterator().next());
		order.setShippingAddress(new Address("Ohlauer Str. 43", "10999", "Berlin"));
		order.setBillingAddress(new Address("Krischerstr. 100", "40789", "Monheim am Rhein"));
		order.addLine(42, itemRepository.findAll().iterator().next());
		return order;
	}

}