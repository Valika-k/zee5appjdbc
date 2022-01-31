package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;
public class SubscriptionRepositoryImpl implements SubscriptionRepository{
	private ArrayList<Subscription> set = new ArrayList<>();

	private static SubscriptionRepository repository;
	
	DBUtils dbUtils = DBUtils.getInstance();
	
	
	private SubscriptionRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
		
		dbUtils = DBUtils.getInstance();
	}
	
	public static SubscriptionRepository getInstance() throws IOException {
		if(repository==null) {
			repository = new SubscriptionRepositoryImpl();
		}
		return repository;
	}

	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;

		String insertStatement = "insert into subscription"
				+ " (subscriptionId,dop,expiry,amount,paymentmode,status,type,autorenewal,regId)"
				+ " values(?,?,?,?,?,?,?,?,?)";

		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,subscription.getSubscriptionId());
			preparedStatement.setString(2, subscription.getDop());
			preparedStatement.setString(3, subscription.getExpiry());
			preparedStatement.setString(4, subscription.getAmount());
			preparedStatement.setString(5, subscription.getPaymentmode());
			preparedStatement.setString(6, subscription.getStatus());
			preparedStatement.setString(7, subscription.getType());
			preparedStatement.setString(8, subscription.getAutorenewal());
			preparedStatement.setString(9, subscription.getRegId());
			int result =preparedStatement.executeUpdate();

			
			if (result > 0) {
					connection.commit();
					return "success";
				} else {
					connection.rollback();
					return "fail";
				}
			
			

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}
		
		
	

	@Override
	public String updateSubscription(String id, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "select * from subscription where regId=?";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			
			resultSet =  preparedStatement.executeQuery();

			if (resultSet.next()) {

				Subscription subscription = new Subscription();
				subscription.setSubscriptionId(resultSet.getString("subscriptionId"));
				subscription.setDop(resultSet.getString("dop"));
				subscription.setExpiry(resultSet.getString("expiry"));
				subscription.setAmount(resultSet.getString("amount"));
				subscription.setPaymentmode(resultSet.getString("paymentmode"));
				subscription.setStatus(resultSet.getString("status"));
				subscription.setType(resultSet.getString("type"));
				subscription.setAutorenewal(resultSet.getString("autorenewal"));
				subscription.setRegId(resultSet.getString("regId"));
				return Optional.of(subscription);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Subscription>> getAllSubscriptionsList() throws InvalidNameException, IdNotFoundException,
			InvalidIdLengthException

	{
		List<Subscription> subscriptions = new ArrayList<>();
		Connection connection = dbUtils.getConnection();

		String getQuery = "SELECT * FROM subscription";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Subscription subscription = new Subscription();
				subscription.setSubscriptionId(result.getString("regId"));
				subscription.setDop(result.getString("firstname"));
				subscription.setExpiry(result.getString("lastname"));
				subscription.setAmount(result.getString("email"));
				subscription.setPaymentmode(result.getString("password"));
				subscription.setStatus(result.getString("contactnumber"));
				subscription.setType(result.getString("contactnumber"));
				subscription.setAutorenewal(result.getString("contactnumber"));
				subscription.setRegId(result.getString("contactnumber"));
				subscriptions.add(subscription);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}

		return Optional.ofNullable(subscriptions);
	}
	
	@Override
	public Subscription[] getAllSubscriptions() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{

		Optional<List<Subscription>> subscriptions = this.getAllSubscriptionsList();

		if (subscriptions.isPresent()) {
	
			return subscriptions.get().toArray(new Subscription[subscriptions.get().size()]);
		}
		
		return null;
	}



	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		Connection connection = dbUtils.getConnection();
		String delQuery = "DELETE FROM subscription where regId=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
				throw new IdNotFoundException("Invalid Id");
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}



	@Override
	public List<Subscription> getAllSubscriptionDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
