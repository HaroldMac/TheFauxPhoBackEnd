package com.thefauxpho.The.Faux.Pho.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thefauxpho.The.Faux.Pho.model.CustomerOrder;
import com.thefauxpho.The.Faux.Pho.model.Food;
import com.thefauxpho.The.Faux.Pho.repository.CustomerOrderRepository;
import com.thefauxpho.The.Faux.Pho.repository.FoodRepository;

@Service
public class FoodOrderRepositoryService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private CustomerOrderRepository orderRepository;

	public void saveFoodOrder(CustomerOrder order, List<Food> foods) {

		List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
		orders.add(order);
		
		foods.forEach(food->food.setOrders(orders));
		
		foods.forEach(food -> food = foodRepository.getOne(food.getId()));
		order.setFoods(foods);
		
		foods.forEach(food->foodRepository.save(food));
		orderRepository.save(order);
	}
	
	public void runnableSaveFoodOrder(CustomerOrder order, List<Food> foods) {
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
				orders.add(order);
				
				foods.forEach(food->food.setOrders(orders));
				
				foods.forEach(food -> food = foodRepository.getOne(food.getId()));
				order.setFoods(foods);
				
				foods.forEach(food->foodRepository.save(food));
				orderRepository.save(order);
				
			}
			
		});
		thread.start();
		
	}
	
}
