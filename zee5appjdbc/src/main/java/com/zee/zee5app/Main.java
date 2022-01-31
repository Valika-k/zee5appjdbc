package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.LoginServiceImpl;
import com.zee.zee5app.service.impl.MovieServiceImpl;
import com.zee.zee5app.service.impl.SeriesServiceImpl;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class Main {

	public static void main(String[] args) throws IOException, InvalidIdLengthException, InvalidNameException  {
		// TODO Auto-generated method stub
		UserService service = UserServiceImpl.getInstance();
//		try {
//			Register register = new Register("ab000011", "abhi", "chivate", "abhi@gmail.com", "12345678");
//			
//			register.setContactNumber(new BigDecimal("9975477764"));
//			
//			UserService service = UserServiceImpl.getInstance();
//			
//			String result = service.addUser(register);
//			System.out.println(result);
//			
//		} catch (InvalidIdLengthException | InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		
//		try {
//			UserService service = UserServiceImpl.getInstance();
//			Optional<Register> register = service.getUserById("ab000001");
//			System.out.println("Registered User retreieved"+register.get());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidIdLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			UserService service = UserServiceImpl.getInstance();
//			String result = service.deleteUserById("ab000011");
//			if(result=="success")
//			{System.out.println("Record deleted successfully");}
//			else
//			{System.out.println("Record deletion failed");}
//		} catch (IdNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		

		UserService userService = UserServiceImpl.getInstance();
		System.out.println("All registrations:");
		try {
			for (Register user : userService.getAllUsers()) {
				System.out.println("\nUser Object: " + user);
			}
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("All registrations list:");
		//userService.getAllUsersList().forEach(e -> System.out.println(e));
		try {
			service.getAllUsersList().get().forEach(e->System.out.println(e));
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		
//	public static void main(String[] args) {
//		try {
//			LoginService service= LoginServiceImpl.getInstance();
//			System.out.println(service.changeRole("abhi@gmail.com", ROLE.ROLE_ADMIN));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	//public static void main(String[] args) throws IOException, InvalidIdLengthException, InvalidNameException  {
	//TODO Auto-generated method stub

//	try {
//		Movie movie = new Movie();
//		movie.setMovieId("m00006");
//		movie.setName("robot");
//		movie.setAgeLimit(13);
//		movie.setCast("abc");
//		movie.setGenre("sciFi");
//		movie.setLength("240");
//		movie.setTrailer(null);
//		movie.setReleasedate("2022-05-01");
//		movie.setLanguage("telugu");
//		
//		
//		
//		MovieService service = MovieServiceImpl.getInstance();
//		
//		String result = service.addMovie(movie);
//		System.out.println(result);
//		
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	
//	try {
//		Series series = new Series();
//		series.setId("show00006");
//		series.setSeriesName("Breaking bad");
//		series.setAgeLimit(18);
//		series.setCast("abc");
//		series.setGenre("Action");
//		series.setLength("240");
//		series.setTrailer(null);
//		series.setReleasedate("2022-05-01");
//		series.setLanguage("English");
//		series.setNoOfEpisodes("78");
//		
//		
//		
//		SeriesService service = SeriesServiceImpl.getInstance();
//		
//		String result = service.addSeries(series);
//		System.out.println(result);
//		
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

}
}