package com.vmutyala.microservice.processing.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.vmutyala.microservice.processing.Shipment;

public class ShipmentDeserializer extends JsonDeserializer<Shipment> {

	public ShipmentDeserializer() {
		super(Shipment.class);
	}

}
