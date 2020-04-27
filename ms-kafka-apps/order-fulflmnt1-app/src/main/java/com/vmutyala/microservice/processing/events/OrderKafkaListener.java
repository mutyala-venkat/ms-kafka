package com.vmutyala.microservice.processing.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.vmutyala.microservice.processing.Shipment;
import com.vmutyala.microservice.processing.ShipmentService;

@Component
public class OrderKafkaListener {

	private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

	private ShipmentService shipmentService;

	public OrderKafkaListener(ShipmentService shipmentService) {
		super();
		this.shipmentService = shipmentService;
	}

	@KafkaListener(topics = "order")
	public void order(Shipment shipment, Acknowledgment acknowledgment) {
		if(shipment.getOrderType().equalsIgnoreCase("Computer")){
		log.info("Received shipment " + shipment.getId());
		shipmentService.ship(shipment);
		acknowledgment.acknowledge();
		//Created status.
		shipment.setOrderStatus("Created");
		shipmentService.fireShipmentCreatedEvent(shipment);
		
		shipment.setOrderStatus("Shipped");
        //System.out.println("Shippment start");
		shipmentService.fireShipmentCreatedEvent(shipment);
		//System.out.println("Shippment completed");
		//Delivery status.
		shipment.setOrderStatus("Delivered");
		shipmentService.fireShipmentCreatedEvent(shipment);


		}

	}

}
