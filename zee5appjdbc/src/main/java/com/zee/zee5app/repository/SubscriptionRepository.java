package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
public interface SubscriptionRepository {
	public String addSubscription(Subscription movie);
	public String updateSubscription(String id, Subscription movie) throws IdNotFoundException;
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Subscription[] getAllSubscriptions() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	public List<Subscription> getAllSubscriptionDetails();
	public String deleteSubscriptionById(String id) throws IdNotFoundException;
	Optional<List<Subscription>> getAllSubscriptionsList()
			throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;

}
