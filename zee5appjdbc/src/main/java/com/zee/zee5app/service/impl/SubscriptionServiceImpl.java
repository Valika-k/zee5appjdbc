package com.zee.zee5app.service.impl;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService{
	
	private SubscriptionServiceImpl()  throws IOException {
		// TODO Auto-generated constructor stub
	}
	private static SubscriptionService service;
	
	public static SubscriptionService getInstance() throws IOException {
		
		if(service==null) {
			service = new SubscriptionServiceImpl();
		}
		
		return service;
	}
	
	//SubscriptionRepository SubscriptionRepository ;

	SubscriptionRepository SubscriptionRepository  =  SubscriptionRepositoryImpl.getInstance();
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		return SubscriptionRepository.addSubscription(subscription);
	}

	@Override
	public String updateSubscription(String id, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return SubscriptionRepository.updateSubscription( id, subscription);
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return SubscriptionRepository.getSubscriptionById(id);
	}

	@Override
	public Subscription[] getAllSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return SubscriptionRepository.deleteSubscriptionById(id);
	}

	@Override
	public List<Subscription> getAllSubscriptionDetails() {
		// TODO Auto-generated method stub
		return SubscriptionRepository.getAllSubscriptionDetails();
	}
	
}
