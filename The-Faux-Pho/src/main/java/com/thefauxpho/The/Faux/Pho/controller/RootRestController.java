package com.thefauxpho.The.Faux.Pho.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thefauxpho.The.Faux.Pho.model.CustomerOrder;
import com.thefauxpho.The.Faux.Pho.model.Food;
import com.thefauxpho.The.Faux.Pho.repository.CustomerOrderRepository;
import com.thefauxpho.The.Faux.Pho.repository.FoodRepository;
import com.thefauxpho.The.Faux.Pho.service.EmailService;
import com.thefauxpho.The.Faux.Pho.service.FoodOrderRepositoryService;
import com.thefauxpho.The.Faux.Pho.service.OrderTimeService;

@RestController
public class RootRestController {
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private CustomerOrderRepository orderRepository;
	
	@Autowired
	private FoodOrderRepositoryService foodOrderRepositoryService;
	
	@Autowired
	private EmailService emailService;
	
	//CustomerOrder aOrder;
	OrderTimeService orderTimeService = new OrderTimeService();
	@GetMapping("/hi")
	public String getAll(){
		
		return "hello world";
	}
	
	
	/**
	 * Retrieves list of all foods from the database
	 * @return List of food objects
	 */
	@GetMapping("/api/foods")
	public List<Food> getAllFoods(){
		List<Food> foods = foodRepository.findAll();
		foods.forEach(food->food.setOrders(null));
		return foods;
	}
	
	/**
	 * Checks to see if the store is currently open based on MST time zone;
	 * @return true if the store is currently open
	 */
	@GetMapping("/api/open")
	public boolean isOpen(){
		return orderTimeService.isOpen();
	}
	
	/**
	 * Looks up the order from the database and return the amount of minutes left till pickup
	 * @param orderNumber
	 * @return String containing amount of time left till pickup,
	 */
	@GetMapping("/api/getOrder")
	public String getOrderCompletionTimeByOrderNumber(@RequestParam(name="orderNumber") String orderNumber){
		Date orderTime = new Date();
		List<CustomerOrder> customerOrders = orderRepository.findAll();
		for (CustomerOrder customerOrder : customerOrders) {
			if (customerOrder.getOrderNumber().equals(orderNumber.toUpperCase())){
				orderTime = customerOrder.getOrderTime();
			}
		}
		return orderTimeService.getTimeTillPickUp(orderTime);
	}
	
	/**
	 * Takes customer info and order and submits data to the database
	 * @param customerName: customer name
	 * @param customerEmail: customer email address
	 * @param foods: list of foods the customer ordered
	 * @return 
	 */
	@PostMapping("/api/order")
	public CustomerOrder submitOrder(@RequestParam(name="customerName") String customerName, @RequestParam(name="customerEmail") String customerEmail, @RequestBody List<Food> foods){
		
		//create order
		Date now = new Date();
		CustomerOrder order = new CustomerOrder(customerName, customerEmail, now);
		
		// save order
		foodOrderRepositoryService.saveFoodOrder(order, foods);
		//foodOrderRepositoryService.runnableSaveFoodOrder(order, foods);
			
		//send confirmation email
		emailService.sendConfirmationOrderNumber(order);
		
		//prepare and send response object
		foods.forEach(food -> food.setOrders(null));
		order.setFoods(foods);
		//order.setFoods(null);
		return order;
	}

}
