package com.vmutyala.microservice.processing.web;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmutyala.microservice.processing.Shipment;
import com.vmutyala.microservice.processing.ShipmentRepository;
import com.vmutyala.microservice.processing.ShipmentService;
import com.vmutyala.microservice.processing.events.OrderKafkaListener;

@Controller
public class ShippingController {

	private ShipmentRepository shipmentRepository;
	private ShipmentService shipmentService;
	private final Logger log = LoggerFactory.getLogger(ShippingController.class);

	@Autowired
	public ShippingController(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView Item(@PathVariable("id") long id) {
		return new ModelAndView("shipment", "shipment", shipmentRepository.findById(id).get());
	}

	@RequestMapping("/")
	public ModelAndView ItemList() {
		return new ModelAndView("shipmentlist", "shipments", shipmentRepository.findAll());
	}
	
	@RequestMapping(value = "/ComputerOrder", method = RequestMethod.POST)
	public ResponseEntity<String> OrderComputer(@RequestBody Shipment shipment) throws JsonParseException, JsonMappingException, IOException {	  //create ObjectMapper instance
       // ObjectMapper objectMapper = new ObjectMapper();

       // InputStream is = TypeReference.class.getResourceAsStream("/json/order2.json");

       // shipment = objectMapper.readValue(is, Shipment.class );
	    
		log.info("Received shipment " + shipment.getId());

			shipmentService.ship(shipment);
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
		    return new ResponseEntity(HttpStatus.CREATED);
		
	}
	
	
}
